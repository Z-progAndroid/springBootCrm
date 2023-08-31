package com.inmozara.crm.tarea.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.tarea.model.EstadoTarea;
import com.inmozara.crm.tarea.model.dto.EstadoTareaDTO;
import com.inmozara.crm.tarea.model.repository.EstadoTareaRepository;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import com.inmozara.crm.tarea.model.service.EstadoTareaService;
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
class EstadoTareaServiceTest {

    @Mock
    private EstadoTareaRepository mockEstadoTareaRepository;
    @Mock
    private TareaRepository mockTareaRepository;

    @InjectMocks
    private EstadoTareaService estadoTareaServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final EstadoTareaDTO estadoTareaDTO = EstadoTareaDTO.builder().build();
        final EstadoTareaDTO expectedResult = EstadoTareaDTO.builder().build();

        // Configure EstadoTareaRepository.save(...).
        final EstadoTarea estadoTarea = EstadoTarea.builder()
                .idEstadoTarea(0)
                .build();
        when(mockEstadoTareaRepository.save(EstadoTarea.builder()
                .idEstadoTarea(0)
                .build())).thenReturn(estadoTarea);

        // Run the test
        final EstadoTareaDTO result = estadoTareaServiceUnderTest.save(estadoTareaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final EstadoTareaDTO estadoTareaDTO = EstadoTareaDTO.builder().build();
        final EstadoTareaDTO expectedResult = EstadoTareaDTO.builder().build();

        // Configure EstadoTareaRepository.save(...).
        final EstadoTarea estadoTarea = EstadoTarea.builder()
                .idEstadoTarea(0)
                .build();
        when(mockEstadoTareaRepository.save(EstadoTarea.builder()
                .idEstadoTarea(0)
                .build())).thenReturn(estadoTarea);

        // Run the test
        final EstadoTareaDTO result = estadoTareaServiceUnderTest.update(estadoTareaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockEstadoTareaRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = estadoTareaServiceUnderTest.delete(0);

        // Verify the results
        verify(mockTareaRepository).actualizarTareasPorEstado(EstadoTarea.builder()
                .idEstadoTarea(0)
                .build(), EstadoTarea.builder()
                .idEstadoTarea(0)
                .build());
        verify(mockEstadoTareaRepository).deleteById(0);
    }

    @Test
    void testDelete_EstadoTareaRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockEstadoTareaRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> estadoTareaServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final EstadoTareaDTO expectedResult = EstadoTareaDTO.builder().build();

        // Configure EstadoTareaRepository.findById(...).
        final Optional<EstadoTarea> estadoTarea = Optional.of(EstadoTarea.builder()
                .idEstadoTarea(0)
                .build());
        when(mockEstadoTareaRepository.findById(0)).thenReturn(estadoTarea);

        // Run the test
        final EstadoTareaDTO result = estadoTareaServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_EstadoTareaRepositoryReturnsAbsent() {
        // Setup
        when(mockEstadoTareaRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> estadoTareaServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<EstadoTareaDTO> expectedResult = List.of(EstadoTareaDTO.builder().build());

        // Configure EstadoTareaRepository.findAll(...).
        final List<EstadoTarea> estadoTareas = List.of(EstadoTarea.builder()
                .idEstadoTarea(0)
                .build());
        when(mockEstadoTareaRepository.findAll()).thenReturn(estadoTareas);

        // Run the test
        final List<EstadoTareaDTO> result = estadoTareaServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_EstadoTareaRepositoryReturnsNoItems() {
        // Setup
        when(mockEstadoTareaRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> estadoTareaServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
