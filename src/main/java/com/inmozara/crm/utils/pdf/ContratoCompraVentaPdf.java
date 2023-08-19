package com.inmozara.crm.utils.pdf;

import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.TipoPago;
import com.inmozara.crm.excepcion.PdfGeneracionException;
import com.inmozara.crm.utils.pdf.base.PdfBase;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import com.itextpdf.text.Paragraph;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Builder
@Getter
public class ContratoCompraVentaPdf extends PdfBase {
    private static final String TITULO_CONTRATO = "CONTRATO DE COMPRAVENTA";
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
            parrafoPrecioCompra(content);
            parrafoEntregaBien(content);
            parrafoGarantias(content);
            parrafoCesion(content);
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
        addEspacio(1, content);
        content.add(new Paragraph("Vendedor: " + arrendador.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR) + arrendador.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR) + ", con domicilio en " + arrendador.map(Usuario::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG) + ", en adelante denominado \"Vendedor\".", HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Comprador: " + arrendatario.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR) + arrendatario.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR) + ", con domicilio en " + arrendatario.map(Usuario::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG) + ", en adelante denominado \"Comprador\".", HELVETICA_SIZE_12_NORMAL));
        content.add(new Paragraph("Ambas partes serán conjuntamente referidas como \"las Partes\".", HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
    }

    private void parrafoObjectoContrato(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("OBJETO DEL CONTRATO:", HELVETICA_SIZE_14_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("El Vendedor se compromete a vender y el Comprador se compromete a comprar el siguiente bien: " + "" + inmueble.map(Inmueble::getDescripcion).orElse(LINEAS_PARA_RELLENAR_EXTRA_BIG) + ", en adelante denominado \"el Bien\"."));
        addEspacio(1, content);
    }

    private void parrafoPrecioCompra(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("PRECIO DE COMPRA:", HELVETICA_SIZE_14_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("El precio acordado por la compraventa del Bien es de " + comprobacionOptinal(contrato.map(Contrato::getValor),LINEAS_PARA_RELLENAR) + EUROS + "," + " que el Comprador se compromete a pagar al Vendedor de la siguiente manera: " + contrato.map(Contrato::getTipoPago).map(TipoPago::getTipo).orElse(LINEAS_PARA_RELLENAR) + "."));
        addEspacio(1, content);
    }

    private void parrafoEntregaBien(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("ENTREGA DEL BIEN:", HELVETICA_SIZE_14_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("El Vendedor se compromete a entregar el Bien al Comprador en el lugar y fecha acordados por ambas Partes." + " La entrega se considerará realizada una vez que el Bien esté físicamente en posesión del Comprador o su representante autorizado."));
        addEspacio(1, content);
    }

    private void parrafoGarantias(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("ENTREGA DEL BIEN:", HELVETICA_SIZE_14_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("El Vendedor declara y garantiza que tiene el derecho legal para vender el Bien y que el Bien se encuentra libre de cualquier gravamen," + " carga o reclamación de terceros. El Comprador acepta el Bien en su estado actual y libera al Vendedor de cualquier responsabilidad después de la entrega."));
        addEspacio(1, content);
    }

    private void parrafoCesion(Paragraph content) {
        addEspacio(2, content);
        content.add(new Paragraph("CESIÓN Y TRANSFERENCIA:", HELVETICA_SIZE_14_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("Este contrato no podrá ser cedido ni transferido por ninguna de las Partes sin el consentimiento previo por escrito de la otra Parte."));
        addEspacio(1, content);
    }
}
