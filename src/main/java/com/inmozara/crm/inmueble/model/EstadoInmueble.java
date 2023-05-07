package com.inmozara.crm.inmueble.model;

import lombok.Data;

@Data
public class EstadoInmueble {
    protected final String TABLA_ESTADO_INMUEBLE = "ESTADO_INMUEBLE";
    private int id;
    private int idEstadoInmueble;
    private String nombre;
    private String descripcion;
}
