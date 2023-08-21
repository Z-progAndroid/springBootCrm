package com.inmozara.crm.utils.excel;

import com.inmozara.crm.tarea.model.dto.EstadoTareaDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class EstadoTareaExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        this.isCustomWidthColumn = true;
        return "LISTADO DE ESTADOS DE TAREA";
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
        for (EstadoTareaDTO dto : (List<EstadoTareaDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdEstadoTarea()), this.row);
            creaCeldaNormal(this.columna++, dto.getEstadoTarea(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
