package com.inmozara.crm.inmueble.service.interfaces;

import com.inmozara.crm.inmueble.model.dto.BarrioDTO;

import java.util.List;

public interface IBarrio extends IService<BarrioDTO, Integer>{
    List<BarrioDTO> findAllByMunicipio(int idMunicipio);
}
