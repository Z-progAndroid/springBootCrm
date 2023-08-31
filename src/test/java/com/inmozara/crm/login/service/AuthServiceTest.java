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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private AuthenticationManager mockAuthenticationManager;
    @Mock
    private UsuarioRepository mockUserRepository;
    @Mock
    private JwtService mockJwtService;

    private AuthService authServiceUnderTest;

    @BeforeEach
    void setUp() {
        authServiceUnderTest = new AuthService(mockPasswordEncoder, mockAuthenticationManager);
        authServiceUnderTest.userRepository = mockUserRepository;
        authServiceUnderTest.jwtService = mockJwtService;
    }

    @Test
    void testLogin() {
        // Setup
        final LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();
        final AuthResponse expectedResult = AuthResponse.builder()
                .idUsuario(0)
                .token("token")
                .username("username")
                .rol("rol")
                .estadoUsuario("estadoUsuario")
                .build();

        // Configure UsuarioRepository.findByUsername(...).
        final Optional<Usuario> usuario = Optional.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .apellido("apellido")
                .email("email")
                .username("username")
                .password("password")
                .telefono("telefono")
                .direccion("direccion")
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .estadoUsuario(EstadoUsuario.builder()
                        .idEstadoUsuario(0)
                        .estadoUsuario("estadoUsuario")
                        .build())
                .rol(Rol.builder()
                        .idRol(0)
                        .rol("rol")
                        .build())
                .build());
        when(mockUserRepository.findByUsername("username")).thenReturn(usuario);

        when(mockJwtService.getToken(any(UserDetails.class))).thenReturn("token");

        // Run the test
        final AuthResponse result = authServiceUnderTest.login(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockAuthenticationManager).authenticate(new TestingAuthenticationToken("user", "pass", "ROLE_USER"));
    }

    @Test
    void testLogin_AuthenticationManagerThrowsAuthenticationException() {
        // Setup
        final LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();
        when(mockAuthenticationManager.authenticate(
                new TestingAuthenticationToken("user", "pass", "ROLE_USER"))).thenThrow(AuthenticationException.class);

        // Run the test
        assertThatThrownBy(() -> authServiceUnderTest.login(request)).isInstanceOf(AuthenticationException.class);
    }

    @Test
    void testLogin_UsuarioRepositoryReturnsAbsent() {
        // Setup
        final LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();
        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> authServiceUnderTest.login(request)).isInstanceOf(NoSuchElementException.class);
        verify(mockAuthenticationManager).authenticate(new TestingAuthenticationToken("user", "pass", "ROLE_USER"));
    }

    @Test
    void testRegister() {
        // Setup
        final RegisterRequest request = RegisterRequest.builder()
                .nombre("nombre")
                .apellido("apellido")
                .email("email")
                .username("username")
                .password("password")
                .telefono("telefono")
                .direccion("direccion")
                .build();
        final AuthResponse expectedResult = AuthResponse.builder()
                .idUsuario(0)
                .token("token")
                .username("username")
                .rol("rol")
                .estadoUsuario("estadoUsuario")
                .build();
        when(mockPasswordEncoder.encode("rawPassword")).thenReturn("password");

        // Configure UsuarioRepository.save(...).
        final Usuario usuario = Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .apellido("apellido")
                .email("email")
                .username("username")
                .password("password")
                .telefono("telefono")
                .direccion("direccion")
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .estadoUsuario(EstadoUsuario.builder()
                        .idEstadoUsuario(0)
                        .estadoUsuario("estadoUsuario")
                        .build())
                .rol(Rol.builder()
                        .idRol(0)
                        .rol("rol")
                        .build())
                .build();
        when(mockUserRepository.save(any(Usuario.class))).thenReturn(usuario);

        when(mockJwtService.getToken(any(UserDetails.class))).thenReturn("token");

        // Run the test
        final AuthResponse result = authServiceUnderTest.register(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testForgotPassword() {
        // Setup
        final ForgotPasswordRequest request = ForgotPasswordRequest.builder()
                .username("username")
                .password("password")
                .build();
        final AuthResponse expectedResult = AuthResponse.builder()
                .idUsuario(0)
                .token("token")
                .username("username")
                .rol("rol")
                .estadoUsuario("estadoUsuario")
                .build();

        // Configure UsuarioRepository.findByUsername(...).
        final Optional<Usuario> usuario = Optional.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .apellido("apellido")
                .email("email")
                .username("username")
                .password("password")
                .telefono("telefono")
                .direccion("direccion")
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .estadoUsuario(EstadoUsuario.builder()
                        .idEstadoUsuario(0)
                        .estadoUsuario("estadoUsuario")
                        .build())
                .rol(Rol.builder()
                        .idRol(0)
                        .rol("rol")
                        .build())
                .build());
        when(mockUserRepository.findByUsername("username")).thenReturn(usuario);

        when(mockPasswordEncoder.encode("password")).thenReturn("password");

        // Configure UsuarioRepository.save(...).
        final Usuario usuario1 = Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .apellido("apellido")
                .email("email")
                .username("username")
                .password("password")
                .telefono("telefono")
                .direccion("direccion")
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .estadoUsuario(EstadoUsuario.builder()
                        .idEstadoUsuario(0)
                        .estadoUsuario("estadoUsuario")
                        .build())
                .rol(Rol.builder()
                        .idRol(0)
                        .rol("rol")
                        .build())
                .build();
        when(mockUserRepository.save(any(Usuario.class))).thenReturn(usuario1);

        when(mockJwtService.getToken(any(UserDetails.class))).thenReturn("token");

        // Run the test
        final AuthResponse result = authServiceUnderTest.forgotPassword(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testForgotPassword_UsuarioRepositoryFindByUsernameReturnsAbsent() {
        // Setup
        final ForgotPasswordRequest request = ForgotPasswordRequest.builder()
                .username("username")
                .password("password")
                .build();
        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> authServiceUnderTest.forgotPassword(request)).isInstanceOf(RecursoNoEncontrado.class);
    }
}
