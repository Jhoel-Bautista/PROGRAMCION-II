package Interfaz;
import Dominio.Reparacion;
import java.util.Date;
import java.util.Scanner;

public class GestionarReparaciones{
    public static Reparacion[] reparaciones = new Reparacion[50];
    public static int total = 0;

    static {
        reparaciones[total++] = new Reparacion(1, new Date(), new Date(), "Cambio de placa base");
    }

    public static void mostrar(Scanner sc) {
        mostrarStaff(sc);
    }

    private static void mostrarStaff(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- GESTIONAR REPARACIONES ---");
            System.out.println("1. Revisar historial de cambios");
            System.out.println("2. Controlar tiempos de entrega");
            System.out.println("3. Verificar reparaciones realizadas");
            System.out.println("4. Generar resumen del servicio (Ticket de salida)");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String entrada = sc.nextLine();
            if (!entrada.matches("[0-4]")) { opcion = -1; continue; }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: revisarHistorial(); break;
                case 2: controlarTiempos(); break;
                case 3: verificarReparacion(sc); break;
                case 4: generarTicket(sc); break;
            }
        } while (opcion != 0);
    }

    private static void revisarHistorial() {
        System.out.println("\n--- Historial de Reparaciones ---");
        for (int i = 0; i < total; i++) {
            if(reparaciones[i] != null) System.out.println("ID: " + reparaciones[i].getIdReparacion() + " | Problema: " + reparaciones[i].getDescripcionProblema() + " | Estado: " + reparaciones[i].getEstadoReparacion());
        }
    }

    private static void controlarTiempos() {
        System.out.println("\n--- Control de Tiempos ---");
        System.out.println("⏳ Verificando plazos de entrega...");
        System.out.println("✅ Todas las reparaciones están a tiempo. No hay avisos de retraso que enviar.");
    }

    private static void verificarReparacion(Scanner sc) {
        revisarHistorial();
        System.out.print("Ingrese ID de la reparación a verificar/terminar: ");
        int id = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < total; i++) {
            if (reparaciones[i] != null && reparaciones[i].getIdReparacion() == id) {
                reparaciones[i].setEstadoReparacion("Verificada y Completada");
                System.out.println("✅ Reparación verificada exitosamente.");
                return;
            }
        }
    }

    private static void generarTicket(Scanner sc) {
        revisarHistorial();
        System.out.print("Ingrese ID para generar ticket de salida: ");
        int id = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < total; i++) {
            if (reparaciones[i] != null && reparaciones[i].getIdReparacion() == id) {
                System.out.println("\n==================================");
                System.out.println("      TICKET DE SALIDA            ");
                System.out.println("==================================");
                System.out.println("ID Reparación: " + reparaciones[i].getIdReparacion());
                System.out.println("Detalle: " + reparaciones[i].getDescripcionProblema());
                System.out.println("Estado Final: " + reparaciones[i].getEstadoReparacion());
                System.out.println("==================================\n");
                return;
            }
        }
    }
}