package com.inmozara.crm.seguimiento.model;

import lombok.Data;

@Data
public class TipoSeguimiento {
    protected static final String TABLA_TIPO_SEGUIMIENTO = "TIPO_SEGUIMIENTO";
    private int id;
    private int idEstado;
    private String nombre;
    private String descripcion;
}
