package com.inmozara.crm.seguimiento.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.seguimiento.model.dto.SeguimientoDTO;
import com.inmozara.crm.seguimiento.service.SeguimientoService;
import com.inmozara.crm.seguimiento.service.interfaces.ISeguimiento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/seguimiento")
public class SeguimientoController implements ISeguimiento {
    @Autowired
    private SeguimientoService seguimientoService;

    @Override
    @PostMapping
    public SeguimientoDTO save(@Valid @RequestBody SeguimientoDTO seguimientoDTO) {
        return seguimientoService.save(seguimientoDTO);
    }

    @Override
    @PutMapping
    public SeguimientoDTO update(@Valid @RequestBody SeguimientoDTO seguimientoDTO) {
        return seguimientoService.update(seguimientoDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idSeguimiento) {
        return seguimientoService.delete(idSeguimiento);
    }

    @Override
    @GetMapping
    public SeguimientoDTO find(@RequestParam Long idSeguimiento) {
        return seguimientoService.find(idSeguimiento);
    }

    @Override
    @GetMapping("/all")
    public List<SeguimientoDTO> findAll() {
        return seguimientoService.findAll();
    }
}
