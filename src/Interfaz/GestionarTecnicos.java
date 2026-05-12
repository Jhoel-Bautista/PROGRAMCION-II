package Interfaz;

import Dominio.Tecnico;
import Util.Validador;
import java.util.Scanner;

public class GestionarTecnicos {

    static Tecnico[] tecnicos = new Tecnico[10];
    static int total = 0;

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIONAR TÉCNICOS ===");
            System.out.println("1. Registrar Técnico");
            System.out.println("2. Listar Técnicos");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            String entrada = sc.nextLine();
            if (!entrada.matches("[0-2]")) {
                System.out.println("Error: Opción inválida.");
                opcion = -1;
                continue;
            }

            opcion = Integer.parseInt(entrada);
            switch (opcion) {
                case 1: registrar(sc); break;
                case 2: listar(); break;
                case 0: System.out.println("Volviendo..."); break;
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        System.out.println("\n--- Registrar Técnico ---");

        String nombre;
        do {
            System.out.print("Nombre completo: ");
            nombre = sc.nextLine();
            if (!Validador.validarNombrePropio(nombre))
                System.out.println("Error: Nombre inválido.");
        } while (!Validador.validarNombrePropio(nombre));

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

        String especialidad;
        do {
            System.out.print("Especialidad: ");
            especialidad = sc.nextLine();
            if (!Validador.validarNombrePropio(especialidad))
                System.out.println("Error: Especialidad inválida.");
        } while (!Validador.validarNombrePropio(especialidad));

        String nivelStr;
        do {
            System.out.print("Nivel de acceso (1-3): ");
            nivelStr = sc.nextLine();
            if (!Validador.validarPrioridad(nivelStr))
                System.out.println("Error: Nivel debe ser 1, 2 o 3.");
        } while (!Validador.validarPrioridad(nivelStr));

        Tecnico t = new Tecnico(total + 1, nombre, email, password, "EMP-" + (total + 1), especialidad, Integer.parseInt(nivelStr));
        tecnicos[total] = t;
        total++;
        System.out.println("Técnico registrado correctamente!");
    }

    private static void listar() {
        System.out.println("\n--- Lista de Técnicos ---");
        if (total == 0) {
            System.out.println("No hay técnicos registrados.");
            return;
        }
        for (int i = 0; i < total; i++)
            System.out.println(tecnicos[i]);
    }
}