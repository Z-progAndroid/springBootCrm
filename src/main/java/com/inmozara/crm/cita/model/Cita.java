package com.inmozara.crm.cita.model;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITA")
    private int idCita;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "FECHA_CITA")
    private String fechaCita;
    @Column(name = "FECHA_CREACION")
    private String fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    private String fechaModificacion;
    @Column(name = "MODIFICADO")
    private String modificado;
    //Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TIPO_CITA")
    private TipoCita tipoCita;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ESTADO_CITA")
    private EstadoCita estadoCita;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INMUEBLE")
    private Inmueble inmueble;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AGENTE", referencedColumnName = "ID_USUARIO")
    private Usuario agente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_USUARIO")
    private Usuario cliente;
}
