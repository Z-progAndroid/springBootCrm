package com.inmozara.crm.inmueble.model;

import lombok.Data;

@Data
public class Pais {
    protected static final String TABLA_PAIS = "PAIS";
    private int id;
    private String codigo;
    private String nombre;
}
