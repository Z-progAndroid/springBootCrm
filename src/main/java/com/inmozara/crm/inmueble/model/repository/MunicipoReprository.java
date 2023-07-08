package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.Municipio;
import com.inmozara.crm.inmueble.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MunicipoReprository extends JpaRepository<Municipio, Integer> {

    List<Municipio> findAllByProvincia(Provincia provincia);

    @Transactional
    @Modifying
    @Query("UPDATE MUNICIPIOS c SET c.provincia = :nuevoEstado WHERE c.provincia = :estadoActual")
    void actualizarProvincia(@Param("estadoActual") Provincia provinciaActual, @Param("nuevoEstado") Provincia nuevaProvincia);
}
