package com.inmozara.crm.inmueble.model;

import com.inmozara.crm.usuario.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class Inmueble {
    protected String TABLA_INMUEBLE = "INMUEBLE";
    private int id;
    private Usuario usuario;
    private String descripcion;
    private String direccion;
    private TipoInmueble tipo;
    private EstadoInmueble estado;
    private Pais pais;
    private Provincia provincia;
    private Municipio municipio;
    private String codigoPostal;
    private double precio_venta;
    private double precio_alquiler;
    private int habitaciones;
    private int banos;
    private Double metros_cuadrados;
    private int ano_construccion;
    private Date fecha_creacion;
    private Date fecha_modificacion;

}
