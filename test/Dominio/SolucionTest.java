package Dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase SolucionTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Solucion.
 *
 * Pruebas incluidas:
 * - testConstructores   : verifica que ambos constructores inicialicen
 * los atributos correctamente
 * - getIdSolucion       : verifica que el getter retorna el ID asignado
 * - setIdSolucion       : verifica que el setter modifica el ID correctamente
 * - getFechaSolucion    : verifica que la fecha se asigna y obtiene correctamente
 * - setFechaSolucion    : verifica que una fecha nula no reemplaza la fecha actual
 * - getDetalleSolucion  : verifica que el getter retorna el detalle asignado
 * - setDetalleSolucion  : verifica que se acepta un detalle válido
 * y se rechaza uno que no cumple la validación
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class SolucionTest {

    /**
     * Verifica que ambos constructores inicialicen los atributos correctamente.
     */
    @Test
    void testConstructores() {
        Solucion s1 = new Solucion();
        assertEquals("Sin detalle", s1.getDetalleSolucion());

        Solucion s2 = new Solucion(1, new Date(), "Cambio de RAM");
        assertEquals("Cambio de RAM", s2.getDetalleSolucion());
        System.out.println("Constructores funcionan✅");
    }

    /**
     * Verifica que el getter retorna el ID asignado.
     */
    @Test
    void getIdSolucion() {
        Solucion s = new Solucion();
        s.setIdSolucion(1);
        assertEquals(1, s.getIdSolucion());
        System.out.println("getIdSolucion funciona✅");
    }

    /**
     * Verifica que el setter modifica el ID correctamente.
     */
    @Test
    void setIdSolucion() {
        Solucion s = new Solucion();
        s.setIdSolucion(2);
        assertEquals(2, s.getIdSolucion());
        System.out.println("setIdSolucion funciona✅");
    }

    /**
     * Verifica que la fecha se asigna y obtiene correctamente.
     */
    @Test
    void getFechaSolucion() {
        Date f = new Date();
        Solucion s = new Solucion();
        s.setFechaSolucion(f);
        assertEquals(f, s.getFechaSolucion());
        System.out.println("getFechaSolucion funciona✅");
    }

    /**
     * Verifica que una fecha nula no reemplaza la fecha actual.
     */
    @Test
    void setFechaSolucion() {
        Solucion s = new Solucion();
        s.setFechaSolucion(null);
        assertNotNull(s.getFechaSolucion());
        System.out.println("setFechaSolucion funciona✅");
    }

    /**
     * Verifica que el getter retorna el detalle asignado.
     */
    @Test
    void getDetalleSolucion() {
        Solucion s = new Solucion();
        s.setDetalleSolucion("Instalacion SO");
        assertEquals("Instalacion SO", s.getDetalleSolucion());
        System.out.println("getDetalleSolucion funciona✅");
    }

    /**
     * Verifica que se acepta un detalle válido y se rechaza uno que no cumple la validación.
     */
    @Test
    void setDetalleSolucion() {
        Solucion s = new Solucion();
        s.setDetalleSolucion("Limpieza profunda terminada");
        assertEquals("Limpieza profunda terminada", s.getDetalleSolucion());
        s.setDetalleSolucion("Mal");
        assertEquals("Sin detalle", s.getDetalleSolucion());
        System.out.println("setDetalleSolucion funciona✅");
    }
}