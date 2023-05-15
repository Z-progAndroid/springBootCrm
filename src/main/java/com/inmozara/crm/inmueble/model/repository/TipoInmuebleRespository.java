package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.TipoInmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInmuebleRespository extends JpaRepository<TipoInmueble, Long> {
}
