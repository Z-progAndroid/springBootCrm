package com.inmozara.crm.inmueble.model;

import lombok.Data;

@Data
public class TipoInmueble {
    protected static final String TABLA_TIPO_INMUEBLE = "TIPO_INMUEBLE";
    private int id;
    private int idTipo;
    private String nombre;
    private String descripcion;
}
