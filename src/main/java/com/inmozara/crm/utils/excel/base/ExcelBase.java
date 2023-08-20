package com.inmozara.crm.utils.excel.base;

import com.inmozara.crm.excepcion.ExcelGenerationException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public abstract class ExcelBase {
    protected static int FILA_INICIO_DE_LA_TABLA = 7;
    private static final int COLUMNA_INICIO_DEL_TITULO = 2;
    private static final int DISTANCIA_FILAS_DESDE_TITULO_A_TABLA_CONETNIDO = 3;
    protected static final String FECHA_CREACION = "Fecha Creación:";
    protected static final String DD_MM_YYYY = "dd/MM/yyyy";
    protected static final int FONT_SIZE_24 = 24;
    protected static final int FONT_SIZE_14 = 14;
    protected static final int FONT_SIZE_12 = 12;
    private XSSFWorkbook workbook;
    public XSSFSheet hoja;
    protected XSSFRow row;
    protected XSSFCellStyle estiloEncabezadoTabla;
    protected XSSFCellStyle normalStyleAlignLeftConBordes;
    protected XSSFCellStyle dateStyle;
    protected int ultimaColumnaCabecera=6;
    protected int fila;
    public int columna;

    public abstract String getTitulo();

    public abstract void crearCabeceraTablaListado(List<String> cabeceraValuesList);

    public abstract void crearContenidoTablaListado(final List contenidoTablaListado);

    public ByteArrayOutputStream generarExcel(final List<String> cabeceraValuesList, final List contenidoTablaListado) {
        init();
        crearCabecera();
        crearCabeceraTablaListado(cabeceraValuesList);
        crearContenidoTablaListado(contenidoTablaListado);
        return generaExcelByteArray();
    }

    private void init() {
        this.workbook = new XSSFWorkbook();
        hoja = getWorkbook().createSheet(getTitulo());
        fila = FILA_INICIO_DE_LA_TABLA;
        columna = COLUMNA_INICIO_DEL_TITULO;
        row = getHoja().createRow(fila);
    }

    private void crearCabecera() {
        Drawing<?> drawing = getHoja().createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 0, 1, 1);
        int pictureIndex = workbook.addPicture(getImageData(), Workbook.PICTURE_TYPE_JPEG);
        Picture picture = drawing.createPicture(anchor, pictureIndex);
        picture.resize(); // Autoajustar tamaño
        Row row = getHoja().createRow(2);
        Cell messageCell = row.createCell(9);
        messageCell.setCellValue(FECHA_CREACION);
        messageCell.setCellStyle(dateStringStyle());
        CellRangeAddress messageMergedRegion = new CellRangeAddress(4, 4, 8, 9);
        getHoja().addMergedRegion(messageMergedRegion);
        Cell dateCell = row.createCell(10);
        dateCell.setCellValue(new Date());
        dateCell.setCellStyle(dateStyle());
        CellRangeAddress dateMergedRegion = new CellRangeAddress(4, 4, 10, 11);
        getHoja().addMergedRegion(dateMergedRegion);
        Row row2 = getHoja().createRow(6);
        Cell titulo = row2.createCell(2);
        titulo.setCellValue(getTitulo());
        titulo.setCellStyle(estiloEncabezadoTabla());
        CellRangeAddress titlemerge = new CellRangeAddress(6, 6, 2, ultimaColumnaCabecera);
        getHoja().addMergedRegion(titlemerge);
        for (int col = 8; col <= 11; col++) {
            getHoja().autoSizeColumn(col);
        }
    }

    public void creaCeldaEncabezadoTabla(int numCelda, String valor, Row row) {
        Cell celdaEncabezado = row.createCell(numCelda);
        celdaEncabezado.setCellValue(valor);
        celdaEncabezado.setCellStyle(estiloEncabezadoTabla());
        getHoja().autoSizeColumn(numCelda);
    }

    public void creaCeldaNormal(int numCelda, String valor, Row row) {
        Cell celdaEncabezado = row.createCell(numCelda);
        celdaEncabezado.setCellValue(valor);
        celdaEncabezado.setCellStyle(estiloCeldaNormal());
        getHoja().autoSizeColumn(numCelda);
    }

    private CellStyle estiloEncabezadoTabla() {
        XSSFFont font = getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) FONT_SIZE_14);
        font.setColor(IndexedColors.WHITE.getIndex());
        CellStyle style = getWorkbook().createCellStyle();
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }


    private CellStyle estiloCeldaNormal() {
        XSSFFont font = getWorkbook().createFont();
        font.setFontHeightInPoints((short) FONT_SIZE_12); // You can adjust the font size as needed
        CellStyle style = getWorkbook().createCellStyle();
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle dateStyle() {
        XSSFFont font = getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) FONT_SIZE_24);
        CellStyle style = getWorkbook().createCellStyle();
        CreationHelper createHelper = getWorkbook().getCreationHelper();
        style.setDataFormat(createHelper.createDataFormat().getFormat(DD_MM_YYYY));
        style.setFont(font);
        return style;
    }

    private CellStyle dateStringStyle() {
        XSSFFont font = getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) FONT_SIZE_24);
        CellStyle style = getWorkbook().createCellStyle();
        style.setFont(font);
        return style;
    }

    private byte[] getImageData() {
        try {
            return IOUtils.toByteArray(new FileInputStream("src/main/resources/static/sql/img/logo2.png"));
        } catch (IOException e) {
            throw new ExcelGenerationException("Error al obtener los datos de la imagen", e);
        }
    }

    public ByteArrayOutputStream generaExcelByteArray() {
        try {
            ByteArrayOutputStream fichero = new ByteArrayOutputStream();
            getWorkbook().write(fichero);
            fichero.close();
            return fichero;
        } catch (IOException e) {
            throw new ExcelGenerationException("Error al generar el excel", e);
        }

    }

    protected void amuentarFila(int fila) {
        row = getHoja().createRow(fila);
        this.columna = 2;
    }
}