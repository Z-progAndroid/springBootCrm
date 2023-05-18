package com.inmozara.crm.seguimiento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tipo_seguimientos")
public class TipoSeguimiento {
    @Id
    @Column(name = "ID_TIPO_SEGUIMIENTO", nullable = false, unique = true)
    private Long idTipoSeguimiento;
    @Column(name = "TIPO_SEGUIMIENTO")
    private String tipoSeguimiento;
    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    @OneToMany(mappedBy = "tipoSeguimiento")
    List<Seguimiento> seguimientos;
}
