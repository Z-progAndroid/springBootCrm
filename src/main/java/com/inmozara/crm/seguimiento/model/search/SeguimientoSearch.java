package com.inmozara.crm.seguimiento.model.search;

import com.inmozara.crm.seguimiento.model.Seguimiento;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeguimientoSearch implements Specification<Seguimiento>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Seguimiento seguimiento;

    @Override
    public Predicate toPredicate(Root<Seguimiento> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (seguimiento != null && seguimiento.getIdSeguimiento() != null) {
            predicates.add(cb.equal(root.get("idSeguimiento"), seguimiento.getIdSeguimiento()));
        }
        if (seguimiento != null && seguimiento.getDescripcion() != null && !seguimiento.getDescripcion().isEmpty()) {
            predicates.add(cb.like(root.get("descripcion"), "%" + seguimiento.getDescripcion() + "%"));
        }
        if (seguimiento != null && seguimiento.getTipoSeguimiento() != null && seguimiento.getTipoSeguimiento().getIdTipoSeguimiento() != 0) {
            predicates.add(cb.equal(root.get("tipoSeguimiento").get("idTipoSeguimiento"), seguimiento.getTipoSeguimiento().getIdTipoSeguimiento()));
        }
        if (seguimiento != null && seguimiento.getInmueble() != null && seguimiento.getInmueble().getIdInmueble() != 0) {
            predicates.add(cb.equal(root.get("inmueble").get("idInmueble"), seguimiento.getInmueble().getIdInmueble()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
