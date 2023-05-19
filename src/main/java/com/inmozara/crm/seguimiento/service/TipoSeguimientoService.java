package com.inmozara.crm.seguimiento.service;

import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.seguimiento.model.TipoSeguimiento;
import com.inmozara.crm.seguimiento.model.dto.TipoSeguimientoDTO;
import com.inmozara.crm.seguimiento.model.repository.TipoSeguimientoRepository;
import com.inmozara.crm.seguimiento.service.interfaces.ITipoSeguimiento;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoSeguimientoService implements ITipoSeguimiento {
    @Autowired
    private TipoSeguimientoRepository tipoSeguimientoRepository;
    @Override
    public TipoSeguimientoDTO save(TipoSeguimientoDTO tipoSeguimientoDTO) {
        TipoSeguimiento tipoSeguimiento = ObjectMapperUtils.map(tipoSeguimientoDTO, TipoSeguimiento.class);
        TipoSeguimiento tipoSeguimiento1 =tipoSeguimientoRepository.save(tipoSeguimiento);
        return ObjectMapperUtils.map(tipoSeguimiento1, TipoSeguimientoDTO.class);
    }

    @Override
    public TipoSeguimientoDTO update(TipoSeguimientoDTO tipoSeguimientoDTO) {
        TipoSeguimiento tipoSeguimiento = ObjectMapperUtils.map(tipoSeguimientoDTO, TipoSeguimiento.class);
        TipoSeguimiento tipoSeguimiento1 =tipoSeguimientoRepository.save(tipoSeguimiento);
        return ObjectMapperUtils.map(tipoSeguimiento1, TipoSeguimientoDTO.class);
    }

    @Override
    public TipoSeguimientoDTO delete(Long idTipoSeguimiento) {
        if (!tipoSeguimientoRepository.existsById(idTipoSeguimiento)) {
            throw new RecursoNoEncontrado("No existe el tipo de seguimiento");
        }
        tipoSeguimientoRepository.deleteById(idTipoSeguimiento);
        return null;
    }

    @Override
    public TipoSeguimientoDTO find(Long idTipoSeguimiento) {
        TipoSeguimiento tipoSeguimiento = tipoSeguimientoRepository.findById(idTipoSeguimiento).orElseThrow(
                () -> new RecursoNoEncontrado("No existe el tipo de seguimiento"));
        return ObjectMapperUtils.map(tipoSeguimiento, TipoSeguimientoDTO.class);
    }

    @Override
    public List<TipoSeguimientoDTO> findAll() {
        List<TipoSeguimiento> tipoSeguimientos = tipoSeguimientoRepository.findAll();
        if (tipoSeguimientos.isEmpty()) {
            throw new RecursoNoEncontrado("No existen tipos de seguimientos");
        }
        return ObjectMapperUtils.mapAll(tipoSeguimientos, TipoSeguimientoDTO.class);
    }
}
