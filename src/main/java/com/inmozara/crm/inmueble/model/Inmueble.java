package com.inmozara.crm.inmueble.model;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.*;

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
    private int idInmueble;
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
    @Lob
    @ToString.Exclude
    @Column(name = "IMAGEN1", columnDefinition = "LONGBLOB")
    private byte[] imagen1;
    @Lob
    @ToString.Exclude
    @Column(name = "IMAGEN2", columnDefinition = "LONGBLOB")
    private byte[] imagen2;
    @Lob
    @ToString.Exclude
    @Column(name = "IMAGEN3", columnDefinition = "LONGBLOB")
    private byte[] imagen3;
    @Lob
    @ToString.Exclude
    @Column(name = "IMAGEN4", columnDefinition = "LONGBLOB")
    private byte[] imagen4;

    //Relaciones
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_INMUEBLE")
    private TipoInmueble tipoInmueble;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_INMUEBLE")
    private EstadoInmueble estadoInmueble;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAIS",nullable = true)
    private Pais pais;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROVINCIA",nullable = true)
    private Provincia provincia;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MUNICIPIO",nullable = true)
    private Municipio municipio;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BARRIO",nullable = true)
    private Barrio barrio;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    @ToString.Exclude
    @OneToMany(mappedBy = "inmueble", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Contrato> contratos;
    @ToString.Exclude
    @OneToMany(mappedBy = "inmueble", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Cita> citas;
}
