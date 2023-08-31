package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.TipoCita;
import com.inmozara.crm.cita.model.dto.TipoCitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.cita.model.repository.TipoCitaRepository;
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
class TipoCitaServiceTest {

    @Mock
    private TipoCitaRepository mockTipoCitaRepository;
    @Mock
    private CitaRepository mockCitaRepository;

    @InjectMocks
    private TipoCitaService tipoCitaServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final TipoCitaDTO tipoCitaDTO = TipoCitaDTO.builder().build();
        final TipoCitaDTO expectedResult = TipoCitaDTO.builder().build();

        // Configure TipoCitaRepository.save(...).
        final TipoCita tipoCita = TipoCita.builder()
                .idTipoCita(0)
                .build();
        when(mockTipoCitaRepository.save(TipoCita.builder()
                .idTipoCita(0)
                .build())).thenReturn(tipoCita);

        // Run the test
        final TipoCitaDTO result = tipoCitaServiceUnderTest.save(tipoCitaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final TipoCitaDTO tipoCitaDTO = TipoCitaDTO.builder().build();
        final TipoCitaDTO expectedResult = TipoCitaDTO.builder().build();

        // Configure TipoCitaRepository.save(...).
        final TipoCita tipoCita = TipoCita.builder()
                .idTipoCita(0)
                .build();
        when(mockTipoCitaRepository.save(TipoCita.builder()
                .idTipoCita(0)
                .build())).thenReturn(tipoCita);

        // Run the test
        final TipoCitaDTO result = tipoCitaServiceUnderTest.update(tipoCitaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockTipoCitaRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = tipoCitaServiceUnderTest.delete(0);

        // Verify the results
        verify(mockCitaRepository).actualizarCitasPorTipo(TipoCita.builder()
                .idTipoCita(0)
                .build(), TipoCita.builder()
                .idTipoCita(0)
                .build());
        verify(mockTipoCitaRepository).deleteById(0);
    }

    @Test
    void testDelete_TipoCitaRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockTipoCitaRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> tipoCitaServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final TipoCitaDTO expectedResult = TipoCitaDTO.builder().build();

        // Configure TipoCitaRepository.findById(...).
        final Optional<TipoCita> tipoCita = Optional.of(TipoCita.builder()
                .idTipoCita(0)
                .build());
        when(mockTipoCitaRepository.findById(0)).thenReturn(tipoCita);

        // Run the test
        final TipoCitaDTO result = tipoCitaServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_TipoCitaRepositoryReturnsAbsent() {
        // Setup
        when(mockTipoCitaRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tipoCitaServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<TipoCitaDTO> expectedResult = List.of(TipoCitaDTO.builder().build());

        // Configure TipoCitaRepository.findAll(...).
        final List<TipoCita> tipoCitas = List.of(TipoCita.builder()
                .idTipoCita(0)
                .build());
        when(mockTipoCitaRepository.findAll()).thenReturn(tipoCitas);

        // Run the test
        final List<TipoCitaDTO> result = tipoCitaServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TipoCitaRepositoryReturnsNoItems() {
        // Setup
        when(mockTipoCitaRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> tipoCitaServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
