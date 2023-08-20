package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.contrato.service.TipoContratoService;
import com.inmozara.crm.contrato.service.interfaces.ITipoContrato;
import com.inmozara.crm.utils.excel.TipoContratoExcel;
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
@RequestMapping("/api_v1/crm/tipo-contrato")
public class TipoContratoController implements ITipoContrato {
    @Autowired
    private TipoContratoService tipoContratoService;

    @Override
    @PostMapping
    public TipoContratoDTO save(@Valid @RequestBody TipoContratoDTO tipoContratoDTO) {
        return tipoContratoService.save(tipoContratoDTO);
    }

    @Override
    @PutMapping
    public TipoContratoDTO update(@Valid @RequestBody TipoContratoDTO tipoContratoDTO) {
        return tipoContratoService.update(tipoContratoDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idTipoContrato) {
        return tipoContratoService.delete(idTipoContrato);
    }

    @Override
    @GetMapping
    public TipoContratoDTO find(@RequestParam Long idTipoContrato) {
        return tipoContratoService.find(idTipoContrato);
    }

    @Override
    @GetMapping("/all")
    public List<TipoContratoDTO> findAll() {
        return tipoContratoService.findAll();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = TipoContratoExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getTiposContrato());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
