package com.inmozara.crm.inmueble.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PAISES")
public class Pais {
    @Id
    @Column(name = "ID_PAIS", nullable = false, unique = true)
    private String idPais;
    @Column(name = "PAIS")
    private String pais;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @ToString.Exclude
    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Provincia> provincias;
}
