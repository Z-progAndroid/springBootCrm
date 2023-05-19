package com.inmozara.crm.contrato.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "CONTRATOS")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTRATO")
    private Long idContrato;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;
    @Column(name = "FECHA_FINALIZACION")
    private Date fechaFinalizacion;
    @Column(name = "DESCRIPCION", length = 1000)
    private String descripcion;
    @Column(name = "TERMINOS_Y_CONDICIONES", length = 1000)
    private String terminosYCondiciones;
    @Column(name = "VALOR")
    private double valor;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_CONTRATO")
    private TipoContrato tipoContrato;
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PAGO")
    private TipoPago tipoPago;
    @ManyToOne
    @JoinColumn(name = "ID_INMUEBLE")
    private Inmueble inmueble;
    @ManyToOne
    @JoinColumn(name = "ID_ESTADO_CONTRATO")
    private EstadoContrato estadoContrato;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENTE", referencedColumnName = "ID_USUARIO")
    private Usuario agente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_USUARIO")
    private Usuario cliente;
}
