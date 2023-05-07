package com.inmozara.crm.usuario.model;

import lombok.Data;

import java.util.Date;

@Data
public class Usuario {
    protected final String TABLA_USUARIO = "USUARIO";
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private Rol rol;
    private EstadoUsuario estadoUsuario;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Date fechaContratacion;
}
