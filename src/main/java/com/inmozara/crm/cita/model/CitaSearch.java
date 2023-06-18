package com.inmozara.crm.cita.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CitaSearch implements Specification<Cita>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Cita cita;

    @Override
    public Predicate toPredicate(Root<Cita> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (cita != null && cita.getIdCita() != 0) {
            predicates.add(cb.equal(root.get("idCita"), cita.getIdCita()));
        }
        if (cita != null && cita.getDescripcion() != null && !cita.getDescripcion().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("descripcion")), "%" + cita.getDescripcion() + "%"));
        }
        if (cita != null && cita.getFechaCita() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFinalizacionStr = sdf.format(cita.getFechaCita());
            String fechaFinalizacionSql = fechaFinalizacionStr.substring(0, 10);
            predicates.add(cb.equal(cb.function("DATE", String.class, root.get("fechaCita")), fechaFinalizacionSql));
        }
        if (cita != null && cita.getTipoCita() != null && cita.getTipoCita().getIdTipoCita() != 0) {
            predicates.add(cb.equal(root.get("tipoCita").get("idTipoCita"), cita.getTipoCita().getIdTipoCita()));
        }
        if (cita != null && cita.getEstadoCita() != null && cita.getEstadoCita().getIdEstadoCita() != 0) {
            predicates.add(cb.equal(root.get("estadoCita").get("idEstadoCita"), cita.getEstadoCita().getIdEstadoCita()));
        }
        if (cita != null && cita.getAgente() != null && cita.getAgente().getIdUsuario() != 0) {
            predicates.add(cb.equal(root.get("agente").get("idUsuario"), cita.getAgente().getIdUsuario()));
        }
        if (cita != null && cita.getCliente() != null && cita.getCliente().getIdUsuario() != 0) {
            predicates.add(cb.equal(root.get("cliente").get("idUsuario"), cita.getCliente().getIdUsuario()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
