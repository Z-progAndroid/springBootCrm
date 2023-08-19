package com.inmozara.crm.utils.excel.dto;

import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import lombok.Data;

import java.util.List;
@Data
public class DatosExportacionDTO {
    List<String> cabeceras;
    List<ContratoDTO> contratos;
}
