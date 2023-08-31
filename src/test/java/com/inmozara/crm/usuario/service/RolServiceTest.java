package com.inmozara.crm.usuario.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.usuario.model.Rol;
import com.inmozara.crm.usuario.model.dto.RolDTO;
import com.inmozara.crm.usuario.model.repository.RolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {

    @Mock
    private RolRepository mockRolRepository;

    @InjectMocks
    private RolService rolServiceUnderTest;

    @Test
    void testSave() {
        // Setup
        final RolDTO rolDTO = RolDTO.builder().build();
        when(mockRolRepository.save(any(Rol.class))).thenReturn(Rol.builder()
                .idRol(0)
                .build());

        // Run the test
        final RolDTO result = rolServiceUnderTest.save(rolDTO);

        // Verify the results
    }

    @Test
    void testUpdate() {
        // Setup
        final RolDTO rolDTO = RolDTO.builder().build();
        when(mockRolRepository.save(any(Rol.class))).thenReturn(Rol.builder()
                .idRol(0)
                .build());

        // Run the test
        final RolDTO result = rolServiceUnderTest.update(rolDTO);

        // Verify the results
    }

    @Test
    void testDelete() {
        // Setup
        when(mockRolRepository.existsById(0)).thenReturn(true);

        // Run the test
        final MensajeDTO result = rolServiceUnderTest.delete(0);

        // Verify the results
        verify(mockRolRepository).deleteById(0);
    }

    @Test
    void testDelete_RolRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockRolRepository.existsById(0)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> rolServiceUnderTest.delete(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFind() {
        // Setup
        // Configure RolRepository.findById(...).
        final Optional<Rol> rol = Optional.of(Rol.builder()
                .idRol(0)
                .build());
        when(mockRolRepository.findById(0)).thenReturn(rol);

        // Run the test
        final RolDTO result = rolServiceUnderTest.find(0);

        // Verify the results
    }

    @Test
    void testFind_RolRepositoryReturnsAbsent() {
        // Setup
        when(mockRolRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> rolServiceUnderTest.find(0)).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testFindAll() {
        // Setup
        // Configure RolRepository.findAll(...).
        final List<Rol> rols = List.of(Rol.builder()
                .idRol(0)
                .build());
        when(mockRolRepository.findAll()).thenReturn(rols);

        // Run the test
        final List<RolDTO> result = rolServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_RolRepositoryReturnsNoItems() {
        // Setup
        when(mockRolRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> rolServiceUnderTest.findAll()).isInstanceOf(RecursoNoEncontrado.class);
    }

    @Test
    void testRolesAdminYAgente() {
        // Setup
        // Configure RolRepository.findRolesAgenteYAdmin(...).
        final List<Rol> rols = List.of(Rol.builder()
                .idRol(0)
                .build());
        when(mockRolRepository.findRolesAgenteYAdmin()).thenReturn(rols);

        // Run the test
        final List<RolDTO> result = rolServiceUnderTest.rolesAdminYAgente();

        // Verify the results
    }

    @Test
    void testRolesAdminYAgente_RolRepositoryReturnsNoItems() {
        // Setup
        when(mockRolRepository.findRolesAgenteYAdmin()).thenReturn(Collections.emptyList());

        // Run the test
        assertThatThrownBy(() -> rolServiceUnderTest.rolesAdminYAgente()).isInstanceOf(RecursoNoEncontrado.class);
    }
}
