package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.EstadoCita;
import com.inmozara.crm.cita.model.dto.EstadoCitaDTO;
import com.inmozara.crm.cita.model.repository.EstadoCitaRepository;
import com.inmozara.crm.cita.service.interfaces.IEstadoCita;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCitaService implements IEstadoCita {
    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    @Override
    public EstadoCitaDTO save(EstadoCitaDTO estadoCitaDTO) {
        EstadoCita estadoCita = ObjectMapperUtils.map(estadoCitaDTO, EstadoCita.class);
        estadoCita = estadoCitaRepository.save(estadoCita);
        return ObjectMapperUtils.map(estadoCita, EstadoCitaDTO.class);
    }

    @Override
    public EstadoCitaDTO update(EstadoCitaDTO estadoCitaDTO) {
        EstadoCita estadoCita = ObjectMapperUtils.map(estadoCitaDTO, EstadoCita.class);
        estadoCita = estadoCitaRepository.save(estadoCita);
        return ObjectMapperUtils.map(estadoCita, EstadoCitaDTO.class);
    }

    @Override
    public EstadoCitaDTO delete(Integer integer) {
        if (!estadoCitaRepository.existsById(integer)) {
            throw new RuntimeException("No se encontro el estado de la cita");
        }
        estadoCitaRepository.deleteById(integer);
        return null;
    }

    @Override
    public EstadoCitaDTO find(Integer integer) {
        EstadoCita estadoCita = estadoCitaRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("No se encontro el estado de la cita"));
        return ObjectMapperUtils.map(estadoCita, EstadoCitaDTO.class);
    }

    @Override
    public List<EstadoCitaDTO> findAll() {
        List<EstadoCita> estadoCitas = estadoCitaRepository.findAll();
        if (estadoCitas.isEmpty()) {
            throw new RuntimeException("No se encontraron estados de citas");
        }
        return ObjectMapperUtils.mapAll(estadoCitas, EstadoCitaDTO.class);
    }
}
