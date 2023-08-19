package com.inmozara.crm.utils.pdf;

import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.excepcion.PdfGeneracionException;
import com.inmozara.crm.inmueble.model.Inmueble;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.utils.Constantes;
import com.inmozara.crm.utils.pdf.base.PdfBase;
import com.itextpdf.text.List;
import com.itextpdf.text.Paragraph;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Builder
@Getter
public class ContratoArrendamientoPdf extends PdfBase {
    private static final String TITULO_CONTRATO = "CONTRATO DE ARRENDAMIENTO";
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
            content.add("En la ciudad de " + CIUDAD + ", a " + comprobacionOptinal(contrato.map(Contrato::getFechaCreacion), LINEAS_PARA_RELLENAR) + ".");
            generarParrafoComparcen(content);
            generarDeclaraciones(content);
            generarClausulas(content);
            genenarTerminosYCondiciones(content);
            parrafoFirmas(content, arrendador, arrendatario);
            addEspacio(1, content);
            content.add(new Paragraph("IMPORTANTE: Este es un modelo de contrato de arrendamiento general y genérico. Te recomiendo encarecidamente que consultes con un profesional legal para adaptar y verificar el contrato de acuerdo a las leyes y regulaciones específicas de tu país y situación.", HELVETICA_SIZE_12_NORMAL));
            getDocument().add(content);
        } catch (Exception e) {
            throw new PdfGeneracionException(e.getMessage());
        }
    }

    private void generarParrafoComparcen(Paragraph content) {
        content.add(new Paragraph("COMPARECEN", HELVETICA_SIZE_12_BOLD));
        addEspacio(1, content);
        content.add(new Paragraph("De una parte, el/la Sr./Sra. " + arrendador.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR) + Constantes.ESPACIO + arrendador.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR) + "," +
                " mayor de edad, con domicilio en " + arrendador.map(Usuario::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG) + ", " +
                "documento de identidad número " + arrendador.map(Usuario::getDni).orElse(LINEAS_PARA_RELLENAR) + ", en adelante \"EL ARRENDADOR\"."
                , HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
        content.add(new Paragraph("Y de la otra parte, el/la Sr./Sra. " + arrendatario.map(Usuario::getNombre).orElse(LINEAS_PARA_RELLENAR) + Constantes.ESPACIO + arrendatario.map(Usuario::getApellido).orElse(LINEAS_PARA_RELLENAR) + ", mayor de edad," +
                "con domicilio en " + arrendatario.map(Usuario::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG) + "," +
                " documento de identidad número " + arrendatario.map(Usuario::getDni).orElse(LINEAS_PARA_RELLENAR) + ", en adelante \"EL ARRENDATARIO\"."
                , HELVETICA_SIZE_12_NORMAL));
        addEspacio(1, content);
        content.add(new Paragraph("Ambas partes, en adelante, podrán ser referidas de manera individual como \"La Parte\" y en conjunto como \"Las Partes\"."
                , HELVETICA_SIZE_12_NORMAL));
    }

    private void generarDeclaraciones(Paragraph content) {
        addEspacio(1, content);
        content.add(new Paragraph("DECLARACIONES", HELVETICA_SIZE_12_BOLD));
        content.add(addLista(Arrays.asList(
                "EL ARRENDADOR es propietario de " + inmueble.map(Inmueble::getDescripcion).orElse(LINEAS_PARA_RELLENAR_EXTRA_BIG) + ", ubicado en " + inmueble.map(Inmueble::getDireccion).orElse(LINEAS_PARA_RELLENAR_BIG),
                "EL ARRENDATARIO está interesado en arrendar el inmueble mencionado en la declaración anterior para su uso exclusivo como vivienda.",
                "Ambas partes, de común acuerdo, establecen las siguientes cláusulas y condiciones que regirán el presente contrato de arrendamiento:"
        ), List.ORDERED));
    }

    private void generarClausulas(Paragraph content) {
        addEspacio(1,content);
        content.add(new Paragraph("CLAUSULAS", HELVETICA_SIZE_12_BOLD));
        content.add(addLista(Arrays.asList(
                "Objeto del Contrato: EL ARRENDADOR cede en arrendamiento al EL ARRENDATARIO el inmueble mencionado en la declaración de las partes, por un periodo de [Duración del Contrato] meses, a contar desde el " + comprobacionOptinal(contrato.map(Contrato::getFechaInicio),LINEAS_PARA_RELLENAR_SMALL) + " hasta el " + comprobacionOptinal(contrato.map(Contrato::getFechaFinalizacion),LINEAS_PARA_RELLENAR_SMALL) + ", con el propósito exclusivo de ser utilizado como vivienda.",
                "Renta: EL ARRENDATARIO se compromete a pagar una renta mensual de " + comprobacionOptinal(contrato.map(Contrato::getValor),LINEAS_PARA_RELLENAR_SMALL) + " " + EUROS + ", que deberá ser abonada antes del " + comprobacionOptinal(contrato.map(Contrato::getFechaInicio),LINEAS_PARA_RELLENAR_SMALL) + " de cada mes.",
                "Depósito de Garantía: EL ARRENDATARIO entregará a EL ARRENDADOR una suma de 500 " + EUROS + " como depósito de garantía, la cual será devuelta al finalizar el contrato, previa inspección del inmueble y en caso de no existir daños o adeudos pendientes.",
                "Uso y Conservación del Inmueble: EL ARRENDATARIO se compromete a utilizar el inmueble exclusivamente como vivienda y a mantenerlo en buen estado de conservación y limpieza. Cualquier modificación o alteración en el inmueble requerirá el consentimiento previo y por escrito de EL ARRENDADOR.",
                "Obligaciones del Arrendador: EL ARRENDADOR se compromete a entregar el inmueble en condiciones adecuadas de habitabilidad y a realizar las reparaciones necesarias para su mantenimiento.",
                "Obligaciones del Arrendatario: EL ARRENDATARIO se compromete a cumplir con el pago puntual de la renta, a respetar las normativas de convivencia del edificio o conjunto residencial, y a informar oportunamente a EL ARRENDADOR sobre cualquier daño o avería en el inmueble.",
                "Término del Contrato: Al finalizar el plazo establecido en la cláusula 1, EL ARRENDATARIO deberá desocupar el inmueble y devolverlo en las mismas condiciones en que fue recibido, salvo el desgaste natural por el uso ordinario.",
                "Rescisión del Contrato: Cualquiera de las partes podrá dar por terminado el contrato de arrendamiento mediante notificación escrita con 30 días de anticipación. En caso de incumplimiento grave de alguna de las cláusulas establecidas en este contrato, la parte afectada podrá rescindir el contrato de manera inmediata.",
                "Ley Aplicable: Este contrato se regirá por las leyes de [Ley Aplicable] y cualquier controversia que surja en relación con este contrato será sometida a la jurisdicción de los tribunales competentes de " + CIUDAD + "."
        ), List.ORDERED));
        addEspacio(1, content);
    }

    private void genenarTerminosYCondiciones(Paragraph content) {
        content.add(new Paragraph("Términos y Condiciones", HELVETICA_SIZE_12_BOLD));
        content.add(addLista(Arrays.asList(
                "Objetivo y Alcance: La inmobiliaria actúa como intermediario en la venta de propiedades inmobiliarias en propiedad.La inmobiliaria no se responsabiliza por la condición, legalidad o calidad de las propiedades ofrecidas.",
                "Propiedad Intelectual: Todo el contenido, imágenes y materiales en el sitio web y documentos de la inmobiliaria están protegidos por derechos de autor y no deben ser reproducidos sin autorización.",
                "Publicidad y Descripciones: La inmobiliaria se esfuerza por proporcionar descripciones precisas y completas de las propiedades, pero no garantiza la exactitud de la información proporcionada.",
                "Confidencialidad: La inmobiliaria tratará la información personal y financiera de los clientes con confidencialidad y no la compartirá con terceros sin consentimiento.",
                "Honorarios y Comisiones: Los honorarios y comisiones de la inmobiliaria se acordarán por escrito antes de comenzar cualquier transacción. Las comisiones se pagan una vez que se ha completado exitosamente la venta.",
                "Obligaciones del Cliente: Los clientes deben proporcionar información precisa y completa sobre la propiedad y su situación financiera. Los clientes deben cumplir con los plazos y requisitos establecidos durante la transacción.",
                "Responsabilidad Legal: La inmobiliaria no se hace responsable por pérdidas o daños resultantes de transacciones o decisiones basadas en la información proporcionada.",
                "Rescisión y Cancelación: Las partes pueden rescindir un acuerdo por escrito en caso de incumplimiento o acuerdo mutuo. Algunos acuerdos pueden estar sujetos a períodos de cancelación específicos.",
                "Ley Aplicable y Jurisdicción: Cualquier disputa o controversia se resolverá de acuerdo con las leyes de " + CIUDAD + " y se someterá a la jurisdicción exclusiva de los tribunales de " + CIUDAD + "."
        ), List.ORDERED));
        addEspacio(1, content);
    }
}
