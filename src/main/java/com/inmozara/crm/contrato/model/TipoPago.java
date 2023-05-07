package com.inmozara.crm.contrato.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = TipoPago.TABLA_TIPO_PAGO)
public class TipoPago {
    protected static final String TABLA_TIPO_PAGO = "TIPO_PAGO";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_TIPO_PAGO")
    private int idTipoPago;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //Relaciones
    @OneToOne(mappedBy = "tipopago", cascade = CascadeType.ALL)
    private Contrato contrato;
}
