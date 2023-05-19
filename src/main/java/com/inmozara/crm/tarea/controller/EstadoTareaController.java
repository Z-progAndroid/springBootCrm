package com.inmozara.crm.tarea.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.tarea.model.dto.EstadoTareaDTO;
import com.inmozara.crm.tarea.service.EstadoService;
import com.inmozara.crm.tarea.service.interfaces.IEstadoTarea;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/estado_tarea")
public class EstadoTareaController implements IEstadoTarea {
    @Autowired
    private EstadoService estadoService;

    @Override
    @PostMapping
    public EstadoTareaDTO save(@Valid @RequestBody EstadoTareaDTO estadoTareaDTO) {
        return estadoService.save(estadoTareaDTO);
    }

    @Override
    @PutMapping
    public EstadoTareaDTO update(@Valid @RequestBody EstadoTareaDTO estadoTareaDTO) {
        return estadoService.update(estadoTareaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer idEstadoTarea) {
        return estadoService.delete(idEstadoTarea);
    }

    @Override
    @GetMapping
    public EstadoTareaDTO find(@RequestParam Integer idEstadoTarea) {
        return estadoService.find(idEstadoTarea);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoTareaDTO> findAll() {
        return estadoService.findAll();
    }
}
