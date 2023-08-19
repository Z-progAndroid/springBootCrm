package com.inmozara.crm.tarea.model;

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
@Entity(name = "estado_tarea")
public class EstadoTarea {
    @Id
    @Column(name = "ID_ESTADO_TAREA", unique = true, nullable = false)
    private int idEstadoTarea;
    @Column(name = "ESTADO_TAREA")
    private String estadoTarea;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
}
