package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EstadoTest {
    @Test
    void enumTieneEstadosPrincipales() {
        assertEquals(1, Estado.PENDIENTE.getIdEstado());
        assertEquals("Pendiente", Estado.PENDIENTE.getNombreEstado());
        assertEquals(2, Estado.EN_PROCESO.getIdEstado());
        assertEquals("En proceso", Estado.EN_PROCESO.getNombreEstado());
    }

    @Test
    void toStringDevuelveNombreLegible() {
        assertEquals("Cerrado", Estado.CERRADO.toString());
    }
}
