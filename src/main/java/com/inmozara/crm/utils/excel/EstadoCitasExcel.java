package com.inmozara.crm.utils.excel;

import com.inmozara.crm.cita.model.dto.EstadoCitaDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class EstadoCitasExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        this.isCustomWidthColumn = true;
        return "LISTADO DE ESTADOS DE CITAS";
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
        for (EstadoCitaDTO dto : (List<EstadoCitaDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdEstadoCita()), this.row);
            creaCeldaNormal(this.columna++, dto.getEstadoCita(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
