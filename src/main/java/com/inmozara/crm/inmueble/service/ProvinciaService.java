package com.inmozara.crm.inmueble.service;

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
    public List<ProvinciaDTO> findbyPais(String idPais) {
//        List<Provincia> provincias = provinciaRepository.filterbyPais(idPais);
//        if (provincias.isEmpty()) {
//            throw new RuntimeException("no se ha encontrado provincias por el id de pais: " + idPais);
//        }
//        return ObjectMapperUtils.mapAll(provincias, ProvinciaDTO.class);
        return null;
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
    public ProvinciaDTO delete(Integer integer) {
        ProvinciaDTO provinciaDTO = this.find(integer);
        provinciaRepository.delete(ObjectMapperUtils.map(provinciaDTO, Provincia.class));
        return provinciaDTO;
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
