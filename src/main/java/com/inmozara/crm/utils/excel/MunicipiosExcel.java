package com.inmozara.crm.utils.excel;

import com.inmozara.crm.inmueble.model.dto.MunicipoDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class MunicipiosExcel extends ExcelBase {
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
        for (MunicipoDTO dto : (List<MunicipoDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdProvincia()), this.row);
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdMunicipio()), this.row);
            creaCeldaNormal(this.columna++, dto.getMunicipio(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
