package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

    private static final String REGEX_EMAIL        = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String REGEX_NOMBRE       = "^[A-ZÁÉÍÓÚ][a-zñáéíóú]+(\\s[A-ZÁÉÍÓÚ][a-zñáéíóú]+)*$";
    private static final String REGEX_PASSWORD     = "^(?=.*[A-Z])(?=.*\\d).{6,}$";
    private static final String REGEX_TELEFONO     = "^(09\\d{8}|0\\d{9}|\\+593\\d{9})$";
    private static final String REGEX_NUMERO_SERIE = "^[A-Za-z0-9\\-]{5,20}$";
    private static final String REGEX_DESCRIPCION  = "^[\\w\\sáéíóúÁÉÍÓÚñÑ.,;:()/\\-]{10,200}$";
    private static final String REGEX_PRIORIDAD    = "^[1-3]$";
    private static final String REGEX_SOLO_NUMEROS = "^\\d+$";

    public static boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarNombrePropio(String nombre) {
        Pattern pattern = Pattern.compile(REGEX_NOMBRE);
        Matcher matcher = pattern.matcher(nombre);
        return matcher.matches();
    }

    public static boolean validarPassword(String password) {
        Pattern pattern = Pattern.compile(REGEX_PASSWORD);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validarTelefono(String telefono) {
        Pattern pattern = Pattern.compile(REGEX_TELEFONO);
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }

    public static boolean validarNumeroSerie(String serie) {
        Pattern pattern = Pattern.compile(REGEX_NUMERO_SERIE);
        Matcher matcher = pattern.matcher(serie);
        return matcher.matches();
    }

    public static boolean validarDescripcion(String descripcion) {
        Pattern pattern = Pattern.compile(REGEX_DESCRIPCION);
        Matcher matcher = pattern.matcher(descripcion);
        return matcher.matches();
    }

    public static boolean validarPrioridad(String prioridad) {
        Pattern pattern = Pattern.compile(REGEX_PRIORIDAD);
        Matcher matcher = pattern.matcher(prioridad);
        return matcher.matches();
    }

    public static boolean validarSoloNumeros(String texto) {
        Pattern pattern = Pattern.compile(REGEX_SOLO_NUMEROS);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
}