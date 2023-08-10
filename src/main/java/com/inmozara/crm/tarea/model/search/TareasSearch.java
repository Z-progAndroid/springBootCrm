package com.inmozara.crm.tarea.model.search;

import com.inmozara.crm.tarea.model.Tarea;
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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareasSearch implements Specification<Tarea>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Tarea tarea;

    @Override
    public Predicate toPredicate(Root<Tarea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (tarea != null && tarea.getIdTarea() != 0) {
            predicates.add(cb.equal(root.get("idTarea"), tarea.getIdTarea()));
        }
        if (tarea != null && tarea.getTitulo() != null && !tarea.getTitulo().isEmpty()) {
            predicates.add(cb.like(root.get("titulo"), "%" + tarea.getTitulo() + "%"));
        }
        if (tarea != null && tarea.getDescripcion() != null && !tarea.getDescripcion().isEmpty()) {
            predicates.add(cb.like(root.get("descripcion"), "%" + tarea.getDescripcion() + "%"));
        }
        if (tarea != null && tarea.getFechaInicio() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFinalizacionStr = sdf.format(tarea.getFechaInicio());
            String fechaFinalizacionSql = fechaFinalizacionStr.substring(0, 10);
            predicates.add(cb.greaterThanOrEqualTo(cb.function("DATE", String.class, root.get("fechaInicio")), fechaFinalizacionSql));
        }
        if (tarea != null && tarea.getFechaFin() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFinalizacionStr = sdf.format(tarea.getFechaFin());
            String fechaFinalizacionSql = fechaFinalizacionStr.substring(0, 10); // Obtener solo la parte de la fecha
            predicates.add(cb.lessThanOrEqualTo(cb.function("DATE", String.class, root.get("fechaFin")), fechaFinalizacionSql));
        }
        if (tarea != null && tarea.getEstadoTarea() != null && tarea.getEstadoTarea().getIdEstadoTarea() != 0) {
            predicates.add(cb.equal(root.get("estadoTarea").get("idEstadoTarea"), tarea.getEstadoTarea().getIdEstadoTarea()));
        }
        if (tarea != null && tarea.getUsuario() != null && tarea.getUsuario().getIdUsuario() != 0) {
            predicates.add(cb.equal(root.get("usuario").get("idUsuario"), tarea.getUsuario().getIdUsuario()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
