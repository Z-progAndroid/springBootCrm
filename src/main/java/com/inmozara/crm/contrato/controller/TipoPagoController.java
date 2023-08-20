package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.dto.TipoPagoDTO;
import com.inmozara.crm.contrato.service.TipoPagoService;
import com.inmozara.crm.contrato.service.interfaces.ITipoPago;
import com.inmozara.crm.utils.excel.TipoPagoExcel;
import com.inmozara.crm.utils.excel.dto.DatosExportacionDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
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
    public MensajeDTO delete(@RequestParam Long idTipoPago) {
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
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = TipoPagoExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getTiposPago());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
