package com.inmozara.crm.contrato.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = Contrato.TABLA_CONTRATO)
public class Contrato {
    protected static final String TABLA_CONTRATO = "CONTRATOS";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTRATO")
    private int id;
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    private Date fechaFin;
    @Column(name = "MONTO")
    private double monto;
    @Column(name = "PLAZO")
    private String plazo;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENTE", referencedColumnName = "ID_USUARIO")
    private Usuario agente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_USUARIO")
    private Usuario cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INMUEBLE")
    private Inmueble inmueble;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CONTRATO")
    private TipoContrato tipoContrato;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_PAGO")
    private TipoPago tipopago;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO")
    private EstadoContrato estado;
}
