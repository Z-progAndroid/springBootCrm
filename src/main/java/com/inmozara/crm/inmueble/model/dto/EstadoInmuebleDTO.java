package com.inmozara.crm.inmueble.model.dto;

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
public class EstadoInmuebleDTO {


    @NotNull(message = "El idEstadoInmueble no puede ser nulo")
    private int idEstadoInmueble;
    @NotEmpty(message = "El estado no puede ser nulo")
    private String estado;
    @NotNull(message = "La fecha de creación no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificación no puede ser nula")
    private Date fechaModificacion;
    @NotNull(message = "El campo modificado no puede ser nulo")
    private String modificado;

}
