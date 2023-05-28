package com.inmozara.crm.tarea.model.repository;

import com.inmozara.crm.tarea.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> , JpaSpecificationExecutor<Tarea> {
}
