package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.CitaSearch;
import com.inmozara.crm.cita.model.EstadoCita;
import com.inmozara.crm.cita.model.dto.EstadoCitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.cita.model.repository.EstadoCitaRepository;
import com.inmozara.crm.cita.service.interfaces.IEstadoCita;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCitaService implements IEstadoCita {
    @Autowired
    private EstadoCitaRepository estadoCitaRepository;
    @Autowired
    private CitaRepository citaRepository;

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
    public MensajeDTO delete(Integer id) {
        if (!estadoCitaRepository.existsById(id)) {
            throw new RecursoNoEncontrado("No se encontro el estado de la cita");
        }

        List<Cita> citas = citaRepository.findAll(CitaSearch.builder()
                .cita(Cita.builder().estadoCita(EstadoCita.builder()
                        .idEstadoCita(id).build()).build()).build());
        if (!citas.isEmpty()) {
            citas.forEach(cita -> {
                cita.setEstadoCita(EstadoCita.builder().idEstadoCita(0).build());
                citaRepository.save(cita);
            });
        }
        estadoCitaRepository.deleteById(id);
        return MensajeDTO.builder()
                .mensaje("Se elimino el estado de la cita con id: " + id)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public EstadoCitaDTO find(Integer integer) {
        EstadoCita estadoCita = estadoCitaRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el estado de la cita"));
        return ObjectMapperUtils.map(estadoCita, EstadoCitaDTO.class);
    }

    @Override
    public List<EstadoCitaDTO> findAll() {
        List<EstadoCita> estadoCitas = estadoCitaRepository.findAll();
        if (estadoCitas.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron estados de citas");
        }
        return ObjectMapperUtils.mapAll(estadoCitas, EstadoCitaDTO.class);
    }
}
