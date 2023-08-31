package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Municipio;
import com.inmozara.crm.inmueble.model.Provincia;
import com.inmozara.crm.inmueble.model.dto.MunicipoDTO;
import com.inmozara.crm.inmueble.model.repository.BarrioRepository;
import com.inmozara.crm.inmueble.model.repository.MunicipoReprository;
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
class MunicipoServiceTest {

    @Mock
    private MunicipoReprository mockMunicipoReprository;
    @Mock
    private BarrioRepository mockBarrioRepository;

    private MunicipoService municipoServiceUnderTest;

    @BeforeEach
    void setUp() {
        municipoServiceUnderTest = new MunicipoService();
        municipoServiceUnderTest.municipoReprository = mockMunicipoReprository;
        municipoServiceUnderTest.barrioRepository = mockBarrioRepository;
    }

    @Test
    void testFindAllByProvincia() {
        // Setup
        final List<MunicipoDTO> expectedResult = List.of(MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build());

        // Configure MunicipoReprository.findAllByProvincia(...).
        final List<Municipio> municipios = List.of(Municipio.builder()
                .idMunicipio(0)
                .build());
        when(mockMunicipoReprository.findAllByProvincia(Provincia.builder()
                .idProvincia(0)
                .build())).thenReturn(municipios);

        // Run the test
        final List<MunicipoDTO> result = municipoServiceUnderTest.findAllByProvincia(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllByProvincia_MunicipoReprositoryReturnsNoItems() {
        // Setup
        when(mockMunicipoReprository.findAllByProvincia(Provincia.builder()
                .idProvincia(0)
                .build())).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> municipoServiceUnderTest.findAllByProvincia(0))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testSave() {
        // Setup
        final MunicipoDTO municipoDTO = MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build();
        final MunicipoDTO expectedResult = MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build();

        // Configure MunicipoReprository.save(...).
        final Municipio municipio = Municipio.builder()
                .idMunicipio(0)
                .build();
        when(mockMunicipoReprository.save(Municipio.builder()
                .idMunicipio(0)
                .build())).thenReturn(municipio);

        when(mockMunicipoReprository.existsById(0)).thenReturn(false);

        // Run the test
        final MunicipoDTO result = municipoServiceUnderTest.save(municipoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave_MunicipoReprositoryExistsByIdReturnsTrue() {
        // Setup
        final MunicipoDTO municipoDTO = MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build();
        final MunicipoDTO expectedResult = MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build();

        // Configure MunicipoReprository.save(...).
        final Municipio municipio = Municipio.builder()
                .idMunicipio(0)
                .build();
        when(mockMunicipoReprository.save(Municipio.builder()
                .idMunicipio(0)
                .build())).thenReturn(municipio);

        when(mockMunicipoReprository.existsById(0)).thenReturn(true);

        // Run the test
        final MunicipoDTO result = municipoServiceUnderTest.save(municipoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockBarrioRepository).actualizarMunicipio(Municipio.builder()
                .idMunicipio(0)
                .build(), Municipio.builder()
                .idMunicipio(0)
                .build());
        verify(mockMunicipoReprository).deleteById(0);
    }

    @Test
    void testUpdate() {
        // Setup
        final MunicipoDTO municipoDTO = MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build();
        final MunicipoDTO expectedResult = MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build();

        // Configure MunicipoReprository.save(...).
        final Municipio municipio = Municipio.builder()
                .idMunicipio(0)
                .build();
        when(mockMunicipoReprository.save(Municipio.builder()
                .idMunicipio(0)
                .build())).thenReturn(municipio);

        // Run the test
        final MunicipoDTO result = municipoServiceUnderTest.update(municipoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockMunicipoReprository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = municipoServiceUnderTest.delete(0);

        // Verify the results
        verify(mockMunicipoReprository).deleteById(0);
    }

    @Test
    void testDelete_MunicipoReprositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockMunicipoReprository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> municipoServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final MunicipoDTO expectedResult = MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build();

        // Configure MunicipoReprository.findById(...).
        final Optional<Municipio> municipio = Optional.of(Municipio.builder()
                .idMunicipio(0)
                .build());
        when(mockMunicipoReprository.findById(0)).thenReturn(municipio);

        // Run the test
        final MunicipoDTO result = municipoServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_MunicipoReprositoryReturnsAbsent() {
        // Setup
        when(mockMunicipoReprository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> municipoServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<MunicipoDTO> expectedResult = List.of(MunicipoDTO.builder()
                .idMunicipio(0)
                .idMunicipioExistente(0)
                .build());

        // Configure MunicipoReprository.findAll(...).
        final List<Municipio> municipios = List.of(Municipio.builder()
                .idMunicipio(0)
                .build());
        when(mockMunicipoReprository.findAll()).thenReturn(municipios);

        // Run the test
        final List<MunicipoDTO> result = municipoServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_MunicipoReprositoryReturnsNoItems() {
        // Setup
        when(mockMunicipoReprository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> municipoServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
