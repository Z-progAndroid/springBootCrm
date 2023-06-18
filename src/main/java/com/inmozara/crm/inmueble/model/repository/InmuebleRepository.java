package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long>, JpaSpecificationExecutor<Inmueble> {
    @Query("SELECT i FROM INMUEBLES i " +
            "LEFT JOIN FETCH i.tipoInmueble " +
            "LEFT JOIN FETCH i.estadoInmueble " +
            "LEFT JOIN FETCH i.pais " +
            "LEFT JOIN FETCH i.provincia " +
            "LEFT JOIN FETCH i.municipio " +
            "LEFT JOIN FETCH i.barrio " +
            "LEFT JOIN FETCH i.usuario")
    List<Inmueble> findAllWithRelations();

    @Query("SELECT i FROM INMUEBLES i " +
            "LEFT JOIN FETCH i.tipoInmueble " +
            "LEFT JOIN FETCH i.estadoInmueble " +
            "LEFT JOIN FETCH i.pais " +
            "LEFT JOIN FETCH i.provincia " +
            "LEFT JOIN FETCH i.municipio " +
            "LEFT JOIN FETCH i.barrio " +
            "LEFT JOIN FETCH i.usuario" +
            " WHERE i.idInmueble = ?1")
    Optional<Inmueble> findByIdInmueble(Long idInmueble);

    @Query("SELECT i.imagen1, i.imagen2, i.imagen3, i.imagen4 FROM INMUEBLES i WHERE i.idInmueble = ?1")
    Optional<String> obtenerimagenes(Long idInmueble);

    @Transactional
    @Modifying
    @Query("UPDATE INMUEBLES i SET i.estadoInmueble = :nuevoEstado WHERE i.estadoInmueble = :estadoActual")
    void actualizarInmueblesPorEstado(@Param("estadoActual") EstadoInmueble estadoActual, @Param("nuevoEstado") EstadoInmueble nuevoEstado);
}