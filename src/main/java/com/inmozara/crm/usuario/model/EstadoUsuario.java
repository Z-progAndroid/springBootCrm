package com.inmozara.crm.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "estado_usuarios")
public class EstadoUsuario {
    @Id
    @Column(name = "ID_ESTADO_USUARIO", nullable = false, unique = true)
    private int idEstadoUsuario;
    @Column(name = "ESTADO_USUARIO")
    private String estadoUsuario;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    @OneToMany(mappedBy = "estadoUsuario")
    private List<Usuario> usuarios;
}
