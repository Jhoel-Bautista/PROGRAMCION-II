package Interfaz;

import Dominio.Cliente;
import Util.Validador;
import java.util.Scanner;

public class GestionarClientes {

    static Cliente[] clientes = new Cliente[10];
    static int total = 0;

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIONAR CLIENTES ===");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Listar Clientes");
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
        System.out.println("\n--- Registrar Cliente ---");

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

        String telefono;
        do {
            System.out.print("Teléfono: ");
            telefono = sc.nextLine();
            if (!Validador.validarTelefono(telefono))
                System.out.println("Error: Teléfono inválido (ej: 0987654321).");
        } while (!Validador.validarTelefono(telefono));

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        String password;
        do {
            System.out.print("Password: ");
            password = sc.nextLine();
            if (!Validador.validarPassword(password))
                System.out.println("Error: Mínimo 6 caracteres, una mayúscula y un número.");
        } while (!Validador.validarPassword(password));

        Cliente c = new Cliente(total + 1, nombre, email, password, "CLI-" + (total + 1), telefono, direccion);
        clientes[total] = c;
        total++;
        System.out.println("Cliente registrado correctamente!");
    }

    private static void listar() {
        System.out.println("\n--- Lista de Clientes ---");
        if (total == 0) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (int i = 0; i < total; i++)
            System.out.println(clientes[i]);
    }
}