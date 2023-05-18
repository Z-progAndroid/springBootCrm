package com.inmozara.crm.tarea.model.repository;

import com.inmozara.crm.tarea.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Integer> {
}
