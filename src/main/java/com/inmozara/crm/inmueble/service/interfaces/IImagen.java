package com.inmozara.crm.inmueble.service.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IImagen {
    void init();

    String saveImage(MultipartFile file, String idInmueble);

    Resource getImage(String imageName, String idInmueble);

    void deleteImage(String imageName, String idInmueble);

    void deleteImages(String imageName, String idInmueble);
}
