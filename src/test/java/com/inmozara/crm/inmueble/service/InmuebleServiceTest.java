package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.EstadoInmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.inmueble.model.search.InmuebleSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InmuebleServiceTest {

    @Mock
    private InmuebleRepository mockInmuebleRepository;
    @Mock
    private EstadoInmuebleRepository mockEstadoInmuebleRepository;

    @InjectMocks
    private InmuebleService inmuebleServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final InmuebleDTO inmuebleDTO = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();
        final InmuebleDTO expectedResult = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();

        // Configure InmuebleRepository.save(...).
        final Inmueble inmueble = Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build();
        when(mockInmuebleRepository.save(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build())).thenReturn(inmueble);

        // Run the test
        final InmuebleDTO result = inmuebleServiceUnderTest.save(inmuebleDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final InmuebleDTO inmuebleDTO = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();
        final InmuebleDTO expectedResult = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();

        // Configure InmuebleRepository.save(...).
        final Inmueble inmueble = Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build();
        when(mockInmuebleRepository.save(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build())).thenReturn(inmueble);

        // Run the test
        final InmuebleDTO result = inmuebleServiceUnderTest.update(inmuebleDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockInmuebleRepository.existsById(0L)).thenReturn(true);

        // Run the test
        final MensajeDTO result = inmuebleServiceUnderTest.delete(0L);

        // Verify the results
        verify(mockInmuebleRepository).deleteById(0L);
    }

    @Test
    void testDelete_InmuebleRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockInmuebleRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.delete(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final InmuebleDTO expectedResult = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();

        // Configure InmuebleRepository.findByIdInmueble(...).
        final Optional<Inmueble> inmueble = Optional.of(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build());
        when(mockInmuebleRepository.findByIdInmueble(0L)).thenReturn(inmueble);

        // Run the test
        final InmuebleDTO result = inmuebleServiceUnderTest.find(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_InmuebleRepositoryReturnsAbsent() {
        // Setup
        when(mockInmuebleRepository.findByIdInmueble(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.find(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<InmuebleDTO> expectedResult = List.of(InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build());

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build());
        when(mockInmuebleRepository.findAll()).thenReturn(inmuebles);

        // Run the test
        final List<InmuebleDTO> result = inmuebleServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_InmuebleRepositoryReturnsNoItems() {
        // Setup
        when(mockInmuebleRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAllSinRelaciones() {
        // Setup
        final List<InmuebleDTO> expectedResult = List.of(InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build());

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build());
        when(mockInmuebleRepository.findAll()).thenReturn(inmuebles);

        // Run the test
        final List<InmuebleDTO> result = inmuebleServiceUnderTest.findAllSinRelaciones();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllSinRelaciones_InmuebleRepositoryReturnsNoItems() {
        // Setup
        when(mockInmuebleRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.findAllSinRelaciones())
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testSearch() {
        // Setup
        final InmuebleDTO search = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();
        final List<InmuebleDTO> expectedResult = List.of(InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build());

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build());
        when(mockInmuebleRepository.findAll(any(InmuebleSearch.class))).thenReturn(inmuebles);

        // Run the test
        final List<InmuebleDTO> result = inmuebleServiceUnderTest.search(search);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearch_InmuebleRepositoryReturnsNoItems() {
        // Setup
        final InmuebleDTO search = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();
        when(mockInmuebleRepository.findAll(any(InmuebleSearch.class))).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.search(search)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testSearchSinRelaciones() {
        // Setup
        final InmuebleDTO search = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();
        final List<InmuebleDTO> expectedResult = List.of(InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build());

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build());
        when(mockInmuebleRepository.findAll(any(InmuebleSearch.class))).thenReturn(inmuebles);

        // Run the test
        final List<InmuebleDTO> result = inmuebleServiceUnderTest.searchSinRelaciones(search);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSearchSinRelaciones_InmuebleRepositoryReturnsNoItems() {
        // Setup
        final InmuebleDTO search = InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build();
        when(mockInmuebleRepository.findAll(any(InmuebleSearch.class))).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.searchSinRelaciones(search))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testGeneraPdfDetalle() {
        // Setup
        // Configure InmuebleRepository.findByIdInmueble(...).
        final Optional<Inmueble> inmueble = Optional.of(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build());
        when(mockInmuebleRepository.findByIdInmueble(0L)).thenReturn(inmueble);

        // Run the test
        final ByteArrayOutputStream result = inmuebleServiceUnderTest.generaPdfDetalle(0L);

        // Verify the results
    }

    @Test
    void testGeneraPdfDetalle_InmuebleRepositoryReturnsAbsent() {
        // Setup
        when(mockInmuebleRepository.findByIdInmueble(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.generaPdfDetalle(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testGuardarImagen() {
        // Setup
        final MultipartFile file = new MockMultipartFile("name", "content".getBytes());

        // Run the test
        final MensajeDTO result = inmuebleServiceUnderTest.guardarImagen("idInmueble", "idImagen", file);

        // Verify the results
        verify(mockInmuebleRepository).actualizarImagen(eq(0L), eq("fieldName"), any(byte[].class));
    }

    @Test
    void testBorrarImagen() {
        // Setup
        // Run the test
        final MensajeDTO result = inmuebleServiceUnderTest.borrarImagen("idInmueble", "idImagen");

        // Verify the results
        verify(mockInmuebleRepository).actualizarImagen(eq(0L), eq("fieldName"), any(byte[].class));
    }

    @Test
    void testObtenerInmueblesPorUsuario() {
        // Setup
        final List<InmuebleDTO> expectedResult = List.of(InmuebleDTO.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .noEliminado(false)
                .build());

        // Configure InmuebleRepository.findInmueblesByUsuarioId(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .idInmueble(0)
                .descripcion("descripcion")
                .direccion("direccion")
                .codigoPostal("codigoPostal")
                .precio_venta(0.0)
                .precio_alquiler(0.0)
                .numHabitaciones(0)
                .numBanos(0)
                .metros_cuadrados(0.0)
                .ano_construccion(0)
                .fechaCreacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .fechaModificacion(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .modificado("modificado")
                .imagen1("content".getBytes())
                .imagen2("content".getBytes())
                .imagen3("content".getBytes())
                .imagen4("content".getBytes())
                .build());
        when(mockInmuebleRepository.findInmueblesByUsuarioId(0L)).thenReturn(inmuebles);

        // Run the test
        final List<InmuebleDTO> result = inmuebleServiceUnderTest.obtenerInmueblesPorUsuario(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testObtenerInmueblesPorUsuario_InmuebleRepositoryReturnsNoItems() {
        // Setup
        when(mockInmuebleRepository.findInmueblesByUsuarioId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> inmuebleServiceUnderTest.obtenerInmueblesPorUsuario(0L))
                .isInstanceOf(RecursoNoEncontrado.class);
    }
}
