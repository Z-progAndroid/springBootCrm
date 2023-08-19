package com.inmozara.crm.inmueble.service.interfaces;

import com.inmozara.crm.inmueble.model.dto.PaisDTO;
import com.inmozara.crm.utils.IService;

public interface IPais extends IService<PaisDTO,String> {
    PaisDTO findByIdPais(String idPais);
}
