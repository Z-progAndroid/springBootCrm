package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Municipio;
import com.inmozara.crm.inmueble.model.Provincia;
import com.inmozara.crm.inmueble.model.dto.MunicipoDTO;
import com.inmozara.crm.inmueble.model.repository.MunicipoReprository;
import com.inmozara.crm.inmueble.service.interfaces.IMunicipo;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            throw new RecursoNoEncontrado("No se encontraron municipios para la provincia con id: " + idProvincia);
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
    public MensajeDTO delete(Integer idMunicipio) {
        if (!municipoReprository.existsById(idMunicipio)) {
            throw new RecursoNoEncontrado("No existe un municipio con el id:" + idMunicipio);

        }
        municipoReprository.deleteById(idMunicipio);
        return MensajeDTO.builder()
                .mensaje("Se elimino el municipio con id: " + idMunicipio)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public MunicipoDTO find(Integer idMunicipio) {
        Municipio municipio = municipoReprository.findById(idMunicipio)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el municipio con id: " + idMunicipio));
        return ObjectMapperUtils.map(municipio, MunicipoDTO.class);
    }

    @Override
    public List<MunicipoDTO> findAll() {
        List<Municipio> municipos = municipoReprository.findAll();
        if (municipos.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron municipios");
        }
        return ObjectMapperUtils.mapAll(municipos, MunicipoDTO.class);
    }

    public static class BarrioService {
    }
}
