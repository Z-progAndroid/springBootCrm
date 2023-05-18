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
@Entity(name = "TIPO_PAGOS")
public class TipoPago {
    @Id
    @Column(name = "ID_TIPO_PAGO", nullable = false, unique = true)
    private Long idTipoPago;
    @Column(name = "TIPO")
    private String tipo;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    @OneToMany(mappedBy = "tipoPago")
    private List<Contrato> contratos;
}
