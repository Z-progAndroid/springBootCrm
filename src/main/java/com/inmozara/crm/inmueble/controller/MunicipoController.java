package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.MunicipoDTO;
import com.inmozara.crm.inmueble.service.MunicipoService;
import com.inmozara.crm.inmueble.service.interfaces.IMunicipo;
import com.inmozara.crm.utils.excel.MunicipiosExcel;
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
@RequestMapping("/api_v1/crm/municipio")
public class MunicipoController implements IMunicipo {
    @Autowired
    MunicipoService municipoService;

    @Override
    @GetMapping("/municipiosByProvincia")
    public List<MunicipoDTO> findAllByProvincia(@RequestParam int idProvincia) {
        return municipoService.findAllByProvincia(idProvincia);
    }

    @Override
    @PostMapping
    public MunicipoDTO save(@Valid @RequestBody MunicipoDTO municipoDTO) {
        return municipoService.save(municipoDTO);
    }

    @Override
    @PutMapping
    public MunicipoDTO update(@Valid @RequestBody MunicipoDTO municipoDTO) {
        return municipoService.update(municipoDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer idMunicipio) {
        return municipoService.delete(idMunicipio);
    }

    @Override
    @GetMapping
    public MunicipoDTO find(@RequestParam Integer idMunicipio) {
        return municipoService.find(idMunicipio);
    }

    @Override
    @GetMapping("/all")
    public List<MunicipoDTO> findAll() {
        return municipoService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = MunicipiosExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getMunicipios());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
