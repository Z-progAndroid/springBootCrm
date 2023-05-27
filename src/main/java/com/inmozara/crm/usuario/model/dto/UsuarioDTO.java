package com.inmozara.crm.usuario.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

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
}
