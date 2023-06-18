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
public class EstadoContratoDTO {
    @NotNull(message = "El id del estado del contrato no puede ser nulo")
    private Long idestadoContrato;
    @NotEmpty(message = "El estado del contrato no puede ser vacio")
    private String estado;
    @NotEmpty(message = "La fecha de creacion del estado del contrato no puede ser vacia")
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String modificado;
}
