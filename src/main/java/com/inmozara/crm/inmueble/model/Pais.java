package com.inmozara.crm.inmueble.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Entity(name = "PAISES")
public class Pais {
    @Id
    @Column(name = "ID_PAIS")
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
    @OneToMany(mappedBy = "pais")
    private List<Inmueble> inmuebles;
    @OneToMany(mappedBy = "pais")
    private List<Provincia> provincias;
}
