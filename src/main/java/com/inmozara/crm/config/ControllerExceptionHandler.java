package com.inmozara.crm.config;

import com.inmozara.crm.excepcion.*;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RecursoNoEncontrado.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public MensajeDTO recursoNoEncontrado(RecursoNoEncontrado e, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.NOT_FOUND.value())
                .fecha(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()))
                .mensaje(e.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MensajeDTO excepcionGobal(Exception ex, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fecha(UtilsDates.now())
                .mensaje(ex.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public MensajeDTO methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.BAD_REQUEST.value())
                .fecha(UtilsDates.now())
                .mensaje(ex.getBindingResult().getFieldErrors()
                        .stream()
                        .map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .collect(Collectors.joining(", ")))
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(GuardarImagenException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MensajeDTO imagenGuardarException(GuardarImagenException e, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fecha(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()))
                .mensaje(e.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(CargarImagenException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MensajeDTO cargarImagenException(CargarImagenException e, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fecha(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()))
                .mensaje(e.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(ContratoException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MensajeDTO contratoActivoException(ContratoException e, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fecha(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()))
                .mensaje(e.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(PdfGeneracionException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MensajeDTO pdfGeneracionException(PdfGeneracionException e, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fecha(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()))
                .mensaje("Se producido un error al generar el pdf " + e.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(ExcelGenerationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MensajeDTO excelGenerationException(ExcelGenerationException e, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fecha(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()))
                .mensaje("Se producido un error al generar el exacel " + e.getMessage())
                .description(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(GenerarGraficoException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public MensajeDTO generarGraficoException(GenerarGraficoException e, WebRequest request) {
        return MensajeDTO.builder()
                .estado(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .fecha(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()))
                .mensaje("Se producido un error al generar el grafico"+e.getMessage())
                .description(request.getDescription(false))
                .build();
    }
}
