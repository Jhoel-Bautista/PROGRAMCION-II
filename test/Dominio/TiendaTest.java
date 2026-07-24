package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TiendaTest {

    @Test
    void testSingleton() {
        Tienda t1 = Tienda.getInstancia();
        Tienda t2 = Tienda.getInstancia();
        assertNotNull(t1);
        assertSame(t1, t2); // Verifica que es la misma instancia
        System.out.println("testSingleton funciona✅");
    }

    @Test
    void testGetSetBasicos() {
        Tienda t = Tienda.getInstancia();
        t.setIdTienda(5);
        t.setNombreTienda("TechRepair");

        assertEquals(5, t.getIdTienda());
        assertEquals("TechRepair", t.getNombreTienda());
        System.out.println("testGetSetBasicos funciona✅");
    }

    @Test
    void testValidaciones() {
        Tienda t = Tienda.getInstancia();

        // Verifica valores por defecto en validaciones
        t.setNombreTienda("");
        assertEquals("Sin nombre", t.getNombreTienda());

        t.setDireccion(null);
        assertEquals("Sin direccion", t.getDireccion());
        System.out.println("testValidaciones funciona✅");
    }
}