package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PROVINCIAS")
public class Provincia {
    @Id
    @Column(name = "ID_PROVINCIA")
    private int idProvincia;
    @Column(name = "PROVINCIA")
    private String provincia;
    //Relaciones
    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Inmueble> inmuebles;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAIS")
    private Pais pais;
    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY)
    private List<Municipio> municipios;
}
