package com.inmozara.crm.usuario.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.usuario.model.dto.EstadoUsuarioDTO;
import com.inmozara.crm.usuario.service.EstadoUsuarioService;
import com.inmozara.crm.usuario.service.interfaces.IEstadoUsuario;
import com.inmozara.crm.utils.excel.EstadoUsuariosExcel;
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
@RequestMapping("/api_v1/crm/usuario-estado")
public class EstadoUsuarioController implements IEstadoUsuario {
    @Autowired
    private EstadoUsuarioService estadoUsuarioService;

    @Override
    @PostMapping
    public EstadoUsuarioDTO save(@Valid @RequestBody EstadoUsuarioDTO estadoUsuarioDTO) {
        return estadoUsuarioService.save(estadoUsuarioDTO);
    }

    @Override
    @PutMapping
    public EstadoUsuarioDTO update(@Valid @RequestBody EstadoUsuarioDTO estadoUsuarioDTO) {
        return estadoUsuarioService.update(estadoUsuarioDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return estadoUsuarioService.delete(id);
    }

    @Override
    @GetMapping
    public EstadoUsuarioDTO find(@RequestParam Integer id) {
        return estadoUsuarioService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<EstadoUsuarioDTO> findAll() {
        return estadoUsuarioService.findAll();
    }

    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = EstadoUsuariosExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getEstadosUsuario());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
