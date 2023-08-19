package com.inmozara.crm.usuario.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.usuario.service.UsuarioService;
import com.inmozara.crm.usuario.service.interfaces.IUsuario;
import com.inmozara.crm.utils.excel.UsuariosExcel;
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
@RequestMapping("/api_v1/crm/usuario")
public class UsuarioController implements IUsuario {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    @PostMapping
    public UsuarioDTO save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.save(usuarioDTO);
    }

    @Override
    @PutMapping
    public UsuarioDTO update(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.update(usuarioDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return usuarioService.delete(id);
    }

    @Override
    @GetMapping
    public UsuarioDTO find(@RequestParam Integer id) {
        return usuarioService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<UsuarioDTO> findAll() {
        return usuarioService.findAll();
    }

    @Override
    @PostMapping("/search")
    public List<UsuarioDTO> findAllBYParams(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.findAllBYParams(usuarioDTO);
    }

    @Override
    @GetMapping("/all/admin-or-agent")
    public List<UsuarioDTO> findAllUserAdminORAgente() {
        return usuarioService.findAllUserAdminORAgente();
    }
    @Override
    @GetMapping("/all/usuarios")
    public List<UsuarioDTO> findAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }
    @PostMapping(value = "/download-excel")
    public ResponseEntity<ByteArrayResource> downloadExcel(@RequestBody DatosExportacionDTO datosExportacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.xlsx");
        ByteArrayOutputStream outputStream = UsuariosExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getUsuarios());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
