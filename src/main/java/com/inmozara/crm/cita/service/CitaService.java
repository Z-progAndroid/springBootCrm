package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.CitaSearch;
import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.cita.service.interfaces.ICita;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public MensajeDTO delete(Integer integer) {
        if (!citaRepository.existsById(integer)) {
            throw new RecursoNoEncontrado("No existe la cita");
        }
        citaRepository.deleteById(integer);
        return MensajeDTO.builder()
                .mensaje("La cita se ha eliminado correctamente con el id: " + integer)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
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

    @Override
    public List<CitaDTO> findAllByParams(CitaDTO citaDTO) {
        List<Cita> citas = citaRepository.findAll(CitaSearch.builder()
                .cita(ObjectMapperUtils.map(citaDTO, Cita.class))
                .build());
        if (citas.isEmpty())
            throw new RecursoNoEncontrado("No existen citas con los parametros enviados");
        return ObjectMapperUtils.mapAll(citas, CitaDTO.class);
    }
}
