package com.inmozara.crm.tarea.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.tarea.model.Tarea;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import com.inmozara.crm.tarea.model.search.TareasSearch;
import com.inmozara.crm.tarea.model.service.TareaService;
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
class TareaServiceTest {

    @Mock
    private TareaRepository mockTareaRepository;

    @InjectMocks
    private TareaService tareaServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final TareaDTO tareaDTO = TareaDTO.builder().build();
        final TareaDTO expectedResult = TareaDTO.builder().build();
        when(mockTareaRepository.save(Tarea.builder().build())).thenReturn(Tarea.builder().build());

        // Run the test
        final TareaDTO result = tareaServiceUnderTest.save(tareaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final TareaDTO tareaDTO = TareaDTO.builder().build();
        final TareaDTO expectedResult = TareaDTO.builder().build();
        when(mockTareaRepository.save(Tarea.builder().build())).thenReturn(Tarea.builder().build());

        // Run the test
        final TareaDTO result = tareaServiceUnderTest.update(tareaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockTareaRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = tareaServiceUnderTest.delete(0);

        // Verify the results
        verify(mockTareaRepository).deleteById(0);
    }

    @Test
    void testDelete_TareaRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockTareaRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> tareaServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final TareaDTO expectedResult = TareaDTO.builder().build();
        when(mockTareaRepository.findById(0)).thenReturn(Optional.of(Tarea.builder().build()));

        // Run the test
        final TareaDTO result = tareaServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_TareaRepositoryReturnsAbsent() {
        // Setup
        when(mockTareaRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tareaServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<TareaDTO> expectedResult = List.of(TareaDTO.builder().build());
        when(mockTareaRepository.findAll()).thenReturn(List.of(Tarea.builder().build()));

        // Run the test
        final List<TareaDTO> result = tareaServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TareaRepositoryReturnsNoItems() {
        // Setup
        when(mockTareaRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> tareaServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAllByParams() {
        // Setup
        final TareaDTO tareaDTO = TareaDTO.builder().build();
        final List<TareaDTO> expectedResult = List.of(TareaDTO.builder().build());
        when(mockTareaRepository.findAll(any(TareasSearch.class))).thenReturn(List.of(Tarea.builder().build()));

        // Run the test
        final List<TareaDTO> result = tareaServiceUnderTest.findAllByParams(tareaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllByParams_TareaRepositoryReturnsNoItems() {
        // Setup
        final TareaDTO tareaDTO = TareaDTO.builder().build();
        when(mockTareaRepository.findAll(any(TareasSearch.class))).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> tareaServiceUnderTest.findAllByParams(tareaDTO))
                .isInstanceOf(RecursoNoEncontrado.class);
    }
}
