package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.inmueble.model.Municipio;
import com.inmozara.crm.inmueble.model.Provincia;
import com.inmozara.crm.inmueble.model.dto.MunicipoDTO;
import com.inmozara.crm.inmueble.model.repository.MunicipoReprository;
import com.inmozara.crm.inmueble.service.interfaces.IMunicipo;
import com.inmozara.crm.utils.ObjectMapperUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MunicipoService implements IMunicipo {
    @Autowired
    MunicipoReprository municipoReprository;

    @Override

    public List<MunicipoDTO> findAllByProvincia(int idProvincia) {
        List<Municipio> municipos = municipoReprository.findAllByProvincia(Provincia
                .builder()
                .idProvincia(idProvincia)
                .build());
        if (municipos.isEmpty()) {
            throw new RuntimeException("No se encontraron municipios para la provincia con id: " + idProvincia);
        }
        return ObjectMapperUtils.mapAll(municipos, MunicipoDTO.class);
    }

    @Override
    public MunicipoDTO save(MunicipoDTO municipoDTO) {
        Municipio municipio = ObjectMapperUtils.map(municipoDTO, Municipio.class);
        Municipio municipio1 = municipoReprository.save(municipio);
        return ObjectMapperUtils.map(municipio1, MunicipoDTO.class);
    }

    @Override
    public MunicipoDTO update(MunicipoDTO municipoDTO) {
        Municipio municipio = ObjectMapperUtils.map(municipoDTO, Municipio.class);
        Municipio municipio1 = municipoReprository.save(municipio);
        return ObjectMapperUtils.map(municipio1, MunicipoDTO.class);
    }

    @Override
    public MunicipoDTO delete(Integer idMunicipio) {
        if (!municipoReprository.existsById(idMunicipio)) {
            throw new RuntimeException("No existe un municipio con el id:" + idMunicipio);

        }
        municipoReprository.deleteById(idMunicipio);
        return null;
    }

    @Override
    public MunicipoDTO find(Integer idMunicipio) {
        Municipio municipio = municipoReprository.findById(idMunicipio)
                .orElseThrow(() -> new RuntimeException("No se encontro el municipio con id: " + idMunicipio));
        return ObjectMapperUtils.map(municipio, MunicipoDTO.class);
    }

    @Override
    public List<MunicipoDTO> findAll() {
        List<Municipio> municipos = municipoReprository.findAll();
        if (municipos.isEmpty()) {
            throw new RuntimeException("No se encontraron municipios");
        }
        return ObjectMapperUtils.mapAll(municipos, MunicipoDTO.class);
    }

    public static class BarrioService {
    }
}
