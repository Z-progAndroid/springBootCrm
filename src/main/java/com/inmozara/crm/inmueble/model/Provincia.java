package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.*;

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
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAIS")
    private Pais pais;
    @ToString.Exclude
    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Municipio> municipios;
}
