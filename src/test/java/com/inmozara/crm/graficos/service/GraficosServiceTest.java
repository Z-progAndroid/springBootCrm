package com.inmozara.crm.graficos.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.EstadoCita;
import com.inmozara.crm.cita.model.TipoCita;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.EstadoContrato;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.graficos.model.dto.GraficoDTO;
import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.tarea.model.EstadoTarea;
import com.inmozara.crm.tarea.model.Tarea;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GraficosServiceTest {

    @Mock
    private ContratoRepository mockContratoRepository;
    @Mock
    private InmuebleRepository mockInmuebleRepository;
    @Mock
    private CitaRepository mockCitaRepository;
    @Mock
    private TareaRepository mockTareaRepository;

    private GraficosService graficosServiceUnderTest;

    @BeforeEach
    void setUp() {
        graficosServiceUnderTest = new GraficosService();
        graficosServiceUnderTest.contratoRepository = mockContratoRepository;
        graficosServiceUnderTest.inmuebleRepository = mockInmuebleRepository;
        graficosServiceUnderTest.citaRepository = mockCitaRepository;
        graficosServiceUnderTest.tareaRepository = mockTareaRepository;
    }

    @Test
    void testGraficoAdmin() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMes(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(contratoes);

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findAll()).thenReturn(inmuebles);

        // Configure CitaRepository.findCitasCreadasEsteMes(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(citas);

        // Configure TareaRepository.findTareasCreadasEsteMes(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAdmin();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAdmin_ContratoRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();
        when(mockContratoRepository.findContratosCreadosEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findAll()).thenReturn(inmuebles);

        // Configure CitaRepository.findCitasCreadasEsteMes(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(citas);

        // Configure TareaRepository.findTareasCreadasEsteMes(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAdmin();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAdmin_InmuebleRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMes(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(contratoes);

        when(mockInmuebleRepository.findAll()).thenReturn(Collections.emptyList());

        // Configure CitaRepository.findCitasCreadasEsteMes(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(citas);

        // Configure TareaRepository.findTareasCreadasEsteMes(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAdmin();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAdmin_CitaRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMes(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(contratoes);

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findAll()).thenReturn(inmuebles);

        when(mockCitaRepository.findCitasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Configure TareaRepository.findTareasCreadasEsteMes(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAdmin();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAdmin_TareaRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMes(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(contratoes);

        // Configure InmuebleRepository.findAll(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findAll()).thenReturn(inmuebles);

        // Configure CitaRepository.findCitasCreadasEsteMes(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(citas);

        when(mockTareaRepository.findTareasCreadasEsteMes(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAdmin();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAgente() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMesPorUsuario(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(contratoes);

        // Configure InmuebleRepository.findInmueblesByUsuarioId(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findInmueblesByUsuarioId(0L)).thenReturn(inmuebles);

        // Configure CitaRepository.findCitasCreadasEsteMesPorUsuario(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(citas);

        // Configure TareaRepository.findTareasCreadasEsteMesPorUsuario(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAgente(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAgente_ContratoRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();
        when(mockContratoRepository.findContratosCreadosEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(Collections.emptyList());

        // Configure InmuebleRepository.findInmueblesByUsuarioId(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findInmueblesByUsuarioId(0L)).thenReturn(inmuebles);

        // Configure CitaRepository.findCitasCreadasEsteMesPorUsuario(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(citas);

        // Configure TareaRepository.findTareasCreadasEsteMesPorUsuario(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAgente(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAgente_InmuebleRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMesPorUsuario(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(contratoes);

        when(mockInmuebleRepository.findInmueblesByUsuarioId(0L)).thenReturn(Collections.emptyList());

        // Configure CitaRepository.findCitasCreadasEsteMesPorUsuario(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(citas);

        // Configure TareaRepository.findTareasCreadasEsteMesPorUsuario(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAgente(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAgente_CitaRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMesPorUsuario(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(contratoes);

        // Configure InmuebleRepository.findInmueblesByUsuarioId(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findInmueblesByUsuarioId(0L)).thenReturn(inmuebles);

        when(mockCitaRepository.findCitasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(Collections.emptyList());

        // Configure TareaRepository.findTareasCreadasEsteMesPorUsuario(...).
        final List<Tarea> tareas = List.of(Tarea.builder()
                .estadoTarea(EstadoTarea.builder()
                        .estadoTarea("estadoTarea")
                        .build())
                .build());
        when(mockTareaRepository.findTareasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(tareas);

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAgente(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGraficoAgente_TareaRepositoryReturnsNoItems() {
        // Setup
        final GraficoDTO expectedResult = GraficoDTO.builder()
                .contratos(Map.ofEntries(Map.entry("value", 0L)))
                .inmuebles(Map.ofEntries(Map.entry("value", 0L)))
                .citas(Map.ofEntries(Map.entry("value", 0L)))
                .tareas(Map.ofEntries(Map.entry("value", 0L)))
                .visitas(Map.ofEntries(Map.entry("value", 0L)))
                .build();

        // Configure ContratoRepository.findContratosCreadosEsteMesPorUsuario(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .estadoContrato(EstadoContrato.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockContratoRepository.findContratosCreadosEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(contratoes);

        // Configure InmuebleRepository.findInmueblesByUsuarioId(...).
        final List<Inmueble> inmuebles = List.of(Inmueble.builder()
                .estadoInmueble(EstadoInmueble.builder()
                        .estado("estado")
                        .build())
                .build());
        when(mockInmuebleRepository.findInmueblesByUsuarioId(0L)).thenReturn(inmuebles);

        // Configure CitaRepository.findCitasCreadasEsteMesPorUsuario(...).
        final List<Cita> citas = List.of(Cita.builder()
                .tipoCita(TipoCita.builder()
                        .tipoCita("tipoCita")
                        .build())
                .estadoCita(EstadoCita.builder()
                        .estadoCita("estadoCita")
                        .build())
                .build());
        when(mockCitaRepository.findCitasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(citas);

        when(mockTareaRepository.findTareasCreadasEsteMesPorUsuario(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0)).thenReturn(Collections.emptyList());

        // Run the test
        final GraficoDTO result = graficosServiceUnderTest.graficoAgente(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
