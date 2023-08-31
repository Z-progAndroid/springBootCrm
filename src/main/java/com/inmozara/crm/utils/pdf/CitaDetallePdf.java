package com.inmozara.crm.utils.pdf;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.EstadoCita;
import com.inmozara.crm.cita.model.TipoCita;
import com.inmozara.crm.excepcion.PdfGeneracionException;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.inmueble.model.TipoInmueble;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.utils.pdf.base.PdfBase;
import com.itextpdf.text.Paragraph;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Builder
@Getter
public class CitaDetallePdf extends PdfBase {
    Optional<Cita> cita;

    @Override
    public String getTitulo() {
        return "CITA DETALLE";
    }

    @Override
    public void crearContendioPdf() {
        try {
            Paragraph content = new Paragraph();
            parrafoDatosCita(content);
            parrafoDatosInmueble(content);
            parrafoDatosCliente(content);
            parrafoDatosDetalleCita(content);
            parrafoFin(content);
            getDocument().add(content);
        } catch (Exception e) {
            throw new PdfGeneracionException(e.getMessage());
        }
    }

    private void parrafoDatosCita(Paragraph content) {
        content.add(new Paragraph("A continuación, se presenta el detalle de la cita programada para la visita a un inmueble:", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
        content.add(new Paragraph("ID de Cita: " + comprobacionOptinal(cita.map(Cita::getIdCita), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Título: " + comprobacionOptinal(cita.map(Cita::getTitulo), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Descripción: " + comprobacionOptinal(cita.map(Cita::getDescripcion), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Fecha de Inicio: " + comprobacionOptinal(cita.map(Cita::getFechaInicio), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Fecha de Fin: " + comprobacionOptinal(cita.map(Cita::getFechaFin), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Fecha de Creación: " + comprobacionOptinal(cita.map(Cita::getFechaCreacion), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Fecha de Modificación: " + comprobacionOptinal(cita.map(Cita::getFechaModificacion), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Última Modificación por: " + comprobacionOptinal(cita.map(Cita::getModificado), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
    }

    private void parrafoDatosInmueble(Paragraph content) {
        addEspacio(2, content);
        content.add(new Paragraph("Detalles del Inmueble:", HELVETICA_SIZE_14_BOLD));
        content.add(new Paragraph("Dirección: " + comprobacionOptinal(cita.map(Cita::getInmueble).map(Inmueble::getDireccion), LINEAS_PARA_RELLENAR), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Tipo de Inmueble: " + comprobacionOptinal(cita.map(Cita::getInmueble).map(Inmueble::getTipoInmueble).map(TipoInmueble::getTipo), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Descripción del Inmueble: " + comprobacionOptinal(cita.map(Cita::getInmueble).map(Inmueble::getDescripcion), LINEAS_PARA_RELLENAR_EXTRA_BIG), HELVETICA_SIZE_12_NORMAL));
    }

    private void parrafoDatosCliente(Paragraph content) {
        addEspacio(2, content);
        content.add(new Paragraph("Detalles del Cliente:", HELVETICA_SIZE_14_BOLD));
        content.add(new Paragraph("Nombre: " + comprobacionOptinal(cita.map(Cita::getCliente).map(Usuario::getNombre), LINEAS_PARA_RELLENAR), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Apellido: " + comprobacionOptinal(cita.map(Cita::getCliente).map(Usuario::getApellido), LINEAS_PARA_RELLENAR), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Correo Electrónico: " + comprobacionOptinal(cita.map(Cita::getCliente).map(Usuario::getEmail), LINEAS_PARA_RELLENAR), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Teléfono: " + comprobacionOptinal(cita.map(Cita::getCliente).map(Usuario::getTelefono), LINEAS_PARA_RELLENAR), HELVETICA_SIZE_12_NORMAL));
    }

    private void parrafoDatosDetalleCita(Paragraph content) {
        addEspacio(2, content);
        content.add(new Paragraph("Detalles de la Cita:", HELVETICA_SIZE_14_BOLD));
        content.add(new Paragraph("Tipo de Cita: " + comprobacionOptinal(cita.map(Cita::getTipoCita).map(TipoCita::getTipoCita), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Estado de Cita: " + comprobacionOptinal(cita.map(Cita::getEstadoCita).map(EstadoCita::getEstadoCita), LINEAS_PARA_RELLENAR_SMALL), HELVETICA_SIZE_12_NORMAL));
    }

    private void parrafoFin(Paragraph content) {
        addEspacio(2, content);
        content.add(new Paragraph("Agradecemos su interés en visitar este inmueble y esperamos que la cita sea exitosa. Si tiene alguna pregunta o necesita más información, no dude en ponerse en contacto con nosotros.", HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Atentamente,", HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Inmozara", HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("contacta con el agente asignado al inmueble para cualquier duda. "
                + comprobacionOptinal(cita.map(Cita::getInmueble).map(Inmueble::getUsuario).map(usuario -> usuario.getNombre() + " " + usuario.getApellido()), LINEAS_PARA_RELLENAR)
                + " con telefono "
                + comprobacionOptinal(cita.map(Cita::getInmueble).map(Inmueble::getUsuario).map(Usuario::getTelefono), LINEAS_PARA_RELLENAR)
                + " y email "
                + comprobacionOptinal(cita.map(Cita::getInmueble).map(Inmueble::getUsuario).map(Usuario::getEmail), LINEAS_PARA_RELLENAR)
                , HELVETICA_SIZE_12_NORMAL));
    }
}
