package com.inmozara.crm.inmueble.service.interfaces;

import com.inmozara.crm.inmueble.model.dto.MunicipoDTO;

import java.util.List;

public interface IMunicipo extends IService<MunicipoDTO, Integer> {
    List<MunicipoDTO> findAllByProvincia(int idProvincia);
}
