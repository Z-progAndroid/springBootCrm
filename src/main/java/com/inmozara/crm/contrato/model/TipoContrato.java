package com.inmozara.crm.contrato.model;

import lombok.Data;

@Data
public class TipoContrato {
    protected static final String TABLA_TIPO_CONTRATO = "TIPO_CONTRATO";
    private int id;
    private int idEstado;
    private String nombre;
    private String descripcion;
}
