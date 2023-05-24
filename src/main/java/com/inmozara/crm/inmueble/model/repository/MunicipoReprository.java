package com.inmozara.crm.inmueble.model.repository;

import com.inmozara.crm.inmueble.model.Municipio;
import com.inmozara.crm.inmueble.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipoReprository extends JpaRepository<Municipio, Integer> {

    List<Municipio> findAllByProvincia(Provincia provincia);
}
