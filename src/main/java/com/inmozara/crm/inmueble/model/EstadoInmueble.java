package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = EstadoInmueble.TABLA_ESTADO_INMUEBLE)
public class EstadoInmueble {
    protected final static String TABLA_ESTADO_INMUEBLE = "ESTADO_INMUEBLE";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_ESTADO_INMUEBLE")
    private int idEstadoInmueble;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //Relaciones
    @OneToOne(mappedBy = "estado", fetch = FetchType.LAZY)
    private Inmueble inmueble;
}
