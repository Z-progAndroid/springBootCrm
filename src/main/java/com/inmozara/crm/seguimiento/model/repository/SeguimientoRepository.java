package com.inmozara.crm.seguimiento.model.repository;

import com.inmozara.crm.seguimiento.model.Seguimiento;
import com.inmozara.crm.seguimiento.model.TipoSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> , JpaSpecificationExecutor<Seguimiento> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE seguimientos c SET c.tipoSeguimiento = :nuevoEstado WHERE c.tipoSeguimiento= :estadoActual")
    void actualizarSegimientosPorTipo(@Param("estadoActual") TipoSeguimiento estadoActual, @Param("nuevoEstado") TipoSeguimiento nuevoEstado);
}
