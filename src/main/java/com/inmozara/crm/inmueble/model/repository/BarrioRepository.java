package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.Barrio;
import com.inmozara.crm.inmueble.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BarrioRepository extends JpaRepository<Barrio, Integer> {
    List<Barrio> findAllByMunicipio(Municipio municipio);
    @Transactional
    @Modifying
    @Query("UPDATE BARRIOS c SET c.municipio = :nuevoEstado WHERE c.municipio = :estadoActual")
    void actualizarMunicipio(@Param("estadoActual") Municipio municipioActual, @Param("nuevoEstado") Municipio nuevoMunicipio);
}
