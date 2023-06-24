package com.inmozara.crm.cita.service;

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
import java.util.stream.Collectors;

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
        citaRepository.actualizarCitasPorTipo(TipoCita.builder().idTipoCita(id).build(), TipoCita.builder().idTipoCita(0).build());
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
        tipoCitas = tipoCitas
                .stream()
                .filter(tipoCita -> tipoCita.getIdTipoCita() != 0)
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(tipoCitas, TipoCitaDTO.class);
    }
}
