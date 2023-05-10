package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = Pais.TABLA_PAIS)
public class Pais {
    protected static final String TABLA_PAIS = "PAISES";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CODIGO_PAIS")
    private String codigo;
    @Column(name = "NOMBRE_PAIS")
    private String nombre;
    //Relaciones
    @OneToMany(mappedBy = "pais")
    private Set<Provincia> provincias = new HashSet<>();
}
