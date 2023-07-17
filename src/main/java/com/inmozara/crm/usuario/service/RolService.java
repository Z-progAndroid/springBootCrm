package com.inmozara.crm.usuario.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.usuario.model.Rol;
import com.inmozara.crm.usuario.model.dto.RolDTO;
import com.inmozara.crm.usuario.model.repository.RolRepository;
import com.inmozara.crm.usuario.service.interfaces.IRol;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public MensajeDTO delete(Integer id) {
        if (!rolRepository.existsById(id)) {
            throw new RecursoNoEncontrado("No se encontro el rol");
        }
        rolRepository.deleteById(id);
        return MensajeDTO.builder()
                .mensaje("Rol eliminado correctamente con el id: " + id)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
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
        roles = roles
                .stream()
                .filter(rol -> rol.getIdRol() != 0)
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(roles, RolDTO.class);
    }
    List<RolDTO> rolesAdminYAgente() {
        List<Rol> roles = rolRepository.findRolesAgenteYAdmin();
        if (roles.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron roles");
        }
        return ObjectMapperUtils.mapAll(roles, RolDTO.class);
    }
}
