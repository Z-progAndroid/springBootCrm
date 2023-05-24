package com.inmozara.crm.inmueble.model.dto;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.Provincia;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaisDTO {
    @NotEmpty(message = "El id del pais no puede estar vacio")
    private String idPais;
    @NotNull(message = "El pais no puede ser nulo")
    @NotEmpty(message = "El pais no puede estar vacio")
    private String pais;
    @NotNull(message = "La fecha de creacion no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificacion no puede ser nula")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacio")
    private String modificado;
    private List<Inmueble> inmuebles;
    private List<Provincia> provincias;
}
