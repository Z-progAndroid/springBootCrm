package com.inmozara.crm.tarea.model.repository;

import com.inmozara.crm.tarea.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> , JpaSpecificationExecutor<Tarea> {
    @Query("SELECT t FROM tareas t WHERE t.estadoTarea.idEstadoTarea = ?1")
    List<Tarea> findAllTareaConIdEstado(Integer idTarea);
}
