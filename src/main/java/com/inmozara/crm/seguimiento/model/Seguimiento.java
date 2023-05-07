package com.inmozara.crm.seguimiento.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class Seguimiento {
    protected final String TABLE_SEGUIMIENTO = "SEGUIMIENTO";
    private int id;
    private Inmueble inmueble;
    private Usuario usuario;
    private TipoSeguimiento idTipoSeguimiento;
    private String descripcion;
    private Date fecha_creacion;
}
