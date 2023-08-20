package com.inmozara.crm.utils.excel;

import com.inmozara.crm.inmueble.model.dto.PaisDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class PaisExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        setCustomWidth(17000);
        setCustomWidthColumn(true);
        return "LISTADO DE PAISES";
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
        for (PaisDTO dto : (List<PaisDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdPais()), this.row);
            creaCeldaNormal(this.columna++, dto.getPais(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
