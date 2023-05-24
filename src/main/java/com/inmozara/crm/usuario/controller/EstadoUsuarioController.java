package com.inmozara.crm.usuario.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.usuario.model.dto.EstadoUsuarioDTO;
import com.inmozara.crm.usuario.service.EstadoUsuarioService;
import com.inmozara.crm.usuario.service.interfaces.IEstadoUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/usuario-estado")
public class EstadoUsuarioController implements IEstadoUsuario {
    @Autowired
    private EstadoUsuarioService estadoUsuarioService;

    @Override
    @PostMapping
    public EstadoUsuarioDTO save(@Valid @RequestBody EstadoUsuarioDTO estadoUsuarioDTO) {
        return estadoUsuarioService.save(estadoUsuarioDTO);
    }

    @Override
    @PutMapping
    public EstadoUsuarioDTO update(@Valid @RequestBody EstadoUsuarioDTO estadoUsuarioDTO) {
        return estadoUsuarioService.update(estadoUsuarioDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return estadoUsuarioService.delete(id);
    }

    @Override
    @GetMapping
    public EstadoUsuarioDTO find(@RequestParam Integer id) {
        return estadoUsuarioService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoUsuarioDTO> findAll() {
        return estadoUsuarioService.findAll();
    }
}
