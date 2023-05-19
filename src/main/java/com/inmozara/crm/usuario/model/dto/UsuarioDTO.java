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
    @NotEmpty(message = "El username no puede estar vacío")
    private String username;
    @NotEmpty(message = "El password no puede estar vacío")
    private String password;
    private String telefono;
    private String direccion;
    private String dni;
    @NotNull(message = "La fecha de creación no puede estar vacía")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificación no puede estar vacía")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacío")
    private String modificado;
    //Relaciones
    @NotNull(message = "El estado del usuario no puede estar vacío")
    private int idEstadoUsuario;
    @NotNull(message = "El rol del usuario no puede estar vacío")
    private int idRol;
    private List<Cita> citasComoAgente;
    private List<Cita> citasComoCliente;
    private List<Contrato> contratoComoAgente;
    private List<Contrato> ciontratoomoCliente;
    private List<Inmueble> inmuebles;

}
