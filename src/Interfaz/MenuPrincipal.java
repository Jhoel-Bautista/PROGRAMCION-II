package Interfaz;

import java.util.Scanner;

public class MenuPrincipal {

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Técnicos");
            System.out.println("3. Gestionar Equipos");
            System.out.println("4. Gestionar Tickets");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            String entrada = sc.nextLine();
            if (!entrada.matches("[0-4]")) {
                System.out.println("Error: Opción inválida.");
                opcion = -1;
                continue;
            }

            opcion = Integer.parseInt(entrada);
            switch (opcion) {
                case 1: GestionarClientes.mostrar(sc); break;
                case 2: GestionarTecnicos.mostrar(sc); break;
                case 3: GestionarEquipos.mostrar(sc); break;
                case 4: GestionarTickets.mostrar(sc); break;
                case 0: System.out.println("Hasta luego!"); break;
            }
        } while (opcion != 0);
    }
}