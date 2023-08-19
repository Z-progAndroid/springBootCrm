package com.inmozara.crm.excepcion;

import java.io.Serial;

public class ExcelGenerationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ExcelGenerationException(String message) {
        super(message);
    }

    public ExcelGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
