package com.inmozara.crm.cita.controller;

import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.cita.service.CitaService;
import com.inmozara.crm.cita.service.interfaces.ICita;
import com.inmozara.crm.config.MensajeDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/cita")
public class CitaController implements ICita {
    @Autowired
    private CitaService citaService;

    @Override
    @PostMapping
    public CitaDTO save(@Valid @RequestBody CitaDTO citaDTO) {
        return citaService.save(citaDTO);
    }

    @Override
    @PutMapping
    public CitaDTO update(@Valid @RequestBody CitaDTO citaDTO) {
        return citaService.update(citaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return citaService.delete(id);
    }

    @Override
    @GetMapping
    public CitaDTO find(@RequestParam Integer id) {
        return citaService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<CitaDTO> findAll() {
        return citaService.findAll();
    }
}
