package com.inmozara.crm.tarea.model.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.tarea.model.EstadoTarea;
import com.inmozara.crm.tarea.model.dto.EstadoTareaDTO;
import com.inmozara.crm.tarea.model.repository.EstadoTareaRepository;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import com.inmozara.crm.tarea.model.service.interfaces.IEstadoTarea;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoTareaService implements IEstadoTarea {
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;
    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public EstadoTareaDTO save(EstadoTareaDTO estadoTareaDTO) {
        EstadoTarea estadoTarea = ObjectMapperUtils.map(estadoTareaDTO, EstadoTarea.class);
        EstadoTarea estadoTareaCreated = estadoTareaRepository.save(estadoTarea);
        return ObjectMapperUtils.map(estadoTareaCreated, EstadoTareaDTO.class);
    }

    @Override
    public EstadoTareaDTO update(EstadoTareaDTO estadoTareaDTO) {
        EstadoTarea estadoTarea = ObjectMapperUtils.map(estadoTareaDTO, EstadoTarea.class);
        EstadoTarea estadoTareaCreated = estadoTareaRepository.save(estadoTarea);
        return ObjectMapperUtils.map(estadoTareaCreated, EstadoTareaDTO.class);
    }

    @Override
    public MensajeDTO delete(Integer idEstadoTarea) {
        if (!estadoTareaRepository.existsById(idEstadoTarea)) {
            throw new RecursoNoEncontrado("No existe el estado con id: " + idEstadoTarea);
        }
        tareaRepository.actualizarTareasPorEstado(EstadoTarea.builder().idEstadoTarea(idEstadoTarea).build(), EstadoTarea.builder().idEstadoTarea(0).build());
        estadoTareaRepository.deleteById(idEstadoTarea);
        return MensajeDTO.builder()
                .mensaje("Estado eliminado correctamente con el id: " + idEstadoTarea)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public EstadoTareaDTO find(Integer integer) {
        EstadoTarea estadoTarea = estadoTareaRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No existe el estado con id: " + integer));
        return ObjectMapperUtils.map(estadoTarea, EstadoTareaDTO.class);
    }

    @Override
    public List<EstadoTareaDTO> findAll() {
        List<EstadoTarea> estados = estadoTareaRepository.findAll();
        if (estados.isEmpty())
            throw new RecursoNoEncontrado("No existen estados");
        List<EstadoTarea> estadoTareas = estados.stream()
                .filter(estadoTarea -> estadoTarea.getIdEstadoTarea() != 0)
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(estadoTareas, EstadoTareaDTO.class);
    }
}
