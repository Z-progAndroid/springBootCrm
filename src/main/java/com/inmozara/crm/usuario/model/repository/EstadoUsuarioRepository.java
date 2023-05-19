package com.inmozara.crm.usuario.model.repository;

import com.inmozara.crm.usuario.model.EstadoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoUsuarioRepository extends JpaRepository<EstadoUsuario, Integer> {
}
