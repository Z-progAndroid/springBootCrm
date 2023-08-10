package com.inmozara.crm.cita.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaDTO {

    private int idCita;
    @NotEmpty(message = "El campo titulo no puede ser nulo")
    private String titulo;
    private String descripcion;
    @NotNull(message = "El campo fechaIncio no puede ser nulo")
    private LocalDateTime fechaIncio;
    private LocalDateTime fechaFin;
    @NotNull(message = "El campo fechaCreacion no puede ser nulo")
    private Date fechaCreacion;
    @NotNull(message = "El campo fechaModificacion no puede estar vacio")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacio")
    private String modificado;
    @NotNull(message = "El campo tipo cita no puede ser nulo")
    private int idTipoCita;
    @NotNull(message = "El campo estado cita no puede ser nulo")
    private int idEstadoCita;
    private int idInmueble;
    private int idUsuarioCliente;
}
