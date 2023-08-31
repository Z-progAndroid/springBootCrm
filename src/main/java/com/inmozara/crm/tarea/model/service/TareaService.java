package com.inmozara.crm.tarea.model.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.tarea.model.Tarea;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import com.inmozara.crm.tarea.model.search.TareasSearch;
import com.inmozara.crm.tarea.model.service.interfaces.ITarea;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public MensajeDTO delete(Integer id) {
        if (!tareaRepository.existsById(id)) {
            throw new RecursoNoEncontrado("No existe la tarea con id: " + id);
        }
        tareaRepository.deleteById(id);
        return MensajeDTO.builder()
                .mensaje("Tarea eliminada correctamente con el id: " + id)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public TareaDTO find(Integer integer) {
        Tarea tarea = tareaRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No existe la tarea con id: " + integer));

        return ObjectMapperUtils.map(tarea, TareaDTO.class);
    }

    @Override
    public List<TareaDTO> findAll() {
        List<Tarea> tareas = tareaRepository.findAll();
        if (tareas.isEmpty())
            throw new RecursoNoEncontrado("No existen tareas");
        return ObjectMapperUtils.mapAll(tareas, TareaDTO.class);

    }

    @Override
    public List<TareaDTO> findAllByParams(TareaDTO tareaDTO) {
        List<Tarea> tareas = tareaRepository.findAll(TareasSearch.builder()
                .tarea(ObjectMapperUtils.map(tareaDTO, Tarea.class))
                .build());
        if (tareas.isEmpty())
            throw new RecursoNoEncontrado("No existen tareas");
        return ObjectMapperUtils.mapAll(tareas, TareaDTO.class);

    }
}
