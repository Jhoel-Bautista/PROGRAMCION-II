package Interfaz;

import java.util.Scanner;

public class MenuPrincipal {

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║           SISTEMA DE GESTIÓN             ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("1. Gestionar Repuestos e inventario");
            System.out.println("2. Gestionar Personal Técnicos");
            System.out.println("3. Gestionar Clientes");
            System.out.println("4. Gestionar Equipos");
            System.out.println("5. Gestionar Reparaciones");
            System.out.println("6. Gestionar Tickets");
            System.out.println("0. Salir del programa");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!entrada.matches("[0-6]")) {
                System.out.println("Error: Opción inválida.");
                opcion = -1;
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: GestionarRepuestos.mostrar(sc); break;
                case 2: GestionarTecnicos.mostrar(sc); break;
                case 3: GestionarClientes.mostrar(sc); break;
                case 4: GestionarEquipos.mostrar(sc); break;
                case 5: GestionarReparaciones.mostrar(sc); break;
                case 6: GestionarTickets.mostrar(sc); break;
                case 0:
                    System.out.println("Cerrando el sistema...");
                    break;
            }
        } while (opcion != 0);
    }
}