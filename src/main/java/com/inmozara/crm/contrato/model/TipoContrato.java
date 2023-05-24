package com.inmozara.crm.contrato.model;

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
@Entity(name = "TIPO_CONTRATOS")
public class TipoContrato {
    @Id
    @Column(name = "ID_TIPO_CONTRATO", nullable = false, unique = true)
    private Long idTipoContrato;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @OneToMany(mappedBy = "tipoContrato")
    private List<Contrato> contratos;
}
