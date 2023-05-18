package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.contrato.model.dto.EstadoContratoDTO;
import com.inmozara.crm.contrato.service.EstadoContratoService;
import com.inmozara.crm.contrato.service.interfaces.IEstadoContrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/estado-contrato")
public class EstadoContratoController implements IEstadoContrato {
    @Autowired
    private EstadoContratoService estadoContratoService;

    @Override
    @PostMapping
    public EstadoContratoDTO save(@RequestBody EstadoContratoDTO estadoContratoDTO) {
        return estadoContratoService.save(estadoContratoDTO);
    }

    @Override
    @PutMapping
    public EstadoContratoDTO update(@RequestBody EstadoContratoDTO estadoContratoDTO) {
        return estadoContratoService.update(estadoContratoDTO);
    }

    @Override
    @DeleteMapping
    public EstadoContratoDTO delete(@RequestParam Long idEstadoContrato) {
        return estadoContratoService.delete(idEstadoContrato);
    }

    @Override
    @GetMapping
    public EstadoContratoDTO find(@RequestParam Long idEstadoContrato) {
        return estadoContratoService.find(idEstadoContrato);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoContratoDTO> findAll() {
        return estadoContratoService.findAll();
    }
}
