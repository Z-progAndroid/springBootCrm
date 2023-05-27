package com.inmozara.crm.inmueble.exception;

import java.io.Serial;

public class CargarImagenException  extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CargarImagenException(String mensaje) {
        super(mensaje);
    }
}
