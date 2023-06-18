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
public class TipoPagoDTO {
    @NotNull(message = "El idTipoPago no puede ser nulo")
    private Long idTipoPago;
    @NotEmpty(message = "El tipo no puede ser vacio")
    private String tipo;
    @NotNull(message = "El estado no puede ser nulo")
    private Date fechaCreacion;
    @NotNull(message = "El estado no puede ser nulo")
    private Date fechaModificacion;
    @NotEmpty(message = "El estado no puede ser vacio")
    private String modificado;
}
