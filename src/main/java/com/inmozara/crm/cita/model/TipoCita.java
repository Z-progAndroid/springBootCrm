package com.inmozara.crm.cita.model;

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

    @OneToMany(mappedBy = "tipoCita")
    private List<Cita> citas;
}
