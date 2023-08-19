package com.inmozara.crm.utils.pdf.base;

import com.inmozara.crm.excepcion.PdfGeneracionException;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.utils.Constantes;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Getter
public abstract class PdfBase {
    private static final String RUTA_IMAGEN_PDF = "src/main/resources/static/sql/img/logo.png";
    protected static final String DD_MM_YYYY = "dd/MM/yyyy";
    private static final int NUM_COLUMNNAS = 2;
    private static final int ANCHO_PORCENTAJE_TABLA = 100;
    private static final int ESCALA_IMAGEN = 80;
    protected static final String LINEAS_PARA_RELLENAR_SMALL = "________________";
    protected static final String LINEAS_PARA_RELLENAR = "_______________________";
    protected static final String LINEAS_PARA_RELLENAR_BIG = "___________________________________________";
    protected static final String LINEAS_PARA_RELLENAR_EXTRA_BIG = "____________________________________________________________________________________________________________________________________________________________";
    protected static final String EUROS = "€";
    private Document document;
    private PdfWriter writer;
    private ByteArrayOutputStream pdfBytes;
    private static final String NOMBRE_APP = "INMOZARA";
    private static final String TIULO_PDF = "INMOZARA PDF";

    protected static final String CIUDAD = "Zaragoza";


    //FUENTES
    protected static final Font HELVETICA_SIZE_14_BOLD = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    protected static final Font HELVETICA_SIZE_12_BOLD = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    protected static final Font HELVETICA_SIZE_12_NORMAL = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);


    public abstract String getTitulo();

    public abstract void crearContendioPdf();

    public ByteArrayOutputStream generarPdf() {
        init();
        crearEncabezado(getTitulo());
        crearContendioPdf();
        getWriter().close();
        getDocument().close();
        return getPdfBytes();
    }

    private void init() {
        try {
            this.document = new Document();
            this.pdfBytes = new ByteArrayOutputStream();
            this.writer = PdfWriter.getInstance(document, pdfBytes);
            getDocument().open();
            getDocument().addAuthor(NOMBRE_APP);
            getDocument().addCreationDate();
            getDocument().addCreator(NOMBRE_APP);
            getDocument().addTitle(TIULO_PDF);
            getDocument().addSubject(TIULO_PDF);
        } catch (DocumentException e) {
            throw new PdfGeneracionException(e.getMessage());
        }
    }

    private void crearEncabezado(final String titulo) {
        try {
            PdfPTable headerTable = new PdfPTable(NUM_COLUMNNAS);
            headerTable.setWidthPercentage(ANCHO_PORCENTAJE_TABLA);

            PdfPCell imageCell = new PdfPCell();
            Image image = Image.getInstance(RUTA_IMAGEN_PDF);
            image.scaleAbsoluteWidth(ESCALA_IMAGEN);
            imageCell.setImage(image);
            imageCell.setBorder(Rectangle.NO_BORDER);
            imageCell.setVerticalAlignment(Element.ALIGN_CENTER);
            headerTable.addCell(imageCell);

            PdfPCell titleCell = new PdfPCell(new Paragraph(titulo, HELVETICA_SIZE_14_BOLD));
            titleCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            titleCell.setPaddingBottom(15.0f);
            titleCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(titleCell);

            getDocument().add(headerTable);
        } catch (DocumentException | IOException e) {
            System.err.println("Se produjo un error " + e);
            throw new PdfGeneracionException(e.getMessage());
        }

    }

    protected Paragraph addEspacio(int numeroEspacios, Paragraph paragraph) {
        for (int i = 0; i < numeroEspacios; i++) {
            paragraph.add(new Phrase(Chunk.NEWLINE));
        }
        return paragraph;
    }

    protected List addLista(java.util.List<String> opciones, boolean ordenanda) {
        List orderedList = new List(ordenanda);
        opciones.stream().map(ListItem::new).forEach(orderedList::add);
        return orderedList;
    }

    protected void parrafoFirmas(Paragraph content, Optional<Usuario> arrendador, Optional<Usuario> arrendatario) {
        addEspacio(1, content);
        content.add(new Paragraph("Las partes declaran que han leído y comprendido cada una de las cláusulas y condiciones contenidas en este contrato," +
                " y lo firman en señal de conformidad en la fecha y lugar mencionados al inicio.", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
        content.add(new Paragraph("Firma del Arrendador: " + LINEAS_PARA_RELLENAR_SMALL + " Firma del Arrendatario: " + LINEAS_PARA_RELLENAR_SMALL + ""));
        content.add(new Paragraph("Nombre Arrendador: " + arrendador.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR_SMALL) + Constantes.ESPACIO + arrendador.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR_SMALL) + " Nombre Arrendatario: " + arrendatario.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR_SMALL) + Constantes.ESPACIO + arrendatario.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR_SMALL) + ""));
        content.add(new Paragraph("Documento de Identidad Arrendador: " + LINEAS_PARA_RELLENAR_SMALL + " Documento de Identidad Arrendatario:" + LINEAS_PARA_RELLENAR_SMALL));
        content.add(new Paragraph("Testigo: " + LINEAS_PARA_RELLENAR + " Testigo: " + LINEAS_PARA_RELLENAR));
        content.add(new Paragraph("Nombre testigo: " + LINEAS_PARA_RELLENAR + " Nombre testigo: " + LINEAS_PARA_RELLENAR));
        content.add(new Paragraph("Documento de Identidad: " + LINEAS_PARA_RELLENAR_SMALL + " Documento de Identidad: " + LINEAS_PARA_RELLENAR_SMALL));
        addEspacio(1, content);
    }

    protected void parrafoFirmaElectronica(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("FIRMA ELECTRÓNICA: ", HELVETICA_SIZE_14_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("Ambas Partes aceptan que sus firmas electrónicas en copias escaneadas o en formato digital tendrán la misma validez y efecto que sus firmas manuscritas."));
        addEspacio(1, content);
    }

    protected void parrafoLeyes(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("LEY APLICABLE Y JURISDICCIÓN:", HELVETICA_SIZE_14_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("Este contrato se regirá e interpretará de acuerdo con las leyes del numero 45. " + "Cualquier disputa relacionada con este contrato estará sujeta a la jurisdicción exclusiva de los tribunales de " + CIUDAD + "."));
        addEspacio(1, content);
    }

    protected Serializable comprobacionOptinal(Optional optional, String sustitucion) {
        if (optional.isPresent() && optional.get() instanceof Date) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DD_MM_YYYY);
            return dateFormat.format(optional.get());
        }
        return optional.isPresent() ?
                (Serializable) optional.get()
                : sustitucion;
    }
}
