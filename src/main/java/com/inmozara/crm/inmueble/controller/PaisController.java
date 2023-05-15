package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.inmueble.model.dto.PaisDTO;
import com.inmozara.crm.inmueble.service.PaisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/pais")
public class PaisController {
    @Autowired
    private PaisService paisService;

    @GetMapping
    public PaisDTO findByIdPais(@RequestParam String idPais) {
        return paisService.findByIdPais(idPais);
    }

    @PostMapping
    public PaisDTO save(@RequestBody @Valid PaisDTO paisDTO) {
        return paisService.save(paisDTO);
    }


    @PutMapping
    public PaisDTO update(@RequestBody @Valid PaisDTO paisDTO) {
        return paisService.update(paisDTO);
    }


    @DeleteMapping
    public PaisDTO delete(@RequestParam String idPais) {
        return paisService.delete(idPais);
    }

    @GetMapping("/all")
    public List<PaisDTO> findAll() {
        return paisService.findAll();
    }
}
