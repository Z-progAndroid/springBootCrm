package com.inmozara.crm.tarea.service.interfaces;

import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.utils.IService;

import java.util.List;

public interface ITarea extends IService<TareaDTO, Integer> {
    List<TareaDTO> findAllByParams(TareaDTO tareaDTO);
}
