package Dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase ReparacionTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Reparacion.
 *
 * Pruebas incluidas:
 * - testConstructores       : verifica que ambos constructores inicialicen
 * los atributos correctamente
 * - getIdReparacion         : verifica que el getter retorna el ID asignado
 * - setIdReparacion         : verifica que el setter modifica el ID correctamente
 * - getDescripcionProblema  : verifica que el getter retorna la descripción asignada
 * - setDescripcionProblema  : verifica que se acepta una descripción válida
 * y se rechaza una que no cumple la validación
 * - getEstadoReparacion     : verifica que el getter retorna el estado asignado
 * - setEstadoReparacion     : verifica que se acepta un estado válido
 * y que un estado vacío mantiene el valor anterior
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class ReparacionTest {

    /**
     * Verifica que ambos constructores inicialicen los atributos correctamente.
     */
    @Test
    void testConstructores() {
        Reparacion r1 = new Reparacion();
        assertEquals("Sin descripcion", r1.getDescripcionProblema());

        Reparacion r2 = new Reparacion(1, new Date(), new Date(), "Pantalla rota");
        assertEquals("Pantalla rota", r2.getDescripcionProblema());
        System.out.println("Constructores funcionan✅");
    }

    /**
     * Verifica que el getter retorna el ID asignado.
     */
    @Test
    void getIdReparacion() {
        Reparacion r = new Reparacion();
        r.setIdReparacion(1);
        assertEquals(1, r.getIdReparacion());
        System.out.println("getIdReparacion funciona✅");
    }

    /**
     * Verifica que el setter modifica el ID correctamente.
     */
    @Test
    void setIdReparacion() {
        Reparacion r = new Reparacion();
        r.setIdReparacion(2);
        assertEquals(2, r.getIdReparacion());
        System.out.println("setIdReparacion funciona✅");
    }

    /**
     * Verifica que el getter retorna la descripción asignada.
     */
    @Test
    void getDescripcionProblema() {
        Reparacion r = new Reparacion();
        r.setDescripcionProblema("Teclado no funciona");
        assertEquals("Teclado no funciona", r.getDescripcionProblema());
        System.out.println("getDescripcionProblema funciona✅");
    }

    /**
     * Verifica que se acepta una descripción válida y se rechaza una que no cumple la validación.
     */
    @Test
    void setDescripcionProblema() {
        Reparacion r = new Reparacion();
        r.setDescripcionProblema("Bateria inflada");
        assertEquals("Bateria inflada", r.getDescripcionProblema());
        r.setDescripcionProblema("Mal");
        assertEquals("Sin descripcion", r.getDescripcionProblema());
        System.out.println("setDescripcionProblema funciona✅");
    }

    /**
     * Verifica que el getter retorna el estado asignado.
     */
    @Test
    void getEstadoReparacion() {
        Reparacion r = new Reparacion();
        r.setEstadoReparacion("Terminado");
        assertEquals("Terminado", r.getEstadoReparacion());
        System.out.println("getEstadoReparacion funciona✅");
    }

    /**
     * Verifica que se acepta un estado válido y que un estado vacío mantiene el valor anterior.
     */
    @Test
    void setEstadoReparacion() {
        Reparacion r = new Reparacion();
        r.setEstadoReparacion("En proceso");
        assertEquals("En proceso", r.getEstadoReparacion());
        r.setEstadoReparacion("");
        assertEquals("En proceso", r.getEstadoReparacion());
        System.out.println("setEstadoReparacion funciona✅");
    }
}