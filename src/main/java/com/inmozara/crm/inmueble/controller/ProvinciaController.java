package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.ProvinciaDTO;
import com.inmozara.crm.inmueble.service.ProvinciaService;
import com.inmozara.crm.inmueble.service.interfaces.IProvincia;
import com.inmozara.crm.utils.excel.ProvinciaExcel;
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
@RequestMapping("/api_v1/crm/provincia")
public class ProvinciaController implements IProvincia {
    @Autowired
    ProvinciaService provinciaService;

    @Override
    @GetMapping("/provinciasByPais")
    public List<ProvinciaDTO> findAllByPais(@RequestParam String idPais) {
        return provinciaService.findAllByPais(idPais);
    }

    @Override
    @PostMapping
    public ProvinciaDTO save(@Valid @RequestBody ProvinciaDTO provinciaDTO) {
        return provinciaService.save(provinciaDTO);
    }

    @Override
    @PutMapping
    public ProvinciaDTO update(@Valid @RequestBody ProvinciaDTO provinciaDTO) {
        return provinciaService.update(provinciaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(Integer idProvincia) {
        return provinciaService.delete(idProvincia);
    }

    @Override
    @GetMapping
    public ProvinciaDTO find(@RequestParam Integer idProvincia) {
        return provinciaService.find(idProvincia);
    }

    @Override
    @GetMapping("/all")
    public List<ProvinciaDTO> findAll() {
        return provinciaService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = ProvinciaExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getProvincias() );
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
