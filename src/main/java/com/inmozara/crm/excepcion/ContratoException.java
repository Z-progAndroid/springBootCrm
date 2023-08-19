package com.inmozara.crm.excepcion;

import java.io.Serial;

public class ContratoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ContratoException(String message) {
        super(message);
    }
}
