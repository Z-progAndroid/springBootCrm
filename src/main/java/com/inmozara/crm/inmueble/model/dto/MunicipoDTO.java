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
public class MunicipoDTO {
    @NotNull(message = "El id del municipio no puede ser nulo")
    private int idMunicipio;
    @NotEmpty(message = "El nombre del municipio no puede estar vacio")
    private String municipio;
    @NotNull(message = "El id de la provincia no puede ser nulo")
    private int idProvincia;
    @NotNull(message = "La fecha de creacion no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificacion no puede ser nula")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacio")
    private String modificado;


}
