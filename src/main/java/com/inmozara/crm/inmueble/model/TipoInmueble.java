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
@Entity(name = "TIPO_INMUEBLES")
public class TipoInmueble {
    @Id
    @Column(name = "ID_TIPO_INMUEBLE", nullable = false, unique = true)
    private Long id;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @OneToMany(mappedBy = "tipoInmueble")
    private List<Inmueble> inmuebles;
}
