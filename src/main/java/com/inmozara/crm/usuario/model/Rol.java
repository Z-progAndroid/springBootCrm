package com.inmozara.crm.usuario.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = Rol.TABLA_ROL)
public class Rol {
    protected static final String TABLA_ROL = "ROLES";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ID_ROL")
    private int idRol;
    @Column(name = "ROL")
    private String rol;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    //Relaciones
    @OneToOne(mappedBy = "rol")
    private Usuario usuario;
}
