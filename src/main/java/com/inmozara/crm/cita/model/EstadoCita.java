package com.inmozara.crm.cita.model;

import lombok.Data;

@Data
public class EstadoCita {
    protected static final String TABLA_ESTADO_CITA = "ESTADO_CITA";
    private int id;
    private int idEstado;
    private String nombre;
    private String descripcion;
}
