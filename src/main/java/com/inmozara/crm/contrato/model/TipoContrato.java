package com.inmozara.crm.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = TipoContrato.TABLA_TIPO_CONTRATO)
public class TipoContrato {
    protected static final String TABLA_TIPO_CONTRATO = "TIPO_CONTRATO";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_TIPO_CONTRATO")
    private int idTipoContrato;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DESCRIPCION")
    private String descripcion;

    //Relaciones
    @OneToOne(mappedBy = "tipoContrato", cascade = CascadeType.ALL)
    private Contrato contrato;
}
