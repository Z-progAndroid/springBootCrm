package com.inmozara.crm.utils.excel;

import com.inmozara.crm.contrato.model.dto.TipoPagoDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class TipoPagoExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        setCustomWidth(17000);
        setCustomWidthColumn(true);
        return "LISTADO DE TIPOS DE PAGOS";
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
        for (TipoPagoDTO dto : (List<TipoPagoDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdTipoPago()), this.row);
            creaCeldaNormal(this.columna++, dto.getTipo(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
