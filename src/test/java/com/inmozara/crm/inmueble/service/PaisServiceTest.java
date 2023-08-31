package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Pais;
import com.inmozara.crm.inmueble.model.dto.PaisDTO;
import com.inmozara.crm.inmueble.model.repository.PaisRepository;
import com.inmozara.crm.inmueble.model.repository.ProvinciaRepository;
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
class PaisServiceTest {

    @Mock
    private PaisRepository mockPaisRepository;
    @Mock
    private ProvinciaRepository mockProvinciaRepository;

    @InjectMocks
    private PaisService paisServiceUnderTest;

    @Test
    void testFindByIdPais() {
        // Setup
        final PaisDTO expectedResult = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();

        // Configure PaisRepository.findByIdPais(...).
        final Optional<Pais> pais = Optional.of(Pais.builder()
                .idPais("idPais")
                .build());
        when(mockPaisRepository.findByIdPais("idPais")).thenReturn(pais);

        // Run the test
        final PaisDTO result = paisServiceUnderTest.findByIdPais("idPais");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByIdPais_PaisRepositoryReturnsAbsent() {
        // Setup
        when(mockPaisRepository.findByIdPais("idPais")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> paisServiceUnderTest.findByIdPais("idPais")).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testSave() {
        // Setup
        final PaisDTO paisDTO = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();
        final PaisDTO expectedResult = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();
        when(mockPaisRepository.existsById("idPais")).thenReturn(false);

        // Configure PaisRepository.save(...).
        final Pais pais = Pais.builder()
                .idPais("idPais")
                .build();
        when(mockPaisRepository.save(Pais.builder()
                .idPais("idPais")
                .build())).thenReturn(pais);

        // Run the test
        final PaisDTO result = paisServiceUnderTest.save(paisDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave_PaisRepositoryExistsByIdReturnsTrue() {
        // Setup
        final PaisDTO paisDTO = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();
        final PaisDTO expectedResult = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();
        when(mockPaisRepository.existsById("idPais")).thenReturn(true);

        // Configure PaisRepository.save(...).
        final Pais pais = Pais.builder()
                .idPais("idPais")
                .build();
        when(mockPaisRepository.save(Pais.builder()
                .idPais("idPais")
                .build())).thenReturn(pais);

        // Run the test
        final PaisDTO result = paisServiceUnderTest.save(paisDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockProvinciaRepository).actualizarPais(Pais.builder()
                .idPais("idPais")
                .build(), Pais.builder()
                .idPais("idPais")
                .build());
        verify(mockPaisRepository).deleteById("idPais");
    }

    @Test
    void testUpdate() {
        // Setup
        final PaisDTO paisDTO = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();
        final PaisDTO expectedResult = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();

        // Configure PaisRepository.save(...).
        final Pais pais = Pais.builder()
                .idPais("idPais")
                .build();
        when(mockPaisRepository.save(Pais.builder()
                .idPais("idPais")
                .build())).thenReturn(pais);

        // Run the test
        final PaisDTO result = paisServiceUnderTest.update(paisDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockPaisRepository.existsById("idPais")).thenReturn(true);

        // Run the test
        final MensajeDTO result = paisServiceUnderTest.delete("idPais");

        // Verify the results
        verify(mockPaisRepository).deleteById("idPais");
    }

    @Test
    void testDelete_PaisRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockPaisRepository.existsById("idPais")).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> paisServiceUnderTest.delete("idPais")).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final PaisDTO expectedResult = PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build();

        // Configure PaisRepository.findById(...).
        final Optional<Pais> pais = Optional.of(Pais.builder()
                .idPais("idPais")
                .build());
        when(mockPaisRepository.findById("id")).thenReturn(pais);

        // Run the test
        final PaisDTO result = paisServiceUnderTest.find("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_PaisRepositoryReturnsAbsent() {
        // Setup
        when(mockPaisRepository.findById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> paisServiceUnderTest.find("id")).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<PaisDTO> expectedResult = List.of(PaisDTO.builder()
                .idPais("idPais")
                .idPaisExistente("idPais")
                .build());

        // Configure PaisRepository.findAll(...).
        final List<Pais> pais = List.of(Pais.builder()
                .idPais("idPais")
                .build());
        when(mockPaisRepository.findAll()).thenReturn(pais);

        // Run the test
        final List<PaisDTO> result = paisServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_PaisRepositoryReturnsNoItems() {
        // Setup
        when(mockPaisRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> paisServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
