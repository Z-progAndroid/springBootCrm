package com.inmozara.crm.inmueble.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "ESTADO_INMUEBLES")
public class EstadoInmueble {
    @Id
    @Column(name = "ID_ESTADO_INMUEBLE", nullable = false, unique = true)
    private int idEstadoInmueble;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
}
