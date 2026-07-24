package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase ClienteTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Cliente.
 *
 * Pruebas incluidas:
 * - testConstructores       : verifica que el constructor con parámetros inicialice
 * correctamente tanto los atributos heredados de Usuario
 * como los propios de Cliente
 * - getNumeroCliente        : verifica que el getter retorna el número de cliente asignado
 * - setNumeroCliente        : verifica que se acepta un número válido
 * y que uno vacío restablece el valor por defecto
 * - getTelefono             : verifica que el getter retorna el teléfono asignado
 * - setTelefono             : verifica que se acepta un teléfono válido (10 dígitos)
 * y que uno inválido restablece el valor por defecto
 * - getDireccion            : verifica que el getter retorna la dirección asignada
 * - setDireccion            : verifica que se acepta una dirección válida
 * y que una vacía restablece el valor por defecto
 * - getTicketsAsociados     : verifica que el getter retorna el arreglo de tickets asignado
 * - setTicketsAsociados     : verifica que el arreglo de tickets se asigna correctamente
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class ClienteTest {

    /**
     * Verifica que el constructor con parámetros inicialice correctamente
     * tanto los atributos heredados de Usuario como los propios de Cliente.
     */
    @Test
    void testConstructores() {
        Ticket[] t = new Ticket[0];
        // Verifica que el constructor con los 8 parámetros asigna correctamente
        // tanto los atributos heredados (nombre) como los propios (numeroCliente)
        Cliente c2 = new Cliente(1, "Juan Perez", "juan@gmail.com", "Juan123", "CLI-1", "0999999999", "Quito", t);
        assertEquals("CLI-1", c2.getNumeroCliente());
        assertEquals("Juan Perez", c2.getNombre());
        System.out.println("Constructores funcionan✅");
    }

    /**
     * Verifica que el getter retorna el número de cliente asignado.
     */
    @Test
    void getNumeroCliente() {
        // Asigna un número de cliente y verifica que el getter lo retorna correctamente
        Cliente c = new Cliente();
        c.setNumeroCliente("CLI-01");
        assertEquals("CLI-01", c.getNumeroCliente());
        System.out.println("getNumeroCliente funciona✅");
    }

    /**
     * Verifica que se acepta un número válido y que uno vacío restablece el valor por defecto.
     */
    @Test
    void setNumeroCliente() {
        Cliente c = new Cliente();
        // Verifica que un número válido se guarda correctamente
        c.setNumeroCliente("CLI-02");
        assertEquals("CLI-02", c.getNumeroCliente());
        // Verifica que un número vacío restablece el valor por defecto "Sin numero"
        c.setNumeroCliente("");
        assertEquals("Sin numero", c.getNumeroCliente());
        System.out.println("setNumeroCliente funciona✅");
    }

    /**
     * Verifica que el getter retorna el teléfono asignado.
     */
    @Test
    void getTelefono() {
        // Asigna un teléfono válido y verifica que el getter lo retorna correctamente
        Cliente c = new Cliente();
        c.setTelefono("0999999999");
        assertEquals("0999999999", c.getTelefono());
        System.out.println("getTelefono funciona✅");
    }

    /**
     * Verifica que se acepta un teléfono válido (10 dígitos) y que uno inválido restablece el valor por defecto.
     */
    @Test
    void setTelefono() {
        Cliente c = new Cliente();
        // Verifica que un teléfono válido de 10 dígitos se guarda correctamente
        c.setTelefono("0988888888");
        assertEquals("0988888888", c.getTelefono());
        // Verifica que un teléfono inválido (muy corto) restablece el valor por defecto
        c.setTelefono("123");
        assertEquals("Sin telefono", c.getTelefono());
        System.out.println("setTelefono funciona✅");
    }

    /**
     * Verifica que el getter retorna la dirección asignada.
     */
    @Test
    void getDireccion() {
        // Asigna una dirección y verifica que el getter la retorna correctamente
        Cliente c = new Cliente();
        c.setDireccion("Quito");
        assertEquals("Quito", c.getDireccion());
        System.out.println("getDireccion funciona✅");
    }

    /**
     * Verifica que se acepta una dirección válida y que una vacía restablece el valor por defecto.
     */
    @Test
    void setDireccion() {
        Cliente c = new Cliente();
        // Verifica que una dirección válida se guarda correctamente
        c.setDireccion("Guayaquil");
        assertEquals("Guayaquil", c.getDireccion());
        // Verifica que una dirección vacía restablece el valor por defecto "Sin direccion"
        c.setDireccion("");
        assertEquals("Sin direccion", c.getDireccion());
        System.out.println("setDireccion funciona✅");
    }

    /**
     * Verifica que el getter retorna el arreglo de tickets asignado.
     */
    @Test
    void getTicketsAsociados() {
        // Asigna un arreglo de tickets y verifica que el getter lo retorna correctamente
        Cliente c = new Cliente();
        Ticket[] t = new Ticket[2];
        c.setTicketsAsociados(t);
        assertEquals(t, c.getTicketsAsociados());
        System.out.println("getTicketsAsociados funciona✅");
    }

    /**
     * Verifica que el arreglo de tickets se asigna correctamente.
     */
    @Test
    void setTicketsAsociados() {
        // Verifica que el arreglo de tickets se asigna y se puede recuperar correctamente
        Cliente c = new Cliente();
        Ticket[] t = new Ticket[2];
        c.setTicketsAsociados(t);
        assertEquals(t, c.getTicketsAsociados());
        System.out.println("setTicketsAsociados funciona✅");
    }
}