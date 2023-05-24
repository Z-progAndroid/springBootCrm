package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.inmueble.service.InmuebleService;
import com.inmozara.crm.inmueble.service.interfaces.IInmueble;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/inmueble")
public class InmuebleController implements IInmueble {
    @Autowired
    InmuebleService inmuebleService;

    @Override
    @PostMapping
    public InmuebleDTO save(@Valid @RequestBody InmuebleDTO inmuebleDTO) {
        return inmuebleService.save(inmuebleDTO);
    }

    @Override
    @PutMapping
    public InmuebleDTO update(@Valid @RequestBody InmuebleDTO inmuebleDTO) {
        return inmuebleService.update(inmuebleDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idInmueble) {
        return inmuebleService.delete(idInmueble);
    }

    @Override
    @GetMapping
    public InmuebleDTO find(@RequestParam Long idInmueble) {
        return inmuebleService.find(idInmueble);
    }

    @Override
    @GetMapping("/all")
    public List<InmuebleDTO> findAll() {
        return inmuebleService.findAll();
    }
}
