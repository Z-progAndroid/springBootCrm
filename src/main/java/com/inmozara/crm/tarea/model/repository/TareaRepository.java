package com.inmozara.crm.tarea.model.repository;

import com.inmozara.crm.tarea.model.EstadoTarea;
import com.inmozara.crm.tarea.model.Tarea;
import com.inmozara.crm.usuario.model.Usuario;
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
public interface TareaRepository extends JpaRepository<Tarea, Integer>, JpaSpecificationExecutor<Tarea> {
    @Transactional
    @Modifying
    @Query("UPDATE tareas t SET t.estadoTarea = :nuevoEstado WHERE t.estadoTarea = :estadoActual")
    void actualizarTareasPorEstado(@Param("estadoActual") EstadoTarea estadoActual, @Param("nuevoEstado") EstadoTarea nuevoEstado);

    @Transactional
    @Modifying
    @Query("UPDATE tareas t SET t.usuario = :nuevoEstado WHERE t.usuario = :estadoActual")
    void actualizarUsuarioTarea(@Param("estadoActual") Usuario estadoActual, @Param("nuevoEstado") Usuario nuevoEstado);

    @Query("SELECT t FROM tareas t WHERE MONTH(t.fechaCreacion) = MONTH(:fechaActual) AND YEAR(t.fechaCreacion) = YEAR(:fechaActual)")
    List<Tarea> findTareasCreadasEsteMes(@Param("fechaActual") Date fechaActual);

    @Query("SELECT t FROM tareas t WHERE MONTH(t.fechaCreacion) = MONTH(:fechaActual) AND YEAR(t.fechaCreacion) = YEAR(:fechaActual) AND t.usuario.idUsuario=:idUsuario")
    List<Tarea> findTareasCreadasEsteMesPorUsuario(@Param("fechaActual") Date fechaActual, @Param("idUsuario") int idUsuario);
}
