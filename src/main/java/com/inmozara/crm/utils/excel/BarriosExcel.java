package com.inmozara.crm.utils.excel;

import com.inmozara.crm.inmueble.model.dto.BarrioDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class BarriosExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(4);
        setCustomWidth(17000);
        setCustomWidthColumn(true);
        return "LISTADO DE MUNICIPIOS";
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
        for (BarrioDTO dto : (List<BarrioDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdMunicipio()), this.row);
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdBarrio()), this.row);
            creaCeldaNormal(this.columna++, dto.getBarrio(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
