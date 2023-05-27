package com.inmozara.crm.inmueble.model.search;

import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
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


    private InmuebleDTO inmuebleDTO;

    @Override
    public Predicate toPredicate(Root<Inmueble> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if (inmuebleDTO.getIdInmueble() != 0) {
            predicates.add(cb.equal(root.get("idInmueble"), inmuebleDTO.getIdInmueble()));
        }
        if (inmuebleDTO.getDescripcion() != null && !inmuebleDTO.getDescripcion().isEmpty()) {
            predicates.add(cb.like(root.get("descripcion"), "%" + inmuebleDTO.getDescripcion() + "%"));
        }
        if (inmuebleDTO.getDireccion() != null && !inmuebleDTO.getDireccion().isEmpty()) {
            predicates.add(cb.like(root.get("direccion"), "%" + inmuebleDTO.getDireccion() + "%"));
        }
        if (inmuebleDTO.getCodigoPostal() != null && !inmuebleDTO.getCodigoPostal().isEmpty()) {
            predicates.add(cb.like(root.get("codigoPostal"), "%" + inmuebleDTO.getCodigoPostal() + "%"));
        }
        if (inmuebleDTO.getPrecio_venta() != 0) {
            predicates.add(cb.equal(root.get("precio_venta"), inmuebleDTO.getPrecio_venta()));
        }
        if (inmuebleDTO.getPrecio_alquiler() != 0) {
            predicates.add(cb.equal(root.get("precio_alquiler"), inmuebleDTO.getPrecio_alquiler()));
        }
        if (inmuebleDTO.getNumHabitaciones() != 0) {
            predicates.add(cb.equal(root.get("numHabitaciones"), inmuebleDTO.getNumHabitaciones()));
        }
        if (inmuebleDTO.getNumBanos() != 0) {
            predicates.add(cb.equal(root.get("numBanos"), inmuebleDTO.getNumBanos()));
        }
        if (inmuebleDTO.getMetros_cuadrados() != null) {
            predicates.add(cb.equal(root.get("metros_cuadrados"), inmuebleDTO.getMetros_cuadrados()));
        }
        if (inmuebleDTO.getAno_construccion() != 0) {
            predicates.add(cb.equal(root.get("ano_construccion"), inmuebleDTO.getAno_construccion()));
        }
        if (inmuebleDTO.getTipoInmueble() != null && !inmuebleDTO.getTipoInmueble().isEmpty()) {
            predicates.add(cb.equal(root.get("tipoInmueble").get("id"), Integer.parseInt(inmuebleDTO.getTipoInmueble())));
        }
        if (inmuebleDTO.getEstadoInmueble() != null && !inmuebleDTO.getEstadoInmueble().isEmpty()) {
            predicates.add(cb.equal(root.get("estadoInmueble").get("idEstadoInmueble"), Integer.parseInt(inmuebleDTO.getEstadoInmueble())));
        }
        if (!inmuebleDTO.getIdPais().isEmpty()) {
            predicates.add(cb.equal(root.get("pais").get("idPais"), inmuebleDTO.getIdPais()));

        }
        if (inmuebleDTO.getIdUsuario() != 0) {
            predicates.add(cb.equal(root.get("usuario").get("idUsuario"), inmuebleDTO.getIdUsuario()));
        }

        if (inmuebleDTO.getBarrio() != null && !inmuebleDTO.getBarrio().isEmpty()) {
            predicates.add(cb.equal(root.get("barrio").get("idBarrio"), Integer.parseInt(inmuebleDTO.getBarrio())));
        }

        if (inmuebleDTO.getMunicipio() != null && !inmuebleDTO.getMunicipio().isEmpty()) {
            predicates.add(cb.equal(root.get("municipio").get("idMunicipio"), Integer.parseInt(inmuebleDTO.getMunicipio())));
        }

        if (inmuebleDTO.getProvincia() != null && !inmuebleDTO.getProvincia().isEmpty()) {
            predicates.add(cb.equal(root.get("provincia").get("idProvincia"), Integer.parseInt(inmuebleDTO.getProvincia())));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
