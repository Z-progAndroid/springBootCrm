package com.inmozara.crm.usuario.model.search;

import com.inmozara.crm.usuario.model.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserSearch implements Specification<Usuario>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private List<Integer> roles;

    @Override
    public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (usuario != null && usuario.getNombre() != null) {
            predicates.add(cb.like(root.get("nombre"), "%" + usuario.getNombre() + "%"));
        }

        if (usuario != null && usuario.getApellido() != null) {
            predicates.add(cb.like(root.get("apellido"), "%" + usuario.getApellido() + "%"));
        }
        if (usuario != null && usuario.getEmail() != null) {
            predicates.add(cb.like(root.get("email"), "%" + usuario.getEmail() + "%"));
        }
        if (usuario != null && usuario.getTelefono() != null) {
            predicates.add(cb.like(root.get("telefono"), "%" + usuario.getTelefono() + "%"));
        }
        if (usuario != null && usuario.getDni() != null) {
            predicates.add(cb.like(root.get("dni"), "%" + usuario.getDni() + "%"));
        }
        if (usuario != null && usuario.getDireccion() != null) {
            predicates.add(cb.like(root.get("direccion"), "%" + usuario.getDireccion() + "%"));
        }
        if (usuario != null && usuario.getRol().getIdRol() != 0) {
            predicates.add(cb.equal(root.get("estadoUsuario"), usuario.getRol().getIdRol()));
        }
        if (usuario != null && usuario.getEstadoUsuario().getIdEstadoUsuario() != 0) {
            predicates.add(cb.equal(root.get("estadoUsuario"), usuario.getEstadoUsuario().getIdEstadoUsuario()));
        }
        if (roles != null && !roles.isEmpty()) {
            predicates.add(root.get("rol").get("idRol").in(roles));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}