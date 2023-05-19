package com.inmozara.crm.tarea.service;

import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.tarea.model.EstadoTarea;
import com.inmozara.crm.tarea.model.dto.EstadoTareaDTO;
import com.inmozara.crm.tarea.model.repository.EstadoTareaRepository;
import com.inmozara.crm.tarea.service.interfaces.IEstadoTarea;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService implements IEstadoTarea {
    @Autowired
    private EstadoTareaRepository estadoTareaRepository;

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
    public EstadoTareaDTO delete(Integer integer) {
        if (!estadoTareaRepository.existsById(integer)) {
            throw new RecursoNoEncontrado("No existe el estado con id: " + integer);
        }
        estadoTareaRepository.deleteById(integer);
        return null;
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
        return ObjectMapperUtils.mapAll(estados, EstadoTareaDTO.class);
    }
}
