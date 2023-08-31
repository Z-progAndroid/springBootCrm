package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Barrio;
import com.inmozara.crm.inmueble.model.Municipio;
import com.inmozara.crm.inmueble.model.dto.BarrioDTO;
import com.inmozara.crm.inmueble.model.repository.BarrioRepository;
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
class BarrioServiceTest {

    @Mock
    private BarrioRepository mockBarrioRepository;

    @InjectMocks
    private BarrioService barrioServiceUnderTest;

    @Test
    void testFindAllByMunicipio() {
        // Setup
        final List<BarrioDTO> expectedResult = List.of(BarrioDTO.builder().build());
        when(mockBarrioRepository.findAllByMunicipio(Municipio.builder()
                .idMunicipio(0)
                .build())).thenReturn(List.of(Barrio.builder().build()));

        // Run the test
        final List<BarrioDTO> result = barrioServiceUnderTest.findAllByMunicipio(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllByMunicipio_BarrioRepositoryReturnsNoItems() {
        // Setup
        when(mockBarrioRepository.findAllByMunicipio(Municipio.builder()
                .idMunicipio(0)
                .build())).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> barrioServiceUnderTest.findAllByMunicipio(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testSave() {
        // Setup
        final BarrioDTO barrioDTO = BarrioDTO.builder().build();
        final BarrioDTO expectedResult = BarrioDTO.builder().build();
        when(mockBarrioRepository.save(Barrio.builder().build())).thenReturn(Barrio.builder().build());

        // Run the test
        final BarrioDTO result = barrioServiceUnderTest.save(barrioDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final BarrioDTO barrioDTO = BarrioDTO.builder().build();
        final BarrioDTO expectedResult = BarrioDTO.builder().build();
        when(mockBarrioRepository.save(Barrio.builder().build())).thenReturn(Barrio.builder().build());

        // Run the test
        final BarrioDTO result = barrioServiceUnderTest.update(barrioDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockBarrioRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = barrioServiceUnderTest.delete(0);

        // Verify the results
        verify(mockBarrioRepository).deleteById(0);
    }

    @Test
    void testDelete_BarrioRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockBarrioRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> barrioServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final BarrioDTO expectedResult = BarrioDTO.builder().build();
        when(mockBarrioRepository.findById(0)).thenReturn(Optional.of(Barrio.builder().build()));

        // Run the test
        final BarrioDTO result = barrioServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_BarrioRepositoryReturnsAbsent() {
        // Setup
        when(mockBarrioRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> barrioServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<BarrioDTO> expectedResult = List.of(BarrioDTO.builder().build());
        when(mockBarrioRepository.findAll()).thenReturn(List.of(Barrio.builder().build()));

        // Run the test
        final List<BarrioDTO> result = barrioServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_BarrioRepositoryReturnsNoItems() {
        // Setup
        when(mockBarrioRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> barrioServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
