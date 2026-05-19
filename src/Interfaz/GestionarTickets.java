package Interfaz;

import Dominio.*;
import Util.Validador;

import java.util.Date;
import java.util.Scanner;

public class GestionarTickets {
    public static Ticket[] tickets = new Ticket[100];
    public static int total = 0;

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE TICKETS ---");
            System.out.println("1. Listar todos los tickets");
            System.out.println("2. Registrar ticket (Asignar manualmente)");
            System.out.println("3. Asignar ticket existente");
            System.out.println("4. Actualizar estado del ticket");
            System.out.println("5. Cerrar ticket");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            String entrada = sc.nextLine();
            if (!entrada.matches("[0-5]")) { opcion = -1; continue; }
            opcion = Integer.parseInt(entrada);

            switch(opcion) {
                case 1: listarTodo(); break;
                case 2: crearTicketAdmin(sc); break;
                case 3: asignarTicket(sc); break;
                case 4: actualizarEstado(sc); break;
                case 5: cerrarTicket(sc); break;
            }
        } while (opcion != 0);
    }

    public static void listarTodo() {
        System.out.println("\n--- Todos los Tickets ---");
        if (total == 0) { System.out.println("No hay tickets en el sistema."); return; }
        for (int i = 0; i < total; i++) {
            if (tickets[i] != null) {
                System.out.println("ID: " + tickets[i].getIdTicket() +
                        " | Cliente: " + (tickets[i].getClienteAsociado() != null ? tickets[i].getClienteAsociado().getNombre() : "N/A") +
                        " | Técnico: " + (tickets[i].getTecnicoAsignado() != null ? tickets[i].getTecnicoAsignado().getNombre() : "Sin asignar") +
                        " | Estado: " + tickets[i].getEstadoActual().getNombreEstado());
            }
        }
    }

    private static void crearTicketAdmin(Scanner sc) {
        String problema;
        while (true) {
            System.out.print("\nProblema reportado: ");
            problema = sc.nextLine();
            if (Validador.validarDescripcion(problema)) break;
            System.out.println("❌ Descripción inválida (debe tener entre 10 y 200 caracteres).");
        }
        Cliente c = (GestionarClientes.total > 0) ? GestionarClientes.clientes[0] : new Cliente(0, "Anónimo", "", "", "", "", "");
        Tecnico t = (GestionarTecnicos.total > 0) ? GestionarTecnicos.tecnicos[0] : null;
        Equipo e = (GestionarEquipos.total > 0) ? GestionarEquipos.equipos[0] : new Equipo(0, "S/N", "Genérico", "PC");

        tickets[total++] = new Ticket(total + 1, new Date(), problema, 1, c, t, e, new Estado(1, "Abierto"));
        System.out.println("✅ Ticket creado exitosamente.");
    }

    private static void asignarTicket(Scanner sc) {
        listarTodo();
        if (total == 0) return;
        System.out.print("ID del Ticket: ");
        int idTicket = Integer.parseInt(sc.nextLine());
        System.out.print("ID del Técnico a asignar (Ej. 1): ");
        int idTec = Integer.parseInt(sc.nextLine());

        for(int i=0; i<total; i++) {
            if(tickets[i] != null && tickets[i].getIdTicket() == idTicket) {
                for(int j=0; j<GestionarTecnicos.total; j++) {
                    if(GestionarTecnicos.tecnicos[j] != null && GestionarTecnicos.tecnicos[j].getIdUsuario() == idTec) {
                        tickets[i].asignarTecnico(GestionarTecnicos.tecnicos[j]);
                        System.out.println("✅ Técnico asignado.");
                        return;
                    }
                }
            }
        }
        System.out.println("❌ Ticket o Técnico no encontrado.");
    }

    private static void actualizarEstado(Scanner sc) {
        System.out.print("\nID del Ticket a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        String est;
        while (true) {
            System.out.print("Nuevo Estado (1=Abierto, 2=En proceso, 3=Terminado): ");
            est = sc.nextLine();
            if (Validador.validarPrioridad(est)) break;
            System.out.println("❌ Opción inválida (Debe ser 1, 2 o 3).");
        }
        String nombre = est.equals("2") ? "En proceso" : (est.equals("3") ? "Terminado" : "Abierto");

        for(int i=0; i<total; i++) {
            if(tickets[i] != null && tickets[i].getIdTicket() == id) {
                tickets[i].getEstadoActual().setNombreEstado(nombre);
                System.out.println("✅ Estado actualizado a: " + nombre);
                return;
            }
        }
        System.out.println("❌ Ticket no encontrado.");
    }

    private static void cerrarTicket(Scanner sc) {
        listarTodo();
        if (total == 0) return;
        System.out.print("\nID del Ticket a cerrar: ");
        int id = Integer.parseInt(sc.nextLine());
        for(int i=0; i<total; i++) {
            if(tickets[i] != null && tickets[i].getIdTicket() == id) {
                tickets[i].cerrarTicket();
                System.out.println("✅ Ticket cerrado definitivamente.");
                return;
            }
        }
        System.out.println("❌ Ticket no encontrado.");
    }
}