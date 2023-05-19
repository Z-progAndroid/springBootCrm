package com.inmozara.crm.contrato.service;

import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.service.interfaces.IContrato;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoService implements IContrato {
    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public ContratoDTO save(ContratoDTO contratoDTO) {
        Contrato contrato = ObjectMapperUtils.map(contratoDTO, Contrato.class);
        contrato = contratoRepository.save(contrato);
        return ObjectMapperUtils.map(contrato, ContratoDTO.class);
    }

    @Override
    public ContratoDTO update(ContratoDTO contratoDTO) {
        Contrato contrato = ObjectMapperUtils.map(contratoDTO, Contrato.class);
        contrato = contratoRepository.save(contrato);
        return ObjectMapperUtils.map(contrato, ContratoDTO.class);
    }

    @Override
    public ContratoDTO delete(Long idContrato) {
        if (!contratoRepository.existsById(idContrato))
            throw new RecursoNoEncontrado("Contrato no encontrado");
        contratoRepository.deleteById(idContrato);
        return null;
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
}
