package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.EstadoContrato;
import com.inmozara.crm.contrato.model.dto.EstadoContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.EstadoContratoRepository;
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
class EstadoContratoServiceTest {

    @Mock
    private EstadoContratoRepository mockEstadoContratoRepository;
    @Mock
    private ContratoRepository mockContratoRepository;

    @InjectMocks
    private EstadoContratoService estadoContratoServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final EstadoContratoDTO estadoContratoDTO = EstadoContratoDTO.builder().build();
        final EstadoContratoDTO expectedResult = EstadoContratoDTO.builder().build();

        // Configure EstadoContratoRepository.save(...).
        final EstadoContrato estadoContrato = EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build();
        when(mockEstadoContratoRepository.save(EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build())).thenReturn(estadoContrato);

        // Run the test
        final EstadoContratoDTO result = estadoContratoServiceUnderTest.save(estadoContratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final EstadoContratoDTO estadoContratoDTO = EstadoContratoDTO.builder().build();
        final EstadoContratoDTO expectedResult = EstadoContratoDTO.builder().build();

        // Configure EstadoContratoRepository.save(...).
        final EstadoContrato estadoContrato = EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build();
        when(mockEstadoContratoRepository.save(EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build())).thenReturn(estadoContrato);

        // Run the test
        final EstadoContratoDTO result = estadoContratoServiceUnderTest.update(estadoContratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockEstadoContratoRepository.existsById(0L)).thenReturn(true);

        // Run the test
        final MensajeDTO result = estadoContratoServiceUnderTest.delete(0L);

        // Verify the results
        verify(mockContratoRepository).actualizarContratosPorEstado(EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build(), EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build());
        verify(mockEstadoContratoRepository).deleteById(0L);
    }

    @Test
    void testDelete_EstadoContratoRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockEstadoContratoRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> estadoContratoServiceUnderTest.delete(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final EstadoContratoDTO expectedResult = EstadoContratoDTO.builder().build();

        // Configure EstadoContratoRepository.findById(...).
        final Optional<EstadoContrato> estadoContrato = Optional.of(EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build());
        when(mockEstadoContratoRepository.findById(0L)).thenReturn(estadoContrato);

        // Run the test
        final EstadoContratoDTO result = estadoContratoServiceUnderTest.find(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_EstadoContratoRepositoryReturnsAbsent() {
        // Setup
        when(mockEstadoContratoRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> estadoContratoServiceUnderTest.find(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<EstadoContratoDTO> expectedResult = List.of(EstadoContratoDTO.builder().build());

        // Configure EstadoContratoRepository.findAll(...).
        final List<EstadoContrato> estadoContratoes = List.of(EstadoContrato.builder()
                .idEstadoContrato(0L)
                .build());
        when(mockEstadoContratoRepository.findAll()).thenReturn(estadoContratoes);

        // Run the test
        final List<EstadoContratoDTO> result = estadoContratoServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_EstadoContratoRepositoryReturnsNoItems() {
        // Setup
        when(mockEstadoContratoRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> estadoContratoServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
