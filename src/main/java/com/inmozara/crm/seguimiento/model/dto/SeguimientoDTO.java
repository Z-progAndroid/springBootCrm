package com.inmozara.crm.seguimiento.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeguimientoDTO {
    private Long idSeguimiento;
    @NotEmpty(message = "La descripción no puede estar vacía")
    private String descripcion;
    @NotNull(message = "La fecha de creación no puede estar vacía")
    private String fechaCreacion;
    @NotNull(message = "La fecha de modificación no puede estar vacía")
    private String fechaModificacion;
    @NotNull(message = "El campo modificado no puede estar vacío")
    private String modificado;
    @NotNull(message = "El id del tipo de seguimiento no puede estar vacío")
    private Long idTipoSeguimiento;
    @NotNull(message = "El id del inmueble no puede estar vacío")
    private int idInmueble;
}
