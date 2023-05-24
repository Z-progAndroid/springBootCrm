package com.inmozara.crm.cita.controller;

import com.inmozara.crm.cita.model.dto.TipoCitaDTO;
import com.inmozara.crm.cita.service.TipoCitaService;
import com.inmozara.crm.cita.service.interfaces.ITipoCita;
import com.inmozara.crm.config.MensajeDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/tipoCita")
public class TipoCitaController implements ITipoCita {
    @Autowired
    private TipoCitaService tipoCitaService;

    @Override
    @PostMapping
    public TipoCitaDTO save(@Valid @RequestBody TipoCitaDTO tipoCitaDTO) {
        return tipoCitaService.save(tipoCitaDTO);
    }

    @Override
    @PutMapping
    public TipoCitaDTO update(@Valid @RequestBody TipoCitaDTO tipoCitaDTO) {
        return tipoCitaService.update(tipoCitaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return tipoCitaService.delete(id);
    }

    @Override
    @GetMapping
    public TipoCitaDTO find(@RequestParam Integer id) {
        return tipoCitaService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<TipoCitaDTO> findAll() {
        return tipoCitaService.findAll();
    }
}
