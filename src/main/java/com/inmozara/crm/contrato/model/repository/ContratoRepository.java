package com.inmozara.crm.contrato.model.repository;

import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.EstadoContrato;
import com.inmozara.crm.contrato.model.TipoContrato;
import com.inmozara.crm.contrato.model.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    @Query("SELECT COUNT(*) FROM CONTRATOS c WHERE c.inmueble.idInmueble = :idInmueble AND c.estadoContrato.idEstadoContrato = 2")
    int checkContratosExistentes(@Param("idInmueble") int idInmueble);

    @Query("SELECT COUNT(*) FROM CONTRATOS c WHERE c.idContrato = :idContrato AND c.estadoContrato.idEstadoContrato = 2")
    int esContratoActivo(@Param("idContrato") Long idContrato);

    @Query("SELECT c FROM CONTRATOS c WHERE MONTH(c.fechaCreacion) = MONTH(:fechaActual) AND YEAR(c.fechaCreacion) = YEAR(:fechaActual)")
    List<Contrato> findContratosCreadosEsteMes(@Param("fechaActual") Date fechaActual);

    @Query("SELECT c FROM CONTRATOS c WHERE MONTH(c.fechaCreacion) = MONTH(:fechaActual) AND YEAR(c.fechaCreacion) = YEAR(:fechaActual) AND c.inmueble.usuario.idUsuario=:idUsuario ")
    List<Contrato> findContratosCreadosEsteMesPorUsuario(@Param("fechaActual") Date fechaActual, @Param("idUsuario") int idUsuario);
}
