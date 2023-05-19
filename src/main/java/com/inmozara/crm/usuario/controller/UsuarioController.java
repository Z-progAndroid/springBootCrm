package com.inmozara.crm.usuario.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.usuario.service.UsuarioService;
import com.inmozara.crm.usuario.service.interfaces.IUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/usuario")
public class UsuarioController implements IUsuario {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    @PostMapping
    public UsuarioDTO save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.save(usuarioDTO);
    }

    @Override
    @PutMapping
    public UsuarioDTO update(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.update(usuarioDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return usuarioService.delete(id);
    }

    @Override
    @GetMapping
    public UsuarioDTO find(@RequestParam Integer id) {
        return usuarioService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<UsuarioDTO> findAll() {
        return usuarioService.findAll();
    }
}
