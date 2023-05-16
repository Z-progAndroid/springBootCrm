package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.inmueble.model.dto.ProvinciaDTO;
import com.inmozara.crm.inmueble.service.ProvinciaService;
import com.inmozara.crm.inmueble.service.interfaces.IProvincia;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/provincia")
public class ProvinciaController implements IProvincia {
    @Autowired
    ProvinciaService provinciaService;

    @Override
    @GetMapping("/provinciasByPais")
    public List<ProvinciaDTO> findAllByPais(@RequestParam String idPais) {
        return provinciaService.findAllByPais(idPais);
    }

    @Override
    @PostMapping
    public ProvinciaDTO save(@Valid @RequestBody ProvinciaDTO provinciaDTO) {
        return provinciaService.save(provinciaDTO);
    }

    @Override
    @PutMapping
    public ProvinciaDTO update(@Valid @RequestBody ProvinciaDTO provinciaDTO) {
        return provinciaService.update(provinciaDTO);
    }

    @Override
    @DeleteMapping
    public ProvinciaDTO delete(Integer idProvincia) {
        return provinciaService.delete(idProvincia);
    }

    @Override
    @GetMapping
    public ProvinciaDTO find(@RequestParam Integer idProvincia) {
        return provinciaService.find(idProvincia);
    }

    @Override
    @GetMapping("/all")
    public List<ProvinciaDTO> findAll() {
        return provinciaService.findAll();
    }
}
