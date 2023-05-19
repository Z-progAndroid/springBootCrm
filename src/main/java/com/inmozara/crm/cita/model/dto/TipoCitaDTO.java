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
public class TipoCitaDTO {
    @NotNull(message = "El idTipoCita no puede ser nulo")
    private int idTipoCita;
    @NotEmpty(message = "El tipoCita no puede ser vacio")
    private String tipoCita;
    @NotNull(message = "El estado no puede ser nulo")
    private Date fechaCreacion;
    @NotNull(message = "El estado no puede ser nulo")
    private Date fechaModificacion;
    @NotEmpty(message = "El estado no puede ser vacio")
    private String modificado;
    private List<Cita> citas;
}