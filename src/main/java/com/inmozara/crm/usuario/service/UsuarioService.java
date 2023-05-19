package com.inmozara.crm.usuario.service;

import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import com.inmozara.crm.usuario.service.interfaces.IUsuario;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = ObjectMapperUtils.map(usuarioDTO, Usuario.class);
        Usuario usuario1 = usuarioRepository.save(usuario);
        return ObjectMapperUtils.map(usuario1, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDTO) {
        Usuario usuario = ObjectMapperUtils.map(usuarioDTO, Usuario.class);
        Usuario usuario1 = usuarioRepository.save(usuario);
        return ObjectMapperUtils.map(usuario1, UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO delete(Integer integer) {
        if (!usuarioRepository.existsById(integer)) {
            throw new RuntimeException("No se encontro el usuario");
        }
        usuarioRepository.deleteById(integer);
        return null;
    }

    @Override
    public UsuarioDTO find(Integer integer) {
        Usuario usuario = usuarioRepository.findById(integer)
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario"));
        return ObjectMapperUtils.map(usuario, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new RuntimeException("No se encontraron usuarios");
        }
        return ObjectMapperUtils.mapAll(usuarios, UsuarioDTO.class);
    }
}
