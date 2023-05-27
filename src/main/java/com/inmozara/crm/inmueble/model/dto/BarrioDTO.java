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
public class BarrioDTO {
    @NotNull(message = "El id del barrio no puede ser nulo")
    private int idBarrio;
    @NotEmpty(message = "El nombre del barrio no puede ser vacio")
    private String barrio;
    @NotNull(message = "La fecha de creacion no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificacion no puede ser nula")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede ser vacio")
    private String modificado;
    @NotNull(message = "El id del municipio no puede ser nulo")
    private int idMunicipio;

}
