package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.inmueble.service.InmuebleService;
import com.inmozara.crm.inmueble.service.interfaces.IInmueble;
import com.inmozara.crm.utils.excel.InmuebleExcel;
import com.inmozara.crm.utils.excel.dto.DatosExportacionDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/inmueble")
public class InmuebleController implements IInmueble {
    @Autowired
    InmuebleService inmuebleService;

    @Override
    @PostMapping
    public InmuebleDTO save(@Valid @RequestBody InmuebleDTO inmuebleDTO) {
        return inmuebleService.save(inmuebleDTO);
    }

    @Override
    @PutMapping
    public InmuebleDTO update(@Valid @RequestBody InmuebleDTO inmuebleDTO) {
        return inmuebleService.update(inmuebleDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Long idInmueble) {
        return inmuebleService.delete(idInmueble);
    }

    @Override
    @GetMapping
    public InmuebleDTO find(@RequestParam Long idInmueble) {
        return inmuebleService.find(idInmueble);
    }

    @Override
    @GetMapping("/all")
    public List<InmuebleDTO> findAll() {
        return inmuebleService.findAll();
    }

    @GetMapping("/findAllRelaciones")
    public List<InmuebleDTO> findAllSinRelaciones() {
        return inmuebleService.findAllSinRelaciones();
    }
    @GetMapping("/findAllDisponibles")
    public List<InmuebleDTO> findAllDisponibles() {
        return inmuebleService.findAllDisponibles();
    }

    @PostMapping("/search")
    public List<InmuebleDTO> search(@RequestBody InmuebleDTO search) {
        return inmuebleService.search(search);
    }

    @PostMapping("/searchSinRelaciones")
    public List<InmuebleDTO> searchSinRelaciones(@RequestBody InmuebleDTO search) {
        return inmuebleService.searchSinRelaciones(search);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<MensajeDTO> uploadImage(@RequestParam("idInmueble") String idInmueble, @RequestParam("idImagen") String idImagen, @RequestParam("file") MultipartFile file) {
        inmuebleService.guardarImagen(idInmueble, idImagen, file);
        return ResponseEntity.ok().body(MensajeDTO.builder()
                .mensaje("Imagen subida correctamente")
                .estado(HttpStatus.OK.value())
                .build());
    }

    @DeleteMapping("/deleteImage")
    public ResponseEntity<MensajeDTO> deleteImage(@RequestParam String idInmueble, @RequestParam String idImagen) {
        inmuebleService.borrarImagen(idInmueble, idImagen);
        return ResponseEntity.ok().body(MensajeDTO.builder()
                .mensaje("Imagen subida correctamente")
                .estado(HttpStatus.OK.value())
                .build());
    }

    @GetMapping(value = "/download-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> downloadPdf(@RequestParam Long idInmueble) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.pdf");
        ByteArrayOutputStream outputStream = inmuebleService.generaPdfDetalle(idInmueble);
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
        ByteArrayOutputStream outputStream = InmuebleExcel.builder()
                .build().generarExcel(datosExportacion.getCabeceras(), datosExportacion.getInmuebles());
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping(value = "/obtenerInmueblesPorUsuario")
    public List<InmuebleDTO> obtenerInmueblesPorUsuario(Long idUsuario) {
        return inmuebleService.obtenerInmueblesPorUsuario(idUsuario);
    }
}
