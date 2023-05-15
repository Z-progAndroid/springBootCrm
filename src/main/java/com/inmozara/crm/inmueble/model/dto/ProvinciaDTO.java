package com.inmozara.crm.inmueble.model.dto;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.Municipio;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<Inmueble> inmuebles;
    private List<Municipio> municipios;
}
