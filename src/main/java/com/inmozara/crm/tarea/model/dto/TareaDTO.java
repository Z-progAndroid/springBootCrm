package com.inmozara.crm.tarea.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaDTO {
    private int idTarea;
    @NotEmpty(message = "El título de la tarea no puede ser vacío")
    private String titulo;
    @NotEmpty(message = "La descripción de la tarea no puede ser vacía")
    private String descripcion;
    @NotNull(message = "La fecha de inicio de la tarea no puede ser nula")
    private Date fechaInicio;
    @NotNull(message = "La fecha de fin de la tarea no puede ser nula")
    private Date fechaFin;
    @NotNull(message = "La fecha de creación de la tarea no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificación de la tarea no puede ser nula")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado de la tarea no puede ser vacío")
    private String modificado;
    @NotNull(message = "El id del estado de la tarea no puede ser nulo")
    private int idEstadoTarea;
    private int idUsuario;
}
