package com.inmozara.crm.seguimiento.model.repository;

import com.inmozara.crm.seguimiento.model.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> , JpaSpecificationExecutor<Seguimiento> {
}
