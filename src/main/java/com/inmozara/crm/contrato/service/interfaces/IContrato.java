package com.inmozara.crm.contrato.service.interfaces;

import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.utils.comon.IService;

import java.util.List;

public interface IContrato extends IService<ContratoDTO,Long> {
    List<ContratoDTO> findAllByParams(ContratoDTO contratoDTO);
}
