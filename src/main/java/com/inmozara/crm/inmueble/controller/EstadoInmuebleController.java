package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.inmueble.model.dto.EstadoInmuebleDTO;
import com.inmozara.crm.inmueble.service.interfaces.IEstadoInmueble;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/estadoInmueble")
public class EstadoInmuebleController implements IEstadoInmueble {
    @Autowired
    IEstadoInmueble estadoInmuebleService;

    @Override
    @PostMapping
    public EstadoInmuebleDTO save(@Valid @RequestBody EstadoInmuebleDTO estadoInmuebleDTO) {
        return estadoInmuebleService.save(estadoInmuebleDTO);
    }

    @Override
    @PutMapping
    public EstadoInmuebleDTO update(@Valid @RequestBody EstadoInmuebleDTO estadoInmuebleDTO) {
        return estadoInmuebleService.update(estadoInmuebleDTO);
    }

    @Override
    @DeleteMapping
    public EstadoInmuebleDTO delete(@RequestParam Integer idEstadoInmueble) {
        return estadoInmuebleService.delete(idEstadoInmueble);
    }

    @Override
    @GetMapping
    public EstadoInmuebleDTO find(@RequestParam Integer idEstadoInmueble) {
        return estadoInmuebleService.find(idEstadoInmueble);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoInmuebleDTO> findAll() {
        return estadoInmuebleService.findAll();
    }
}
