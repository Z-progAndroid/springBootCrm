package com.inmozara.crm.graficos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraficoDTO {
    private Map<String, Long> contratos;
    private Map<String, Long> inmuebles;
    private Map<String, Long> citas;
    private Map<String, Long> tareas;
    private Map<String, Long> visitas;
}
