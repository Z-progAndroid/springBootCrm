package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.TipoInmuebleDTO;
import com.inmozara.crm.inmueble.service.TipoInmuebleService;
import com.inmozara.crm.inmueble.service.interfaces.ITipoInmueble;
import com.inmozara.crm.utils.excel.TipoInmueblesExcel;
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
@RequestMapping("/api_v1/crm/tipoInmueble")
public class TipoInmuebleController implements ITipoInmueble {
    @Autowired
    TipoInmuebleService tipoInmuebleService;

    @Override
    @PostMapping
    public TipoInmuebleDTO save(@Valid @RequestBody TipoInmuebleDTO tipoInmuebleDTO) {
        return tipoInmuebleService.save(tipoInmuebleDTO);
    }

    @Override
    @PutMapping
    public TipoInmuebleDTO update(@Valid @RequestBody TipoInmuebleDTO tipoInmuebleDTO) {
        return tipoInmuebleService.update(tipoInmuebleDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idTipoInmueble) {
        return tipoInmuebleService.delete(idTipoInmueble);
    }

    @Override
    @GetMapping
    public TipoInmuebleDTO find(@RequestParam Long idTipoInmueble) {
        return tipoInmuebleService.find(idTipoInmueble);
    }

    @Override
    @GetMapping("/all")
    public List<TipoInmuebleDTO> findAll() {
        return tipoInmuebleService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = TipoInmueblesExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getTiposInmueble());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
