package com.inmozara.crm.excepcion;

import java.io.Serial;

public class GuardarImagenException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public GuardarImagenException(String mensaje) {
        super(mensaje);
    }
}
