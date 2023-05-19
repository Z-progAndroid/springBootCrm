package com.inmozara.crm.tarea.service;

import com.inmozara.crm.tarea.model.Tarea;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import com.inmozara.crm.tarea.service.interfaces.ITarea;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TareaService implements ITarea {
    @Autowired
    private TareaRepository tareaRepository;

    @Override
    public TareaDTO save(TareaDTO tareaDTO) {
        Tarea tarea = ObjectMapperUtils.map(tareaDTO, Tarea.class);
        Tarea tareaCreated = tareaRepository.save(tarea);
        return ObjectMapperUtils.map(tareaCreated, TareaDTO.class);
    }

    @Override
    public TareaDTO update(TareaDTO tareaDTO) {
        Tarea tarea = ObjectMapperUtils.map(tareaDTO, Tarea.class);
        Tarea tareaCreated = tareaRepository.save(tarea);
        return ObjectMapperUtils.map(tareaCreated, TareaDTO.class);
    }

    @Override
    public TareaDTO delete(Integer integer) {
        if (!tareaRepository.existsById(integer)) {
            throw new RuntimeException("No existe la tarea con id: " + integer);
        }
        tareaRepository.deleteById(integer);
        return null;
    }

    @Override
    public TareaDTO find(Integer integer) {
        Tarea tarea = tareaRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("No existe la tarea con id: " + integer));

        return ObjectMapperUtils.map(tarea, TareaDTO.class);
    }

    @Override
    public List<TareaDTO> findAll() {
        List<Tarea> tareas = tareaRepository.findAll();
        if (tareas.isEmpty())
            throw new RuntimeException("No existen tareas");
        return ObjectMapperUtils.mapAll(tareas, TareaDTO.class);

    }
}