package com.inmozara.crm.usuario.model;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.tarea.model.Tarea;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private int idUsuario;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "DNI")
    private String dni;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_USUARIO")
    private EstadoUsuario estadoUsuario;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ROL")
    private Rol rol;
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Cita> citasComoCliente;
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Contrato> contratosComoCliente;
    @ToString.Exclude
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Inmueble> inmuebles;
    @ToString.Exclude
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarea> tareas;

}
