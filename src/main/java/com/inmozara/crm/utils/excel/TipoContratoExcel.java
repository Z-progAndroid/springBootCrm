package com.inmozara.crm.utils.excel;

import com.inmozara.crm.contrato.model.dto.TipoContratoDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class TipoContratoExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        setCustomWidth(17000);
        setCustomWidthColumn(true);
        return "LISTADO DE TIPOS DE CONTRATO";
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
        for (TipoContratoDTO dto : (List<TipoContratoDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdTipoContrato()), this.row);
            creaCeldaNormal(this.columna++, dto.getTipo(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
