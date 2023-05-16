package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "MUNICIPIOS")
public class Municipio {
    @Id
    @Column(name = "ID_MUNICIPIO")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROVINCIA")
    private Provincia provincia;
    @OneToMany(mappedBy = "municipio")
    private List<Inmueble> inmuebles;
    @OneToMany(mappedBy = "municipio")
    private List<Barrio> barrios;
}
