package com.inmozara.crm.cita.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class Cita {
    protected static final String TABLA_CITA = "CITA";
    private int id;
    private Usuario usuario;
    private Inmueble inmueble;
    private EstadoCita estadoCita;
    private String motivo;
    private String comentario;
    private Date fecha;
}
