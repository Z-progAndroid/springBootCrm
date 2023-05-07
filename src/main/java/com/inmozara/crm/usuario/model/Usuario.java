package com.inmozara.crm.usuario.model;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.seguimiento.model.Seguimiento;
import com.inmozara.crm.tarea.model.Tarea;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = Usuario.TABLA_USUARIO)
public class Usuario {
    protected static final String TABLA_USUARIO = "USUARIOS";
    @Id
    @Column(name = "ID_USUARIO")
    private int id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DNI")
    private String dni;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "Telefono")
    private String telefono;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "FECHA_CONTRATACION")
    private Date fechaContratacion;
    //Relaciones

    @OneToOne
    @JoinColumn(name = "ID_ROL")
    private Rol rol;
    @OneToOne
    @JoinColumn(name = "ID_ESTADO_USUARIO")
    private EstadoUsuario estadoUsuario;
    @OneToMany(mappedBy = "agente")
    private Set<Cita> citasAgente = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Cita> citasCliente = new HashSet<>();

    @OneToMany(mappedBy = "agente")
    private Set<Contrato> contratosAgente = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    private Set<Contrato> contratosCliente = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Inmueble> inmuebles = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Seguimiento> seguimientos = new HashSet<>();
    @OneToMany(mappedBy = "usuario")
    private Set<Tarea> tareas = new HashSet<>();

}
