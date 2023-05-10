package com.inmozara.crm.tarea.model;

import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = Tarea.TABLA_TAREA)
public class Tarea {
    protected static final String TABLA_TAREA = "TAREAS";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TAREA")
    private int id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    private Date fechaFin;
    //Relaciones
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "ID_ESTADO")
    private EstadoTarea estado;

}
