package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase TecnicoTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Tecnico.
 *
 * Pruebas incluidas:
 * - testConstructores   : verifica que ambos constructores inicialicen
 * los atributos correctamente
 * - getNumeroEmpleado   : verifica que el getter retorna el número de empleado asignado
 * - setNumeroEmpleado   : verifica que se acepta un número válido
 * y que uno vacío restablece el valor por defecto
 * - getEspecialidad     : verifica que el getter retorna la especialidad asignada
 * - setEspecialidad     : verifica que se acepta una especialidad válida
 * y que una vacía restablece el valor por defecto
 * - getNivelAcceso      : verifica que el getter retorna el nivel de acceso asignado
 * - setNivelAcceso      : verifica que se acepta un nivel positivo
 * y que un nivel negativo restablece el valor por defecto (0)
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class TecnicoTest {

    /**
     * Verifica que ambos constructores inicialicen los atributos correctamente.
     */
    @Test
    void testConstructores() {
        // Verifica que el constructor vacío asigna "Sin numero" por defecto
        Tecnico t1 = new Tecnico();
        assertEquals("Sin numero", t1.getNumeroEmpleado());
        // Verifica que el constructor con parámetros asigna el número correctamente
        Tecnico t2 = new Tecnico(1, "Pedro", "p@m.com", "P123", "EMP-1", "Hardware", 1);
        assertEquals("EMP-1", t2.getNumeroEmpleado());
        System.out.println("Constructores funcionan✅");
    }

    /**
     * Verifica que el getter retorna el número de empleado asignado.
     */
    @Test
    void getNumeroEmpleado() {
        // Asigna un número de empleado y verifica que el getter lo retorna correctamente
        Tecnico t = new Tecnico();
        t.setNumeroEmpleado("EMP-2");
        assertEquals("EMP-2", t.getNumeroEmpleado());
        System.out.println("getNumeroEmpleado funciona✅");
    }

    /**
     * Verifica que se acepta un número válido y que uno vacío restablece el valor por defecto.
     */
    @Test
    void setNumeroEmpleado() {
        Tecnico t = new Tecnico();
        // Verifica que un número válido se guarda correctamente
        t.setNumeroEmpleado("EMP-3");
        assertEquals("EMP-3", t.getNumeroEmpleado());
        // Verifica que un número vacío restablece el valor por defecto "Sin numero"
        t.setNumeroEmpleado("");
        assertEquals("Sin numero", t.getNumeroEmpleado());
        System.out.println("setNumeroEmpleado funciona✅");
    }

    /**
     * Verifica que el getter retorna la especialidad asignada.
     */
    @Test
    void getEspecialidad() {
        // Asigna una especialidad y verifica que el getter la retorna correctamente
        Tecnico t = new Tecnico();
        t.setEspecialidad("Redes");
        assertEquals("Redes", t.getEspecialidad());
        System.out.println("getEspecialidad funciona✅");
    }

    /**
     * Verifica que se acepta una especialidad válida y que una vacía restablece el valor por defecto.
     */
    @Test
    void setEspecialidad() {
        Tecnico t = new Tecnico();
        // Verifica que una especialidad válida se guarda correctamente
        t.setEspecialidad("Mantenimiento PC");
        assertEquals("Mantenimiento PC", t.getEspecialidad());
        // Verifica que una especialidad vacía restablece el valor por defecto
        t.setEspecialidad("");
        assertEquals("Sin especialidad", t.getEspecialidad());
        System.out.println("setEspecialidad funciona✅");
    }

    /**
     * Verifica que el getter retorna el nivel de acceso asignado.
     */
    @Test
    void getNivelAcceso() {
        // Asigna un nivel de acceso y verifica que el getter lo retorna correctamente
        Tecnico t = new Tecnico();
        t.setNivelAcceso(2);
        assertEquals(2, t.getNivelAcceso());
        System.out.println("getNivelAcceso funciona✅");
    }

    /**
     * Verifica que se acepta un nivel positivo y que un nivel negativo restablece el valor por defecto (0).
     */
    @Test
    void setNivelAcceso() {
        Tecnico t = new Tecnico();
        // Verifica que un nivel de acceso positivo se guarda correctamente
        t.setNivelAcceso(3);
        assertEquals(3, t.getNivelAcceso());
        // Verifica que un nivel negativo restablece el valor por defecto (0)
        t.setNivelAcceso(-1);
        assertEquals(0, t.getNivelAcceso());
        System.out.println("setNivelAcceso funciona✅");
    }
}