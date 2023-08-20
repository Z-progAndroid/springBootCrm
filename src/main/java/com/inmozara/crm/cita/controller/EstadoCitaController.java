package com.inmozara.crm.cita.controller;

import com.inmozara.crm.cita.model.dto.EstadoCitaDTO;
import com.inmozara.crm.cita.service.EstadoCitaService;
import com.inmozara.crm.cita.service.interfaces.IEstadoCita;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.utils.excel.EstadoCitasExcel;
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
@RequestMapping("/api_v1/crm/cita-estado")
public class EstadoCitaController implements IEstadoCita {
    @Autowired
    private EstadoCitaService estadoCitaService;

    @Override
    @PostMapping
    public EstadoCitaDTO save(@Valid @RequestBody EstadoCitaDTO estadoCitaDTO) {
        return estadoCitaService.save(estadoCitaDTO);
    }

    @Override
    @PutMapping
    public EstadoCitaDTO update(@Valid @RequestBody EstadoCitaDTO estadoCitaDTO) {
        return estadoCitaService.update(estadoCitaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return estadoCitaService.delete(id);
    }

    @Override
    @GetMapping
    public EstadoCitaDTO find(@RequestParam Integer id) {
        return estadoCitaService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoCitaDTO> findAll() {
        return estadoCitaService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = EstadoCitasExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getEstadosCitas());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
