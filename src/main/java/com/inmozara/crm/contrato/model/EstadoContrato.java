package com.inmozara.crm.contrato.model;

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
@Entity(name = "ESTADO_CONTRATOS")
public class EstadoContrato {
    @Id
    @Column(name = "ID_ESTADO_CONTRATO", nullable = false, unique = true)
    private Long idEstadoContrato;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
}
