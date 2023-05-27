package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.inmueble.exception.CargarImagenException;
import com.inmozara.crm.inmueble.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api_v1/crm/imagen")
public class ImagenController {


    @Autowired
    private ImagenService imagenService;

    @PostMapping
    public MensajeDTO saveImage(@RequestParam MultipartFile file, @RequestParam String idInmueble) {
        String image = imagenService.saveImage(file, idInmueble);
        return MensajeDTO.builder()
                .mensaje("Se ha guardado la imagen " + image + " correctamente")
                .build();
    }

    @GetMapping
    public ResponseEntity<Resource> getImage(@RequestParam String imageName, @RequestParam String idInmueble) {
        Resource resource = imagenService.getImage(imageName, idInmueble);
        try {
            String contentType = Files.probeContentType(resource.getFile().toPath());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(resource);
        } catch (IOException e) {
            throw new CargarImagenException("Se ha producido un error al cargar la imagen " + imageName);
        }

    }

    @DeleteMapping
    public MensajeDTO deleteImage(String imageName, String idInmueble) {
        imagenService.deleteImage(imageName, idInmueble);
        return MensajeDTO.builder()
                .mensaje("Se ha eliminado la imagen " + imageName + " correctamente")
                .build();
    }
}
