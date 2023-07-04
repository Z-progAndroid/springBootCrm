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
public class PaisDTO {
    @NotEmpty(message = "El id del pais no puede estar vacio")
    private String idPais;
    @NotEmpty(message = "El nombre del pais no puede estar vacio")
    private String pais;
    @NotNull(message = "La fecha de creacion no puede estar vacia")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificacion no puede estar vacia")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacio")
    private String modificado;
    private String idPaisExistente;
}
