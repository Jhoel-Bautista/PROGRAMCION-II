package Util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para {@link Validador}.
 * Cubre cada método con casos válidos, inválidos y borde.
 * Son los tests más importantes del proyecto porque el Validador
 * protege todos los puntos de entrada de datos del usuario.
 */
@DisplayName("Tests de Validador (Util)")
class ValidadorTest {

    // ══════════════════════════════════════════════════════════════════════════
    // validarEmail
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Verifica que los emails correctos sean aceptados.
     * Emails correctos deben ser aceptados sin importar dominio o extensión.
     *
     * @param email Email válido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Email válido: {0}")
    @ValueSource(strings = {"juan@gmail.com", "ana.test@uce.edu.ec", "admin+tag@empresa.org"})
    @DisplayName("validarEmail - emails válidos son aceptados")
    void testEmailValidos(String email) {
        assertTrue(Validador.validarEmail(email));
    }

    /**
     * Verifica que los emails con formato incorrecto sean rechazados por el sistema.
     *
     * @param email Email inválido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Email inválido: {0}")
    @ValueSource(strings = {"sinArroba", "@dominio.com", "user@", "user@.com", ""})
    @DisplayName("validarEmail - emails inválidos son rechazados")
    void testEmailInvalidos(String email) {
        assertFalse(Validador.validarEmail(email));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // validarNombrePropio — test más importante del Validador
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Crítico: el sistema requiere mayúsculas iniciales para distinguir
     * nombres reales de texto genérico o entradas maliciosas.
     *
     * @param nombre Nombre propio válido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Nombre válido: {0}")
    @ValueSource(strings = {"Juan", "Ana Lopez", "Carlos Alberto Ruiz"})
    @DisplayName("validarNombrePropio - nombres con mayúsculas iniciales son aceptados")
    void testNombresValidos(String nombre) {
        assertTrue(Validador.validarNombrePropio(nombre));
    }

    /**
     * Verifica que los nombres que no cumplan con el formato de mayúsculas
     * iniciales o que contengan números sean rechazados.
     *
     * @param nombre Nombre propio inválido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Nombre inválido: {0}")
    @ValueSource(strings = {"juan perez", "JUAN PEREZ", "juan123", "", "  "})
    @DisplayName("validarNombrePropio - nombres sin mayúsculas iniciales son rechazados")
    void testNombresInvalidos(String nombre) {
        assertFalse(Validador.validarNombrePropio(nombre));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // validarPassword
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Verifica que las contraseñas que cumplen con los requisitos de seguridad
     * sean aceptadas correctamente.
     *
     * @param pass Contraseña válida inyectada por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Password válida: {0}")
    @ValueSource(strings = {"Admin123", "Juan123", "Tecnico1", "Pass99word"})
    @DisplayName("validarPassword - passwords válidas son aceptadas")
    void testPasswordValidas(String pass) {
        assertTrue(Validador.validarPassword(pass));
    }

    /**
     * Verifica que el regex rechace contraseñas inseguras.
     * Requisitos evaluados: al menos 1 mayúscula, al menos 1 dígito, mínimo 6 caracteres.
     *
     * @param pass Contraseña inválida inyectada por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Password inválida: {0}")
    @ValueSource(strings = {"sinmayuscula1", "sinNumero", "corto", ""})
    @DisplayName("validarPassword - passwords sin mayúscula, sin número o muy cortas son rechazadas")
    void testPasswordInvalidas(String pass) {
        assertFalse(Validador.validarPassword(pass));
    }

    /**
     * Caso de borde: Verifica que una contraseña de exactamente 6 caracteres
     * con al menos una mayúscula y un número sea validada correctamente.
     */
    @Test
    @DisplayName("validarPassword - exactamente 6 caracteres con mayúscula y número es válida")
    void testPasswordBorde6Caracteres() {
        assertTrue(Validador.validarPassword("Abcd1e")); // exactamente 6
    }

    // ══════════════════════════════════════════════════════════════════════════
    // validarTelefono
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Verifica que los números de teléfono con formato ecuatoriano sean aceptados.
     *
     * @param tel Teléfono válido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Teléfono válido: {0}")
    @ValueSource(strings = {"0999999999", "0987654321", "+593987654321"})
    @DisplayName("validarTelefono - teléfonos ecuatorianos válidos son aceptados")
    void testTelefonosValidos(String tel) {
        assertTrue(Validador.validarTelefono(tel));
    }

    /**
     * Verifica que los formatos de teléfono incorrectos o incompletos sean rechazados.
     *
     * @param tel Teléfono inválido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Teléfono inválido: {0}")
    @ValueSource(strings = {"123456789", "099999999", "telefono", ""})
    @DisplayName("validarTelefono - teléfonos inválidos son rechazados")
    void testTelefonosInvalidos(String tel) {
        assertFalse(Validador.validarTelefono(tel));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // validarNumeroSerie
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Verifica que los números de serie de los equipos con formato correcto sean aceptados.
     *
     * @param serie Número de serie válido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Serie válida: {0}")
    @ValueSource(strings = {"SN-98765", "ABC12", "SERIE-001-XYZ"})
    @DisplayName("validarNumeroSerie - series válidas son aceptadas")
    void testSeriesValidas(String serie) {
        assertTrue(Validador.validarNumeroSerie(serie));
    }

    /**
     * Verifica que los números de serie mal formateados o vacíos sean rechazados.
     *
     * @param serie Número de serie inválido inyectado por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Serie inválida: {0}")
    @ValueSource(strings = {"AB", "serie con espacios", "", "SERIE_CON_GUION_BAJO"})
    @DisplayName("validarNumeroSerie - series inválidas son rechazadas")
    void testSeriesInvalidas(String serie) {
        assertFalse(Validador.validarNumeroSerie(serie));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // validarDescripcion
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Verifica que una descripción de problema que cumple con el mínimo
     * de 10 caracteres sea considerada válida por el sistema.
     */
    @Test
    @DisplayName("validarDescripcion - texto de 10 caracteres mínimos es válido")
    void testDescripcionMinima() {
        assertTrue(Validador.validarDescripcion("El equipo no enciende al conectarlo"));
    }

    /**
     * Verifica que descripciones demasiado cortas (menores a 10 caracteres)
     * sean rechazadas para obligar al usuario a dar más detalles.
     */
    @Test
    @DisplayName("validarDescripcion - texto de menos de 10 caracteres es inválido")
    void testDescripcionCorta() {
        assertFalse(Validador.validarDescripcion("Falla"));
    }

    /**
     * Caso de borde: Verifica que el validador maneje un valor nulo de forma segura
     * devolviendo false en lugar de lanzar un NullPointerException.
     */
    @Test
    @DisplayName("validarDescripcion - null devuelve false sin lanzar excepción")
    void testDescripcionNull() {
        assertFalse(Validador.validarDescripcion(null));
    }

    // ══════════════════════════════════════════════════════════════════════════
    // validarPrioridad
    // ══════════════════════════════════════════════════════════════════════════

    /**
     * Verifica que el sistema acepte únicamente las prioridades configuradas (1, 2 y 3).
     *
     * @param p Prioridad válida inyectada por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Prioridad válida: {0}")
    @ValueSource(strings = {"1", "2", "3"})
    @DisplayName("validarPrioridad - valores 1, 2 y 3 son aceptados")
    void testPrioridadValida(String p) {
        assertTrue(Validador.validarPrioridad(p));
    }

    /**
     * Verifica que cualquier valor numérico fuera de rango o texto no numérico
     * sea rechazado como prioridad inválida.
     *
     * @param p Prioridad inválida inyectada por la prueba parametrizada.
     */
    @ParameterizedTest(name = "Prioridad inválida: {0}")
    @ValueSource(strings = {"0", "4", "abc", ""})
    @DisplayName("validarPrioridad - valores fuera de rango son rechazados")
    void testPrioridadInvalida(String p) {
        assertFalse(Validador.validarPrioridad(p));
    }
}