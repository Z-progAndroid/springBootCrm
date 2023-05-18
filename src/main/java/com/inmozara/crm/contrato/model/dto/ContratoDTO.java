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
public class ContratoDTO {
    @NotNull(message = "El id no puede ser nulo")
    private Long idContrato;
    @NotEmpty(message = "El titulo no puede ser nulo")
    private String titulo;
    @NotNull(message = "La fecha de inicio no puede ser nula")
    private Date fechaInicio;
    @NotNull(message = "La fecha de finalizacion no puede ser nula")
    private Date fechaFinalizacion;
    @NotEmpty(message = "La descripcion no puede ser nula")
    private String descripcion;
    @NotEmpty(message = "Los terminos y condiciones no pueden ser nulos")
    private String terminosYCondiciones;
    @NotNull(message = "El valor no puede ser nulo")
    private double valor;
    private String observaciones;
    @NotNull(message = "La fecha de creacion no puede ser nula")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificacion no puede ser nula")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede ser nulo")
    private String modificado;
    //Relaciones
    @NotNull(message = "El id del tipo de contrato no puede ser nulo")
    private Long idTipoContrato;
    @NotNull(message = "El id del tipo de pago no puede ser nulo")
    private Long idTipoPago;
    @NotNull(message = "El id del estado del contrato no puede ser nulo")
    private Long idInmueble;
    @NotNull(message = "El id del estado del contrato no puede ser nulo")
    private Long idEstadoContrato;
}
