package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.EstadoInmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoInmuebleRepository extends JpaRepository<EstadoInmueble, Integer> {

}
