package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {
}
