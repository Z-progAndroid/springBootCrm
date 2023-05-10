package com.inmozara.crm.seguimiento.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = Seguimiento.TABLE_SEGUIMIENTO)
public class Seguimiento {
    protected static final String TABLE_SEGUIMIENTO = "SEGUIMIENTOS";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SEGUIMIENTO")
    private int id;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHA_CREACION")
    private Date fecha_creacion;
    //Relaciones
    @ManyToOne
    @JoinColumn(name = "ID_INMUEBLE")
    private Inmueble inmueble;
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_SEGUIMIENTO")
    private TipoSeguimiento tipoSeguimiento;
}
