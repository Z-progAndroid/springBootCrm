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
@Entity(name = "PROVINCIAS")
public class Provincia {
    @Id
    @Column(name = "ID_PROVINCIA", nullable = false, unique = true)
    private int idProvincia;
    @Column(name = "PROVINCIA")
    private String provincia;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Inmueble> inmuebles;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PAIS")
    private Pais pais;
    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Municipio> municipios;
}
