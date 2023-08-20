package com.inmozara.crm.tarea.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.tarea.service.TareaService;
import com.inmozara.crm.tarea.service.interfaces.ITarea;
import com.inmozara.crm.utils.excel.TareaExcel;
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
@RequestMapping("/api_v1/crm/tarea")
public class TareaController implements ITarea {
    @Autowired
    private TareaService tareaService;

    @Override
    @PostMapping
    public TareaDTO save(@Valid @RequestBody TareaDTO tareaDTO) {
        return tareaService.save(tareaDTO);
    }

    @Override
    @PutMapping
    public TareaDTO update(@Valid @RequestBody TareaDTO tareaDTO) {
        return tareaService.update(tareaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer idTarea) {
        return tareaService.delete(idTarea);
    }

    @Override
    @GetMapping
    public TareaDTO find(@RequestParam Integer idTarea) {
        return tareaService.find(idTarea);
    }

    @Override
    @GetMapping("/all")
    public List<TareaDTO> findAll() {
        return tareaService.findAll();
    }

    @Override
    @PostMapping("/search")
    public List<TareaDTO> findAllByParams(@RequestBody TareaDTO tareaDTO) {
        return tareaService.findAllByParams(tareaDTO);
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = TareaExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getTareas());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
