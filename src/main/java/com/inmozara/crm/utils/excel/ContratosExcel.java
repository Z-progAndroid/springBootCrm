package com.inmozara.crm.utils.excel;

import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.text.SimpleDateFormat;
import java.util.List;

@Builder
public class ContratosExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        return "LISTADO DE CONTRATOS";
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
        SimpleDateFormat dateFormat =new SimpleDateFormat(DD_MM_YYYY);
        for (ContratoDTO dto : (List<ContratoDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, dto.getTitulo(), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, dateFormat.format(dto.getFechaInicio()), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, dateFormat.format(dto.getFechaFinalizacion()), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, String.valueOf(dto.getValor()), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, dto.getEstadoContrato(), this.row);
            getHoja().autoSizeColumn(this.columna);
            amuentarFila(++this.fila);
        }
    }
}
