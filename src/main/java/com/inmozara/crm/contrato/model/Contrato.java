package com.inmozara.crm.contrato.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class Contrato {
    protected static final String TABLA_CONTRATO = "CONTRATO";
    private int id;
    private Usuario usuario;
    private Inmueble idInmueble;
    private TipoContrato tipoContrato;
    private TipoPago tipopago;
    private EstadoContrato estado;
    private Date fechaInicio;
    private Date fechaFin;
    private double monto;
    private String plazo;
    private String observaciones;
}
