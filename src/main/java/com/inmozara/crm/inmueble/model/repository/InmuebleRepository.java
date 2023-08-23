package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.TipoInmueble;
import com.inmozara.crm.usuario.model.Usuario;
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
            "LEFT JOIN FETCH i.usuario" +
            " WHERE i.idInmueble = ?1")
    Optional<Inmueble> findByIdInmueble(Long idInmueble);
    @Transactional
    @Modifying
    @Query("UPDATE INMUEBLES i SET i.estadoInmueble = :nuevoEstado WHERE i.estadoInmueble = :estadoActual")
    void actualizarInmueblesPorEstado(@Param("estadoActual") EstadoInmueble estadoActual, @Param("nuevoEstado") EstadoInmueble nuevoEstado);
    @Transactional
    @Modifying
    @Query("UPDATE INMUEBLES i SET i.estadoInmueble = :nuevoEstado WHERE i.idInmueble = :idInmueble")
    void actualizarInmueblesEstado(@Param("idInmueble") int idInmueble, @Param("nuevoEstado") EstadoInmueble nuevoEstado);

    @Transactional
    @Modifying
    @Query("UPDATE INMUEBLES i SET i.tipoInmueble = :nuevoEstado WHERE i.tipoInmueble = :estadoActual")
    void actualizarInmueblesPorTipo(@Param("estadoActual") TipoInmueble estadoActual, @Param("nuevoEstado") TipoInmueble nuevoEstado);
    @Transactional
    @Modifying
    @Query("UPDATE INMUEBLES i SET i.usuario = :nuevoEstado WHERE i.usuario = :estadoActual")
    void actualizarAgenteInmuebles(@Param("estadoActual") Usuario estadoActual, @Param("nuevoEstado") Usuario nuevoEstado);
    @Transactional
    @Modifying
    @Query("UPDATE INMUEBLES i SET i.imagen1 = CASE WHEN :fieldName = 'imagen1' THEN :bytes ELSE i.imagen1 END, " +
            "i.imagen2 = CASE WHEN :fieldName = 'imagen2' THEN :bytes ELSE i.imagen2 END, " +
            "i.imagen3 = CASE WHEN :fieldName = 'imagen3' THEN :bytes ELSE i.imagen3 END, " +
            "i.imagen4 = CASE WHEN :fieldName = 'imagen4' THEN :bytes ELSE i.imagen4 END " +
            "WHERE i.idInmueble = :idInmueble")
    void actualizarImagen(@Param("idInmueble") Long idInmueble, @Param("fieldName") String fieldName, @Param("bytes") byte[] bytes);
    @Query("SELECT i FROM INMUEBLES i WHERE i.usuario.idUsuario = :idUsuario")
    List<Inmueble> findInmueblesByUsuarioId(@Param("idUsuario") Long idUsuario);

}