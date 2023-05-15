package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, String> {
    Optional<Pais> findByIdPais(String idPais);
}
