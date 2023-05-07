package com.inmozara.crm.tarea.model;

import com.inmozara.crm.usuario.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class Tarea {
    protected final String TABLA_TAREA = "TAREA";
    private int id;
    private Usuario usuario;
    private EstadoTarea estado;
    private String titulo;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;

}
