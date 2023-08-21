package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.dto.EstadoContratoDTO;
import com.inmozara.crm.contrato.service.EstadoContratoService;
import com.inmozara.crm.contrato.service.interfaces.IEstadoContrato;
import com.inmozara.crm.utils.excel.EstadoContratoExcel;
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
@RequestMapping("/api_v1/crm/estado-contrato")
public class EstadoContratoController implements IEstadoContrato {
    @Autowired
    private EstadoContratoService estadoContratoService;

    @Override
    @PostMapping
    public EstadoContratoDTO save(@Valid  @RequestBody EstadoContratoDTO estadoContratoDTO) {
        return estadoContratoService.save(estadoContratoDTO);
    }

    @Override
    @PutMapping
    public EstadoContratoDTO update(@Valid @RequestBody EstadoContratoDTO estadoContratoDTO) {
        return estadoContratoService.update(estadoContratoDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idEstadoContrato) {
        return estadoContratoService.delete(idEstadoContrato);
    }

    @Override
    @GetMapping
    public EstadoContratoDTO find(@RequestParam Long idEstadoContrato) {
        return estadoContratoService.find(idEstadoContrato);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoContratoDTO> findAll() {
        return estadoContratoService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = EstadoContratoExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getEstadoContratos());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
