package com.inmozara.crm.utils.excel;

import com.inmozara.crm.contrato.model.dto.EstadoContratoDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;

@Builder
public class EstadoContratoExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        setUltimaColumnaCabecera(3);
        this.isCustomWidthColumn = true;
        return "LISTADO DE ESTADOS DE CONTRATO";
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
        for (EstadoContratoDTO dto : (List<EstadoContratoDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, String.valueOf(dto.getIdestadoContrato()), this.row);
            creaCeldaNormal(this.columna++, dto.getEstado(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
