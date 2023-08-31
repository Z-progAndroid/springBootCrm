package com.inmozara.crm.usuario.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.usuario.model.repository.UsuarioRepository;
import com.inmozara.crm.usuario.model.search.UserSearch;
import com.inmozara.crm.usuario.service.interfaces.IUsuario;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EstadoUsuarioService estadoUsuarioService;
    @Autowired
    private RolService rolService;

    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private InmuebleRepository inmuebleRepository;
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = ObjectMapperUtils.map(usuarioDTO, Usuario.class);
        usuario.setPassword(passwordEncoder().encode(usuario.getPassword()));
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
        Usuario usuario = usuarioRepository.obtenerUsuarioPorDefecto()
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el usuario por defecto"));
        tareaRepository.actualizarUsuarioTarea(Usuario.builder().idUsuario(integer).build(), Usuario.builder().idUsuario(usuario.getIdUsuario()).build());
        inmuebleRepository.actualizarAgenteInmuebles(Usuario.builder().idUsuario(integer).build(), Usuario.builder().idUsuario(usuario.getIdUsuario()).build());
        usuarioRepository.deleteById(integer);
        return MensajeDTO.builder()
                .mensaje("Usuario eliminado correctamente")
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public UsuarioDTO find(Integer integer) {
        Usuario usuario = usuarioRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el usuario"));
        return ObjectMapperUtils.map(usuario, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron usuarios");
        }
        usuarios = usuarios
                .stream()
                .filter(usuario -> !usuario.getNombre().equalsIgnoreCase("DEFECTO_ADMIN"))
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(usuarios, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> findAllBYParams(UsuarioDTO usuarioDTO) {
        Usuario usuario = ObjectMapperUtils.map(usuarioDTO, Usuario.class);
        List<Usuario> usuarios = usuarioRepository.findAll(UserSearch.builder()
                .usuario(usuario)
                .build());
        if (usuarios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron usuarios por los parametros");
        }
        usuarios = usuarios
                .stream()
                .filter(usuario1 -> !usuario1.getNombre().equalsIgnoreCase("DEFECTO_ADMIN"))
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(usuarios, UsuarioDTO.class);
    }

    public List<UsuarioDTO> findAllUserAdminORAgente() {
        List<Usuario> usuarios = usuarioRepository.obtenerAgentesYAdministradores();
        if (usuarios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron usuarios con rol de administrador o agente");
        }
        usuarios = usuarios
                .stream()
                .filter(usuario1 -> !usuario1.getNombre().equalsIgnoreCase("DEFECTO_ADMIN"))
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(usuarios, UsuarioDTO.class);
    }

    public List<UsuarioDTO> findAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.obtenerUsuarios();
        if (usuarios.isEmpty()) {
            throw new RecursoNoEncontrado("No se encontraron usuarios con rol de usuario");
        }
        usuarios = usuarios
                .stream()
                .filter(usuario1 -> !usuario1.getNombre().equalsIgnoreCase("DEFECTO_ADMIN"))
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(usuarios, UsuarioDTO.class);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
