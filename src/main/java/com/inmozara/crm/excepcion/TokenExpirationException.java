package com.inmozara.crm.excepcion;

import java.io.Serial;

public class TokenExpirationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public TokenExpirationException(String message) {
        super(message);
    }
}
