package Interfaz;
import Dominio.Repuesto;
import java.util.Scanner;

public class GestionarRepuestos {
    public static Repuesto[] repuestos = new Repuesto[50];
    public static int total = 0;

    static {
        repuestos[total++] = new Repuesto(1, "Disco Duro SSD", "500GB", 3, 45.50); // Stock bajo intencional
        repuestos[total++] = new Repuesto(2, "Memoria RAM", "8GB DDR4", 15, 30.00);
    }

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- GESTIONAR REPUESTOS E INVENTARIO (ADMINISTRADOR) ---");
            System.out.println("1. Verificar disponibilidad de stock");
            System.out.println("2. Registrar entrada de componentes");
            System.out.println("3. Monitorear niveles críticos (Stock bajo)");
            System.out.println("4. Consultar catálogo de piezas");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String entrada = sc.nextLine();
            if (!entrada.matches("[0-4]")) { opcion = -1; continue; }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: consultar(false); break;
                case 2: registrarEntrada(sc); break;
                case 3: consultar(true); break; // true = solo críticos
                case 4: consultar(false); break;
            }
        } while (opcion != 0);
    }

    private static void consultar(boolean soloCriticos) {
        System.out.println("\n--- " + (soloCriticos ? "Alertas de Stock Bajo (< 5 unidades)" : "Catálogo General de Repuestos") + " ---");
        boolean hay = false;
        for (int i = 0; i < total; i++) {
            if (repuestos[i] != null) {
                if (!soloCriticos || repuestos[i].getStockDisponible() < 5) {
                    System.out.println("ID: " + repuestos[i].getIdRepuesto() + " | Pieza: " + repuestos[i].getNombreRepuesto() + " | Stock: " + repuestos[i].getStockDisponible() + " | Precio: $" + repuestos[i].getCostoUnitario());
                    hay = true;
                }
            }
        }
        if (!hay) System.out.println("No hay piezas en esta categoría.");
    }

    private static void registrarEntrada(Scanner sc) {
        System.out.println("\n1. Agregar stock a repuesto existente\n2. Ingresar un repuesto nuevo");
        System.out.print("Opción: ");
        String op = sc.nextLine();

        if (op.equals("1")) {
            consultar(false);
            System.out.print("Ingrese ID del repuesto: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Cantidad a sumar: ");
            int cant = Integer.parseInt(sc.nextLine());
            for (int i = 0; i < total; i++) {
                if (repuestos[i] != null && repuestos[i].getIdRepuesto() == id) {
                    repuestos[i].setStockDisponible(repuestos[i].getStockDisponible() + cant);
                    System.out.println("✅ Stock actualizado correctamente. Notificación de mercadería enviada.");
                    return;
                }
            }
        } else if (op.equals("2")) {
            System.out.print("Nombre de la pieza: ");
            String nombre = sc.nextLine();
            System.out.print("Descripción: ");
            String desc = sc.nextLine();
            System.out.print("Stock inicial: ");
            int stock = Integer.parseInt(sc.nextLine());
            System.out.print("Costo unitario ($): ");
            double costo = Double.parseDouble(sc.nextLine());
            repuestos[total++] = new Repuesto(total + 1, nombre, desc, stock, costo);
            System.out.println("✅ Nuevo repuesto ingresado al catálogo.");
        }
    }
}