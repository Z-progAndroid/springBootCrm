package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.contrato.model.dto.TipoPagoDTO;
import com.inmozara.crm.contrato.service.TipoPagoService;
import com.inmozara.crm.contrato.service.interfaces.ITipoPago;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/tipo-pago")
public class TipoPagoController implements ITipoPago {
    @Autowired
    private TipoPagoService tipoPagoService;

    @Override
    @PostMapping
    public TipoPagoDTO save(@Valid @RequestBody TipoPagoDTO tipoPagoDTO) {
        return tipoPagoService.save(tipoPagoDTO);
    }

    @Override
    @PutMapping
    public TipoPagoDTO update(@Valid @RequestBody TipoPagoDTO tipoPagoDTO) {
        return tipoPagoService.update(tipoPagoDTO);
    }

    @Override
    @DeleteMapping
    public TipoPagoDTO delete(@RequestParam Long idTipoPago) {
        return tipoPagoService.delete(idTipoPago);
    }

    @Override
    @GetMapping
    public TipoPagoDTO find(@RequestParam Long idTipoPago) {
        return tipoPagoService.find(idTipoPago);
    }

    @Override
    @GetMapping("/all")
    public List<TipoPagoDTO> findAll() {
        return tipoPagoService.findAll();
    }
}
