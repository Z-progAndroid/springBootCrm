package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = Municipio.TABLA_MUNICIPIO)
public class Municipio {
    protected static final String TABLA_MUNICIPIO = "MUNICIPIOS";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CODIGO_MUNICIPIO")
    private String codigo;
    @Column(name = "NOMBRE_MUNICIPIO")
    private String nombre;
    //Relaciones
    @ManyToOne
    @JoinColumn(name = "CODIGO_PROVINCIA")
    private Provincia provincia;
    @OneToMany(mappedBy = "municipio")
    private Set<Inmueble> inmuebles = new HashSet<>();
}
