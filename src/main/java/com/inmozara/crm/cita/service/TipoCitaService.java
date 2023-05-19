package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.TipoCita;
import com.inmozara.crm.cita.model.dto.TipoCitaDTO;
import com.inmozara.crm.cita.model.repository.TipoCitaRepository;
import com.inmozara.crm.cita.service.interfaces.ITipoCita;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCitaService implements ITipoCita {
    @Autowired
    private TipoCitaRepository tipoCitaRepository;

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
    public TipoCitaDTO delete(Integer integer) {
        if (!tipoCitaRepository.existsById(integer)) {
            throw new RuntimeException("No se encontro el tipo de la cita");
        }
        tipoCitaRepository.deleteById(integer);
        return null;
    }

    @Override
    public TipoCitaDTO find(Integer integer) {
        TipoCita tipoCita = tipoCitaRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("No se encontro el tipo de la cita"));
        return ObjectMapperUtils.map(tipoCita, TipoCitaDTO.class);
    }

    @Override
    public List<TipoCitaDTO> findAll() {
        List<TipoCita> tipoCitas = tipoCitaRepository.findAll();
        if (tipoCitas.isEmpty()) {
            throw new RuntimeException("No se encontraron tipos de citas");
        }
        return ObjectMapperUtils.mapAll(tipoCitas, TipoCitaDTO.class);
    }
}
