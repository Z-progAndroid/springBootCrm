package com.inmozara.crm.utils.excel;

import com.inmozara.crm.cita.model.dto.TipoCitaDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class TipoCitaExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        setCustomWidth(17000);
        setCustomWidthColumn(true);
        return "LISTADO DE TIPOS DE CITA";
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
        for (TipoCitaDTO dto : (List<TipoCitaDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdTipoCita()), this.row);
            creaCeldaNormal(this.columna++, dto.getTipoCita(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
