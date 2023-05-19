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
@Entity(name = "roles")
public class Rol {
    @Id
    @Column(name = "ID_ROL", nullable = false, unique = true)
    private int idRol;
    @Column(name = "ROL")
    private String rol;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;
}
