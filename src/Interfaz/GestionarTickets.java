package Interfaz;

import Dominio.*;
import Util.Validador;
import java.util.Date;
import java.util.Scanner;

public class GestionarTickets {

    static Ticket[] tickets = new Ticket[10];
    static int total = 0;

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIONAR TICKETS ===");
            System.out.println("1. Crear Ticket");
            System.out.println("2. Listar Tickets");
            System.out.println("3. Actualizar Estado");
            System.out.println("0. Volver");
            System.out.print("Opción: ");

            String entrada = sc.nextLine();
            if (!entrada.matches("[0-3]")) {
                System.out.println("Error: Opción inválida.");
                opcion = -1;
                continue;
            }

            opcion = Integer.parseInt(entrada);
            switch (opcion) {
                case 1: crear(sc); break;
                case 2: listar(); break;
                case 3: actualizarEstado(sc); break;
                case 0: System.out.println("Volviendo..."); break;
            }
        } while (opcion != 0);
    }

    private static void crear(Scanner sc) {
        System.out.println("\n--- Crear Ticket ---");

        if (GestionarClientes.total == 0) {
            System.out.println("No hay clientes. Registre uno primero.");
            return;
        }
        System.out.println("Clientes:");
        for (int i = 0; i < GestionarClientes.total; i++)
            System.out.println((i + 1) + ". " + GestionarClientes.clientes[i].getNombre());
        System.out.print("Seleccione cliente: ");
        int idCliente = Integer.parseInt(sc.nextLine()) - 1;
        Cliente cliente = GestionarClientes.clientes[idCliente];

        if (GestionarTecnicos.total == 0) {
            System.out.println("No hay técnicos. Registre uno primero.");
            return;
        }
        System.out.println("Técnicos:");
        for (int i = 0; i < GestionarTecnicos.total; i++)
            System.out.println((i + 1) + ". " + GestionarTecnicos.tecnicos[i].getNombre());
        System.out.print("Seleccione técnico: ");
        int idTecnico = Integer.parseInt(sc.nextLine()) - 1;
        Tecnico tecnico = GestionarTecnicos.tecnicos[idTecnico];

        if (GestionarEquipos.total == 0) {
            System.out.println("No hay equipos. Registre uno primero.");
            return;
        }
        System.out.println("Equipos:");
        for (int i = 0; i < GestionarEquipos.total; i++)
            System.out.println((i + 1) + ". " + GestionarEquipos.equipos[i].getModelo());
        System.out.print("Seleccione equipo: ");
        int idEquipo = Integer.parseInt(sc.nextLine()) - 1;
        Equipo equipo = GestionarEquipos.equipos[idEquipo];

        String descripcion;
        do {
            System.out.print("Descripción: ");
            descripcion = sc.nextLine();
            if (!Validador.validarDescripcion(descripcion))
                System.out.println("Error: Mínimo 10 caracteres.");
        } while (!Validador.validarDescripcion(descripcion));

        String prioridad;
        do {
            System.out.print("Prioridad (1=Alta, 2=Media, 3=Baja): ");
            prioridad = sc.nextLine();
            if (!Validador.validarPrioridad(prioridad))
                System.out.println("Error: Solo 1, 2 o 3.");
        } while (!Validador.validarPrioridad(prioridad));

        Estado estado = new Estado(1, "Abierto");
        Ticket t = new Ticket(total + 1, new Date(), descripcion, Integer.parseInt(prioridad), cliente, tecnico, equipo, estado);
        tickets[total] = t;
        total++;
        System.out.println("Ticket creado correctamente!");
    }

    private static void listar() {
        System.out.println("\n--- Lista de Tickets ---");
        if (total == 0) {
            System.out.println("No hay tickets registrados.");
            return;
        }
        for (int i = 0; i < total; i++)
            System.out.println(tickets[i]);
    }

    private static void actualizarEstado(Scanner sc) {
        System.out.println("\n--- Actualizar Estado ---");
        if (total == 0) {
            System.out.println("No hay tickets registrados.");
            return;
        }
        listar();
        System.out.print("Seleccione ticket: ");
        int id = Integer.parseInt(sc.nextLine()) - 1;

        System.out.println("1. Abierto  2. En proceso  3. Cerrado");
        System.out.print("Nuevo estado: ");
        String op = sc.nextLine();

        String nombreEstado;
        switch (op) {
            case "1": nombreEstado = "Abierto"; break;
            case "2": nombreEstado = "En proceso"; break;
            case "3": nombreEstado = "Cerrado"; break;
            default: nombreEstado = "Abierto";
        }
        tickets[id].setEstadoActual(new Estado(Integer.parseInt(op), nombreEstado));
        System.out.println("Estado actualizado a: " + nombreEstado);
    }
}