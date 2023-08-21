package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "BARRIOS")
public class Barrio {
    @Id
    @Column(name = "ID_BARRIO", nullable = false, unique = true)
    private int idBarrio;
    @Column(name = "BARRIO")
    private String barrio;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MUNICIPIO")
    private Municipio municipio;
    @ToString.Exclude
    @OneToMany(mappedBy = "barrio", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Inmueble> inmuebles;
}