package com.inmozara.crm.inmueble.model.dto;

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
public class InmuebleDTO {
    private int idInmueble;
    private String descripcion;
    @NotEmpty(message = "La dirección no puede estar vacía")
    private String direccion;
    @NotEmpty(message = "El código postal no puede estar vacío")
    private String codigoPostal;
    @NotNull(message = "El precio de venta no puede estar vacío")
    private double precio_venta;
    @NotNull(message = "El precio de alquiler no puede estar vacío")
    private double precio_alquiler;
    @NotNull(message = "El número de habitaciones no puede estar vacío")
    private int numHabitaciones;
    @NotNull(message = "El número de baños no puede estar vacío")
    private int numBanos;
    @NotNull(message = "Los metros cuadrados no pueden estar vacíos")
    private Double metros_cuadrados;
    @NotNull(message = "El año de construcción no puede estar vacío")
    private int ano_construccion;
    @NotNull(message = "La fecha de creación no puede estar vacía")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificación no puede estar vacía")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacío")
    private String modificado;
    //Relaciones
    @NotNull(message = "El tipo de inmueble no puede estar vacío")
    private Long idTipoInmueble;
    @NotNull(message = "El estado del inmueble no puede estar vacío")
    private int idEstadoInmueble;
    @NotEmpty(message = "El id del pais no puede estar vacío")
    private String idPais;
    @NotNull(message = "La provincia no puede estar vacía")
    private int idProvincia;
    @NotNull(message = "El id de  municipio no puede estar vacío")
    private int idMunicipio;
    private int idUsuario;
    @NotNull(message = "El id del barrio no puede estar vacío")
    private int idBarrio;
    private String imagen1;
    private String imagen2;
    private String imagen3;
    private String imagen4;
    //PARA FRONT
    private String barrio;
    private String estadoInmueble;
    private String municipio;
    private String pais;
    private String provincia;
    private String tipoInmueble;
    private List<String> imagenes;

}
