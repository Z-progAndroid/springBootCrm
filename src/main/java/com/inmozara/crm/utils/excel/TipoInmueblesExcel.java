package com.inmozara.crm.utils.excel;

import com.inmozara.crm.inmueble.model.dto.TipoInmuebleDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class TipoInmueblesExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        setCustomWidth(17000);
        setCustomWidthColumn(true);
        return "LISTADO DE TIPOS DE INMEBLES";
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
        for (TipoInmuebleDTO dto : (List<TipoInmuebleDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getId()), this.row);
            creaCeldaNormal(this.columna++, dto.getTipo(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
