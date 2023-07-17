package com.inmozara.crm.contrato.model.repository;

import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.EstadoContrato;
import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.TipoPago;
import com.inmozara.crm.usuario.model.Usuario;
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

    @Transactional
    @Modifying
    @Query(value = "UPDATE CONTRATOS c SET c.tipoContrato = :nuevoEstado WHERE c.tipoContrato= :estadoActual")
    void actualizarContratosPorTipo(@Param("estadoActual") TipoContrato estadoActual, @Param("nuevoEstado") TipoContrato nuevoEstado);

    @Transactional
    @Modifying
    @Query(value = "UPDATE CONTRATOS c SET c.tipoPago = :nuevoEstado WHERE c.tipoPago= :estadoActual")
    void actualizarContratosPorTipoPago(@Param("estadoActual") TipoPago estadoActual, @Param("nuevoEstado") TipoPago nuevoEstado);
    @Transactional
    @Modifying
    @Query(value = "UPDATE CONTRATOS c SET c.agente = :nuevoEstado WHERE c.agente= :estadoActual")
    void actualizarAgenteContrato(@Param("estadoActual") Usuario estadoActual, @Param("nuevoEstado") Usuario nuevoEstado);
}
