package com.inmozara.crm.tarea.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = EstadoTarea.TABLA_ESTADO_TAREA)
public class EstadoTarea {
    protected static final String TABLA_ESTADO_TAREA = "ESTADO_TAREA";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_ESTADO")
    private int idEstado;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "DESCRIPCION")
    private String descripcion;

    //Relaciones
    @OneToOne(mappedBy = "estado")
    private Tarea tarea;
}
