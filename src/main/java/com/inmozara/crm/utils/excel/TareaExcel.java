package com.inmozara.crm.utils.excel;

import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.text.SimpleDateFormat;
import java.util.List;
@Builder
public class TareaExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(7);
        return "LISTADO DE TAREAS";
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
        SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
        for (TareaDTO dto : (List<TareaDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, dto.getTitulo(), this.row);
            creaCeldaNormal(this.columna++, dto.getDescripcion(), this.row);
            creaCeldaNormal(this.columna++, dateFormat.format(dto.getFechaInicio()), this.row);
            creaCeldaNormal(this.columna++, dateFormat.format(dto.getFechaFin()), this.row);
            creaCeldaNormal(this.columna++, dto.getEstadoTarea(), this.row);
            creaCeldaNormal(this.columna++, dto.getNombre(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
