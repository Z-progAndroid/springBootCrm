package com.inmozara.crm.inmueble.model;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.seguimiento.model.Seguimiento;
import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = Inmueble.TABLA_INMUEBLE)
public class Inmueble {
    protected static final String TABLA_INMUEBLE = "INMUEBLES";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INMUEBLE")
    private int id;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @Column(name = "PRECIO_VENTA")
    private double precio_venta;
    @Column(name = "PRECIO_ALQUILER")
    private double precio_alquiler;
    @Column(name = "NUM_HABITACIONES")
    private int numHabitaciones;
    @Column(name = "NUM_BANOS")
    private int numBanos;
    @Column(name = "METROS_CUADRADOS")
    private Double metros_cuadrados;
    @Column(name = "ANO_CONSTRUCCION")
    private int ano_construccion;
    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fecha_modificacion;
    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private Municipio municipio;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_INMUEBLE")
    private TipoInmueble tipo;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_INMUEBLE")
    private EstadoInmueble estado;
    @OneToMany(mappedBy = "inmueble")
    private Set<Cita> citas = new HashSet<>();
    @OneToMany(mappedBy = "inmueble")
    private Set<Contrato> contratos = new HashSet<>();
    @OneToMany(mappedBy = "inmueble")
    private Set<Seguimiento> seguimientos = new HashSet<>();

}
