package com.inmozara.crm.usuario.model.dto;

import com.inmozara.crm.usuario.model.Usuario;
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
public class RolDTO {
    @NotNull(message = "El id del rol no puede estar vacío")
    private int idRol;
    @NotEmpty(message = "El rol no puede estar vacío")
    private String rol;
    @NotNull(message = "La fecha de creación no puede estar vacía")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificación no puede estar vacía")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacío")
    private String modificado;
    private List<Usuario> usuarios;
}

