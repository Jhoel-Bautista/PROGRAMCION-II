package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase EquipoTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Equipo.
 *
 * Pruebas incluidas:
 * - testConstructores : verifica que ambos constructores inicialicen
 * los atributos correctamente
 * - getIdEquipo       : verifica que el getter retorna el ID asignado
 * - setIdEquipo       : verifica que el setter modifica el ID correctamente
 * - getNumeroSerie    : verifica que el getter retorna el número de serie asignado
 * - setNumeroSerie    : verifica que se acepta un número de serie válido
 * y se rechaza uno inválido (demasiado corto)
 * - getModelo         : verifica que el getter retorna el modelo asignado
 * - setModelo         : verifica que se acepta un modelo válido
 * y se rechaza uno vacío
 * - getTipoEquipo     : verifica que el getter retorna el tipo de equipo asignado
 * - setTipoEquipo     : verifica que se acepta un tipo válido
 * y se rechaza uno vacío
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class EquipoTest {

    /**
     * Verifica que ambos constructores inicialicen los atributos correctamente.
     */
    @Test
    void testConstructores() {
        Equipo e1 = new Equipo();
        assertEquals("Sin serie", e1.getNumeroSerie());

        Equipo e2 = new Equipo(1, "SN-123", "Dell", "Laptop");
        assertEquals("SN-123", e2.getNumeroSerie());
        System.out.println("Constructores funcionan✅");
    }

    /**
     * Verifica que el getter retorna el ID asignado.
     */
    @Test
    void getIdEquipo() {
        Equipo e = new Equipo();
        e.setIdEquipo(5);
        assertEquals(5, e.getIdEquipo());
        System.out.println("getIdEquipo funciona✅");
    }

    /**
     * Verifica que el setter modifica el ID correctamente.
     */
    @Test
    void setIdEquipo() {
        Equipo e = new Equipo();
        e.setIdEquipo(10);
        assertEquals(10, e.getIdEquipo());
        System.out.println("setIdEquipo funciona✅");
    }

    /**
     * Verifica que el getter retorna el número de serie asignado.
     */
    @Test
    void getNumeroSerie() {
        Equipo e = new Equipo();
        e.setNumeroSerie("SN-999");
        assertEquals("SN-999", e.getNumeroSerie());
        System.out.println("getNumeroSerie funciona✅");
    }

    /**
     * Verifica que se acepta un número de serie válido y se rechaza uno inválido (demasiado corto).
     */
    @Test
    void setNumeroSerie() {
        Equipo e = new Equipo();
        e.setNumeroSerie("SN-000");
        assertEquals("SN-000", e.getNumeroSerie());
        e.setNumeroSerie("1");
        assertEquals("Sin serie", e.getNumeroSerie());
        System.out.println("setNumeroSerie funciona✅");
    }

    /**
     * Verifica que el getter retorna el modelo asignado.
     */
    @Test
    void getModelo() {
        Equipo e = new Equipo();
        e.setModelo("HP");
        assertEquals("HP", e.getModelo());
        System.out.println("getModelo funciona✅");
    }

    /**
     * Verifica que se acepta un modelo válido y se rechaza uno vacío.
     */
    @Test
    void setModelo() {
        Equipo e = new Equipo();
        e.setModelo("Asus");
        assertEquals("Asus", e.getModelo());
        e.setModelo("");
        assertEquals("Sin modelo", e.getModelo());
        System.out.println("setModelo funciona✅");
    }

    /**
     * Verifica que el getter retorna el tipo de equipo asignado.
     */
    @Test
    void getTipoEquipo() {
        Equipo e = new Equipo();
        e.setTipoEquipo("PC");
        assertEquals("PC", e.getTipoEquipo());
        System.out.println("getTipoEquipo funciona✅");
    }

    /**
     * Verifica que se acepta un tipo válido y se rechaza uno vacío.
     */
    @Test
    void setTipoEquipo() {
        Equipo e = new Equipo();
        e.setTipoEquipo("Servidor");
        assertEquals("Servidor", e.getTipoEquipo());
        e.setTipoEquipo("");
        assertEquals("Sin tipo", e.getTipoEquipo());
        System.out.println("setTipoEquipo funciona✅");
    }
}