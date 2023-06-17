package com.inmozara.crm.cita.model.repository;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer>, JpaSpecificationExecutor<Cita> {

    @Transactional
    @Modifying
    @Query("UPDATE citas c SET c.estadoCita = :nuevoEstado WHERE c.estadoCita = :estadoActual")
    void actualizarCitasPorEstado(@Param("estadoActual") EstadoCita estadoActual, @Param("nuevoEstado") EstadoCita nuevoEstado);


}
