package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.dto.EstadoInmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.EstadoInmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.inmueble.model.search.InmuebleSearch;
import com.inmozara.crm.inmueble.service.interfaces.IEstadoInmueble;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EstadoInmuebleService implements IEstadoInmueble {
    @Autowired
    EstadoInmuebleRepository estadoInmuebleRepository;
    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Override
    public EstadoInmuebleDTO save(EstadoInmuebleDTO estadoInmuebleDTO) {
        EstadoInmueble estadoInmueble = ObjectMapperUtils.map(estadoInmuebleDTO, EstadoInmueble.class);
        EstadoInmueble estadoInmueble1 = estadoInmuebleRepository.save(estadoInmueble);
        return ObjectMapperUtils.map(estadoInmueble1, EstadoInmuebleDTO.class);
    }

    @Override
    public EstadoInmuebleDTO update(EstadoInmuebleDTO estadoInmuebleDTO) {
        EstadoInmueble estadoInmueble = ObjectMapperUtils.map(estadoInmuebleDTO, EstadoInmueble.class);
        EstadoInmueble estadoInmueble1 = estadoInmuebleRepository.save(estadoInmueble);
        return ObjectMapperUtils.map(estadoInmueble1, EstadoInmuebleDTO.class);
    }

    @Override
    public MensajeDTO delete(Integer idEstadoInmueble) {
        if (!estadoInmuebleRepository.existsById(idEstadoInmueble)) {
            throw new RecursoNoEncontrado("No existe el estado inmueble con el id: " + idEstadoInmueble);
        }
        List<Inmueble> inmuebles = inmuebleRepository.findAll(InmuebleSearch.builder()
                .inmueble(Inmueble.builder()
                        .estadoInmueble(EstadoInmueble.builder()
                                .idEstadoInmueble(idEstadoInmueble).build()).build()).build());
        if (!inmuebles.isEmpty()) {
            inmuebles.forEach(inmueble -> {
                inmueble.setEstadoInmueble(EstadoInmueble.builder().idEstadoInmueble(0).build());
            });
        }
        inmuebleRepository.saveAll(inmuebles);
        estadoInmuebleRepository.deleteById(idEstadoInmueble);
        return MensajeDTO.builder()
                .mensaje("Se elimino el estado de inmueble con id: " + idEstadoInmueble)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public EstadoInmuebleDTO find(Integer integer) {
        EstadoInmueble estadoInmueble = estadoInmuebleRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el estado de inmueble con id: " + integer));
        return ObjectMapperUtils.map(estadoInmueble, EstadoInmuebleDTO.class);
    }

    @Override
    public List<EstadoInmuebleDTO> findAll() {
        List<EstadoInmueble> estadoInmuebles = estadoInmuebleRepository.findAll();
        if (estadoInmuebles.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron estados de inmuebles");
        }
        return ObjectMapperUtils.mapAll(estadoInmuebles, EstadoInmuebleDTO.class);
    }
}
