package com.inmozara.crm.utils.pdf;

import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.excepcion.PdfGeneracionException;
import com.inmozara.crm.utils.pdf.base.PdfBase;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.utils.Constantes;
import com.itextpdf.text.Paragraph;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.Optional;

@Builder
@Getter
public class ContratoArrendamientoConOpcionACompraPdf extends PdfBase {
    private static final String TITULO_CONTRATO = "CONTRATO DE ARRENDAMIENTO CON OPCIÓN DE COMPRA";
    private Optional<Usuario> arrendador;
    private Optional<Usuario> arrendatario;
    private Optional<Inmueble> inmueble;
    private Optional<Contrato> contrato;

    @Override
    public String getTitulo() {
        return TITULO_CONTRATO;
    }

    @Override
    public void crearContendioPdf() {
        try {
            Paragraph content = new Paragraph();
            parrafoPartesContratantes(content);
            parrafoObjectoContrato(content);
            parrafoPlazoContrato(content);
            parrafoRenta(content);
            parrafoOpcionACompra(content);
            parrafoEjecerOpcionACompra(content);
            parrafoCondicionesVenta(content);
            parrafoGastosYResposibilidades(content);
            parrafoLeyes(content);
            parrafoFirmaElectronica(content);
            parrafoFirmas(content, arrendador, arrendatario);
            getDocument().add(content);
        } catch (Exception e) {
             throw new PdfGeneracionException(e.getMessage());
        }
    }

    private void parrafoPartesContratantes(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("PARTES CONTRATANTES:", HELVETICA_SIZE_14_BOLD));
        content.add(new Paragraph("Arrendador: " + arrendador.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR) + Constantes.ESPACIO + arrendador.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR) + ", " +
                "con domicilio en " + arrendador.map(Usuario::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG) + ", en adelante denominado \"Arrendador\".", HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Arrendatario:" + arrendador.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR) + Constantes.ESPACIO + arrendador.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR) + "," +
                " con domicilio en " + arrendador.map(Usuario::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG) + ", en adelante denominado \"Arrendatario\".", HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Ambas partes serán conjuntamente referidas como \"las Partes\".", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoObjectoContrato(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("OBJETO DEL CONTRATO:", HELVETICA_SIZE_14_BOLD));
        content.add(new Paragraph("El Arrendador se compromete a arrendar y el Arrendatario se compromete a tomar en arrendamiento el siguiente inmueble situado en " +
                inmueble.map(Inmueble::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG) + " y las siguientes caracteristicas " + inmueble.map(Inmueble::getDescripcion).orElse(LINEAS_PARA_RELLENAR_EXTRA_BIG) +
                ", en adelante denominado \"el Inmueble\".", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoPlazoContrato(Paragraph content) {
        addEspacio(1, content);
        long diffInMilliseconds = contrato.map(Contrato::getFechaFinalizacion).orElse(new Date()).getTime() - contrato.map(Contrato::getFechaInicio).orElse(new Date()).getTime();
        long diffInMonths = (diffInMilliseconds / (24 * 60 * 60 * 1000)) / 30;
        content.add(new Paragraph("PLAZO DEL CONTRATO:", HELVETICA_SIZE_12_BOLD));
        content.add(new Paragraph("El plazo de arrendamiento será de " + diffInMonths + " meses , comenzando el " +
                "" + comprobacionOptinal(contrato.map(Contrato::getFechaInicio),LINEAS_PARA_RELLENAR_SMALL) + " y finalizando el " + comprobacionOptinal(contrato.map(Contrato::getFechaFinalizacion),LINEAS_PARA_RELLENAR_SMALL) + ".", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoRenta(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("RENTA Y PAGO:", HELVETICA_SIZE_12_BOLD));
        content.add(new Paragraph("El Arrendatario se compromete a pagar al Arrendador una renta mensual de " + comprobacionOptinal(contrato.map(Contrato::getValor),LINEAS_PARA_RELLENAR_SMALL) + " " + EUROS + " durante el plazo de arrendamiento." +
                " El pago de la renta se realizará el " + comprobacionOptinal(contrato.map(Contrato::getFechaInicio),LINEAS_PARA_RELLENAR_SMALL) + " de cada mes.", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoOpcionACompra(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("OPCIÓN DE COMPRA:", HELVETICA_SIZE_12_BOLD));
        content.add(new Paragraph("Durante el plazo del contrato de arrendamiento, " +
                "el Arrendatario tiene la opción de comprar el Inmueble al finalizar el contrato, ejerciendo su derecho de opción de compra. " +
                "El precio de compra acordado para el Inmueble es de " + comprobacionOptinal(inmueble.map(Inmueble::getPrecio_venta),LINEAS_PARA_RELLENAR_SMALL) + " " + EUROS + ".", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoEjecerOpcionACompra(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("OPCIÓN DE COMPRA:", HELVETICA_SIZE_12_BOLD));
        content.add(new Paragraph("Para ejercer la opción de compra, el Arrendatario deberá notificar por escrito al Arrendador su intención de comprar el Inmueble con al menos " +
                "30 dias" + "de anticipación al final del contrato de arrendamiento." +
                "En caso de ejercicio de la opción, el monto de la renta pagado hasta la fecha será aplicado al precio de compra.", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoCondicionesVenta(Paragraph content) {
        addEspacio(3, content);
        content.add(new Paragraph("CONDICIONES DE VENTA:", HELVETICA_SIZE_12_BOLD));
        content.add(new Paragraph("La venta del Inmueble se realizará mediante la firma de un contrato de compraventa separado," +
                " que detallará los términos y condiciones de la transacción.", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoGastosYResposibilidades(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("GASTOS Y RESPONSABILIDADES:", HELVETICA_SIZE_12_BOLD));
        content.add(new Paragraph("El Arrendatario será responsable de los gastos de mantenimiento y reparación menores del Inmueble durante el plazo de arrendamiento.", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }
}
