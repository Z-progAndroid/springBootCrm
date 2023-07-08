package com.inmozara.crm.inmueble.model.repository;


import com.inmozara.crm.inmueble.model.Pais;
import com.inmozara.crm.inmueble.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findAllByPais(Pais pais);

    @Transactional
    @Modifying
    @Query("UPDATE PROVINCIAS c SET c.pais = :nuevoEstado WHERE c.pais = :estadoActual")
    void actualizarPais(@Param("estadoActual") Pais paisActual, @Param("nuevoEstado") Pais nuevoPais);
}
