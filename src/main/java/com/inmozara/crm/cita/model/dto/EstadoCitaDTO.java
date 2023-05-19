package com.inmozara.crm.cita.model.dto;

import com.inmozara.crm.cita.model.Cita;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoCitaDTO {
    @NotNull(message = "El id del estado de la cita no puede estar vacío")
    private int idEstadoCita;
    @NotEmpty(message = "El estado de la cita no puede estar vacío")
    private String estadoCita;
    @NotNull(message = "La fecha de creación no puede estar vacía")
    private Date fechaCreacion;
    @NotNull(message = "La fecha de modificación no puede estar vacía")
    private Date fechaModificacion;
    @NotEmpty(message = "El campo modificado no puede estar vacío")
    private String modificado;
    private List<Cita> citas;
}
