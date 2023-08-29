package com.inmozara.crm.login.service;

import com.inmozara.crm.config.security.jwt.JwtService;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.login.model.AuthResponse;
import com.inmozara.crm.login.model.ForgotPasswordRequest;
import com.inmozara.crm.login.model.LoginRequest;
import com.inmozara.crm.login.model.RegisterRequest;
import com.inmozara.crm.usuario.model.EstadoUsuario;
import com.inmozara.crm.usuario.model.Rol;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import com.inmozara.crm.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    UsuarioRepository userRepository;
    @Autowired
    JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .idUsuario(Integer.parseInt(user.getAuthorities().stream().skip(2).findFirst().get().getAuthority()))
                .token(token)
                .username(user.getUsername())
                .rol(user.getAuthorities().stream().findFirst().get().getAuthority())
                .estadoUsuario(user.getAuthorities().stream().skip(1).findFirst().get().getAuthority())
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Usuario usuario = new Usuario();
        Optional.ofNullable(request).map(RegisterRequest::getNombre).ifPresent(usuario::setNombre);
        Optional.ofNullable(request).map(RegisterRequest::getApellido).ifPresent(usuario::setApellido);
        Optional.ofNullable(request).map(RegisterRequest::getEmail).ifPresent(usuario::setEmail);
        Optional.ofNullable(request).map(RegisterRequest::getUsername).ifPresent(usuario::setUsername);
        Optional.ofNullable(request).map(RegisterRequest::getPassword).ifPresent(s -> usuario.setPassword(passwordEncoder.encode(s)));
        Optional.ofNullable(request).map(RegisterRequest::getTelefono).ifPresent(usuario::setTelefono);
        Optional.ofNullable(request).map(RegisterRequest::getDireccion).ifPresent(usuario::setDireccion);
        usuario.setFechaCreacion(new Date());
        usuario.setFechaModificacion(new Date());
        usuario.setModificado(Constantes.REGISTRO_APP);
        usuario.setEstadoUsuario(EstadoUsuario.builder().idEstadoUsuario(1).build());
        usuario.setRol(Rol.builder().idRol(1).build());
        Usuario usuarioGuardado = userRepository.save(usuario);
        return AuthResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .token(jwtService.getToken(usuario))
                .username(usuario.getUsername())
                .rol(usuarioGuardado.getRol().getRol())
                .estadoUsuario(usuarioGuardado.getEstadoUsuario().getEstadoUsuario())
                .build();
    }

    public AuthResponse forgotPassword(ForgotPasswordRequest request) {
        Usuario usuario = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RecursoNoEncontrado("Usuario no encontrado por el nombre de usuario"));
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        Usuario usuarioGuardado = userRepository.save(usuario);
        return AuthResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .token(jwtService.getToken(usuarioGuardado))
                .username(usuario.getUsername())
                .rol(usuarioGuardado.getRol().getRol())
                .estadoUsuario(usuarioGuardado.getEstadoUsuario().getEstadoUsuario())
                .build();
    }
}