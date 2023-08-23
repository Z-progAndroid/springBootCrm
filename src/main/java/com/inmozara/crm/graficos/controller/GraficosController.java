package com.inmozara.crm.graficos.controller;

import com.inmozara.crm.graficos.model.dto.GraficoDTO;
import com.inmozara.crm.graficos.service.GraficosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api_v1/crm/graficos")
public class GraficosController {
    @Autowired
    GraficosService graficosService;

    @GetMapping(value = "/graficoAdmin")
    public GraficoDTO graficoAdmin() {
        return graficosService.graficoAdmin();
    }

    @GetMapping(value = "/graficoAgente")
    public GraficoDTO graficoAgente(@RequestParam int idUsuario) {
        return graficosService.graficoAgente(idUsuario);
    }
}
