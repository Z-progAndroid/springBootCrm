package com.inmozara.crm.usuario.service;

import com.inmozara.crm.usuario.model.EstadoUsuario;
import com.inmozara.crm.usuario.model.dto.EstadoUsuarioDTO;
import com.inmozara.crm.usuario.model.repository.EstadoUsuarioRepository;
import com.inmozara.crm.usuario.service.interfaces.IEstadoUsuario;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoUsuarioService implements IEstadoUsuario {
    @Autowired
    private EstadoUsuarioRepository estadoUsuarioRepository;

    @Override
    public EstadoUsuarioDTO save(EstadoUsuarioDTO estadoUsuarioDTO) {
        EstadoUsuario estadoUsuario = ObjectMapperUtils.map(estadoUsuarioDTO, EstadoUsuario.class);
        estadoUsuario = estadoUsuarioRepository.save(estadoUsuario);
        return ObjectMapperUtils.map(estadoUsuario, EstadoUsuarioDTO.class);
    }

    @Override
    public EstadoUsuarioDTO update(EstadoUsuarioDTO estadoUsuarioDTO) {
        EstadoUsuario estadoUsuario = ObjectMapperUtils.map(estadoUsuarioDTO, EstadoUsuario.class);
        estadoUsuario = estadoUsuarioRepository.save(estadoUsuario);
        return ObjectMapperUtils.map(estadoUsuario, EstadoUsuarioDTO.class);
    }

    @Override
    public EstadoUsuarioDTO delete(Integer integer) {
        if (!estadoUsuarioRepository.existsById(integer)) {
            throw new RuntimeException("No se encontro el estado del usuario");
        }
        estadoUsuarioRepository.deleteById(integer);
        return null;
    }

    @Override
    public EstadoUsuarioDTO find(Integer integer) {
        EstadoUsuario estadoUsuario = estadoUsuarioRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("No se encontro el estado del usuario"));
        return ObjectMapperUtils.map(estadoUsuario, EstadoUsuarioDTO.class);
    }

    @Override
    public List<EstadoUsuarioDTO> findAll() {
        List<EstadoUsuario> estadoUsuarios = estadoUsuarioRepository.findAll();
        if (estadoUsuarios.isEmpty()) {
            throw new RuntimeException("No se encontraron estados de usuarios");
        }
        return ObjectMapperUtils.mapAll(estadoUsuarios, EstadoUsuarioDTO.class);
    }
}
