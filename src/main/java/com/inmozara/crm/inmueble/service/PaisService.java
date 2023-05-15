package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.inmueble.model.Pais;
import com.inmozara.crm.inmueble.model.dto.PaisDTO;
import com.inmozara.crm.inmueble.model.repository.PaisRepository;
import com.inmozara.crm.inmueble.service.interfaces.IPais;
import com.inmozara.crm.utils.ObjectMapperUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService implements IPais {
    @Autowired
    private PaisRepository paisRepository;


    @Override
    public PaisDTO findByIdPais(String idPais) {
        Pais pais = paisRepository.findByIdPais(idPais)
                .orElseThrow(() -> new RuntimeException("No se encontro el pais con id: " + idPais));
        return ObjectMapperUtils.map(pais, PaisDTO.class);
    }

    @Override
    public PaisDTO save(@Valid PaisDTO paisDTO) {
        Pais pais = paisRepository.save(ObjectMapperUtils.map(paisDTO, Pais.class));
        return ObjectMapperUtils.map(pais, PaisDTO.class);
    }

    @Override
    public PaisDTO update(@Valid PaisDTO paisDTO) {
        Pais pais = paisRepository.save(ObjectMapperUtils.map(paisDTO, Pais.class));
        return ObjectMapperUtils.map(pais, PaisDTO.class);
    }

    @Override
    public PaisDTO delete(String idPais) {
        PaisDTO paisDTO = findByIdPais(idPais);
        paisRepository.delete(ObjectMapperUtils.map(paisDTO, Pais.class));
        return paisDTO;
    }

    @Override
    public PaisDTO find(String id) {
        Pais pais = Optional.of(paisRepository.getReferenceById(id))
                .orElseThrow(() -> new RuntimeException("No se encontro el pais con id: " + id));
        return ObjectMapperUtils.map(pais, PaisDTO.class);

    }

    @Override
    public List<PaisDTO> findAll() {
        List<Pais> pais = paisRepository.findAll();
        if (pais.isEmpty()) {
            throw new RuntimeException("No se encontraron paises");
        }
        return ObjectMapperUtils.mapAll(pais, PaisDTO.class);
    }
}
