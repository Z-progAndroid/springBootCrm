package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.EstadoContratoRepository;
import com.inmozara.crm.contrato.model.repository.TipoContratoRepository;
import com.inmozara.crm.contrato.model.search.ContratoSearch;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.repository.EstadoInmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContratoServiceTest {

    @Mock
    private ContratoRepository mockContratoRepository;
    @Mock
    private InmuebleRepository mockInmuebleRepository;
    @Mock
    private EstadoInmuebleRepository mockEstadoInmuebleRepository;
    @Mock
    private UsuarioRepository mockUsuarioRepository;
    @Mock
    private TipoContratoRepository mockTipoContratoRepository;
    @Mock
    private EstadoContratoRepository mockEstadoContratoRepository;

    @InjectMocks
    private ContratoService contratoServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final ContratoDTO contratoDTO = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        final ContratoDTO expectedResult = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        when(mockContratoRepository.checkContratosExistentes(0)).thenReturn(0);
        when(mockContratoRepository.esContratoActivo(0L)).thenReturn(0);

        // Configure ContratoRepository.save(...).
        final Contrato contrato = Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build();
        when(mockContratoRepository.save(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build())).thenReturn(contrato);

        // Configure EstadoInmuebleRepository.findAll(...).
        final List<EstadoInmueble> estadoInmuebles = List.of(EstadoInmueble.builder()
                .estado("estado")
                .build());
        when(mockEstadoInmuebleRepository.findAll()).thenReturn(estadoInmuebles);

        // Configure TipoContratoRepository.findById(...).
        final Optional<TipoContrato> tipoContrato = Optional.of(TipoContrato.builder()
                .idTipoContrato(0L)
                .tipo("tipo")
                .build());
        when(mockTipoContratoRepository.findById(0L)).thenReturn(tipoContrato);

        // Run the test
        final ContratoDTO result = contratoServiceUnderTest.save(contratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockInmuebleRepository).actualizarInmueblesEstado(0, EstadoInmueble.builder()
                .estado("estado")
                .build());
    }

    @Test
    void testSave_ContratoRepositorySaveReturnsNull() {
        // Setup
        final ContratoDTO contratoDTO = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        final ContratoDTO expectedResult = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        when(mockContratoRepository.checkContratosExistentes(0)).thenReturn(0);
        when(mockContratoRepository.esContratoActivo(0L)).thenReturn(0);
        when(mockContratoRepository.save(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build())).thenReturn(null);

        // Configure EstadoInmuebleRepository.findAll(...).
        final List<EstadoInmueble> estadoInmuebles = List.of(EstadoInmueble.builder()
                .estado("estado")
                .build());
        when(mockEstadoInmuebleRepository.findAll()).thenReturn(estadoInmuebles);

        // Configure TipoContratoRepository.findById(...).
        final Optional<TipoContrato> tipoContrato = Optional.of(TipoContrato.builder()
                .idTipoContrato(0L)
                .tipo("tipo")
                .build());
        when(mockTipoContratoRepository.findById(0L)).thenReturn(tipoContrato);

        // Run the test
        final ContratoDTO result = contratoServiceUnderTest.save(contratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockInmuebleRepository).actualizarInmueblesEstado(0, EstadoInmueble.builder()
                .estado("estado")
                .build());
    }

    @Test
    void testSave_EstadoInmuebleRepositoryReturnsNoItems() {
        // Setup
        final ContratoDTO contratoDTO = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        final ContratoDTO expectedResult = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        when(mockContratoRepository.checkContratosExistentes(0)).thenReturn(0);
        when(mockContratoRepository.esContratoActivo(0L)).thenReturn(0);

        // Configure ContratoRepository.save(...).
        final Contrato contrato = Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build();
        when(mockContratoRepository.save(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build())).thenReturn(contrato);

        when(mockEstadoInmuebleRepository.findAll()).thenReturn(Collections.emptyList());

        // Configure TipoContratoRepository.findById(...).
        final Optional<TipoContrato> tipoContrato = Optional.of(TipoContrato.builder()
                .idTipoContrato(0L)
                .tipo("tipo")
                .build());
        when(mockTipoContratoRepository.findById(0L)).thenReturn(tipoContrato);

        // Run the test
        final ContratoDTO result = contratoServiceUnderTest.save(contratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockInmuebleRepository).actualizarInmueblesEstado(0, EstadoInmueble.builder()
                .estado("estado")
                .build());
    }

    @Test
    void testSave_TipoContratoRepositoryReturnsAbsent() {
        // Setup
        final ContratoDTO contratoDTO = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        final ContratoDTO expectedResult = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        when(mockContratoRepository.checkContratosExistentes(0)).thenReturn(0);
        when(mockContratoRepository.esContratoActivo(0L)).thenReturn(0);

        // Configure ContratoRepository.save(...).
        final Contrato contrato = Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build();
        when(mockContratoRepository.save(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build())).thenReturn(contrato);

        // Configure EstadoInmuebleRepository.findAll(...).
        final List<EstadoInmueble> estadoInmuebles = List.of(EstadoInmueble.builder()
                .estado("estado")
                .build());
        when(mockEstadoInmuebleRepository.findAll()).thenReturn(estadoInmuebles);

        when(mockTipoContratoRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ContratoDTO result = contratoServiceUnderTest.save(contratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate() {
        // Setup
        final ContratoDTO contratoDTO = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        final ContratoDTO expectedResult = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();

        // Configure ContratoRepository.save(...).
        final Contrato contrato = Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build();
        when(mockContratoRepository.save(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build())).thenReturn(contrato);

        // Run the test
        final ContratoDTO result = contratoServiceUnderTest.update(contratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDelete() {
        // Setup
        when(mockContratoRepository.existsById(0L)).thenReturn(true);

        // Run the test
        final MensajeDTO result = contratoServiceUnderTest.delete(0L);

        // Verify the results
        verify(mockContratoRepository).deleteById(0L);
    }

    @Test
    void testDelete_ContratoRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockContratoRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> contratoServiceUnderTest.delete(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        final ContratoDTO expectedResult = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();

        // Configure ContratoRepository.findById(...).
        final Optional<Contrato> contrato = Optional.of(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build());
        when(mockContratoRepository.findById(0L)).thenReturn(contrato);

        // Run the test
        final ContratoDTO result = contratoServiceUnderTest.find(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_ContratoRepositoryReturnsAbsent() {
        // Setup
        when(mockContratoRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> contratoServiceUnderTest.find(0L)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<ContratoDTO> expectedResult = List.of(ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build());

        // Configure ContratoRepository.findAll(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build());
        when(mockContratoRepository.findAll()).thenReturn(contratoes);

        // Run the test
        final List<ContratoDTO> result = contratoServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_ContratoRepositoryReturnsNoItems() {
        // Setup
        when(mockContratoRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> contratoServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAllByParams() {
        // Setup
        final ContratoDTO contratoDTO = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        final List<ContratoDTO> expectedResult = List.of(ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build());

        // Configure ContratoRepository.findAll(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build());
        when(mockContratoRepository.findAll(any(ContratoSearch.class))).thenReturn(contratoes);

        // Run the test
        final List<ContratoDTO> result = contratoServiceUnderTest.findAllByParams(contratoDTO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAllByParams_ContratoRepositoryReturnsNoItems() {
        // Setup
        final ContratoDTO contratoDTO = ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build();
        when(mockContratoRepository.findAll(any(ContratoSearch.class))).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> contratoServiceUnderTest.findAllByParams(contratoDTO))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testGenerarContratoPdf() {
        // Setup
        // Configure ContratoRepository.findById(...).
        final Optional<Contrato> contrato = Optional.of(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build());
        when(mockContratoRepository.findById(0L)).thenReturn(contrato);

        // Run the test
        final ByteArrayOutputStream result = contratoServiceUnderTest.generarContratoPdf(0L);

        // Verify the results
    }

    @Test
    void testGenerarContratoPdf_ContratoRepositoryReturnsAbsent() {
        // Setup
        when(mockContratoRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> contratoServiceUnderTest.generarContratoPdf(0L))
                .isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testObtenerContratosPorUsuario() {
        // Setup
        final List<ContratoDTO> expectedResult = List.of(ContratoDTO.builder()
                .idContrato(0L)
                .idInmueble(0)
                .noestadoEliminado(false)
                .build());

        // Configure ContratoRepository.obtenerContratosPorUsuario(...).
        final List<Contrato> contratoes = List.of(Contrato.builder()
                .tipoContrato(TipoContrato.builder()
                        .idTipoContrato(0L)
                        .tipo("tipo")
                        .build())
                .inmueble(Inmueble.builder()
                        .idInmueble(0)
                        .usuario(Usuario.builder().build())
                        .build())
                .cliente(Usuario.builder().build())
                .build());
        when(mockContratoRepository.obtenerContratosPorUsuario(0)).thenReturn(contratoes);

        // Run the test
        final List<ContratoDTO> result = contratoServiceUnderTest.obtenerContratosPorUsuario(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testObtenerContratosPorUsuario_ContratoRepositoryReturnsNoItems() {
        // Setup
        when(mockContratoRepository.obtenerContratosPorUsuario(0)).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> contratoServiceUnderTest.obtenerContratosPorUsuario(0))
                .isInstanceOf(RecursoNoEncontrado.class);
    }
}
