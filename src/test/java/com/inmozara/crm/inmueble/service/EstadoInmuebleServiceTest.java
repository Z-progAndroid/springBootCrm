package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.dto.EstadoInmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.EstadoInmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EstadoInmuebleServiceTest {

    @Mock
    private EstadoInmuebleRepository mockEstadoInmuebleRepository;
    @Mock
    private InmuebleRepository mockInmuebleRepository;

    @InjectMocks
    private EstadoInmuebleService estadoInmuebleServiceUnderTest;

    @BeforeEach
    void setUp() {
        estadoInmuebleServiceUnderTest.estadoInmuebleRepository = mockEstadoInmuebleRepository;
    }

    @Test
    void testSave() {
        // Setup
        final EstadoInmuebleDTO estadoInmuebleDTO = EstadoInmuebleDTO.builder().build();
        final EstadoInmuebleDTO expectedResult = EstadoInmuebleDTO.builder().build();

        // Configure EstadoInmuebleRepository.save(...).
        final EstadoInmueble estadoInmueble = EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build();
        when(mockEstadoInmuebleRepository.save(EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build())).thenReturn(estadoInmueble);

        // Run the test
        final EstadoInmuebleDTO result = estadoInmuebleServiceUnderTest.save(estadoInmuebleDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final EstadoInmuebleDTO estadoInmuebleDTO = EstadoInmuebleDTO.builder().build();
        final EstadoInmuebleDTO expectedResult = EstadoInmuebleDTO.builder().build();

        // Configure EstadoInmuebleRepository.save(...).
        final EstadoInmueble estadoInmueble = EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build();
        when(mockEstadoInmuebleRepository.save(EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build())).thenReturn(estadoInmueble);

        // Run the test
        final EstadoInmuebleDTO result = estadoInmuebleServiceUnderTest.update(estadoInmuebleDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockEstadoInmuebleRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = estadoInmuebleServiceUnderTest.delete(0);

        // Verify the results
        verify(mockInmuebleRepository).actualizarInmueblesPorEstado(EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build(), EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build());
        verify(mockEstadoInmuebleRepository).deleteById(0);
    }

    @Test
    void testDelete_EstadoInmuebleRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockEstadoInmuebleRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> estadoInmuebleServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final EstadoInmuebleDTO expectedResult = EstadoInmuebleDTO.builder().build();

        // Configure EstadoInmuebleRepository.findById(...).
        final Optional<EstadoInmueble> estadoInmueble = Optional.of(EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build());
        when(mockEstadoInmuebleRepository.findById(0)).thenReturn(estadoInmueble);

        // Run the test
        final EstadoInmuebleDTO result = estadoInmuebleServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_EstadoInmuebleRepositoryReturnsAbsent() {
        // Setup
        when(mockEstadoInmuebleRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> estadoInmuebleServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<EstadoInmuebleDTO> expectedResult = List.of(EstadoInmuebleDTO.builder().build());

        // Configure EstadoInmuebleRepository.findAll(...).
        final List<EstadoInmueble> estadoInmuebles = List.of(EstadoInmueble.builder()
                .idEstadoInmueble(0)
                .build());
        when(mockEstadoInmuebleRepository.findAll()).thenReturn(estadoInmuebles);

        // Run the test
        final List<EstadoInmuebleDTO> result = estadoInmuebleServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_EstadoInmuebleRepositoryReturnsNoItems() {
        // Setup
        when(mockEstadoInmuebleRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> estadoInmuebleServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
