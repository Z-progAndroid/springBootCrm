package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Barrio;
import com.inmozara.crm.inmueble.model.Municipio;
import com.inmozara.crm.inmueble.model.dto.BarrioDTO;
import com.inmozara.crm.inmueble.model.repository.BarrioRepository;
import com.inmozara.crm.inmueble.service.interfaces.IBarrio;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BarrioService implements IBarrio {
    @Autowired
    private BarrioRepository barrioRepository;

    @Override
    public List<BarrioDTO> findAllByMunicipio(int idMunicipio) {
        List<Barrio> barrios = barrioRepository.findAllByMunicipio(Municipio.builder().idMunicipio(idMunicipio).build());
        if (barrios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron barrios para el municipio con id: " + idMunicipio);
        }
        return ObjectMapperUtils.mapAll(barrios, BarrioDTO.class);
    }

    @Override
    public BarrioDTO save(BarrioDTO barrioDTO) {
        Barrio barrio = ObjectMapperUtils.map(barrioDTO, Barrio.class);
        Barrio barrio1 = barrioRepository.save(barrio);
        return ObjectMapperUtils.map(barrio1, BarrioDTO.class);
    }

    @Override
    public BarrioDTO update(BarrioDTO barrioDTO) {
        Barrio barrio = ObjectMapperUtils.map(barrioDTO, Barrio.class);
        Barrio barrio1 = barrioRepository.save(barrio);
        return ObjectMapperUtils.map(barrio1, BarrioDTO.class);
    }

    @Override
    public MensajeDTO delete(Integer idBarrio) {
        if (!barrioRepository.existsById(idBarrio)) {
            throw new RecursoNoEncontrado("No existe un barrio con este id");
        }
        barrioRepository.deleteById(idBarrio);
        return MensajeDTO.builder()
                .mensaje("Barrio eliminado correctamente con id: " + idBarrio)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public BarrioDTO find(Integer idMunicipio) {
        Barrio barrio = barrioRepository.findById(idMunicipio)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el barrio con id: " + idMunicipio));
        return ObjectMapperUtils.map(barrio, BarrioDTO.class);
    }

    @Override
    public List<BarrioDTO> findAll() {
        List<Barrio> barrios = barrioRepository.findAll();
        if (barrios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron barrios");
        }
        return ObjectMapperUtils.mapAll(barrios, BarrioDTO.class);
    }
}
