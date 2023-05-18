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
public class EstadoTareaDTO {
    @NotNull(message = "El id del estado de la tarea no puede ser nulo")
    private int idEstadoTarea;
    @NotEmpty(message = "El estado de la tarea no puede ser vacío")
    private String estadoTarea;
    @NotNull(message = "La fecha de creación del estado de la tarea no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificación del estado de la tarea no puede ser nula")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado del estado de la tarea no puede ser vacío")
    private String modificado;
}
