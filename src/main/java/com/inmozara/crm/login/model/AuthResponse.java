package com.inmozara.crm.login.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private int idUsuario;
    private String token;
    private String username;
    private String rol;
    private String estadoUsuario;
}