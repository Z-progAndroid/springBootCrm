package com.inmozara.crm.contrato.service;

import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.contrato.model.repository.TipoContratoRepository;
import com.inmozara.crm.contrato.service.interfaces.ITipoContrato;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoContratoService implements ITipoContrato {
    @Autowired
    private TipoContratoRepository tipoContratoRepository;

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
    public TipoContratoDTO delete(Long idTipoContrato) {
        if (!tipoContratoRepository.existsById(idTipoContrato)) {
            throw new RecursoNoEncontrado("El tipo de contrato no existe");
        }
        tipoContratoRepository.deleteById(idTipoContrato);
        return null;
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
