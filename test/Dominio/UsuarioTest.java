package Dominio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase UsuarioTest
 * Contiene las pruebas unitarias para verificar el correcto
 * funcionamiento de la clase Usuario.
 *
 * Pruebas incluidas:
 * - testConstructores : verifica que ambos constructores inicialicen
 * los atributos correctamente
 * - getIdUsuario      : verifica que el getter retorna el ID asignado
 * - setIdUsuario      : verifica que el setter modifica el ID correctamente
 * - getNombre         : verifica que el getter retorna el nombre asignado
 * - setNombre         : verifica que se acepta un nombre válido (sin números)
 * y se rechaza uno que contiene solo dígitos
 * - getEmail          : verifica que el getter retorna el correo asignado
 * - setEmail          : verifica que se acepta un correo con '@'
 * y se rechaza uno sin '@'
 * - getPassword       : verifica que el getter retorna la contraseña asignada
 * - setPassword       : verifica que se acepta una contraseña de más de 3 caracteres
 * y se rechaza una demasiado corta
 *
 * Se utiliza JUnit 5 para la ejecución de las pruebas.
 */
class UsuarioTest {

    /**
     * Verifica que ambos constructores inicialicen los atributos correctamente.
     */
    @Test
    void testConstructores() {
        // Verifica que el constructor vacío asigna "Sin nombre" por defecto
        Usuario u1 = new Usuario();
        assertEquals("Sin nombre", u1.getNombre());
        // Verifica que el constructor con parámetros asigna el nombre correctamente
        Usuario u2 = new Usuario(1, "Ana Lopez", "ana@gmail.com", "Ana123");
        assertEquals("Ana Lopez", u2.getNombre());
        System.out.println("testConstructores funciona✅");
    }

    /**
     * Verifica que el getter retorna el ID asignado.
     */
    @Test
    void getIdUsuario() {
        // Crea un usuario con ID 5 y verifica que el getter lo retorna correctamente
        Usuario u = new Usuario(5, "Juan", "j@mail.com", "Pass123");
        assertEquals(5, u.getIdUsuario());
        System.out.println("getIdUsuario funciona✅");
    }

    /**
     * Verifica que el setter modifica el ID correctamente.
     */
    @Test
    void setIdUsuario() {
        // Modifica el ID y verifica que el cambio se guardó correctamente
        Usuario u = new Usuario();
        u.setIdUsuario(10);
        assertEquals(10, u.getIdUsuario());
        System.out.println("setIdUsuario funciona✅");
    }

    /**
     * Verifica que el getter retorna el nombre asignado.
     */
    @Test
    void getNombre() {
        // Crea un usuario con nombre y verifica que el getter lo retorna correctamente
        Usuario u = new Usuario(1, "Carlos", "c@mail.com", "Pass123");
        assertEquals("Carlos", u.getNombre());
        System.out.println("getNombre funciona✅");
    }

    /**
     * Verifica que se acepta un nombre válido (sin números) y se rechaza uno que contiene solo dígitos.
     */
    @Test
    void setNombre() {
        Usuario u = new Usuario();
        // Verifica que un nombre válido (sin números) se guarda correctamente
        u.setNombre("Odalys");
        assertEquals("Odalys", u.getNombre());
        // Verifica que un nombre con solo números es rechazado y queda "Sin nombre"
        u.setNombre("12345");
        assertEquals("Sin nombre", u.getNombre());
        System.out.println("setNombre funciona✅");
    }

    /**
     * Verifica que el getter retorna el correo asignado.
     */
    @Test
    void getEmail() {
        // Crea un usuario con correo y verifica que el getter lo retorna correctamente
        Usuario u = new Usuario(1, "Ana", "ana@gmail.com", "Pass123");
        assertEquals("ana@gmail.com", u.getEmail());
        System.out.println("getEmail funciona✅");
    }

    /**
     * Verifica que se acepta un correo con '@' y se rechaza uno sin '@'.
     */
    @Test
    void setEmail() {
        Usuario u = new Usuario();
        // Verifica que un correo con '@' se guarda correctamente
        u.setEmail("test@uce.com");
        assertEquals("test@uce.com", u.getEmail());
        // Verifica que un correo sin '@' es rechazado y queda "Sin correo"
        u.setEmail("correo-malo");
        assertEquals("Sin correo", u.getEmail());
        System.out.println("setEmail funciona✅");
    }

    /**
     * Verifica que el getter retorna la contraseña asignada.
     */
    @Test
    void getPassword() {
        // Crea un usuario con contraseña y verifica que el getter la retorna correctamente
        Usuario u = new Usuario(1, "Ana", "a@a.com", "Clave123");
        assertEquals("Clave123", u.getPassword());
        System.out.println("getPassword funciona✅");
    }

    /**
     * Verifica que se acepta una contraseña de más de 3 caracteres y se rechaza una demasiado corta.
     */
    @Test
    void setPassword() {
        Usuario u = new Usuario();
        // Verifica que una contraseña de más de 3 caracteres se guarda correctamente
        u.setPassword("Clave123");
        assertEquals("Clave123", u.getPassword());
        // Verifica que una contraseña de 3 caracteres o menos es rechazada
        u.setPassword("123");
        assertEquals("Sin contrasena", u.getPassword());
        System.out.println("setPassword funciona✅");
    }
}