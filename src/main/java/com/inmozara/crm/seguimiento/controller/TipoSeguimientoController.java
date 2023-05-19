package com.inmozara.crm.seguimiento.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.seguimiento.model.dto.TipoSeguimientoDTO;
import com.inmozara.crm.seguimiento.service.interfaces.ITipoSeguimiento;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/tipo_seguimiento")
public class TipoSeguimientoController implements ITipoSeguimiento {
    @Autowired
    private ITipoSeguimiento tipoSeguimientoService;

    @Override
    @PostMapping
    public TipoSeguimientoDTO save(@Valid @RequestBody TipoSeguimientoDTO tipoSeguimientoDTO) {
        return tipoSeguimientoService.save(tipoSeguimientoDTO);
    }

    @Override
    @PutMapping
    public TipoSeguimientoDTO update(@Valid @RequestBody TipoSeguimientoDTO tipoSeguimientoDTO) {
        return tipoSeguimientoService.update(tipoSeguimientoDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long id) {
        return tipoSeguimientoService.delete(id);
    }

    @Override
    @GetMapping
    public TipoSeguimientoDTO find(@RequestParam Long id) {
        return tipoSeguimientoService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<TipoSeguimientoDTO> findAll() {
        return tipoSeguimientoService.findAll();
    }
}
