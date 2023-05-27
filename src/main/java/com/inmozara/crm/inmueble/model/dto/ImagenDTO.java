package com.inmozara.crm.inmueble.model.dto;

import com.inmozara.crm.inmueble.model.Inmueble;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImagenDTO {
        private int idImagen;
        private byte[] contenido;
        private Inmueble inmueble;
}
