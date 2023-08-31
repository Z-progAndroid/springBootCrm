package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Pais;
import com.inmozara.crm.inmueble.model.Provincia;
import com.inmozara.crm.inmueble.model.dto.ProvinciaDTO;
import com.inmozara.crm.inmueble.model.repository.MunicipoReprository;
import com.inmozara.crm.inmueble.model.repository.ProvinciaRepository;
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
class ProvinciaServiceTest {

    @Mock
    private ProvinciaRepository mockProvinciaRepository;
    @Mock
    private MunicipoReprository mockMunicipoReprository;

    private ProvinciaService provinciaServiceUnderTest;

    @BeforeEach
    void setUp() {
        provinciaServiceUnderTest = new ProvinciaService();
        provinciaServiceUnderTest.provinciaRepository = mockProvinciaRepository;
        provinciaServiceUnderTest.municipoReprository = mockMunicipoReprository;
    }

    @Test
    void testFindAllByPais() {
        // Setup
        final List<ProvinciaDTO> expectedResult = List.of(ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build());

        // Configure ProvinciaRepository.findAllByPais(...).
        final List<Provincia> provincias = List.of(Provincia.builder()
                .idProvincia(0)
                .build());
        when(mockProvinciaRepository.findAllByPais(Pais.builder()
                .idPais("idPais")
                .build())).thenReturn(provincias);

        // Run the test
        final List<ProvinciaDTO> result = provinciaServiceUnderTest.findAllByPais("idPais");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllByPais_ProvinciaRepositoryReturnsNoItems() {
        // Setup
        when(mockProvinciaRepository.findAllByPais(Pais.builder()
                .idPais("idPais")
                .build())).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> provinciaServiceUnderTest.findAllByPais("idPais"))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testSave() {
        // Setup
        final ProvinciaDTO provinciaDTO = ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build();
        final ProvinciaDTO expectedResult = ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build();
        when(mockProvinciaRepository.existsById(0)).thenReturn(false);

        // Configure ProvinciaRepository.save(...).
        final Provincia provincia = Provincia.builder()
                .idProvincia(0)
                .build();
        when(mockProvinciaRepository.save(Provincia.builder()
                .idProvincia(0)
                .build())).thenReturn(provincia);

        // Run the test
        final ProvinciaDTO result = provinciaServiceUnderTest.save(provinciaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave_ProvinciaRepositoryExistsByIdReturnsTrue() {
        // Setup
        final ProvinciaDTO provinciaDTO = ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build();
        final ProvinciaDTO expectedResult = ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build();
        when(mockProvinciaRepository.existsById(0)).thenReturn(true);

        // Configure ProvinciaRepository.save(...).
        final Provincia provincia = Provincia.builder()
                .idProvincia(0)
                .build();
        when(mockProvinciaRepository.save(Provincia.builder()
                .idProvincia(0)
                .build())).thenReturn(provincia);

        // Run the test
        final ProvinciaDTO result = provinciaServiceUnderTest.save(provinciaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockMunicipoReprository).actualizarProvincia(Provincia.builder()
                .idProvincia(0)
                .build(), Provincia.builder()
                .idProvincia(0)
                .build());
        verify(mockProvinciaRepository).deleteById(0);
    }

    @Test
    void testUpdate() {
        // Setup
        final ProvinciaDTO provinciaDTO = ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build();
        final ProvinciaDTO expectedResult = ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build();

        // Configure ProvinciaRepository.save(...).
        final Provincia provincia = Provincia.builder()
                .idProvincia(0)
                .build();
        when(mockProvinciaRepository.save(Provincia.builder()
                .idProvincia(0)
                .build())).thenReturn(provincia);

        // Run the test
        final ProvinciaDTO result = provinciaServiceUnderTest.update(provinciaDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockProvinciaRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = provinciaServiceUnderTest.delete(0);

        // Verify the results
        verify(mockProvinciaRepository).deleteById(0);
    }

    @Test
    void testDelete_ProvinciaRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockProvinciaRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> provinciaServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final ProvinciaDTO expectedResult = ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build();

        // Configure ProvinciaRepository.findById(...).
        final Optional<Provincia> provincia = Optional.of(Provincia.builder()
                .idProvincia(0)
                .build());
        when(mockProvinciaRepository.findById(0)).thenReturn(provincia);

        // Run the test
        final ProvinciaDTO result = provinciaServiceUnderTest.find(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_ProvinciaRepositoryReturnsAbsent() {
        // Setup
        when(mockProvinciaRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> provinciaServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<ProvinciaDTO> expectedResult = List.of(ProvinciaDTO.builder()
                .idProvincia(0)
                .idProvinciaExistente(0)
                .build());

        // Configure ProvinciaRepository.findAll(...).
        final List<Provincia> provincias = List.of(Provincia.builder()
                .idProvincia(0)
                .build());
        when(mockProvinciaRepository.findAll()).thenReturn(provincias);

        // Run the test
        final List<ProvinciaDTO> result = provinciaServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_ProvinciaRepositoryReturnsNoItems() {
        // Setup
        when(mockProvinciaRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> provinciaServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
