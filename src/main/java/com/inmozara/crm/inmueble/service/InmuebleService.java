package com.inmozara.crm.inmueble.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.exception.GuardarImagenException;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.inmueble.model.repository.EstadoInmuebleRepository;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.inmueble.model.search.InmuebleSearch;
import com.inmozara.crm.inmueble.service.interfaces.IInmueble;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class InmuebleService implements IInmueble {
    @Autowired
    private InmuebleRepository inmuebleRepository;
    @Autowired
    private EstadoInmuebleRepository estadoInmuebleRepository;

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
    public MensajeDTO delete(Long idInmueble) {
        if (!inmuebleRepository.existsById(idInmueble)) {
            throw new RecursoNoEncontrado("No existe un inmueble con el id: " + idInmueble);
        }
        inmuebleRepository.deleteById(idInmueble);
        return MensajeDTO.builder()
                .mensaje("El inmueble se ha eliminado correctamente con el id: " + idInmueble)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public InmuebleDTO find(Long aLong) {
        Inmueble inmueble = inmuebleRepository.findByIdInmueble(aLong)
                .orElseThrow(() -> new RecursoNoEncontrado("Inmueble no encontrado por el id: " + aLong));
        InmuebleDTO inmuebleDTO = ObjectMapperUtils.map(inmueble, InmuebleDTO.class);
        return inmuebleDTO;
    }

    @Override
    public List<InmuebleDTO> findAll() {
        List<Inmueble> inmuebles = inmuebleRepository.findAll();
        if (inmuebles.isEmpty())
            throw new RecursoNoEncontrado("No hay inmuebles en la base de datos");
        List<InmuebleDTO> inmuebleDTOS = ObjectMapperUtils.mapAll(inmuebles, InmuebleDTO.class);
        return inmuebleDTOS;
    }
    public List<InmuebleDTO> search(InmuebleDTO search) {
        List<Inmueble> inmuebles = inmuebleRepository.findAll(InmuebleSearch.builder()
                .inmueble(ObjectMapperUtils.map(search, Inmueble.class))
                .build());

        if (inmuebles.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron inmuebles con los parametros de busqueda");
        }
        return ObjectMapperUtils.mapAll(inmuebles, InmuebleDTO.class);
    }
    public MensajeDTO actualizarEstadoInmuebles(int nuevoIdEstado, int idEstadoAntiguo) {


        return MensajeDTO.builder()
                .mensaje("Se han actualizado los estados de los inmuebles correctamente")
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    public MensajeDTO guardarImagen(String idInmueble, String idImagen, MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new GuardarImagenException("No se ha seleccionado ninguna imagen");
            }
            inmuebleRepository.actualizarImagen(Long.valueOf(idInmueble), "imagen" + idImagen, file.getBytes());
        } catch (IOException e) {
            throw new GuardarImagenException("Error al guardar la imagen ");
        }
        return MensajeDTO.builder()
                .mensaje("imagen guardada correctamente")
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    public MensajeDTO borrarImagen(String idInmueble, String idImagen) {
        inmuebleRepository.actualizarImagen(Long.valueOf(idInmueble), "imagen" + idImagen, null);
        return MensajeDTO.builder()
                .mensaje("imagen borrada correctamente")
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

}
