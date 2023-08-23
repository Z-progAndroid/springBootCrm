package com.inmozara.crm.cita.model.repository;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.EstadoCita;
import com.inmozara.crm.cita.model.TipoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>, JpaSpecificationExecutor<Cita> {

    @Transactional
    @Modifying
    @Query("UPDATE citas c SET c.estadoCita = :nuevoEstado WHERE c.estadoCita = :estadoActual")
    void actualizarCitasPorEstado(@Param("estadoActual") EstadoCita estadoActual, @Param("nuevoEstado") EstadoCita nuevoEstado);

    @Transactional
    @Modifying
    @Query("UPDATE citas c SET c.tipoCita = :nuevoEstado WHERE c.tipoCita = :estadoActual")
    void actualizarCitasPorTipo(@Param("estadoActual") TipoCita estadoActual, @Param("nuevoEstado") TipoCita nuevoEstado);

    @Query("SELECT c FROM citas c WHERE c.inmueble.idInmueble = :idInmueble AND c.fechaInicio BETWEEN :startDate AND :endDate")
    List<Cita> countConflictingCitas(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("idInmueble") int idInmueble);

    @Query("SELECT c FROM citas c WHERE c.estadoCita.estadoCita IN ('PENDIENTE', 'ACTIVA')")
    List<Cita> findCitasPendientesYActivas();

    @Query("SELECT c FROM citas c WHERE MONTH(c.fechaCreacion) = MONTH(:fechaActual) AND YEAR(c.fechaCreacion) = YEAR(:fechaActual)")
    List<Cita> findCitasCreadasEsteMes(@Param("fechaActual") Date fechaActual);

    @Query("SELECT c FROM citas c WHERE MONTH(c.fechaCreacion) = MONTH(:fechaActual) AND YEAR(c.fechaCreacion) = YEAR(:fechaActual) AND c.inmueble.usuario.idUsuario=:idUsuario")
    List<Cita> findCitasCreadasEsteMesPorUsuario(@Param("fechaActual") Date fechaActual, @Param("idUsuario") int idUsuario);

}
