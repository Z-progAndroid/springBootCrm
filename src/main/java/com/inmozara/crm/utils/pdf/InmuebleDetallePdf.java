package com.inmozara.crm.utils.pdf;

import com.inmozara.crm.excepcion.PdfGeneracionException;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.utils.pdf.base.PdfBase;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Builder
@Getter
public class InmuebleDetallePdf extends PdfBase {
    private Optional<Inmueble> inmueble;

    @Override
    public String getTitulo() {
        return "DETALLE DE INMUEBLE";
    }

    @Override
    public void crearContendioPdf() {
        try {
            Paragraph content = new Paragraph();
            parrafoImagenes(content);
            parrafoDetalle(content);
            getDocument().add(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void parrafoImagenes(Paragraph paragraph) {
        addEspacio(1, paragraph);
        PdfPTable table = new PdfPTable(2);
        getInmueble()
                .map(Inmueble::getImagen1)
                .ifPresent(bytes -> this.addImageEnTabla(table, bytes));
        getInmueble()
                .map(Inmueble::getImagen2)
                .ifPresent(bytes -> this.addImageEnTabla(table, bytes));
        getInmueble()
                .map(Inmueble::getImagen3)
                .ifPresent(bytes -> this.addImageEnTabla(table, bytes));
        getInmueble()
                .map(Inmueble::getImagen4)
                .ifPresent(bytes -> this.addImageEnTabla(table, bytes));
        paragraph.add(table);
        addEspacio(1, paragraph);
    }

    private void parrafoDetalle(Paragraph paragraph) {
        addEspacio(1, paragraph);
        paragraph.add(new Paragraph("Direccion: " + comprobacionOptinal(getInmueble().map(Inmueble::getDireccion), ""), HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, paragraph);
        paragraph.add(new Paragraph("Precio venta: " + comprobacionOptinal(getInmueble().map(Inmueble::getPrecio_venta), ""), HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, paragraph);
        paragraph.add(new Paragraph("Precio aquiler: " + comprobacionOptinal(getInmueble().map(Inmueble::getPrecio_alquiler), ""), HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, paragraph);
        paragraph.add(new Paragraph("Numero Baños: " + comprobacionOptinal(getInmueble().map(Inmueble::getNumBanos), "") +
                " Numero Habitaciones " + comprobacionOptinal(getInmueble().map(Inmueble::getNumHabitaciones), "") +
                " Metros Cuadrados " + comprobacionOptinal(getInmueble().map(Inmueble::getMetros_cuadrados), ""), HELVETICA_SIZE_12_NORMAL));
        paragraph.add(new Paragraph("Descripcion: " + comprobacionOptinal(getInmueble().map(Inmueble::getDescripcion), ""), HELVETICA_SIZE_12_NORMAL));


    }

    private void addImageEnTabla(PdfPTable pdfPTable, byte[] bytes) {
        try {
            pdfPTable.addCell(Image.getInstance(bytes));
        } catch (Exception e) {
            throw new PdfGeneracionException("Error al añdir las imagenes");
        }
    }
}
