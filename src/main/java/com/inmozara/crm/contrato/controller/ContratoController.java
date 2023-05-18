package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.contrato.service.ContratoService;
import com.inmozara.crm.contrato.service.interfaces.IContrato;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/contrato")
public class ContratoController implements IContrato {
    @Autowired
    private ContratoService contratoService;

    @Override
    @PostMapping
    public ContratoDTO save(@Valid  @RequestBody ContratoDTO contratoDTO) {
        return contratoService.save(contratoDTO);
    }

    @Override
    @PutMapping
    public ContratoDTO update(@Valid @RequestBody ContratoDTO contratoDTO) {
        return contratoService.update(contratoDTO);
    }

    @Override
    @DeleteMapping
    public ContratoDTO delete(@RequestParam Long idContrato) {
        return contratoService.delete(idContrato);
    }

    @Override
    @GetMapping
    public ContratoDTO find(@RequestParam Long idContrato) {
        return contratoService.find(idContrato);
    }

    @Override
    @GetMapping("/all")
    public List<ContratoDTO> findAll() {
        return contratoService.findAll();
    }
}
