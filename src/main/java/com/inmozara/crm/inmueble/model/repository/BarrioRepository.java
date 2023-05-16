package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.Barrio;
import com.inmozara.crm.inmueble.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarrioRepository extends JpaRepository<Barrio, Integer> {
    List<Barrio> findAllByMunicipio(Municipio municipio);
}
