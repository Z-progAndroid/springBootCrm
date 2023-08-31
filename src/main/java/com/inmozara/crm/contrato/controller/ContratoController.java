package com.inmozara.crm.contrato.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.contrato.service.ContratoService;
import com.inmozara.crm.contrato.service.interfaces.IContrato;
import com.inmozara.crm.utils.excel.ContratosExcel;
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
@RequestMapping("/api_v1/crm/contrato")
public class ContratoController implements IContrato {
    @Autowired
    private ContratoService contratoService;

    @Override
    @PostMapping
    public ContratoDTO save(@Valid @RequestBody ContratoDTO contratoDTO) {
        return contratoService.save(contratoDTO);
    }

    @Override
    @PutMapping
    public ContratoDTO update(@Valid @RequestBody ContratoDTO contratoDTO) {
        return contratoService.update(contratoDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idContrato) {
        return contratoService.delete(idContrato);
    }

    @Override
    @GetMapping
    public ContratoDTO find(@RequestParam Long idContrato) {
        return contratoService.find(idContrato);
    }

    @Override
    @GetMapping("/all")
    public List<ContratoDTO> findAll() {
        return contratoService.findAll();
    }

    @Override
    @PostMapping("/search")
    public List<ContratoDTO> findAllByParams(@RequestBody ContratoDTO contratoDTO) {
        return contratoService.findAllByParams(contratoDTO);
    }

    @GetMapping(value = "/download-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> downloadPdf(@RequestParam Long idContrato) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.pdf");
        ByteArrayOutputStream outputStream = contratoService.generarContratoPdf(idContrato);
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = ContratosExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getContratos());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    @GetMapping(value = "/obtenerContratosPorUsuario")
    public List<ContratoDTO> obtenerContratosPorUsuario(int idUsuario) {
        return contratoService.obtenerContratosPorUsuario(idUsuario);
    }
}
