package com.inmozara.crm.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class MensajeDTO {
    private int estado;
    private String fecha;
    private String mensaje;
    private String description;
}
