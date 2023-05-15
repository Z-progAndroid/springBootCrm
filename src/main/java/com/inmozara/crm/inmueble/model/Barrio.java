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
@Entity(name = "BARRIOS")
public class Barrio {
    @Id
    @Column(name = "ID_BARRIO")
    private int idBarrio;
    @Column(name = "BARRIO")
    private String barrio;
    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MUNICIPIO")
    private Municipio municipio;
    @OneToMany(mappedBy = "barrio")
    private List<Inmueble> inmuebles;
}
