package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.TipoPago;
import com.inmozara.crm.contrato.model.dto.TipoPagoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.TipoPagoRepository;
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
class TipoPagoServiceTest {

    @Mock
    private TipoPagoRepository mockTipoPagoRepository;
    @Mock
    private ContratoRepository mockContratoRepository;

    @InjectMocks
    private TipoPagoService tipoPagoServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final TipoPagoDTO tipoPagoDTO = TipoPagoDTO.builder().build();
        final TipoPagoDTO expectedResult = TipoPagoDTO.builder().build();

        // Configure TipoPagoRepository.save(...).
        final TipoPago tipoPago = TipoPago.builder()
                .idTipoPago(0L)
                .build();
        when(mockTipoPagoRepository.save(TipoPago.builder()
                .idTipoPago(0L)
                .build())).thenReturn(tipoPago);

        // Run the test
        final TipoPagoDTO result = tipoPagoServiceUnderTest.save(tipoPagoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final TipoPagoDTO tipoPagoDTO = TipoPagoDTO.builder().build();
        final TipoPagoDTO expectedResult = TipoPagoDTO.builder().build();

        // Configure TipoPagoRepository.save(...).
        final TipoPago tipoPago = TipoPago.builder()
                .idTipoPago(0L)
                .build();
        when(mockTipoPagoRepository.save(TipoPago.builder()
                .idTipoPago(0L)
                .build())).thenReturn(tipoPago);

        // Run the test
        final TipoPagoDTO result = tipoPagoServiceUnderTest.update(tipoPagoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockTipoPagoRepository.existsById(0L)).thenReturn(true);

        // Run the test
        final MensajeDTO result = tipoPagoServiceUnderTest.delete(0L);

        // Verify the results
        verify(mockContratoRepository).actualizarContratosPorTipoPago(TipoPago.builder()
                .idTipoPago(0L)
                .build(), TipoPago.builder()
                .idTipoPago(0L)
                .build());
        verify(mockTipoPagoRepository).deleteById(0L);
    }

    @Test
    void testDelete_TipoPagoRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockTipoPagoRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> tipoPagoServiceUnderTest.delete(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final TipoPagoDTO expectedResult = TipoPagoDTO.builder().build();

        // Configure TipoPagoRepository.findById(...).
        final Optional<TipoPago> tipoPago = Optional.of(TipoPago.builder()
                .idTipoPago(0L)
                .build());
        when(mockTipoPagoRepository.findById(0L)).thenReturn(tipoPago);

        // Run the test
        final TipoPagoDTO result = tipoPagoServiceUnderTest.find(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_TipoPagoRepositoryReturnsAbsent() {
        // Setup
        when(mockTipoPagoRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tipoPagoServiceUnderTest.find(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<TipoPagoDTO> expectedResult = List.of(TipoPagoDTO.builder().build());

        // Configure TipoPagoRepository.findAll(...).
        final List<TipoPago> tipoPagos = List.of(TipoPago.builder()
                .idTipoPago(0L)
                .build());
        when(mockTipoPagoRepository.findAll()).thenReturn(tipoPagos);

        // Run the test
        final List<TipoPagoDTO> result = tipoPagoServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TipoPagoRepositoryReturnsNoItems() {
        // Setup
        when(mockTipoPagoRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> tipoPagoServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
