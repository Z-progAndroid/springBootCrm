package com.inmozara.crm.seguimiento.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "seguimientos")
public class Seguimiento {
    @Id
    @Column(name = "ID_SEGUIMIENTO")
    private Long idSeguimiento;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHA_CREACION")
    private String fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private String fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_SEGUIMIENTO")
    private TipoSeguimiento tipoSeguimiento;
    @ManyToOne
    @JoinColumn(name = "ID_INMUEBLE")
    private Inmueble inmueble;

}
