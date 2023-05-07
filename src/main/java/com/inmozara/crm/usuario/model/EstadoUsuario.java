package com.inmozara.crm.usuario.model;

import lombok.Data;

@Data
public class EstadoUsuario {
    protected final String TABLA_ESTADO_USUARIO = "ESTADO_USUARIO";
    private int id;
    private int idEstadoUsuario;
    private String nombre;
    private String descripcion;
}
