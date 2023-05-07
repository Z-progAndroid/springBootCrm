package com.inmozara.crm.tarea.model;

import lombok.Data;

@Data
public class EstadoTarea {
    protected final String TABLA_ESTADO_TAREA = "ESTADO_TAREA";
    private int id;
    private int idEstado;
    private String nombre;
    private String descripcion;
}
