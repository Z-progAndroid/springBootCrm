package com.inmozara.crm.usuario.controller;

import com.inmozara.crm.usuario.model.dto.RolDTO;
import com.inmozara.crm.usuario.service.RolService;
import com.inmozara.crm.usuario.service.interfaces.IRol;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/rol")
public class RolController implements IRol {
    @Autowired
    private RolService rolService;

    @Override
    @PostMapping
    public RolDTO save(@Valid @RequestBody RolDTO rolDTO) {
        return rolService.save(rolDTO);
    }

    @Override
    @PutMapping
    public RolDTO update(@Valid @RequestBody RolDTO rolDTO) {
        return rolService.update(rolDTO);
    }

    @Override
    @DeleteMapping
    public RolDTO delete(@RequestParam Integer id) {
        return rolService.delete(id);
    }

    @Override
    @GetMapping
    public RolDTO find(@RequestParam Integer id) {
        return rolService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<RolDTO> findAll() {
        return rolService.findAll();
    }
}
