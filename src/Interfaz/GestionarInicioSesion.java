package Interfaz;

import Util.Validador;
import java.util.Scanner;

public class GestionarInicioSesion {

    public static void mostrar(Scanner sc) {
        System.out.println("\n=== INICIAR SESIÓN ===");

        String email;
        do {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (!Validador.validarEmail(email))
                System.out.println("Error: Email inválido.");
        } while (!Validador.validarEmail(email));

        String password;
        do {
            System.out.print("Password: ");
            password = sc.nextLine();
            if (!Validador.validarPassword(password))
                System.out.println("Error: Mínimo 6 caracteres, una mayúscula y un número.");
        } while (!Validador.validarPassword(password));

        System.out.println("Sesión iniciada correctamente!");
    }
}