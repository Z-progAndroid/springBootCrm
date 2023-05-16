package com.inmozara.crm.inmueble.service.interfaces;

import com.inmozara.crm.inmueble.model.dto.ProvinciaDTO;

import java.util.List;

public interface IProvincia extends IService<ProvinciaDTO,Integer> {
    List<ProvinciaDTO> findAllByPais(String idPais);
}
