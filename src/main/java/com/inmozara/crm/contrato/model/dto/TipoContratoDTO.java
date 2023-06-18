package com.inmozara.crm.contrato.model.dto;

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
public class TipoContratoDTO {
    @NotNull(message = "El idTipoContrato no puede ser nulo")
    private Long idTipoContrato;
    @NotEmpty(message = "El tipo no puede ser vacio")
    private String tipo;
    @NotNull(message = "La fechaCreacion no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fechaModificacion no puede ser nula")
    private Date fechaModificacion;
    @NotNull(message = "El modificado no puede ser nulo")
    private String modificado;
}
