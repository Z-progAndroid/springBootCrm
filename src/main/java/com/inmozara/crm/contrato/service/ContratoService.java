package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.EstadoContratoRepository;
import com.inmozara.crm.contrato.model.repository.TipoContratoRepository;
import com.inmozara.crm.contrato.model.search.ContratoSearch;
import com.inmozara.crm.contrato.service.interfaces.IContrato;
import com.inmozara.crm.excepcion.ContratoException;
import com.inmozara.crm.excepcion.PdfGeneracionException;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.repository.EstadoInmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import com.inmozara.crm.utils.pdf.ContratoArrendamientoConOpcionACompraPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoService implements IContrato {
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private InmuebleRepository inmuebleRepository;
    @Autowired
    private EstadoInmuebleRepository estadoInmuebleRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoContratoRepository tipoContratoRepository;
    @Autowired
    private EstadoContratoRepository estadoContratoRepository;


    @Override
    public ContratoDTO save(ContratoDTO contratoDTO) {
        if (this.checkContratosActivos(contratoDTO.getIdInmueble()) && !esContratoActivo(contratoDTO.getIdContrato())) {
            throw new ContratoException("El inmueble tiene un contrato activo, reviselo antes de asignar uno nuevo");
        }
        Contrato contrato = ObjectMapperUtils.map(contratoDTO, Contrato.class);
        contrato = contratoRepository.save(contrato);

        actulizarEstadoInumeble(Optional.ofNullable(contrato).map(Contrato::getInmueble).map(Inmueble::getIdInmueble)
                , Optional.ofNullable(contrato).map(Contrato::getTipoContrato).map(TipoContrato::getIdTipoContrato));
        return ObjectMapperUtils.map(contrato, ContratoDTO.class);
    }

    private void actulizarEstadoInumeble(Optional<Integer> idInmueble, Optional<Long> idTipoContrato) {
        List<EstadoInmueble> estadoInmuebles = estadoInmuebleRepository.findAll();
        Optional<EstadoInmueble> estadoAquilado = estadoInmuebles
                .stream()
                .filter(estado -> estado.getEstado().contains("ALQUILADO"))
                .findFirst();
        Optional<EstadoInmueble> estadoVendido = estadoInmuebles
                .stream()
                .filter(estado -> estado.getEstado().contains("VENDIDO"))
                .findFirst();
        Optional<TipoContrato> tipoContrato = tipoContratoRepository.findById(idTipoContrato.get());
        if (tipoContrato.isPresent() && idInmueble.isPresent() && tipoContrato.get().getTipo().contains("ARRENDAMIENTO") && estadoAquilado.isPresent()) {
            inmuebleRepository.actualizarInmueblesEstado(idInmueble.get(), estadoAquilado.get());
        }
        if (tipoContrato.isPresent() && idInmueble.isPresent() && !tipoContrato.get().getTipo().contains("ARRENDAMIENTO") && estadoVendido.isPresent()) {
            inmuebleRepository.actualizarInmueblesEstado(idInmueble.get(), estadoVendido.get());
        }
    }

    @Override
    public ContratoDTO update(ContratoDTO contratoDTO) {
        Contrato contrato = ObjectMapperUtils.map(contratoDTO, Contrato.class);
        contrato = contratoRepository.save(contrato);
        return ObjectMapperUtils.map(contrato, ContratoDTO.class);
    }

    @Override
    public MensajeDTO delete(Long idContrato) {
        if (!contratoRepository.existsById(idContrato))
            throw new RecursoNoEncontrado("Contrato no encontrado");
        contratoRepository.deleteById(idContrato);
        return MensajeDTO.builder()
                .mensaje("Contrato eliminado correctamente con el id: " + idContrato)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public ContratoDTO find(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontrado("Contrato no encontrado"));
        return ObjectMapperUtils.map(contrato, ContratoDTO.class);
    }

    @Override
    public List<ContratoDTO> findAll() {
        List<Contrato> contratos = contratoRepository.findAll();
        if (contratos.isEmpty()) {
            throw new RecursoNoEncontrado("No hay contratos");
        }
        return ObjectMapperUtils.mapAll(contratos, ContratoDTO.class);
    }

    @Override
    public List<ContratoDTO> findAllByParams(ContratoDTO contratoDTO) {
        List<Contrato> contratos = contratoRepository.findAll(ContratoSearch.builder()
                .contrato(ObjectMapperUtils.map(contratoDTO, Contrato.class))
                .build());
        if (contratos.isEmpty()) {
            throw new RecursoNoEncontrado("No hay contratos con los parametros enviados");
        }
        return ObjectMapperUtils.mapAll(contratos, ContratoDTO.class);
    }

    private boolean checkContratosActivos(int idInmueble) {
        int contratos = contratoRepository.checkContratosExistentes(idInmueble);
        return contratos > 0;
    }

    private boolean esContratoActivo(Long idContrato) {
        int contratos = contratoRepository.esContratoActivo(idContrato);
        return contratos > 0;
    }

    public ByteArrayOutputStream generarContratoPdf(Long idContrato) {
        Contrato contrato = contratoRepository.findById(idContrato)
                .orElseThrow(() -> new RecursoNoEncontrado("Contrato no encontrado"));
        if (contrato.getTipoContrato().getTipo().equalsIgnoreCase("CONTRATO DE ARRENDAMIENTO")) {
            return ContratoArrendamientoConOpcionACompraPdf
                    .builder()
                    .arrendador(Optional.ofNullable(contrato.getInmueble().getUsuario()))
                    .arrendatario(Optional.ofNullable(contrato.getCliente()))
                    .inmueble(Optional.ofNullable(contrato.getInmueble()))
                    .contrato(Optional.of(contrato))
                    .build()
                    .generarPdf();
        }
        if (contrato.getTipoContrato().getTipo().equalsIgnoreCase("CONTRATO DE COMPRAVENTA")) {
            return ContratoArrendamientoConOpcionACompraPdf
                    .builder()
                    .arrendador(Optional.ofNullable(contrato.getInmueble().getUsuario()))
                    .arrendatario(Optional.ofNullable(contrato.getCliente()))
                    .inmueble(Optional.ofNullable(contrato.getInmueble()))
                    .contrato(Optional.of(contrato))
                    .build()
                    .generarPdf();
        }
        if (contrato.getTipoContrato().getTipo().equalsIgnoreCase("CONTRATO DE ARRENDAMIENTO CON OPCIÓN DE COMPRA")) {
            return ContratoArrendamientoConOpcionACompraPdf
                    .builder()
                    .arrendador(Optional.ofNullable(contrato.getInmueble().getUsuario()))
                    .arrendatario(Optional.ofNullable(contrato.getCliente()))
                    .inmueble(Optional.ofNullable(contrato.getInmueble()))
                    .contrato(Optional.of(contrato))
                    .build()
                    .generarPdf();
        }
        throw new PdfGeneracionException("No se ha encontrado el pdf por el tipo de contrato " + contrato.getTipoContrato().getTipo());
    }
}
