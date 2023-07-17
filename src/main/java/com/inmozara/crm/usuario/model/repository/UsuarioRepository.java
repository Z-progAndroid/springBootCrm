package com.inmozara.crm.usuario.model.repository;

import com.inmozara.crm.usuario.model.EstadoUsuario;
import com.inmozara.crm.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {
    @Transactional
    @Modifying
    @Query("UPDATE usuarios u SET u.estadoUsuario = :nuevoEstado WHERE u.estadoUsuario = :estadoActual")
    void actualizarUsuariosPorEstado(@Param("estadoActual") EstadoUsuario estadoActual, @Param("nuevoEstado") EstadoUsuario nuevoEstado);
    @Query("SELECT u FROM usuarios u LEFT JOIN FETCH u.rol r WHERE r.rol = 'ADMIN' OR r.rol = 'AGENTE'")
    List<Usuario> obtenerAgentesYAdministradores();
    @Query("SELECT u FROM usuarios u  WHERE u.nombre = 'DEFECTO_ADMIN' OR u.apellido = 'DEFECTO_ADMIN' OR u.email = 'DEFECTO_ADMIN@DEFECTO_ADMIN.COM' ")
    Optional<Usuario> obtenerUsuarioPorDefecto();


}
