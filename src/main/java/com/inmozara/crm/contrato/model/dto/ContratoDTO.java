package com.inmozara.crm.contrato.model.dto;

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
public class ContratoDTO {
    private Long idContrato;
    private String titulo;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private String descripcion;
    private String terminosYCondiciones;
    private double valor;
    private String observaciones;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String modificado;
    //Relaciones
    @NotNull(message = "El tipo de contrato no puede ser nulo")
    private Long idTipoContrato;
    @NotNull(message = "El tipo de pago no puede ser nulo")
    private Long idTipoPago;
    @NotNull(message = "El estado del contrato no puede ser nulo")
    private int idInmueble;
    @NotNull(message = "El estado del contrato no puede ser nulo")
    private Long idEstadoContrato;
    @NotNull(message = "El cliente no puede ser nulo")
    private int cliente;
}
