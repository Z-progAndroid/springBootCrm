package com.inmozara.crm.tarea.model.repository;

import com.inmozara.crm.tarea.model.EstadoTarea;
import com.inmozara.crm.tarea.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer>, JpaSpecificationExecutor<Tarea> {
    @Transactional
    @Modifying
    @Query("UPDATE tareas t SET t.estadoTarea = :nuevoEstado WHERE t.estadoTarea = :estadoActual")
    void actualizarTareasPorEstado(@Param("estadoActual") EstadoTarea estadoActual, @Param("nuevoEstado") EstadoTarea nuevoEstado);
}
