package com.inmozara.crm.usuario.service;

import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.usuario.model.Rol;
import com.inmozara.crm.usuario.model.dto.RolDTO;
import com.inmozara.crm.usuario.model.repository.RolRepository;
import com.inmozara.crm.usuario.service.interfaces.IRol;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRol {
    @Autowired
    private RolRepository rolRepository;

    @Override
    public RolDTO save(RolDTO rolDTO) {
        Rol rol = ObjectMapperUtils.map(rolDTO, Rol.class);
        rol = rolRepository.save(rol);
        return ObjectMapperUtils.map(rol, RolDTO.class);
    }

    @Override
    public RolDTO update(RolDTO rolDTO) {
        Rol rol = ObjectMapperUtils.map(rolDTO, Rol.class);
        rol = rolRepository.save(rol);
        return ObjectMapperUtils.map(rol, RolDTO.class);
    }

    @Override
    public RolDTO delete(Integer integer) {
        if (!rolRepository.existsById(integer)) {
            throw new RecursoNoEncontrado("No se encontro el rol");
        }
        rolRepository.deleteById(integer);
        return null;
    }

    @Override
    public RolDTO find(Integer integer) {
        Rol rol = rolRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el rol"));
        return ObjectMapperUtils.map(rol, RolDTO.class);
    }

    @Override
    public List<RolDTO> findAll() {
        List<Rol> roles = rolRepository.findAll();
        if (roles.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron roles");
        }
        return ObjectMapperUtils.mapAll(roles, RolDTO.class);
    }
}
