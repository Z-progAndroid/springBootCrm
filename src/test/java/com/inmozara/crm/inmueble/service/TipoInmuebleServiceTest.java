package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.TipoInmueble;
import com.inmozara.crm.inmueble.model.dto.TipoInmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.TipoInmuebleRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class TipoInmuebleServiceTest {

    @Mock
    private TipoInmuebleRespository mockTipoInmuebleRespository;
    @Mock
    private InmuebleRepository mockInmuebleRepository;

    private TipoInmuebleService tipoInmuebleServiceUnderTest;

    @BeforeEach
    void setUp() {
        tipoInmuebleServiceUnderTest = new TipoInmuebleService();
        tipoInmuebleServiceUnderTest.tipoInmuebleRespository = mockTipoInmuebleRespository;
        tipoInmuebleServiceUnderTest.inmuebleRepository = mockInmuebleRepository;
    }

    @Test
    void testSave() {
        // Setup
        final TipoInmuebleDTO tipoInmuebleDTO = TipoInmuebleDTO.builder().build();
        final TipoInmuebleDTO expectedResult = TipoInmuebleDTO.builder().build();
        when(mockTipoInmuebleRespository.save(TipoInmueble.builder()
                .id(0L)
                .build())).thenReturn(TipoInmueble.builder()
                .id(0L)
                .build());

        // Run the test
        final TipoInmuebleDTO result = tipoInmuebleServiceUnderTest.save(tipoInmuebleDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final TipoInmuebleDTO tipoInmuebleDTO = TipoInmuebleDTO.builder().build();
        final TipoInmuebleDTO expectedResult = TipoInmuebleDTO.builder().build();
        when(mockTipoInmuebleRespository.save(TipoInmueble.builder()
                .id(0L)
                .build())).thenReturn(TipoInmueble.builder()
                .id(0L)
                .build());

        // Run the test
        final TipoInmuebleDTO result = tipoInmuebleServiceUnderTest.update(tipoInmuebleDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockTipoInmuebleRespository.existsById(0L)).thenReturn(true);

        // Run the test
        final MensajeDTO result = tipoInmuebleServiceUnderTest.delete(0L);

        // Verify the results
        verify(mockInmuebleRepository).actualizarInmueblesPorTipo(TipoInmueble.builder()
                .id(0L)
                .build(), TipoInmueble.builder()
                .id(0L)
                .build());
        verify(mockTipoInmuebleRespository).deleteById(0L);
    }

    @Test
    void testDelete_TipoInmuebleRespositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockTipoInmuebleRespository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> tipoInmuebleServiceUnderTest.delete(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final TipoInmuebleDTO expectedResult = TipoInmuebleDTO.builder().build();

        // Configure TipoInmuebleRespository.findById(...).
        final Optional<TipoInmueble> tipoInmueble = Optional.of(TipoInmueble.builder()
                .id(0L)
                .build());
        when(mockTipoInmuebleRespository.findById(0L)).thenReturn(tipoInmueble);

        // Run the test
        final TipoInmuebleDTO result = tipoInmuebleServiceUnderTest.find(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_TipoInmuebleRespositoryReturnsAbsent() {
        // Setup
        when(mockTipoInmuebleRespository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> tipoInmuebleServiceUnderTest.find(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<TipoInmuebleDTO> expectedResult = List.of(TipoInmuebleDTO.builder().build());

        // Configure TipoInmuebleRespository.findAll(...).
        final List<TipoInmueble> tipoInmuebles = List.of(TipoInmueble.builder()
                .id(0L)
                .build());
        when(mockTipoInmuebleRespository.findAll()).thenReturn(tipoInmuebles);

        // Run the test
        final List<TipoInmuebleDTO> result = tipoInmuebleServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_TipoInmuebleRespositoryReturnsNoItems() {
        // Setup
        when(mockTipoInmuebleRespository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> tipoInmuebleServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
