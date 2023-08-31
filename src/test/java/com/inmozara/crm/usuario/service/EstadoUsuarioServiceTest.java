package com.inmozara.crm.usuario.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.usuario.model.EstadoUsuario;
import com.inmozara.crm.usuario.model.dto.EstadoUsuarioDTO;
import com.inmozara.crm.usuario.model.repository.EstadoUsuarioRepository;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstadoUsuarioServiceTest {

    @Mock
    private EstadoUsuarioRepository mockEstadoUsuarioRepository;
    @Mock
    private UsuarioRepository mockUsuarioRepository;

    @InjectMocks
    private EstadoUsuarioService estadoUsuarioServiceUnderTest;

    @BeforeEach
    void setUp() {
        estadoUsuarioServiceUnderTest.usuarioRepository = mockUsuarioRepository;
    }

    @Test
    void testSave() {
        // Setup
        final EstadoUsuarioDTO estadoUsuarioDTO = EstadoUsuarioDTO.builder().build();

        // Configure EstadoUsuarioRepository.save(...).
        final EstadoUsuario estadoUsuario = EstadoUsuario.builder()
                .idEstadoUsuario(0)
                .build();
        when(mockEstadoUsuarioRepository.save(any(EstadoUsuario.class))).thenReturn(estadoUsuario);

        // Run the test
        final EstadoUsuarioDTO result = estadoUsuarioServiceUnderTest.save(estadoUsuarioDTO);

        // Verify the results
    }

    @Test
    void testUpdate() {
        // Setup
        final EstadoUsuarioDTO estadoUsuarioDTO = EstadoUsuarioDTO.builder().build();

        // Configure EstadoUsuarioRepository.save(...).
        final EstadoUsuario estadoUsuario = EstadoUsuario.builder()
                .idEstadoUsuario(0)
                .build();
        when(mockEstadoUsuarioRepository.save(any(EstadoUsuario.class))).thenReturn(estadoUsuario);

        // Run the test
        final EstadoUsuarioDTO result = estadoUsuarioServiceUnderTest.update(estadoUsuarioDTO);

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        when(mockEstadoUsuarioRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = estadoUsuarioServiceUnderTest.delete(0);

        // Verify the results
        verify(mockUsuarioRepository).actualizarUsuariosPorEstado(any(EstadoUsuario.class), any(EstadoUsuario.class));
        verify(mockEstadoUsuarioRepository).deleteById(0);
    }

    @Test
    void testDelete_EstadoUsuarioRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockEstadoUsuarioRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> estadoUsuarioServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        // Configure EstadoUsuarioRepository.findById(...).
        final Optional<EstadoUsuario> estadoUsuario = Optional.of(EstadoUsuario.builder()
                .idEstadoUsuario(0)
                .build());
        when(mockEstadoUsuarioRepository.findById(0)).thenReturn(estadoUsuario);

        // Run the test
        final EstadoUsuarioDTO result = estadoUsuarioServiceUnderTest.find(0);

        // Verify the results
    }

    @Test
    void testFind_EstadoUsuarioRepositoryReturnsAbsent() {
        // Setup
        when(mockEstadoUsuarioRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> estadoUsuarioServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        // Configure EstadoUsuarioRepository.findAll(...).
        final List<EstadoUsuario> estadoUsuarios = List.of(EstadoUsuario.builder()
                .idEstadoUsuario(0)
                .build());
        when(mockEstadoUsuarioRepository.findAll()).thenReturn(estadoUsuarios);

        // Run the test
        final List<EstadoUsuarioDTO> result = estadoUsuarioServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_EstadoUsuarioRepositoryReturnsNoItems() {
        // Setup
        when(mockEstadoUsuarioRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> estadoUsuarioServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
