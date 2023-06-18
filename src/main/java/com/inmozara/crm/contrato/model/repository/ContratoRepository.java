package com.inmozara.crm.contrato.model.repository;

import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.EstadoContrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long>, JpaSpecificationExecutor<Contrato> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE CONTRATOS c SET c.estadoContrato = :nuevoEstado WHERE c.estadoContrato= :estadoActual")
    void actualizarContratosPorEstado(@Param("estadoActual") EstadoContrato estadoActual, @Param("nuevoEstado") EstadoContrato nuevoEstado);
}
