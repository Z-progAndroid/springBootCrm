package com.inmozara.crm.seguimiento.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = TipoSeguimiento.TABLA_TIPO_SEGUIMIENTO)
public class TipoSeguimiento {
    protected static final String TABLA_TIPO_SEGUIMIENTO = "TIPO_SEGUIMIENTO";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_TIPO_SEGUIMIENTO")
    private int id_tipo_seguimiento;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //Relaciones
    @OneToOne(mappedBy = "tipoSeguimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Seguimiento seguimiento;
}
