package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.inmueble.model.TipoInmueble;
import com.inmozara.crm.inmueble.model.dto.TipoInmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.TipoInmuebleRespository;
import com.inmozara.crm.inmueble.service.interfaces.ITipoInmueble;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoInmuebleService implements ITipoInmueble {
    @Autowired
    TipoInmuebleRespository tipoInmuebleRespository;

    @Override
    public TipoInmuebleDTO save(TipoInmuebleDTO tipoInmuebleDTO) {
        TipoInmueble tipoInmueble = ObjectMapperUtils.map(tipoInmuebleDTO, TipoInmueble.class);
        TipoInmueble inmueble = tipoInmuebleRespository.save(tipoInmueble);
        return ObjectMapperUtils.map(inmueble, TipoInmuebleDTO.class);
    }

    @Override
    public TipoInmuebleDTO update(TipoInmuebleDTO tipoInmuebleDTO) {
        TipoInmueble tipoInmueble = ObjectMapperUtils.map(tipoInmuebleDTO, TipoInmueble.class);
        TipoInmueble inmueble = tipoInmuebleRespository.save(tipoInmueble);
        return ObjectMapperUtils.map(inmueble, TipoInmuebleDTO.class);
    }

    @Override
    public TipoInmuebleDTO delete(Long idTipoInmueble) {
        TipoInmuebleDTO tipoInmuebleDTO = this.find(idTipoInmueble);
        tipoInmuebleRespository.delete(ObjectMapperUtils.map(tipoInmuebleDTO, TipoInmueble.class));
        return tipoInmuebleDTO;
    }

    @Override
    public TipoInmuebleDTO find(Long idTipoInmueble) {
        TipoInmueble inmueble = tipoInmuebleRespository.findById(idTipoInmueble).
                orElseThrow(() -> new RuntimeException("No se encontro el tipo de inmueblepor id: " + idTipoInmueble));
        return ObjectMapperUtils.map(inmueble, TipoInmuebleDTO.class);
    }

    public List<TipoInmuebleDTO> findAll() {
        List<TipoInmueble> inmuebles = tipoInmuebleRespository.findAll();
        if (inmuebles.isEmpty())
            throw new RuntimeException("No se encontraron tipos de inmuebles");
        return ObjectMapperUtils.mapAll(inmuebles, TipoInmuebleDTO.class);
    }
}
