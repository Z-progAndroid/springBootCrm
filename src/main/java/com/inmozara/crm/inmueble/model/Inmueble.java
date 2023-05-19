package com.inmozara.crm.inmueble.model;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.seguimiento.model.Seguimiento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "INMUEBLES")
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INMUEBLE")
    private Long idInmueble;
    @Column(name = "DESCRIPCION", length = 200)
    private String descripcion;
    @Column(name = "DIRECCION", length = 200)
    private String direccion;
    @Column(name = "CODIGO_POSTAL", length = 10)
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
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;

    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_INMUEBLE")
    private TipoInmueble tipoInmueble;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_INMUEBLE")
    private EstadoInmueble estadoInmueble;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAIS")
    private Pais pais;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MUNICIPIO")
    private Municipio municipio;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BARRIO")
    private Barrio barrio;
    @OneToMany(mappedBy = "inmueble")
    private List<Seguimiento> seguimientos;
    @OneToMany(mappedBy = "inmueble")
    private List<Contrato> contratos;
    @OneToMany(mappedBy = "inmueble")
    private List<Cita> citas;
}
