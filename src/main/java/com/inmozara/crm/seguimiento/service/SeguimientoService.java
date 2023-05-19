package com.inmozara.crm.seguimiento.service;

import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.seguimiento.model.Seguimiento;
import com.inmozara.crm.seguimiento.model.dto.SeguimientoDTO;
import com.inmozara.crm.seguimiento.model.repository.SeguimientoRepository;
import com.inmozara.crm.seguimiento.service.interfaces.ISeguimiento;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguimientoService implements ISeguimiento {
    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Override
    public SeguimientoDTO save(SeguimientoDTO seguimientoDTO) {
        Seguimiento seguimiento = ObjectMapperUtils.map(seguimientoDTO, Seguimiento.class);
        seguimiento = seguimientoRepository.save(seguimiento);
        return ObjectMapperUtils.map(seguimiento, SeguimientoDTO.class);
    }

    @Override
    public SeguimientoDTO update(SeguimientoDTO seguimientoDTO) {
        Seguimiento seguimiento = ObjectMapperUtils.map(seguimientoDTO, Seguimiento.class);
        seguimiento = seguimientoRepository.save(seguimiento);
        return ObjectMapperUtils.map(seguimiento, SeguimientoDTO.class);
    }

    @Override
    public SeguimientoDTO delete(Long idSeguimiento) {
        if (!seguimientoRepository.existsById(idSeguimiento)) {
            throw new RecursoNoEncontrado("No existe el seguimiento");
        }
        seguimientoRepository.deleteById(idSeguimiento);
        return null;
    }

    @Override
    public SeguimientoDTO find(Long aLong) {
        Seguimiento seguimiento = seguimientoRepository.findById(aLong).orElseThrow(
                () -> new RecursoNoEncontrado("No existe el seguimiento"));
        return ObjectMapperUtils.map(seguimiento, SeguimientoDTO.class);
    }

    @Override
    public List<SeguimientoDTO> findAll() {
        List<Seguimiento> seguimientos = seguimientoRepository.findAll();
        if (seguimientos.isEmpty()) {
            throw new RecursoNoEncontrado("No existen seguimientos");
        }
        return ObjectMapperUtils.mapAll(seguimientos, SeguimientoDTO.class);
    }
}
