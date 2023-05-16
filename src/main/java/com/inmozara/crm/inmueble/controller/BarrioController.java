package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.inmueble.model.dto.BarrioDTO;
import com.inmozara.crm.inmueble.service.BarrioService;
import com.inmozara.crm.inmueble.service.interfaces.IBarrio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/barrio")
public class BarrioController implements IBarrio {
    @Autowired
    BarrioService barrioService;

    @Override
    @GetMapping("/findAllByMunicipio")
    public List<BarrioDTO> findAllByMunicipio(@RequestParam int idMunicipio) {
        return barrioService.findAllByMunicipio(idMunicipio);
    }

    @Override
    @PostMapping
    public BarrioDTO save(@Valid @RequestBody BarrioDTO barrioDTO) {
        return barrioService.save(barrioDTO);
    }

    @Override
    @PutMapping
    public BarrioDTO update(@Valid @RequestBody BarrioDTO barrioDTO) {
        return barrioService.update(barrioDTO);
    }

    @Override
    @DeleteMapping
    public BarrioDTO delete(@RequestParam Integer idBarrio) {
        return barrioService.delete(idBarrio);
    }

    @Override
    @GetMapping
    public BarrioDTO find(@RequestParam Integer idBarrio) {
        return barrioService.find(idBarrio);
    }

    @Override
    @GetMapping("/all")
    public List<BarrioDTO> findAll() {
        return barrioService.findAll();
    }
}
