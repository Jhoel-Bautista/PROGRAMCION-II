package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase utilitaria {@code Validador} con métodos estáticos de validación
 * mediante expresiones regulares.
 *
 * <p>Versión <b>ESTRICTA</b>: exige formatos exactos para cada campo.
 * Todos los métodos son {@code static} y pueden llamarse sin instanciar la clase.
 *
 * <p>Usada por las clases del dominio para validar sus atributos antes de asignarlos:
 * <ul>
 * <li>{@link Dominio.Cliente} – valida teléfono</li>
 * <li>{@link Dominio.Equipo}  – valida número de serie</li>
 * <li>{@link Dominio.Reparacion}, {@link Dominio.Diagnostico}, {@link Dominio.Solucion} – validan descripción</li>
 * </ul>
 *
 * @author ProyectoFinal
 * @version 1.0
 */
public class Validador {

    /**
     * Expresión regular para nombres propios.
     * Obliga a usar mayúscula inicial en cada palabra (ej. "Juan Perez").
     * Rechaza nombres en minúscula como "fffff".
     */
    private static final String REGEX_NOMBRE       = "^[A-ZÁÉÍÓÚ][a-zñáéíóú]+(\\s[A-ZÁÉÍÓÚ][a-zñáéíóú]+)*$";

    /**
     * Expresión regular para correo electrónico.
     * Formato estricto: {@code usuario@dominio.com}
     */
    private static final String REGEX_EMAIL        = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    /**
     * Expresión regular para contraseña.
     * Mínimo 6 caracteres, al menos una mayúscula y un número.
     */
    private static final String REGEX_PASSWORD     = "^(?=.*[A-Z])(?=.*\\d).{6,}$";

    /**
     * Expresión regular para teléfono ecuatoriano.
     * Formatos válidos: {@code 09XXXXXXXX}, {@code 0XXXXXXXXX}, {@code +593XXXXXXXXX}.
     */
    private static final String REGEX_TELEFONO     = "^(09\\d{8}|0\\d{9}|\\+593\\d{9})$";

    /**
     * Expresión regular para número de serie de equipo.
     * Entre 5 y 20 caracteres alfanuméricos o guiones.
     */
    private static final String REGEX_NUMERO_SERIE = "^[A-Za-z0-9\\-]{5,20}$";

    /**
     * Expresión regular para descripciones y detalles.
     * Entre 10 y 200 caracteres. Bloquea textos demasiado cortos.
     */
    private static final String REGEX_DESCRIPCION  = "^[\\w\\sáéíóúÁÉÍÓÚñÑ.,;:()/\\-]{10,200}$";

    /**
     * Expresión regular para prioridad.
     * Exactamente 1, 2 o 3.
     */
    private static final String REGEX_PRIORIDAD    = "^[1-3]$";

    /**
     * Valida un correo electrónico con formato {@code usuario@dominio.tld}.
     *
     * @param email correo electrónico a validar
     * @return {@code true} si el email es válido, {@code false} en caso contrario
     */
    public static boolean validarEmail(String email) {
        return match(REGEX_EMAIL, email);
    }

    /**
     * Valida un nombre propio (mayúscula inicial en cada palabra).
     * <p>Ejemplos válidos: "Juan Perez", "Maria Jose".
     * Ejemplos inválidos: "juan perez", "JUAN".
     *
     * @param nombre nombre a validar
     * @return {@code true} si el nombre cumple el formato, {@code false} en caso contrario
     */
    public static boolean validarNombrePropio(String nombre) {
        return match(REGEX_NOMBRE, nombre);
    }

    /**
     * Valida una contraseña segura.
     * <p>Requisitos: mínimo 6 caracteres, al menos una mayúscula y un número.
     *
     * @param password contraseña a validar
     * @return {@code true} si la contraseña cumple los requisitos, {@code false} en caso contrario
     */
    public static boolean validarPassword(String password) {
        return match(REGEX_PASSWORD, password);
    }

    /**
     * Valida un número de teléfono ecuatoriano.
     * <p>Formatos aceptados:
     * <ul>
     * <li>{@code 09XXXXXXXX} – celular (10 dígitos)</li>
     * <li>{@code 0XXXXXXXXX} – fijo (10 dígitos)</li>
     * <li>{@code +593XXXXXXXXX} – internacional (formato Ecuador)</li>
     * </ul>
     *
     * @param telefono número de teléfono a validar
     * @return {@code true} si el teléfono tiene un formato válido, {@code false} en caso contrario
     */
    public static boolean validarTelefono(String telefono) {
        return match(REGEX_TELEFONO, telefono);
    }

    /**
     * Valida un número de serie de equipo.
     * <p>Debe tener entre 5 y 20 caracteres alfanuméricos o guiones.
     * Ejemplo válido: {@code ABC-12345}.
     *
     * @param serie número de serie a validar
     * @return {@code true} si el número de serie es válido, {@code false} en caso contrario
     */
    public static boolean validarNumeroSerie(String serie) {
        return match(REGEX_NUMERO_SERIE, serie);
    }

    /**
     * Valida una descripción o detalle de texto.
     * <p>Debe tener entre 10 y 200 caracteres. Bloquea textos demasiado cortos
     * que no aporten información útil.
     *
     * @param descripcion texto a validar
     * @return {@code true} si la descripción tiene longitud válida, {@code false} en caso contrario
     */
    public static boolean validarDescripcion(String descripcion) {
        return match(REGEX_DESCRIPCION, descripcion);
    }

    /**
     * Valida un valor de prioridad.
     * <p>Solo acepta los valores {@code "1"}, {@code "2"} o {@code "3"}.
     *
     * @param prioridad valor de prioridad como texto
     * @return {@code true} si la prioridad es 1, 2 o 3, {@code false} en caso contrario
     */
    public static boolean validarPrioridad(String prioridad) {
        return match(REGEX_PRIORIDAD, prioridad);
    }

    /**
     * Método privado auxiliar que aplica una expresión regular a un valor.
     * <p>Aplica {@code trim()} al valor para evitar que espacios accidentales
     * rompan la validación estricta.
     *
     * @param regex expresión regular a aplicar
     * @param valor texto a validar
     * @return {@code true} si el valor coincide con la expresión regular, {@code false} en caso contrario
     */
    private static boolean match(String regex, String valor) {
        if (valor == null || valor.trim().isEmpty()) return false;
        Matcher m = Pattern.compile(regex).matcher(valor.trim());
        return m.matches();
    }
}