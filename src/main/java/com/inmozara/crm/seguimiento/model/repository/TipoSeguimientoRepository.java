package com.inmozara.crm.seguimiento.model.repository;

import com.inmozara.crm.seguimiento.model.TipoSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSeguimientoRepository extends JpaRepository<TipoSeguimiento, Long> {
}
