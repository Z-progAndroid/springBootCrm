package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.CitaSearch;
import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository mockCitaRepository;

    @InjectMocks
    private CitaService citaServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final CitaDTO citaDTO = CitaDTO.builder().build();
        final CitaDTO expectedResult = CitaDTO.builder().build();

        // Configure CitaRepository.save(...).
        final Cita cita = Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockCitaRepository.save(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build())).thenReturn(cita);

        // Run the test
        final CitaDTO result = citaServiceUnderTest.save(citaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final CitaDTO citaDTO = CitaDTO.builder().build();
        final CitaDTO expectedResult = CitaDTO.builder().build();

        // Configure CitaRepository.save(...).
        final Cita cita = Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockCitaRepository.save(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build())).thenReturn(cita);

        // Run the test
        final CitaDTO result = citaServiceUnderTest.update(citaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockCitaRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = citaServiceUnderTest.delete(0);

        // Verify the results
        verify(mockCitaRepository).deleteById(0);
    }

    @Test
    void testDelete_CitaRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockCitaRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final CitaDTO expectedResult = CitaDTO.builder().build();

        // Configure CitaRepository.findById(...).
        final Optional<Cita> cita = Optional.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.findById(0)).thenReturn(cita);

        // Run the test
        final CitaDTO result = citaServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_CitaRepositoryReturnsAbsent() {
        // Setup
        when(mockCitaRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<CitaDTO> expectedResult = List.of(CitaDTO.builder().build());

        // Configure CitaRepository.findAll(...).
        final List<Cita> citas = List.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.findAll()).thenReturn(citas);

        // Run the test
        final List<CitaDTO> result = citaServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_CitaRepositoryReturnsNoItems() {
        // Setup
        when(mockCitaRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAllByParams() {
        // Setup
        final CitaDTO citaDTO = CitaDTO.builder().build();
        final List<CitaDTO> expectedResult = List.of(CitaDTO.builder().build());

        // Configure CitaRepository.findAll(...).
        final List<Cita> citas = List.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.findAll(any(CitaSearch.class))).thenReturn(citas);

        // Run the test
        final List<CitaDTO> result = citaServiceUnderTest.findAllByParams(citaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllByParams_CitaRepositoryReturnsNoItems() {
        // Setup
        final CitaDTO citaDTO = CitaDTO.builder().build();
        when(mockCitaRepository.findAll(any(CitaSearch.class))).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.findAllByParams(citaDTO)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testCheckAvailability() {
        // Setup
        // Configure CitaRepository.countConflictingCitas(...).
        final List<Cita> citas = List.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.countConflictingCitas(LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0)).thenReturn(citas);

        // Run the test
        final MensajeDTO result = citaServiceUnderTest.checkAvailability("startDateStr", "endDateStr", 0);

        // Verify the results
    }

    @Test
    void testCheckAvailability_CitaRepositoryReturnsNoItems() {
        // Setup
        when(mockCitaRepository.countConflictingCitas(LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0), 0)).thenReturn(Collections.emptyList());

        // Run the test
        final MensajeDTO result = citaServiceUnderTest.checkAvailability("startDateStr", "endDateStr", 0);

        // Verify the results
    }

    @Test
    void testFindAllPendienteYActivas() {
        // Setup
        final List<CitaDTO> expectedResult = List.of(CitaDTO.builder().build());

        // Configure CitaRepository.findCitasPendientesYActivas(...).
        final List<Cita> citas = List.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.findCitasPendientesYActivas()).thenReturn(citas);

        // Run the test
        final List<CitaDTO> result = citaServiceUnderTest.findAllPendienteYActivas();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllPendienteYActivas_CitaRepositoryReturnsNoItems() {
        // Setup
        when(mockCitaRepository.findCitasPendientesYActivas()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.findAllPendienteYActivas())
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testObtenerCitasUsuarioNoEliminadas() {
        // Setup
        final List<CitaDTO> expectedResult = List.of(CitaDTO.builder().build());

        // Configure CitaRepository.findCitasByUserIdAndEstadoIn(...).
        final List<Cita> citas = List.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.findCitasByUserIdAndEstadoIn(0)).thenReturn(citas);

        // Run the test
        final List<CitaDTO> result = citaServiceUnderTest.obtenerCitasUsuarioNoEliminadas(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testObtenerCitasUsuarioNoEliminadas_CitaRepositoryReturnsNoItems() {
        // Setup
        when(mockCitaRepository.findCitasByUserIdAndEstadoIn(0)).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.obtenerCitasUsuarioNoEliminadas(0))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindCitasCreadasParaElAgente() {
        // Setup
        final List<CitaDTO> expectedResult = List.of(CitaDTO.builder().build());

        // Configure CitaRepository.findCitasCreadasParaElAgente(...).
        final List<Cita> citas = List.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.findCitasCreadasParaElAgente(0)).thenReturn(citas);

        // Run the test
        final List<CitaDTO> result = citaServiceUnderTest.findCitasCreadasParaElAgente(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindCitasCreadasParaElAgente_CitaRepositoryReturnsNoItems() {
        // Setup
        when(mockCitaRepository.findCitasCreadasParaElAgente(0)).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.findCitasCreadasParaElAgente(0))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testGenerarCitaPdf() {
        // Setup
        // Configure CitaRepository.findById(...).
        final Optional<Cita> cita = Optional.of(Cita.builder()
                .fechaInicio(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .fechaFin(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build());
        when(mockCitaRepository.findById(0)).thenReturn(cita);

        // Run the test
        final ByteArrayOutputStream result = citaServiceUnderTest.generarCitaPdf(0);

        // Verify the results
    }

    @Test
    void testGenerarCitaPdf_CitaRepositoryReturnsAbsent() {
        // Setup
        when(mockCitaRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> citaServiceUnderTest.generarCitaPdf(0)).isInstanceOf(RecursoNoEncontrado.class);
    }
}
