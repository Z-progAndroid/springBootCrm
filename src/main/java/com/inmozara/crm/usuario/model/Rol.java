package com.inmozara.crm.usuario.model;

import lombok.Data;

@Data
public class Rol {
    protected final String TABLA_ROL = "ROL";
    private int id;
    private int idRol;
    private String nombre;
    private String descripcion;
}
