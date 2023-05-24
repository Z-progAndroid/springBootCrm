package com.inmozara.crm.tarea.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.tarea.service.TareaService;
import com.inmozara.crm.tarea.service.interfaces.ITarea;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/tarea")
public class TareaController implements ITarea {
    @Autowired
    private TareaService tareaService;

    @Override
    @PostMapping
    public TareaDTO save(@Valid @RequestBody TareaDTO tareaDTO) {
        return tareaService.save(tareaDTO);
    }

    @Override
    @PutMapping
    public TareaDTO update(@Valid @RequestBody TareaDTO tareaDTO) {
        return tareaService.update(tareaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer idTarea) {
        return tareaService.delete(idTarea);
    }

    @Override
    @GetMapping
    public TareaDTO find(@RequestParam Integer idTarea) {
        return tareaService.find(idTarea);
    }

    @Override
    @GetMapping("/all")
    public List<TareaDTO> findAll() {
        return tareaService.findAll();
    }
}
