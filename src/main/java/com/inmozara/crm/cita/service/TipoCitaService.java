package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.CitaSearch;
import com.inmozara.crm.cita.model.EstadoCita;
import com.inmozara.crm.cita.model.TipoCita;
import com.inmozara.crm.cita.model.dto.TipoCitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.cita.model.repository.TipoCitaRepository;
import com.inmozara.crm.cita.service.interfaces.ITipoCita;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCitaService implements ITipoCita {
    @Autowired
    private TipoCitaRepository tipoCitaRepository;
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public TipoCitaDTO save(TipoCitaDTO tipoCitaDTO) {
        TipoCita tipoCita = ObjectMapperUtils.map(tipoCitaDTO, TipoCita.class);
        tipoCita = tipoCitaRepository.save(tipoCita);
        return ObjectMapperUtils.map(tipoCita, TipoCitaDTO.class);
    }

    @Override
    public TipoCitaDTO update(TipoCitaDTO tipoCitaDTO) {
        TipoCita tipoCita = ObjectMapperUtils.map(tipoCitaDTO, TipoCita.class);
        tipoCita = tipoCitaRepository.save(tipoCita);
        return ObjectMapperUtils.map(tipoCita, TipoCitaDTO.class);
    }

    @Override
    public MensajeDTO delete(Integer id) {
        if (!tipoCitaRepository.existsById(id)) {
            throw new RecursoNoEncontrado("No se encontro el tipo de la cita");
        }
        List<Cita> citas = citaRepository.findAll(CitaSearch.builder()
                .cita(Cita.builder().tipoCita(TipoCita.builder()
                        .idTipoCita(id).build()).build()).build());
        if (!citas.isEmpty()) {
            citas.forEach(cita -> {
                cita.setEstadoCita(EstadoCita.builder().idEstadoCita(0).build());
                citaRepository.save(cita);
            });
        }
        tipoCitaRepository.deleteById(id);
        return MensajeDTO.builder()
                .mensaje("El tipo de la cita se ha eliminado correctamente con el id: " + id)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public TipoCitaDTO find(Integer integer) {
        TipoCita tipoCita = tipoCitaRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el tipo de la cita"));
        return ObjectMapperUtils.map(tipoCita, TipoCitaDTO.class);
    }

    @Override
    public List<TipoCitaDTO> findAll() {
        List<TipoCita> tipoCitas = tipoCitaRepository.findAll();
        if (tipoCitas.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron tipos de citas");
        }
        return ObjectMapperUtils.mapAll(tipoCitas, TipoCitaDTO.class);
    }
}
