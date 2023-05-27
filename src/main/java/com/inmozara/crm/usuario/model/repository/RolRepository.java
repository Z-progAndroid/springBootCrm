package com.inmozara.crm.usuario.model.repository;

import com.inmozara.crm.usuario.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    @Query("SELECT r FROM roles r WHERE r.rol IN ('agente', 'admin')")
    List<Rol> findRolesAgenteYAdmin();
}
