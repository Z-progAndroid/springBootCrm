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
public class ProvinciaDTO {


    @NotNull(message = "El id de la provincia no puede ser nulo")
    private int idProvincia;
    @NotEmpty(message = "El nombre de la provincia no puede estar vacio")
    private String provincia;
    @NotEmpty(message = "El id del pais no puede estar vacio")
    private String idPais;
    @NotNull(message = "La fecha de creacion no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificacion no puede ser nula")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacio")
    private String modificado;
}
