package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.PaisDTO;
import com.inmozara.crm.inmueble.service.PaisService;
import com.inmozara.crm.utils.excel.PaisExcel;
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
@RequestMapping("/api_v1/crm/pais")
public class PaisController {
    @Autowired
    private PaisService paisService;

    @GetMapping
    public PaisDTO findByIdPais(@RequestParam String idPais) {
        return paisService.findByIdPais(idPais);
    }

    @PostMapping
    public PaisDTO save(@RequestBody @Valid PaisDTO paisDTO) {
        return paisService.save(paisDTO);
    }


    @PutMapping
    public PaisDTO update(@RequestBody @Valid PaisDTO paisDTO) {
        return paisService.update(paisDTO);
    }


    @DeleteMapping
    public MensajeDTO delete(@RequestParam String idPais) {
        return paisService.delete(idPais);
    }

    @GetMapping("/all")
    public List<PaisDTO> findAll() {
        return paisService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = PaisExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getPaises());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
