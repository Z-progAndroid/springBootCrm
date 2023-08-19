package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "MUNICIPIOS")
public class Municipio {
    @Id
    @Column(name = "ID_MUNICIPIO", nullable = false, unique = true)
    private int idMunicipio;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
     @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;
     @ToString.Exclude
    @OneToMany(mappedBy = "municipio" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Barrio> barrios;
}
