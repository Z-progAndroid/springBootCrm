package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.TipoInmuebleDTO;
import com.inmozara.crm.inmueble.service.TipoInmuebleService;
import com.inmozara.crm.inmueble.service.interfaces.ITipoInmueble;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/tipoInmueble")
public class TipoInmuebleController implements ITipoInmueble {
    @Autowired
    TipoInmuebleService tipoInmuebleService;

    @Override
    @PostMapping
    public TipoInmuebleDTO save(@Valid @RequestBody TipoInmuebleDTO tipoInmuebleDTO) {
        return tipoInmuebleService.save(tipoInmuebleDTO);
    }

    @Override
    @PutMapping
    public TipoInmuebleDTO update(@Valid @RequestBody TipoInmuebleDTO tipoInmuebleDTO) {
        return tipoInmuebleService.update(tipoInmuebleDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idTipoInmueble) {
        return tipoInmuebleService.delete(idTipoInmueble);
    }

    @Override
    @GetMapping
    public TipoInmuebleDTO find(@RequestParam Long idTipoInmueble) {
        return tipoInmuebleService.find(idTipoInmueble);
    }

    @Override
    @GetMapping("/all")
    public List<TipoInmuebleDTO> findAll() {
        return tipoInmuebleService.findAll();
    }
}
