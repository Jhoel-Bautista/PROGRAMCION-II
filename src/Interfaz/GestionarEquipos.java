package Interfaz;

import Dominio.Equipo;
import Util.Validador;
import java.util.Scanner;

public class GestionarEquipos {
    public static Equipo[] equipos = new Equipo[50];
    public static int total = 0;

    static {
        equipos[total++] = new Equipo(1, "SN-98765", "Dell Inspiron 15", "Laptop");
    }

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- GESTIONAR EQUIPOS ---");
            System.out.println("1. Registrar equipo");
            System.out.println("2. Consultar equipo");
            System.out.println("3. Actualizar datos del equipo");
            System.out.println("4. Eliminar equipo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String entrada = sc.nextLine();
            if (!entrada.matches("[0-4]")) { opcion = -1; continue; }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: registrar(sc); break;
                case 2: consultar(); break;
                case 3: actualizar(sc); break;
                case 4: eliminar(sc); break;
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        String serie;
        while (true) {
            System.out.print("\nNúmero de serie: ");
            serie = sc.nextLine();
            if (Validador.validarNumeroSerie(serie)) break;
            System.out.println("❌ Número de serie inválido.");
        }
        System.out.print("Modelo: ");
        String modelo = sc.nextLine();
        System.out.print("Tipo (Laptop/Desktop/Impresora): ");
        String tipo = sc.nextLine();
        equipos[total++] = new Equipo(total + 1, serie, modelo, tipo);
        System.out.println("✅ Equipo registrado exitosamente.");
    }

    public static void consultar() {
        System.out.println("\n--- Equipos Registrados ---");
        boolean hay = false;
        for (int i = 0; i < total; i++) {
            if (equipos[i] != null) {
                System.out.println("ID: " + equipos[i].getIdEquipo() + " | Modelo: " + equipos[i].getModelo() + " | Serie: " + equipos[i].getNumeroSerie());
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay equipos.");
    }

    private static void actualizar(Scanner sc) {
        consultar();
        System.out.print("\nID del equipo a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < total; i++) {
            if (equipos[i] != null && equipos[i].getIdEquipo() == id) {
                System.out.print("Nuevo Modelo [Enter para omitir]: ");
                String mod = sc.nextLine();
                if (!mod.isEmpty()) equipos[i].setModelo(mod);
                System.out.println("✅ Datos actualizados.");
                return;
            }
        }
    }

    private static void eliminar(Scanner sc) {
        consultar();
        System.out.print("\nID del equipo a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < total; i++) {
            if (equipos[i] != null && equipos[i].getIdEquipo() == id) {
                equipos[i] = null;
                System.out.println("✅ Equipo eliminado.");
                return;
            }
        }
    }
}