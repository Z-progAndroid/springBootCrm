package com.inmozara.crm.contrato.model.repository;

import com.inmozara.crm.contrato.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> , JpaSpecificationExecutor<Contrato> {
}
