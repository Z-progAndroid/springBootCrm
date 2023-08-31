package com.inmozara.crm.usuario.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import com.inmozara.crm.usuario.model.search.UserSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository mockUsuarioRepository;
    @Mock
    private EstadoUsuarioService mockEstadoUsuarioService;
    @Mock
    private RolService mockRolService;
    @Mock
    private TareaRepository mockTareaRepository;
    @Mock
    private ContratoRepository mockContratoRepository;
    @Mock
    private InmuebleRepository mockInmuebleRepository;

    @InjectMocks
    private UsuarioService usuarioServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();

        // Configure UsuarioRepository.save(...).
        final Usuario usuario = Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build();
        when(mockUsuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Run the test
        final UsuarioDTO result = usuarioServiceUnderTest.save(usuarioDTO);

        // Verify the results
    }

    @Test
    void testUpdate() {
        // Setup
        final UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();

        // Configure UsuarioRepository.save(...).
        final Usuario usuario = Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build();
        when(mockUsuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        // Run the test
        final UsuarioDTO result = usuarioServiceUnderTest.update(usuarioDTO);

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        when(mockUsuarioRepository.existsById(0)).thenReturn(true);

        // Configure UsuarioRepository.obtenerUsuarioPorDefecto(...).
        final Optional<Usuario> usuario = Optional.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build());
        when(mockUsuarioRepository.obtenerUsuarioPorDefecto()).thenReturn(usuario);

        // Run the test
        final MensajeDTO result = usuarioServiceUnderTest.delete(0);

        // Verify the results
        verify(mockTareaRepository).actualizarUsuarioTarea(any(Usuario.class), any(Usuario.class));
        verify(mockInmuebleRepository).actualizarAgenteInmuebles(any(Usuario.class), any(Usuario.class));
        verify(mockUsuarioRepository).deleteById(0);
    }

    @Test
    void testDelete_UsuarioRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockUsuarioRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> usuarioServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testDelete_UsuarioRepositoryObtenerUsuarioPorDefectoReturnsAbsent() {
        // Setup
        when(mockUsuarioRepository.existsById(0)).thenReturn(true);
        when(mockUsuarioRepository.obtenerUsuarioPorDefecto()).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> usuarioServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        // Configure UsuarioRepository.findById(...).
        final Optional<Usuario> usuario = Optional.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build());
        when(mockUsuarioRepository.findById(0)).thenReturn(usuario);

        // Run the test
        final UsuarioDTO result = usuarioServiceUnderTest.find(0);

        // Verify the results
    }

    @Test
    void testFind_UsuarioRepositoryReturnsAbsent() {
        // Setup
        when(mockUsuarioRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> usuarioServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        // Configure UsuarioRepository.findAll(...).
        final List<Usuario> usuarios = List.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build());
        when(mockUsuarioRepository.findAll()).thenReturn(usuarios);

        // Run the test
        final List<UsuarioDTO> result = usuarioServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_UsuarioRepositoryReturnsNoItems() {
        // Setup
        when(mockUsuarioRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> usuarioServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAllBYParams() {
        // Setup
        final UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();

        // Configure UsuarioRepository.findAll(...).
        final List<Usuario> usuarios = List.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build());
        when(mockUsuarioRepository.findAll(UserSearch.builder()
                .usuario(Usuario.builder()
                        .idUsuario(0)
                        .nombre("nombre")
                        .build())
                .build())).thenReturn(usuarios);

        // Run the test
        final List<UsuarioDTO> result = usuarioServiceUnderTest.findAllBYParams(usuarioDTO);

        // Verify the results
    }

    @Test
    void testFindAllBYParams_UsuarioRepositoryReturnsNoItems() {
        // Setup
        final UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();
        when(mockUsuarioRepository.findAll(UserSearch.builder()
                .usuario(Usuario.builder()
                        .idUsuario(0)
                        .nombre("nombre")
                        .build())
                .build())).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> usuarioServiceUnderTest.findAllBYParams(usuarioDTO))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAllUserAdminORAgente() {
        // Setup
        // Configure UsuarioRepository.obtenerAgentesYAdministradores(...).
        final List<Usuario> usuarios = List.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build());
        when(mockUsuarioRepository.obtenerAgentesYAdministradores()).thenReturn(usuarios);

        // Run the test
        final List<UsuarioDTO> result = usuarioServiceUnderTest.findAllUserAdminORAgente();

        // Verify the results
    }

    @Test
    void testFindAllUserAdminORAgente_UsuarioRepositoryReturnsNoItems() {
        // Setup
        when(mockUsuarioRepository.obtenerAgentesYAdministradores()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> usuarioServiceUnderTest.findAllUserAdminORAgente())
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAllUsuarios() {
        // Setup
        // Configure UsuarioRepository.obtenerUsuarios(...).
        final List<Usuario> usuarios = List.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build());
        when(mockUsuarioRepository.obtenerUsuarios()).thenReturn(usuarios);

        // Run the test
        final List<UsuarioDTO> result = usuarioServiceUnderTest.findAllUsuarios();

        // Verify the results
    }

    @Test
    void testFindAllUsuarios_UsuarioRepositoryReturnsNoItems() {
        // Setup
        when(mockUsuarioRepository.obtenerUsuarios()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> usuarioServiceUnderTest.findAllUsuarios()).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindByUsername() {
        // Setup
        // Configure UsuarioRepository.findByUsername(...).
        final Optional<Usuario> usuario = Optional.of(Usuario.builder()
                .idUsuario(0)
                .nombre("nombre")
                .build());
        when(mockUsuarioRepository.findByUsername("username")).thenReturn(usuario);

        // Run the test
        final Optional<Usuario> result = usuarioServiceUnderTest.findByUsername("username");

        // Verify the results
    }

    @Test
    void testFindByUsername_UsuarioRepositoryReturnsAbsent() {
        // Setup
        when(mockUsuarioRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Run the test
        final Optional<Usuario> result = usuarioServiceUnderTest.findByUsername("username");

        // Verify the results
        assertThat(result).isEmpty();
    }
}
