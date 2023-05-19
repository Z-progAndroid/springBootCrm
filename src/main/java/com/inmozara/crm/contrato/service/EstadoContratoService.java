package com.inmozara.crm.contrato.service;

import com.inmozara.crm.contrato.model.EstadoContrato;
import com.inmozara.crm.contrato.model.dto.EstadoContratoDTO;
import com.inmozara.crm.contrato.model.repository.EstadoContratoRepository;
import com.inmozara.crm.contrato.service.interfaces.IEstadoContrato;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoContratoService implements IEstadoContrato {
    @Autowired
    private EstadoContratoRepository estadoContratoRepository;

    @Override
    public EstadoContratoDTO save(EstadoContratoDTO estadoContratoDTO) {
        EstadoContrato estadoContrato = ObjectMapperUtils.map(estadoContratoDTO, EstadoContrato.class);
        estadoContrato = estadoContratoRepository.save(estadoContrato);
        return ObjectMapperUtils.map(estadoContrato, EstadoContratoDTO.class);

    }

    @Override
    public EstadoContratoDTO update(EstadoContratoDTO estadoContratoDTO) {
        EstadoContrato estadoContrato = ObjectMapperUtils.map(estadoContratoDTO, EstadoContrato.class);
        estadoContrato = estadoContratoRepository.save(estadoContrato);
        return ObjectMapperUtils.map(estadoContrato, EstadoContratoDTO.class);
    }

    @Override
    public EstadoContratoDTO delete(Long idEstadoContrato) {
        if(!estadoContratoRepository.existsById(idEstadoContrato)){
            throw new RuntimeException("no existe un estado de contrato con el id: "+idEstadoContrato);
        }
        estadoContratoRepository.deleteById(idEstadoContrato);
        return null;
    }

    @Override
    public EstadoContratoDTO find(Long idEstadoContrato) {
        EstadoContrato estadoContrato = estadoContratoRepository.findById(idEstadoContrato)
                .orElseThrow(() -> new RuntimeException("No se encontro el estado del contrato con el id: " + idEstadoContrato));
        return ObjectMapperUtils.map(estadoContrato, EstadoContratoDTO.class);
    }

    @Override
    public List<EstadoContratoDTO> findAll() {
        List<EstadoContrato> estadoContratos = estadoContratoRepository.findAll();
        if (estadoContratos.isEmpty())
            throw new RuntimeException("No se encontraron estados de contratos");
        return ObjectMapperUtils.mapAll(estadoContratos, EstadoContratoDTO.class);
    }
}
