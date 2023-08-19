package com.inmozara.crm.cita.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tipos_citas")
public class TipoCita {
    @Id
    @Column(name = "ID_TIPO_CITA", nullable = false, unique = true)
    private int idTipoCita;
    @Column(name = "ESTADO_CITA")
    private String tipoCita;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    @ToString.Exclude
    @OneToMany(mappedBy = "tipoCita",fetch = FetchType.LAZY)
    private List<Cita> citas;
}
