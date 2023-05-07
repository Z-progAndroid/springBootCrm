package com.inmozara.crm.usuario.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = EstadoUsuario.TABLA_ESTADO_USUARIO)
public class EstadoUsuario {
    protected static final String TABLA_ESTADO_USUARIO = "ESTADO_USUARIO";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_ESTADO_USUARIO")
    private int idEstadoUsuario;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //Relaciones
    @OneToOne(mappedBy = "estadoUsuario")
    private Usuario usuario;
}
