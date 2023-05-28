package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.TipoContratoRepository;
import com.inmozara.crm.contrato.model.search.ContratoSearch;
import com.inmozara.crm.contrato.service.interfaces.ITipoContrato;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Contrato> contratos = contratoRepository.findAll(ContratoSearch.builder()
                .contrato(Contrato.builder().tipoContrato(TipoContrato.builder().idTipoContrato(idTipoContrato).build()).build()).build());
        if (!contratos.isEmpty()) {
            contratos.forEach(contrato -> {
                contrato.setTipoContrato(TipoContrato.builder().idTipoContrato(0L).build());
                contratoRepository.save(contrato);
            });
        }
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
        return ObjectMapperUtils.mapAll(tipoContratos, TipoContratoDTO.class);
    }
}
