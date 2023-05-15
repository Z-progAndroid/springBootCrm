package com.inmozara.crm.inmueble.model.repository;


import com.inmozara.crm.inmueble.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {


}
