package com.inmozara.crm.tarea.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.tarea.model.dto.EstadoTareaDTO;
import com.inmozara.crm.tarea.service.EstadoTareaService;
import com.inmozara.crm.tarea.service.interfaces.IEstadoTarea;
import com.inmozara.crm.utils.excel.EstadoTareaExcel;
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
@RequestMapping("/api_v1/crm/estado_tarea")
public class EstadoTareaController implements IEstadoTarea {
    @Autowired
    private EstadoTareaService estadoService;

    @Override
    @PostMapping
    public EstadoTareaDTO save(@Valid @RequestBody EstadoTareaDTO estadoTareaDTO) {
        return estadoService.save(estadoTareaDTO);
    }

    @Override
    @PutMapping
    public EstadoTareaDTO update(@Valid @RequestBody EstadoTareaDTO estadoTareaDTO) {
        return estadoService.update(estadoTareaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer idEstadoTarea) {
        return estadoService.delete(idEstadoTarea);
    }

    @Override
    @GetMapping
    public EstadoTareaDTO find(@RequestParam Integer idEstadoTarea) {
        return estadoService.find(idEstadoTarea);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoTareaDTO> findAll() {
        return estadoService.findAll();
    }

    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = EstadoTareaExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getEstadosTareas());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
