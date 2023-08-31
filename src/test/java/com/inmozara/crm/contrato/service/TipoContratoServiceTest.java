package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.TipoContratoRepository;
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
class TipoContratoServiceTest {

    @Mock
    private TipoContratoRepository mockTipoContratoRepository;
    @Mock
    private ContratoRepository mockContratoRepository;

    @InjectMocks
    private TipoContratoService tipoContratoServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final TipoContratoDTO tipoContratoDTO = TipoContratoDTO.builder().build();
        final TipoContratoDTO expectedResult = TipoContratoDTO.builder().build();

        // Configure TipoContratoRepository.save(...).
        final TipoContrato tipoContrato = TipoContrato.builder()
                .idTipoContrato(0L)
                .build();
        when(mockTipoContratoRepository.save(TipoContrato.builder()
                .idTipoContrato(0L)
                .build())).thenReturn(tipoContrato);

        // Run the test
        final TipoContratoDTO result = tipoContratoServiceUnderTest.save(tipoContratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final TipoContratoDTO tipoContratoDTO = TipoContratoDTO.builder().build();
        final TipoContratoDTO expectedResult = TipoContratoDTO.builder().build();

        // Configure TipoContratoRepository.save(...).
        final TipoContrato tipoContrato = TipoContrato.builder()
                .idTipoContrato(0L)
                .build();
        when(mockTipoContratoRepository.save(TipoContrato.builder()
                .idTipoContrato(0L)
                .build())).thenReturn(tipoContrato);

        // Run the test
        final TipoContratoDTO result = tipoContratoServiceUnderTest.update(tipoContratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockTipoContratoRepository.existsById(0L)).thenReturn(true);

        // Run the test
        final MensajeDTO result = tipoContratoServiceUnderTest.delete(0L);

        // Verify the results
        verify(mockContratoRepository).actualizarContratosPorTipo(TipoContrato.builder()
                .idTipoContrato(0L)
                .build(), TipoContrato.builder()
                .idTipoContrato(0L)
                .build());
        verify(mockTipoContratoRepository).deleteById(0L);
    }

    @Test
    void testDelete_TipoContratoRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockTipoContratoRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> tipoContratoServiceUnderTest.delete(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final TipoContratoDTO expectedResult = TipoContratoDTO.builder().build();

        // Configure TipoContratoRepository.findById(...).
        final Optional<TipoContrato> tipoContrato = Optional.of(TipoContrato.builder()
                .idTipoContrato(0L)
                .build());
        when(mockTipoContratoRepository.findById(0L)).thenReturn(tipoContrato);

        // Run the test
        final TipoContratoDTO result = tipoContratoServiceUnderTest.find(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_TipoContratoRepositoryReturnsAbsent() {
        // Setup
        when(mockTipoContratoRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tipoContratoServiceUnderTest.find(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<TipoContratoDTO> expectedResult = List.of(TipoContratoDTO.builder().build());

        // Configure TipoContratoRepository.findAll(...).
        final List<TipoContrato> tipoContratoes = List.of(TipoContrato.builder()
                .idTipoContrato(0L)
                .build());
        when(mockTipoContratoRepository.findAll()).thenReturn(tipoContratoes);

        // Run the test
        final List<TipoContratoDTO> result = tipoContratoServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TipoContratoRepositoryReturnsNoItems() {
        // Setup
        when(mockTipoContratoRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> tipoContratoServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
