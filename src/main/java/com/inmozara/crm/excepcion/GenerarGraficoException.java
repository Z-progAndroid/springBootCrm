package com.inmozara.crm.excepcion;

import java.io.Serial;

public class GenerarGraficoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public GenerarGraficoException(String message) {
        super(message);
    }

    public GenerarGraficoException(String message, Throwable cause) {
        super(message, cause);
    }
}
