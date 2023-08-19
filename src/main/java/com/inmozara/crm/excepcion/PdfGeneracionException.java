package com.inmozara.crm.excepcion;

import java.io.Serial;

public class PdfGeneracionException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public PdfGeneracionException(String message) {
        super(message);
    }

    public PdfGeneracionException(String message, Throwable cause) {
        super(message, cause);
    }
}
