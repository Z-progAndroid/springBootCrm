package com.inmozara.crm.inmueble.model.search;

import com.inmozara.crm.inmueble.model.Inmueble;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InmuebleSearch implements Specification<Inmueble>, Serializable {


    private Inmueble inmueble;

    @Override
    public Predicate toPredicate(Root<Inmueble> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (inmueble != null && inmueble.getIdInmueble() != 0) {
            predicates.add(cb.equal(root.get("idInmueble"), inmueble.getIdInmueble()));
        }
        if (inmueble != null && inmueble.getDescripcion() != null && !inmueble.getDescripcion().isEmpty()) {
            predicates.add(cb.like(root.get("descripcion"), "%" + inmueble.getDescripcion() + "%"));
        }
        if (inmueble != null && inmueble.getDireccion() != null && !inmueble.getDireccion().isEmpty()) {
            predicates.add(cb.like(root.get("direccion"), "%" + inmueble.getDireccion() + "%"));
        }
        if (inmueble != null && inmueble.getCodigoPostal() != null && !inmueble.getCodigoPostal().isEmpty()) {
            predicates.add(cb.like(root.get("codigoPostal"), "%" + inmueble.getCodigoPostal() + "%"));
        }
        if (inmueble != null && inmueble.getPrecio_venta() != 0) {
            predicates.add(cb.equal(root.get("precio_venta"), inmueble.getPrecio_venta()));
        }
        if (inmueble != null && inmueble.getPrecio_alquiler() != 0) {
            predicates.add(cb.equal(root.get("precio_alquiler"), inmueble.getPrecio_alquiler()));
        }
        if (inmueble != null && inmueble.getNumHabitaciones() != 0) {
            predicates.add(cb.equal(root.get("numHabitaciones"), inmueble.getNumHabitaciones()));
        }
        if (inmueble != null && inmueble.getNumBanos() != 0) {
            predicates.add(cb.equal(root.get("numBanos"), inmueble.getNumBanos()));
        }
        if (inmueble != null && inmueble.getMetros_cuadrados() != null && inmueble.getMetros_cuadrados() != 0) {
            predicates.add(cb.equal(root.get("metros_cuadrados"), inmueble.getMetros_cuadrados()));
        }
        if (inmueble != null && inmueble.getAno_construccion() != 0) {
            predicates.add(cb.equal(root.get("ano_construccion"), inmueble.getAno_construccion()));
        }
        if (inmueble != null && inmueble.getTipoInmueble() != null && inmueble.getTipoInmueble().getId() != 0) {
            predicates.add(cb.equal(root.get("tipoInmueble").get("id"), inmueble.getTipoInmueble().getId()));
        }
        if (inmueble != null && inmueble.getEstadoInmueble() != null && inmueble.getEstadoInmueble().getIdEstadoInmueble() != 0) {
            predicates.add(cb.equal(root.get("estadoInmueble").get("idEstadoInmueble"), inmueble.getEstadoInmueble().getIdEstadoInmueble()));
        }
        if (inmueble != null && inmueble.getPais() != null && !inmueble.getPais().getIdPais().isEmpty()) {
            predicates.add(cb.equal(root.get("pais").get("idPais"), inmueble.getPais().getIdPais()));
        }
        if (inmueble != null && inmueble.getProvincia() != null && inmueble.getProvincia().getIdProvincia() != 0) {
            predicates.add(cb.equal(root.get("provincia").get("idProvincia"), inmueble.getProvincia().getIdProvincia()));
        }
        if (inmueble != null && inmueble.getMunicipio() != null && inmueble.getMunicipio().getIdMunicipio() != 0) {
            predicates.add(cb.equal(root.get("municipio").get("idMunicipio"), inmueble.getMunicipio().getIdMunicipio()));
        }
        if (inmueble != null && inmueble.getBarrio() != null && inmueble.getBarrio().getIdBarrio() != 0) {
            predicates.add(cb.equal(root.get("barrio").get("idBarrio"), inmueble.getBarrio().getIdBarrio()));
        }
        if (inmueble != null && inmueble.getUsuario() != null && inmueble.getUsuario().getIdUsuario() != 0) {
            predicates.add(cb.equal(root.get("usuario").get("idUsuario"), inmueble.getUsuario().getIdUsuario()));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
