package com.inmozara.crm.usuario.model.dto;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.inmueble.model.Inmueble;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String password;
    private String telefono;
    private String direccion;
    private String dni;
    private Date fechaCreacion;
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacío")
    private String modificado;
    //Relaciones
    @NotNull(message = "El estado del usuario no puede estar vacío")
    private int idEstadoUsuario;
    @NotNull(message = "El rol del usuario no puede estar vacío")
    private int idRol;
    /* Datos front*/
    private String estadoUsuario;
    private String rol;
    private List<Cita> citasComoAgente;
    private List<Cita> citasComoCliente;
    private List<Contrato> contratoComoAgente;
    private List<Contrato> ciontratoomoCliente;
    private List<Inmueble> inmuebles;
}
