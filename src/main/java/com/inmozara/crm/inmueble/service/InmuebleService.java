package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.inmueble.service.interfaces.IInmueble;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InmuebleService implements IInmueble {
    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Override
    public InmuebleDTO save(InmuebleDTO inmuebleDTO) {
        Inmueble inmueble = ObjectMapperUtils.map(inmuebleDTO, Inmueble.class);
        Inmueble inmueble1 = inmuebleRepository.save(inmueble);
        return ObjectMapperUtils.map(inmueble1, InmuebleDTO.class);
    }

    @Override
    public InmuebleDTO update(InmuebleDTO inmuebleDTO) {
        Inmueble inmueble = ObjectMapperUtils.map(inmuebleDTO, Inmueble.class);
        Inmueble inmueble1 = inmuebleRepository.save(inmueble);
        return ObjectMapperUtils.map(inmueble1, InmuebleDTO.class);
    }

    @Override
    public InmuebleDTO delete(Long idInmueble) {
        if (!inmuebleRepository.existsById(idInmueble)){
            throw new RecursoNoEncontrado("No existe un inmueble con el id: "+idInmueble);
        }
        inmuebleRepository.deleteById(idInmueble);
        return null;
    }

    @Override
    public InmuebleDTO find(Long aLong) {
        Inmueble inmueble = inmuebleRepository.findById(aLong)
                .orElseThrow(() -> new RecursoNoEncontrado("Inmueble no encontrado por el id: " + aLong));

        return ObjectMapperUtils.map(inmueble, InmuebleDTO.class);
    }

    @Override
    public List<InmuebleDTO> findAll() {
        List<Inmueble> inmuebles = inmuebleRepository.findAll();
        if (inmuebles.isEmpty())
            throw new RecursoNoEncontrado("No hay inmuebles en la base de datos");
        return ObjectMapperUtils.mapAll(inmuebles, InmuebleDTO.class);

    }
}
