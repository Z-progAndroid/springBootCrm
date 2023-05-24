package com.inmozara.crm.usuario.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import com.inmozara.crm.usuario.model.search.UserSearch;
import com.inmozara.crm.usuario.service.interfaces.IUsuario;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EstadoUsuarioService estadoUsuarioService;
    @Autowired
    private RolService rolService;

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
    public MensajeDTO delete(Integer integer) {
        if (!usuarioRepository.existsById(integer)) {
            throw new RecursoNoEncontrado("No se encontro el usuario");
        }
        usuarioRepository.deleteById(integer);
        return MensajeDTO.builder()
                .mensaje("Usuario eliminado correctamente con el id: " + integer)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public UsuarioDTO find(Integer integer) {
        Usuario usuario = usuarioRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el usuario"));
        Map<Integer, String> rol = rolService.findAllMap();
        Map<Integer, String> estadoUsuario = estadoUsuarioService.findAllMap();
        UsuarioDTO usuarioDTOS = ObjectMapperUtils.map(usuario, UsuarioDTO.class);
        usuarioDTOS.setRol(rol.get(usuarioDTOS.getIdRol()));
        usuarioDTOS.setEstadoUsuario(estadoUsuario.get(usuarioDTOS.getIdEstadoUsuario()));
        return usuarioDTOS;
    }

    @Override
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron usuarios");
        }
        Map<Integer, String> rol = rolService.findAllMap();
        Map<Integer, String> estadoUsuario = estadoUsuarioService.findAllMap();
        List<UsuarioDTO> usuarioDTOS = ObjectMapperUtils.mapAll(usuarios, UsuarioDTO.class);

        return usuarioDTOS.stream().map(usuarioDTO -> {
            usuarioDTO.setRol(rol.get(usuarioDTO.getIdRol()));
            usuarioDTO.setEstadoUsuario(estadoUsuario.get(usuarioDTO.getIdEstadoUsuario()));
            return usuarioDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> findAllBYParams(UsuarioDTO usuarioDTO) {
        Usuario usuario = ObjectMapperUtils.map(usuarioDTO, Usuario.class);
        List<Usuario> usuarios = usuarioRepository.findAll(UserSearch.builder()
                .usuario(usuario)
                .build());
        if (usuarios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron usuarios por los parametros ingresados");
        }
        Map<Integer, String> rol = rolService.findAllMap();
        Map<Integer, String> estadoUsuario = estadoUsuarioService.findAllMap();
        List<UsuarioDTO> usuarioDTOS = ObjectMapperUtils.mapAll(usuarios, UsuarioDTO.class);
        return usuarioDTOS.stream().map(user -> {
            user.setRol(rol.get(user.getIdRol()));
            user.setEstadoUsuario(estadoUsuario.get(user.getIdEstadoUsuario()));
            return user;
        }).collect(Collectors.toList());
    }
}
