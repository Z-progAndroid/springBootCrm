package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.inmueble.model.dto.MunicipoDTO;
import com.inmozara.crm.inmueble.service.MunicipoService;
import com.inmozara.crm.inmueble.service.interfaces.IMunicipo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/municipio")
public class MunicipoController implements IMunicipo {
    @Autowired
    MunicipoService municipoService;

    @Override
    @GetMapping("/municipiosByProvincia")
    public List<MunicipoDTO> findAllByProvincia(@RequestParam int idProvincia) {
        return municipoService.findAllByProvincia(idProvincia);
    }

    @Override
    @PostMapping
    public MunicipoDTO save(@Valid @RequestBody MunicipoDTO municipoDTO) {
        return municipoService.save(municipoDTO);
    }

    @Override
    @PutMapping
    public MunicipoDTO update(@Valid @RequestBody MunicipoDTO municipoDTO) {
        return municipoService.update(municipoDTO);
    }

    @Override
    @DeleteMapping
    public MunicipoDTO delete(@RequestParam Integer idMunicipio) {
        return municipoService.delete(idMunicipio);
    }

    @Override
    @GetMapping
    public MunicipoDTO find(@RequestParam Integer idMunicipio) {
        return municipoService.find(idMunicipio);
    }

    @Override
    @GetMapping("/all")
    public List<MunicipoDTO> findAll() {
        return municipoService.findAll();
    }
}
