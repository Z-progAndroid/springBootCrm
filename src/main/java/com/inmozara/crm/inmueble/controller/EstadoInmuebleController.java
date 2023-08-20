package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.EstadoInmuebleDTO;
import com.inmozara.crm.inmueble.service.EstadoInmuebleService;
import com.inmozara.crm.inmueble.service.interfaces.IEstadoInmueble;
import com.inmozara.crm.utils.excel.EstadoInmuebleExcel;
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
@RequestMapping("/api_v1/crm/estadoInmueble")
public class EstadoInmuebleController implements IEstadoInmueble {
    @Autowired
    EstadoInmuebleService estadoInmuebleService;

    @Override
    @PostMapping
    public EstadoInmuebleDTO save(@Valid @RequestBody EstadoInmuebleDTO estadoInmuebleDTO) {
        return estadoInmuebleService.save(estadoInmuebleDTO);
    }

    @Override
    @PutMapping
    public EstadoInmuebleDTO update(@Valid @RequestBody EstadoInmuebleDTO estadoInmuebleDTO) {
        return estadoInmuebleService.update(estadoInmuebleDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer idEstadoInmueble) {
        return estadoInmuebleService.delete(idEstadoInmueble);
    }

    @Override
    @GetMapping
    public EstadoInmuebleDTO find(@RequestParam Integer idEstadoInmueble) {
        return estadoInmuebleService.find(idEstadoInmueble);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoInmuebleDTO> findAll() {
        return estadoInmuebleService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = EstadoInmuebleExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getEstadoInmuebles());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
