package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testConstructorYGetters() {
        // Probamos el constructor con los 3 parámetros actuales
        Ticket t = new Ticket(1, "PC no enciende", Estado.PENDIENTE);

        assertEquals(1, t.getIdTicket());
        assertEquals("PC no enciende", t.getDescripcion());
        assertEquals(Estado.PENDIENTE, t.getEstadoActual());
        System.out.println("Constructor y Getters funcionan✅");
    }

    @Test
    void setIdTicket() {
        Ticket t = new Ticket(1, "Desc", Estado.PENDIENTE);
        t.setIdTicket(2);
        assertEquals(2, t.getIdTicket());
        System.out.println("setIdTicket funciona✅");
    }

    @Test
    void setDescripcion() {
        Ticket t = new Ticket(1, "Desc", Estado.PENDIENTE);
        t.setDescripcion("Nueva descripcion");
        assertEquals("Nueva descripcion", t.getDescripcion());
        System.out.println("setDescripcion funciona✅");
    }

    @Test
    void setEstadoActual() {
        Ticket t = new Ticket(1, "Desc", Estado.PENDIENTE);
        // Probamos cambiar a otro estado del Enum
        t.setEstadoActual(Estado.EN_PROCESO);
        assertEquals(Estado.EN_PROCESO, t.getEstadoActual());
        System.out.println("setEstadoActual funciona✅");
    }

    @Test
    void testFinalizarTicket() {
        Ticket t = new Ticket(1, "Desc", Estado.PENDIENTE);
        t.finalizarTicket();
        assertEquals(Estado.CERRADO, t.getEstadoActual());
        System.out.println("finalizarTicket funciona✅");
    }
}