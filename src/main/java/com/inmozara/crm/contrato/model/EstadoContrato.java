package com.inmozara.crm.contrato.model;

import lombok.Data;

@Data
public class EstadoContrato {
    protected final String TABLA_ESTADO_CONTRATO = "ESTADO_CONTRATO";
    private int id;
    private int idEstado;
    private String nombre;
    private String descripcion;
}
