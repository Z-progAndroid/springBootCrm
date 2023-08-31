package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.EstadoCita;
import com.inmozara.crm.cita.model.dto.EstadoCitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.cita.model.repository.EstadoCitaRepository;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
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
class EstadoCitaServiceTest {

    @Mock
    private EstadoCitaRepository mockEstadoCitaRepository;
    @Mock
    private CitaRepository mockCitaRepository;

    @InjectMocks
    private EstadoCitaService estadoCitaServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final EstadoCitaDTO estadoCitaDTO = EstadoCitaDTO.builder().build();
        final EstadoCitaDTO expectedResult = EstadoCitaDTO.builder().build();

        // Configure EstadoCitaRepository.save(...).
        final EstadoCita estadoCita = EstadoCita.builder()
                .idEstadoCita(0)
                .build();
        when(mockEstadoCitaRepository.save(EstadoCita.builder()
                .idEstadoCita(0)
                .build())).thenReturn(estadoCita);

        // Run the test
        final EstadoCitaDTO result = estadoCitaServiceUnderTest.save(estadoCitaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final EstadoCitaDTO estadoCitaDTO = EstadoCitaDTO.builder().build();
        final EstadoCitaDTO expectedResult = EstadoCitaDTO.builder().build();

        // Configure EstadoCitaRepository.save(...).
        final EstadoCita estadoCita = EstadoCita.builder()
                .idEstadoCita(0)
                .build();
        when(mockEstadoCitaRepository.save(EstadoCita.builder()
                .idEstadoCita(0)
                .build())).thenReturn(estadoCita);

        // Run the test
        final EstadoCitaDTO result = estadoCitaServiceUnderTest.update(estadoCitaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockEstadoCitaRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = estadoCitaServiceUnderTest.delete(0);

        // Verify the results
        verify(mockCitaRepository).actualizarCitasPorEstado(EstadoCita.builder()
                .idEstadoCita(0)
                .build(), EstadoCita.builder()
                .idEstadoCita(0)
                .build());
        verify(mockEstadoCitaRepository).deleteById(0);
    }

    @Test
    void testDelete_EstadoCitaRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockEstadoCitaRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> estadoCitaServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final EstadoCitaDTO expectedResult = EstadoCitaDTO.builder().build();

        // Configure EstadoCitaRepository.findById(...).
        final Optional<EstadoCita> estadoCita = Optional.of(EstadoCita.builder()
                .idEstadoCita(0)
                .build());
        when(mockEstadoCitaRepository.findById(0)).thenReturn(estadoCita);

        // Run the test
        final EstadoCitaDTO result = estadoCitaServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_EstadoCitaRepositoryReturnsAbsent() {
        // Setup
        when(mockEstadoCitaRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> estadoCitaServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<EstadoCitaDTO> expectedResult = List.of(EstadoCitaDTO.builder().build());

        // Configure EstadoCitaRepository.findAll(...).
        final List<EstadoCita> estadoCitas = List.of(EstadoCita.builder()
                .idEstadoCita(0)
                .build());
        when(mockEstadoCitaRepository.findAll()).thenReturn(estadoCitas);

        // Run the test
        final List<EstadoCitaDTO> result = estadoCitaServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_EstadoCitaRepositoryReturnsNoItems() {
        // Setup
        when(mockEstadoCitaRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> estadoCitaServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
