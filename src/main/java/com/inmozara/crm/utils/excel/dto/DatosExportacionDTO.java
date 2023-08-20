package com.inmozara.crm.utils.excel.dto;

import com.inmozara.crm.cita.model.dto.EstadoCitaDTO;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.contrato.model.dto.EstadoContratoDTO;
import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.inmueble.model.dto.EstadoInmuebleDTO;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.tarea.model.dto.EstadoTareaDTO;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.usuario.model.dto.EstadoUsuarioDTO;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import lombok.Data;

import java.util.List;

@Data
public class DatosExportacionDTO {
    List<String> cabeceras;
    List<ContratoDTO> contratos;
    List<UsuarioDTO> usuarios;
    List<InmuebleDTO> inmuebles;
    List<TareaDTO> tareas;
    List<EstadoContratoDTO> estadoContratos;
    List<EstadoInmuebleDTO> estadoInmuebles;
    List<EstadoTareaDTO>estadosTareas;
    List<EstadoUsuarioDTO>estadosUsuario;
    List<EstadoCitaDTO> estadosCitas;
    List<TipoContratoDTO>tiposContrato;
}
