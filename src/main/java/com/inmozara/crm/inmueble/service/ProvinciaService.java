package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.inmueble.model.Pais;
import com.inmozara.crm.inmueble.model.Provincia;
import com.inmozara.crm.inmueble.model.dto.ProvinciaDTO;
import com.inmozara.crm.inmueble.model.repository.ProvinciaRepository;
import com.inmozara.crm.inmueble.service.interfaces.IProvincia;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciaService implements IProvincia {
    @Autowired
    ProvinciaRepository provinciaRepository;

    @Override
    public List<ProvinciaDTO> findAllByPais(String idPais) {
        List<Provincia> provincias = provinciaRepository.findAllByPais(Pais
                .builder()
                .idPais(idPais)
                .build());
        if (provincias.isEmpty()) {
            throw new RuntimeException("no se ha encontrado provincias");
        }
        return ObjectMapperUtils.mapAll(provincias, ProvinciaDTO.class);
    }

    @Override
    public ProvinciaDTO save(ProvinciaDTO provinciaDTO) {
        Provincia provincia = provinciaRepository.save(ObjectMapperUtils.map(provinciaDTO, Provincia.class));
        return ObjectMapperUtils.map(provincia, ProvinciaDTO.class);
    }

    @Override
    public ProvinciaDTO update(ProvinciaDTO provinciaDTO) {
        Provincia provincia = provinciaRepository.save(ObjectMapperUtils.map(provinciaDTO, Provincia.class));
        return ObjectMapperUtils.map(provincia, ProvinciaDTO.class);
    }

    @Override
    public ProvinciaDTO delete(Integer idPorvincia) {
        if (!provinciaRepository.existsById(idPorvincia)) {
            throw new RuntimeException("No existe una  provincia con el id:" + idPorvincia);

        }
        provinciaRepository.deleteById(idPorvincia);
        return null;
    }

    @Override
    public ProvinciaDTO find(Integer integer) {
        Provincia provincia = Optional.of(provinciaRepository.getReferenceById(integer))
                .orElseThrow(() -> new RuntimeException("No se encontro la provincia con id: " + integer));
        return ObjectMapperUtils.map(provincia, ProvinciaDTO.class);
    }

    @Override
    public List<ProvinciaDTO> findAll() {
        List<Provincia> provincias = provinciaRepository.findAll();
        if (provincias.isEmpty()) {
            throw new RuntimeException("no se ha encontrado provincias");
        }
        return ObjectMapperUtils.mapAll(provincias, ProvinciaDTO.class);
    }
}
