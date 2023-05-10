package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = TipoInmueble.TABLA_TIPO_INMUEBLE)
public class TipoInmueble {
    protected static final String TABLA_TIPO_INMUEBLE = "TIPO_INMUEBLE";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_TIPO_INMUEBLE")
    private int idTipo;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //Relaciones
    @OneToOne(mappedBy = "tipo", fetch = FetchType.LAZY)
    private Inmueble inmueble;
}
