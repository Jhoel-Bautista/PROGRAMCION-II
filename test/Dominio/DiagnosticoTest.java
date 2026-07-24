package Dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase DiagnosticoTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Diagnostico.
 *
 * Pruebas incluidas:
 * - testConstructores        : verifica que ambos constructores inicialicen
 * los atributos correctamente
 * - getIdDiagnostico         : verifica que el getter retorna el ID asignado
 * - setIdDiagnostico         : verifica que el setter modifica el ID correctamente
 * - getFechaDiagnostico      : verifica que la fecha se asigna y obtiene correctamente
 * - setFechaDiagnostico      : verifica que una fecha nula no reemplaza la fecha actual
 * - getDetalleDiagnostico    : verifica que el getter retorna el detalle asignado
 * - setDetalleDiagnostico    : verifica que el setter acepta detalles válidos
 * y rechaza los que no cumplen la validación
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class DiagnosticoTest {

    /**
     * Verifica que ambos constructores inicialicen los atributos correctamente.
     */
    @Test
    void testConstructores() {
        Diagnostico d1 = new Diagnostico();
        assertEquals("Sin detalle", d1.getDetalleDiagnostico());

        Diagnostico d2 = new Diagnostico(1, new Date(), "Falla placa");
        assertEquals("Falla placa", d2.getDetalleDiagnostico());
        System.out.println("Constructores funcionan✅");
    }

    /**
     * Verifica que el getter retorna el ID asignado.
     */
    @Test
    void getIdDiagnostico() {
        Diagnostico d = new Diagnostico();
        d.setIdDiagnostico(1);
        assertEquals(1, d.getIdDiagnostico());
        System.out.println("getIdDiagnostico funciona✅");
    }

    /**
     * Verifica que el setter modifica el ID correctamente.
     */
    @Test
    void setIdDiagnostico() {
        Diagnostico d = new Diagnostico();
        d.setIdDiagnostico(2);
        assertEquals(2, d.getIdDiagnostico());
        System.out.println("setIdDiagnostico funciona✅");
    }

    /**
     * Verifica que la fecha se asigna y obtiene correctamente.
     */
    @Test
    void getFechaDiagnostico() {
        Date f = new Date();
        Diagnostico d = new Diagnostico();
        d.setFechaDiagnostico(f);
        assertEquals(f, d.getFechaDiagnostico());
        System.out.println("getFechaDiagnostico funciona✅");
    }

    /**
     * Verifica que una fecha nula no reemplaza la fecha actual.
     */
    @Test
    void setFechaDiagnostico() {
        Diagnostico d = new Diagnostico();
        d.setFechaDiagnostico(null);
        assertNotNull(d.getFechaDiagnostico());
        System.out.println("setFechaDiagnostico funciona✅");
    }

    /**
     * Verifica que el getter retorna el detalle asignado.
     */
    @Test
    void getDetalleDiagnostico() {
        Diagnostico d = new Diagnostico();
        d.setDetalleDiagnostico("Placa quemada");
        assertEquals("Placa quemada", d.getDetalleDiagnostico());
        System.out.println("getDetalleDiagnostico funciona✅");
    }

    /**
     * Verifica que el setter acepta detalles válidos y rechaza los que no cumplen la validación.
     */
    @Test
    void setDetalleDiagnostico() {
        Diagnostico d = new Diagnostico();
        d.setDetalleDiagnostico("Corto circuito en placa");
        assertEquals("Corto circuito en placa", d.getDetalleDiagnostico());
        d.setDetalleDiagnostico("Mal");
        assertEquals("Sin detalle", d.getDetalleDiagnostico());
        System.out.println("setDetalleDiagnostico funciona✅");
    }
}