package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.TipoContratoRepository;
import com.inmozara.crm.contrato.service.interfaces.ITipoContrato;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoContratoService implements ITipoContrato {
    @Autowired
    private TipoContratoRepository tipoContratoRepository;
    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public TipoContratoDTO save(TipoContratoDTO tipoContratoDTO) {
        TipoContrato tipoContrato = ObjectMapperUtils.map(tipoContratoDTO, TipoContrato.class);
        TipoContrato tipoContrato1 = tipoContratoRepository.save(tipoContrato);
        return ObjectMapperUtils.map(tipoContrato1, TipoContratoDTO.class);
    }

    @Override
    public TipoContratoDTO update(TipoContratoDTO tipoContratoDTO) {
        TipoContrato tipoContrato = ObjectMapperUtils.map(tipoContratoDTO, TipoContrato.class);
        TipoContrato tipoContrato1 = tipoContratoRepository.save(tipoContrato);
        return ObjectMapperUtils.map(tipoContrato1, TipoContratoDTO.class);
    }

    @Override
    public MensajeDTO delete(Long idTipoContrato) {
        if (!tipoContratoRepository.existsById(idTipoContrato)) {
            throw new RecursoNoEncontrado("El tipo de contrato no existe");
        }
        contratoRepository.actualizarContratosPorTipo(
                TipoContrato.builder().idTipoContrato(idTipoContrato).build(),
                TipoContrato.builder().idTipoContrato(0L).build());
        tipoContratoRepository.deleteById(idTipoContrato);
        return MensajeDTO.builder()
                .mensaje("El tipo de contrato se ha eliminado correctamente con el id: " + idTipoContrato)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public TipoContratoDTO find(Long idTipoContrato) {
        TipoContrato tipoContrato = tipoContratoRepository.findById(idTipoContrato)
                .orElseThrow(() -> new RecursoNoEncontrado("El tipo de contrato no existe"));
        return ObjectMapperUtils.map(tipoContrato, TipoContratoDTO.class);
    }

    @Override
    public List<TipoContratoDTO> findAll() {
        List<TipoContrato> tipoContratos = tipoContratoRepository.findAll();
        if (tipoContratos.isEmpty()) {
            throw new RecursoNoEncontrado("No hay tipos de contratos");
        }
        tipoContratos = tipoContratos
                .stream()
                .filter(tipoContrato -> tipoContrato.getIdTipoContrato() != 0)
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(tipoContratos, TipoContratoDTO.class);
    }
}
