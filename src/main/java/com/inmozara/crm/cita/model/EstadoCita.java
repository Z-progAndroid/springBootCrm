package com.inmozara.crm.cita.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "estados_citas")
public class EstadoCita {
    @Id
    @Column(name = "ID_ESTADO_CITA", nullable = false, unique = true)
    private int idEstadoCita;
    @Column(name = "ESTADO_CITA")
    private String estadoCita;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    @ToString.Exclude
    @OneToMany(mappedBy = "estadoCita",fetch = FetchType.LAZY)
    private List<Cita> citas;
}

