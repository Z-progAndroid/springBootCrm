package com.inmozara.crm.cita.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = EstadoCita.TABLA_ESTADO_CITA)
public class EstadoCita {
    protected static final String TABLA_ESTADO_CITA = "ESTADO_CITA";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_ESTADO_CITA")
    private int idEstadoCita;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //Relaciones
    @OneToOne(mappedBy = "estadoCita", fetch = FetchType.LAZY)
    private Cita cita;
}
