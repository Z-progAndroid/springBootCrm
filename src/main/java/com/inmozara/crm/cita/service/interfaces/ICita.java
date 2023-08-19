package com.inmozara.crm.cita.service.interfaces;

import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.utils.IService;

import java.util.List;

public interface ICita extends IService<CitaDTO, Integer> {
    List<CitaDTO> findAllByParams(CitaDTO citaDTO);
}
