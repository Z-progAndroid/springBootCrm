package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.BarrioDTO;
import com.inmozara.crm.inmueble.service.BarrioService;
import com.inmozara.crm.inmueble.service.interfaces.IBarrio;
import com.inmozara.crm.utils.excel.BarriosExcel;
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
@RequestMapping("/api_v1/crm/barrio")
public class BarrioController implements IBarrio {
    @Autowired
    BarrioService barrioService;

    @Override
    @GetMapping("/findAllByMunicipio")
    public List<BarrioDTO> findAllByMunicipio(@RequestParam int idMunicipio) {
        return barrioService.findAllByMunicipio(idMunicipio);
    }

    @Override
    @PostMapping
    public BarrioDTO save(@Valid @RequestBody BarrioDTO barrioDTO) {
        return barrioService.save(barrioDTO);
    }

    @Override
    @PutMapping
    public BarrioDTO update(@Valid @RequestBody BarrioDTO barrioDTO) {
        return barrioService.update(barrioDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer idBarrio) {
        return barrioService.delete(idBarrio);
    }

    @Override
    @GetMapping
    public BarrioDTO find(@RequestParam Integer idBarrio) {
        return barrioService.find(idBarrio);
    }

    @Override
    @GetMapping("/all")
    public List<BarrioDTO> findAll() {
        return barrioService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = BarriosExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getBarrios());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
