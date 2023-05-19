package com.inmozara.crm.cita.controller;

import com.inmozara.crm.cita.model.dto.EstadoCitaDTO;
import com.inmozara.crm.cita.service.EstadoCitaService;
import com.inmozara.crm.cita.service.interfaces.IEstadoCita;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/cita-estado")
public class EstadoCitaController implements IEstadoCita {
    @Autowired
    private EstadoCitaService estadoCitaService;

    @Override
    @PostMapping
    public EstadoCitaDTO save(@Valid @RequestBody EstadoCitaDTO estadoCitaDTO) {
        return estadoCitaService.save(estadoCitaDTO);
    }

    @Override
    @PutMapping
    public EstadoCitaDTO update(@Valid @RequestBody EstadoCitaDTO estadoCitaDTO) {
        return estadoCitaService.update(estadoCitaDTO);
    }

    @Override
    @DeleteMapping
    public EstadoCitaDTO delete(@RequestParam Integer id) {
        return estadoCitaService.delete(id);
    }

    @Override
    @GetMapping
    public EstadoCitaDTO find(@RequestParam Integer id) {
        return estadoCitaService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoCitaDTO> findAll() {
        return estadoCitaService.findAll();
    }
}
