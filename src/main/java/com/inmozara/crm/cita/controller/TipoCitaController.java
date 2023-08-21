package com.inmozara.crm.cita.controller;

import com.inmozara.crm.cita.model.dto.TipoCitaDTO;
import com.inmozara.crm.cita.service.TipoCitaService;
import com.inmozara.crm.cita.service.interfaces.ITipoCita;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.utils.excel.TipoCitaExcel;
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
@RequestMapping("/api_v1/crm/tipoCita")
public class TipoCitaController implements ITipoCita {
    @Autowired
    private TipoCitaService tipoCitaService;

    @Override
    @PostMapping
    public TipoCitaDTO save(@Valid @RequestBody TipoCitaDTO tipoCitaDTO) {
        return tipoCitaService.save(tipoCitaDTO);
    }

    @Override
    @PutMapping
    public TipoCitaDTO update(@Valid @RequestBody TipoCitaDTO tipoCitaDTO) {
        return tipoCitaService.update(tipoCitaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return tipoCitaService.delete(id);
    }

    @Override
    @GetMapping
    public TipoCitaDTO find(@RequestParam Integer id) {
        return tipoCitaService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<TipoCitaDTO> findAll() {
        return tipoCitaService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = TipoCitaExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getTipoCita());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
