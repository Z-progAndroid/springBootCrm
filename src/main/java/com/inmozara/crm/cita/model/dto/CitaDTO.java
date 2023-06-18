package com.inmozara.crm.cita.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaDTO {
    private int idCita;
    @NotEmpty(message = "El campo descripcion no puede estar vacio")
    private String descripcion;
    @NotNull(message = "El campo fechaCita no puede estar vacio")
    private Date fechaCita;
    @NotNull(message = "El campo horaCita no puede estar vacio")
    private Date fechaCreacion;
    @NotEmpty(message = "El campo horaCita no puede estar vacio")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo horaCita no puede estar vacio")
    private String modificado;
    @NotNull(message = "El campo horaCita no puede estar vacio")
    private int idTipoCita;
    @NotNull(message = "El campo horaCita no puede estar vacio")
    private int idEstadoCita;
    @NotNull(message = "El campo horaCita no puede estar vacio")
    private int idInmueble;
    private int idUsuarioAgente;
    private int idUsuarioCliente;
}
