package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.contrato.service.TipoContratoService;
import com.inmozara.crm.contrato.service.interfaces.ITipoContrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/tipo-contrato")
public class TipoContratoController implements ITipoContrato {
    @Autowired
    private TipoContratoService tipoContratoService;

    @Override
    @PostMapping
    public TipoContratoDTO save(@RequestBody TipoContratoDTO tipoContratoDTO) {
        return tipoContratoService.save(tipoContratoDTO);
    }

    @Override
    @PutMapping
    public TipoContratoDTO update(@RequestBody TipoContratoDTO tipoContratoDTO) {
        return tipoContratoService.update(tipoContratoDTO);
    }

    @Override
    @DeleteMapping
    public TipoContratoDTO delete(@RequestParam Long idTipoContrato) {
        return tipoContratoService.delete(idTipoContrato);
    }

    @Override
    @GetMapping
    public TipoContratoDTO find(@RequestParam Long idTipoContrato) {
        return tipoContratoService.find(idTipoContrato);
    }

    @Override
    @GetMapping("/all")
    public List<TipoContratoDTO> findAll() {
        return tipoContratoService.findAll();
    }
}
