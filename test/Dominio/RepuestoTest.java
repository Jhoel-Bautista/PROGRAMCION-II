package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase RepuestoTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Repuesto.
 *
 * Pruebas incluidas:
 * - testConstructores      : verifica que ambos constructores inicialicen
 * los atributos correctamente
 * - getIdRepuesto          : verifica que el getter retorna el ID asignado
 * - setIdRepuesto          : verifica que el setter modifica el ID correctamente
 * - getNombreRepuesto      : verifica que el getter retorna el nombre asignado
 * - setNombreRepuesto      : verifica que se acepta un nombre válido
 * y se rechaza uno vacío
 * - getDescripcionRepuesto : verifica que el getter retorna la descripción asignada
 * - setDescripcionRepuesto : verifica que se acepta una descripción válida
 * y se rechaza una vacía
 * - getStockDisponible     : verifica que el getter retorna el stock asignado
 * - setStockDisponible     : verifica que se acepta stock positivo
 * y se rechaza stock negativo (queda en 0)
 * - getCostoUnitario       : verifica que el getter retorna el costo asignado
 * - setCostoUnitario       : verifica que se acepta costo positivo
 * y se rechaza costo negativo (queda en 0.0)
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class RepuestoTest {

    /**
     * Verifica que ambos constructores inicialicen los atributos correctamente.
     */
    @Test
    void testConstructores() {
        Repuesto r1 = new Repuesto();
        assertEquals("Sin nombre", r1.getNombreRepuesto());

        Repuesto r2 = new Repuesto(1, "RAM", "8GB", 10, 25.5);
        assertEquals("RAM", r2.getNombreRepuesto());
        System.out.println("Constructores funcionan✅");
    }

    /**
     * Verifica que el getter retorna el ID asignado.
     */
    @Test
    void getIdRepuesto() {
        Repuesto r = new Repuesto();
        r.setIdRepuesto(1);
        assertEquals(1, r.getIdRepuesto());
        System.out.println("getIdRepuesto funciona✅");
    }

    /**
     * Verifica que el setter modifica el ID correctamente.
     */
    @Test
    void setIdRepuesto() {
        Repuesto r = new Repuesto();
        r.setIdRepuesto(2);
        assertEquals(2, r.getIdRepuesto());
        System.out.println("setIdRepuesto funciona✅");
    }

    /**
     * Verifica que el getter retorna el nombre asignado.
     */
    @Test
    void getNombreRepuesto() {
        Repuesto r = new Repuesto();
        r.setNombreRepuesto("SSD");
        assertEquals("SSD", r.getNombreRepuesto());
        System.out.println("getNombreRepuesto funciona✅");
    }

    /**
     * Verifica que se acepta un nombre válido y se rechaza uno vacío.
     */
    @Test
    void setNombreRepuesto() {
        Repuesto r = new Repuesto();
        r.setNombreRepuesto("Pantalla");
        assertEquals("Pantalla", r.getNombreRepuesto());
        r.setNombreRepuesto("");
        assertEquals("Sin nombre", r.getNombreRepuesto());
        System.out.println("setNombreRepuesto funciona✅");
    }

    /**
     * Verifica que el getter retorna la descripción asignada.
     */
    @Test
    void getDescripcionRepuesto() {
        Repuesto r = new Repuesto();
        r.setDescripcionRepuesto("Nueva");
        assertEquals("Nueva", r.getDescripcionRepuesto());
        System.out.println("getDescripcionRepuesto funciona✅");
    }

    /**
     * Verifica que se acepta una descripción válida y se rechaza una vacía.
     */
    @Test
    void setDescripcionRepuesto() {
        Repuesto r = new Repuesto();
        r.setDescripcionRepuesto("Usada");
        assertEquals("Usada", r.getDescripcionRepuesto());
        r.setDescripcionRepuesto("");
        assertEquals("Sin descripcion", r.getDescripcionRepuesto());
        System.out.println("setDescripcionRepuesto funciona✅");
    }

    /**
     * Verifica que el getter retorna el stock asignado.
     */
    @Test
    void getStockDisponible() {
        Repuesto r = new Repuesto();
        r.setStockDisponible(5);
        assertEquals(5, r.getStockDisponible());
        System.out.println("getStockDisponible funciona✅");
    }

    /**
     * Verifica que se acepta stock positivo y se rechaza stock negativo (queda en 0).
     */
    @Test
    void setStockDisponible() {
        Repuesto r = new Repuesto();
        r.setStockDisponible(10);
        assertEquals(10, r.getStockDisponible());
        r.setStockDisponible(-5);
        assertEquals(0, r.getStockDisponible());
        System.out.println("setStockDisponible funciona✅");
    }

    /**
     * Verifica que el getter retorna el costo asignado.
     */
    @Test
    void getCostoUnitario() {
        Repuesto r = new Repuesto();
        r.setCostoUnitario(15.0);
        assertEquals(15.0, r.getCostoUnitario());
        System.out.println("getCostoUnitario funciona✅");
    }

    /**
     * Verifica que se acepta costo positivo y se rechaza costo negativo (queda en 0.0).
     */
    @Test
    void setCostoUnitario() {
        Repuesto r = new Repuesto();
        r.setCostoUnitario(20.0);
        assertEquals(20.0, r.getCostoUnitario());
        r.setCostoUnitario(-10.0);
        assertEquals(0.0, r.getCostoUnitario());
        System.out.println("setCostoUnitario funciona✅");
    }
}