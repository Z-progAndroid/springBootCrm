package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.inmueble.exception.CargarImagenException;
import com.inmozara.crm.inmueble.exception.GuardarImagenException;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.inmueble.service.interfaces.IImagen;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImagenService implements IImagen {
    @Autowired
    private InmuebleService inmuebleService;
    @Value("${media.upload-dir}")
    private String uploadDir;
    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() {
        System.out.println("uploadDir: " + uploadDir);
        rootLocation = Paths.get(uploadDir);
    }

    @Override
    public String saveImage(MultipartFile file, String idInmueble) {
        this.init();
        if (file == null || file.isEmpty()) {
            throw new GuardarImagenException("Se ha producido un error al guardar la imagen" + file.getOriginalFilename());
        }
        File carpetaJava = new File(uploadDir+"/"+idInmueble);
        if (!carpetaJava.exists()) {
            carpetaJava.mkdir();
        }
        String fileName = file.getOriginalFilename();
        Path path = rootLocation.resolve(Paths.get(idInmueble, fileName))
                .normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            System.err.println(e.getCause());
            throw new GuardarImagenException("Se ha producido un error al guardar la imagen" + file.getOriginalFilename());
        }
    }

    @Override
    public Resource getImage(String imageName, String idInmueble) {
        this.init();
        try {
            Path file =rootLocation.resolve(Paths.get(idInmueble, imageName));
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() && !resource.isReadable()) {
                throw new CargarImagenException("No se pudo leer la imagen"+imageName);
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new CargarImagenException("No se pudo leer la imagen"+imageName);
        }
    }

    @Override
    public void deleteImage(String imageName, String idInmueble) {
        this.init();
        try {
            Path file = rootLocation.resolve(Paths.get(idInmueble, imageName));
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() && !resource.isReadable()) {
                throw new CargarImagenException("No se pudo leer la imagen"+imageName);
            }
            Files.delete(file);
            InmuebleDTO inmuebleDTO=inmuebleService.find(Long.valueOf(idInmueble));
            if(inmuebleDTO.getImagen1()!=null && inmuebleDTO.getImagen1().equals(imageName)){
                inmuebleDTO.setImagen1(null);
            }
            if(inmuebleDTO.getImagen2()!=null && inmuebleDTO.getImagen2().equals(imageName)){
                inmuebleDTO.setImagen2(null);
            }
            if(inmuebleDTO.getImagen3()!=null && inmuebleDTO.getImagen3().equals(imageName)){
                inmuebleDTO.setImagen3(null);
            }
            if(inmuebleDTO.getImagen4()!=null && inmuebleDTO.getImagen4().equals(imageName)){
                inmuebleDTO.setImagen4(null);
            }
            inmuebleService.save(inmuebleDTO);
        } catch (IOException e) {
            throw new CargarImagenException("No se pudo leer la imagen"+imageName);
        }
    }

    public void deleteImages(String imageName, String idInmueble) {
        this.init();
        try {
            Path file = rootLocation.resolve(Paths.get(idInmueble, imageName));
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() && !resource.isReadable()) {
                throw new CargarImagenException("No se pudo leer la imagen"+imageName);
            }
            Files.delete(file);
        } catch (IOException e) {
            throw new CargarImagenException("No se pudo leer la imagen"+imageName);
        }
    }
}
