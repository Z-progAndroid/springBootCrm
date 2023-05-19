package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.cita.service.interfaces.ICita;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaService implements ICita {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public CitaDTO save(CitaDTO citaDTO) {
        Cita cita = ObjectMapperUtils.map(citaDTO, Cita.class);
        cita = citaRepository.save(cita);
        return ObjectMapperUtils.map(cita, CitaDTO.class);
    }

    @Override
    public CitaDTO update(CitaDTO citaDTO) {
        Cita cita = ObjectMapperUtils.map(citaDTO, Cita.class);
        cita = citaRepository.save(cita);
        return ObjectMapperUtils.map(cita, CitaDTO.class);
    }

    @Override
    public CitaDTO delete(Integer integer) {
        if (!citaRepository.existsById(integer)) {
            throw new RecursoNoEncontrado("No existe la cita");
        }
        citaRepository.deleteById(integer);
        return null;
    }

    @Override
    public CitaDTO find(Integer integer) {
        Cita cita = citaRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No existe la cita"));
        return ObjectMapperUtils.map(cita, CitaDTO.class);
    }

    @Override
    public List<CitaDTO> findAll() {
        List<Cita> citas = citaRepository.findAll();
        if (citas.isEmpty())
            throw new RecursoNoEncontrado("No existen citas");
        return ObjectMapperUtils.mapAll(citas, CitaDTO.class);
    }
}
