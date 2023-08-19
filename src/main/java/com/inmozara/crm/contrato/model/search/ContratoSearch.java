package com.inmozara.crm.contrato.model.search;

import com.inmozara.crm.contrato.model.Contrato;
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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ContratoSearch implements Specification<Contrato>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Contrato contrato;

    @Override
    public Predicate toPredicate(Root<Contrato> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (contrato != null && contrato.getIdContrato() != null) {
            predicates.add(cb.equal(root.get("idContrato"), contrato.getIdContrato()));
        }
        if (contrato != null && contrato.getTitulo() != null && !contrato.getTitulo().isEmpty()) {
            predicates.add(cb.like(root.get("titulo"), "%" + contrato.getTitulo() + "%"));
        }
        if (contrato != null && contrato.getFechaInicio() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaInicioStr = sdf.format(contrato.getFechaInicio());
            String fechaInicioSql = fechaInicioStr.substring(0, 10); // Obtener solo la parte de la fecha
            predicates.add(cb.greaterThanOrEqualTo(cb.function("DATE", String.class, root.get("fechaInicio")), fechaInicioSql));
        }
        if (contrato != null && contrato.getFechaFinalizacion() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFinalizacionStr = sdf.format(contrato.getFechaFinalizacion());
            String fechaFinalizacionSql = fechaFinalizacionStr.substring(0, 10); // Obtener solo la parte de la fecha
            predicates.add(cb.lessThanOrEqualTo(cb.function("DATE", String.class, root.get("fechaFinalizacion")), fechaFinalizacionSql));
        }
        if (contrato != null && contrato.getDescripcion() != null && !contrato.getDescripcion().isEmpty()) {
            predicates.add(cb.like(root.get("descripcion"), "%" + contrato.getDescripcion() + "%"));
        }
        if (contrato != null && contrato.getTerminosYCondiciones() != null && !contrato.getTerminosYCondiciones().isEmpty()) {
            predicates.add(cb.like(root.get("terminosYCondiciones"), "%" + contrato.getTerminosYCondiciones() + "%"));
        }
        if (contrato != null && contrato.getValor() != 0) {
            predicates.add(cb.equal(root.get("valor"), contrato.getValor()));
        }
        if (contrato != null && contrato.getObservaciones() != null && !contrato.getObservaciones().isEmpty()) {
            predicates.add(cb.like(root.get("observaciones"), "%" + contrato.getObservaciones() + "%"));
        }
        if (contrato != null && contrato.getTipoPago() != null && contrato.getTipoPago().getIdTipoPago() != null && contrato.getTipoPago().getIdTipoPago() != 0) {
            predicates.add(cb.equal(root.get("tipoPago").get("idTipoPago"), contrato.getTipoPago().getIdTipoPago()));
        }
        if (contrato != null && contrato.getEstadoContrato() != null && contrato.getEstadoContrato().getIdEstadoContrato() != null && contrato.getEstadoContrato().getIdEstadoContrato() != 0) {
            predicates.add(cb.equal(root.get("estadoContrato").get("idEstadoContrato"), contrato.getEstadoContrato().getIdEstadoContrato()));
        }
        if (contrato != null && contrato.getTipoContrato() != null && contrato.getTipoContrato().getIdTipoContrato() != null && contrato.getTipoContrato().getIdTipoContrato() != 0) {
            predicates.add(cb.equal(root.get("tipoContrato").get("idTipoContrato"), contrato.getTipoContrato().getIdTipoContrato()));
        }
        if (contrato != null && contrato.getInmueble() != null && contrato.getInmueble().getIdInmueble() != 0) {
            predicates.add(cb.equal(root.get("inmueble").get("idInmueble"), contrato.getInmueble().getIdInmueble()));
        }
        if (contrato != null && contrato.getCliente() != null && contrato.getCliente().getIdUsuario() != 0) {
            predicates.add(cb.equal(root.get("cliente").get("idUsuario"), contrato.getCliente().getIdUsuario()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
