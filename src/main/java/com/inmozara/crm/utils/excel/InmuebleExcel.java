package com.inmozara.crm.utils.excel;

import com.inmozara.crm.inmueble.model.dto.InmuebleDTO;
import com.inmozara.crm.utils.excel.base.ExcelBase;
import lombok.Builder;

import java.util.List;
@Builder
public class InmuebleExcel extends ExcelBase {
    @Override
    public String getTitulo() {
        return "LISTADO INMUEBLES";
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
        for (InmuebleDTO dto : (List<InmuebleDTO>) contenidoTablaListado) {
            creaCeldaNormal(this.columna++, dto.getDireccion(), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, String.valueOf(dto.getPrecio_venta()), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, String.valueOf(dto.getPrecio_alquiler()), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, dto.getTipoInmueble(), this.row);
            getHoja().autoSizeColumn(this.columna);
            creaCeldaNormal(this.columna++, dto.getEstadoInmueble(), this.row);
            amuentarFila(++this.fila);
        }
    }
}
