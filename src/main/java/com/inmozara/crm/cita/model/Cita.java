package com.inmozara.crm.cita.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = Cita.TABLA_CITAS)
public class Cita {
    protected static final String TABLA_CITAS = "CITAS";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITA")
    private int id;
    @Column(name = "MOTIVO")
    private String motivo;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "FECHA_CITA")
    private Date fechaCita;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
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
    @JoinColumn(name = "ID_ESTADO_CITA")
    private EstadoCita estadoCita;

}
