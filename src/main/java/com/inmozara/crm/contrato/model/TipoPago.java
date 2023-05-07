package com.inmozara.crm.contrato.model;

import lombok.Data;

@Data
public class TipoPago {
    protected static final String TABLA_TIPO_PAGO = "TIPO_PAGO";
    private int id;
    private int id_estado;
    private String nombre;
    private String descripcion;
}
