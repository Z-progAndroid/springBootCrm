package com.inmozara.crm.excepcion;

import java.io.Serial;

public class RecursoNoEncontrado extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public RecursoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
