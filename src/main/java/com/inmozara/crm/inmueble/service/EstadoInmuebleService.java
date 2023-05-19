package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.inmueble.model.EstadoInmueble;
import com.inmozara.crm.inmueble.model.dto.EstadoInmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.EstadoInmuebleRepository;
import com.inmozara.crm.inmueble.service.interfaces.IEstadoInmueble;
import com.inmozara.crm.utils.ObjectMapperUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EstadoInmuebleService implements IEstadoInmueble {
    @Autowired
    EstadoInmuebleRepository estadoInmuebleRepository;

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
    public EstadoInmuebleDTO delete(Integer idEstadoInmueble) {
        if (!estadoInmuebleRepository.existsById(idEstadoInmueble)) {
            throw new RuntimeException("No existe el estado inmueble con el id: " + idEstadoInmueble);
        }
        estadoInmuebleRepository.deleteById(idEstadoInmueble);
        return null;
    }

    @Override
    public EstadoInmuebleDTO find(Integer integer) {
        EstadoInmueble estadoInmueble = estadoInmuebleRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("No se encontro el estado de inmueble con id: " + integer));
        return ObjectMapperUtils.map(estadoInmueble, EstadoInmuebleDTO.class);
    }

    @Override
    public List<EstadoInmuebleDTO> findAll() {
        List<EstadoInmueble> estadoInmuebles = estadoInmuebleRepository.findAll();
        if (estadoInmuebles.isEmpty()) {
            throw new RuntimeException("No se encontraron estados de inmuebles");
        }
        return ObjectMapperUtils.mapAll(estadoInmuebles, EstadoInmuebleDTO.class);
    }
}
