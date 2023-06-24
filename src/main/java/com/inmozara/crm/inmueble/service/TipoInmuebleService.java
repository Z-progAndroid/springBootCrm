package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.TipoInmueble;
import com.inmozara.crm.inmueble.model.dto.TipoInmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.TipoInmuebleRespository;
import com.inmozara.crm.inmueble.service.interfaces.ITipoInmueble;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoInmuebleService implements ITipoInmueble {
    @Autowired
    TipoInmuebleRespository tipoInmuebleRespository;
    @Autowired
    InmuebleRepository inmuebleRepository;

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
    public MensajeDTO delete(Long idTipoInmueble) {
        if (!tipoInmuebleRespository.existsById(idTipoInmueble)) {
            throw new RecursoNoEncontrado("No existe un tipo inmueble con el id:" + idTipoInmueble);
        }
        inmuebleRepository.actualizarInmueblesPorTipo(TipoInmueble.builder().id(idTipoInmueble).build(), TipoInmueble.builder().id(0L).build());
        tipoInmuebleRespository.deleteById(idTipoInmueble);
        return MensajeDTO.builder()
                .mensaje("Se elimino el tipo de inmueble con id: " + idTipoInmueble)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public TipoInmuebleDTO find(Long idTipoInmueble) {
        TipoInmueble inmueble = tipoInmuebleRespository.findById(idTipoInmueble).
                orElseThrow(() -> new RecursoNoEncontrado("No se encontro el tipo de inmueblepor id: " + idTipoInmueble));
        return ObjectMapperUtils.map(inmueble, TipoInmuebleDTO.class);
    }

    public List<TipoInmuebleDTO> findAll() {
        List<TipoInmueble> tipoInmuebles = tipoInmuebleRespository.findAll();
        if (tipoInmuebles.isEmpty())
            throw new RecursoNoEncontrado("No se encontraron tipos de inmuebles");
        tipoInmuebles = tipoInmuebles
                .stream()
                .filter(inmueble -> inmueble.getId() != 0)
                .collect(java.util.stream.Collectors.toList());
        return ObjectMapperUtils.mapAll(tipoInmuebles, TipoInmuebleDTO.class);
    }
}
