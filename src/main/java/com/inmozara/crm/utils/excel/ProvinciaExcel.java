package com.inmozara.crm.utils.excel;

import com.inmozara.crm.inmueble.model.dto.ProvinciaDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class ProvinciaExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(4);
        setCustomWidth(17000);
        setCustomWidthColumn(true);
        return "LISTADO DE PROVINCIAS";
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
        for (ProvinciaDTO dto : (List<ProvinciaDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdPais()), this.row);
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdProvincia()), this.row);
            creaCeldaNormal(this.columna++, dto.getProvincia(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
