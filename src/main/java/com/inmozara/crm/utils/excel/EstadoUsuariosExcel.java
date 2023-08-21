package com.inmozara.crm.utils.excel;

import com.inmozara.crm.usuario.model.dto.EstadoUsuarioDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class EstadoUsuariosExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        this.isCustomWidthColumn = true;
        return "LISTADO DE ESTADOS DE USUARIO";
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
        for (EstadoUsuarioDTO dto : (List<EstadoUsuarioDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdEstadoUsuario()), this.row);
            creaCeldaNormal(this.columna++, dto.getEstadoUsuario(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
