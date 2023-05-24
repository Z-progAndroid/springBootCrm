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
public class TipoInmuebleDTO {
    @NotNull(message = "El id del tipo de inmueble no puede ser nulo")
    private Long id;
    @NotEmpty(message = "El tipo de inmueble no puede estar vacio")
    private String tipo;
    @NotNull(message = "La fecha de creacion no puede estar vacio")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificacion no puede estar vacio")
    private Date fechaModificacion;
    @NotEmpty(message = "El usuario que ha modificado no puede estar vacio")
    private String modificado;
}
