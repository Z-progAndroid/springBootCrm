package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = Provincia.TABLA_PROVINCIA)
public class Provincia {
    protected static final String TABLA_PROVINCIA = "PROVINCIAS";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CODIGO_PROVINCIA")
    private String codigo;
    @Column(name = "NOMBRE_PROVINCIA")
    private String nombre;
    //Relaciones
    @ManyToOne
    @JoinColumn(name = "CODIGO_PAIS")
    private Pais pais;
    @OneToMany(mappedBy = "provincia")
    private Set<Municipio> municipios=new HashSet<>();
}
