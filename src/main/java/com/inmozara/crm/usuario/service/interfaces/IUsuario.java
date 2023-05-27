package com.inmozara.crm.usuario.service.interfaces;

import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.utils.comon.IService;

import java.util.List;

public interface IUsuario extends IService<UsuarioDTO, Integer> {
    List<UsuarioDTO> findAllBYParams(UsuarioDTO usuarioDTO);
    List<UsuarioDTO> findAllUserAdminORAgente();
}
