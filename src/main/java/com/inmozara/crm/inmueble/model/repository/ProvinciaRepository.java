package com.inmozara.crm.inmueble.model.repository;


import com.inmozara.crm.inmueble.model.Pais;
import com.inmozara.crm.inmueble.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findAllByPais(Pais pais);
}
