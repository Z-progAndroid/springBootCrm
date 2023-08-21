package com.inmozara.crm.utils.excel;

import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class UsuariosExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        return "LISTADO DE USUARIOS";
    }

    @Override
    public void crearCabeceraTablaListado(List<String> cabeceraValuesList) {
        for (String s : cabeceraValuesList) {
            creaCeldaEncabezadoTabla(this.columna++, s, this.row);
        }
        amuentarFila(++this.fila);
    }

    @Override
    public void crearContenidoTablaListado(List contenidoTablaListado) {
        for (UsuarioDTO dto : (List<UsuarioDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, dto.getNombre(), this.row);
            creaCeldaNormal(this.columna++, dto.getApellido(), this.row);
            creaCeldaNormal(this.columna++, dto.getEmail(), this.row);
            creaCeldaNormal(this.columna++, dto.getRol(), this.row);
            creaCeldaNormal(this.columna++, dto.getEstadoUsuario(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
