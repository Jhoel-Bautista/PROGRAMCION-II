package Interfaz;

import Dominio.Equipo;
import Util.Validador;
import java.util.Scanner;

public class GestionarEquipos {

    static Equipo[] equipos = new Equipo[10];
    static int total = 0;

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n=== GESTIONAR EQUIPOS ===");
            System.out.println("1. Registrar Equipo");
            System.out.println("2. Listar Equipos");
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
        System.out.println("\n--- Registrar Equipo ---");

        String numeroSerie;
        do {
            System.out.print("Número de serie: ");
            numeroSerie = sc.nextLine();
            if (!Validador.validarNumeroSerie(numeroSerie))
                System.out.println("Error: Serie inválida (5-20 caracteres alfanuméricos).");
        } while (!Validador.validarNumeroSerie(numeroSerie));

        String modelo;
        do {
            System.out.print("Modelo: ");
            modelo = sc.nextLine();
            if (modelo.isEmpty())
                System.out.println("Error: Modelo no puede estar vacío.");
        } while (modelo.isEmpty());

        String tipoEquipo;
        do {
            System.out.print("Tipo (Laptop/Desktop/Impresora): ");
            tipoEquipo = sc.nextLine();
            if (tipoEquipo.isEmpty())
                System.out.println("Error: Tipo no puede estar vacío.");
        } while (tipoEquipo.isEmpty());

        Equipo e = new Equipo(total + 1, numeroSerie, modelo, tipoEquipo);
        equipos[total] = e;
        total++;
        System.out.println("Equipo registrado correctamente!");
    }

    private static void listar() {
        System.out.println("\n--- Lista de Equipos ---");
        if (total == 0) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        for (int i = 0; i < total; i++)
            System.out.println(equipos[i]);
    }
}