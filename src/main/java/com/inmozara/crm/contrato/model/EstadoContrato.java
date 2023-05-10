package com.inmozara.crm.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = EstadoContrato.TABLA_ESTADO_CONTRATO)
public class EstadoContrato {
    protected static final String TABLA_ESTADO_CONTRATO = "ESTADO_CONTRATO";
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
    @OneToOne(mappedBy = "estado", cascade = CascadeType.ALL)
    private Contrato contrato;
}
