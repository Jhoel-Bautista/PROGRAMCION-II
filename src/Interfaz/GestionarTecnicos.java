package Interfaz;

import Dominio.Tecnico;

import java.util.Scanner;

public class GestionarTecnicos {
    public static Tecnico[] tecnicos = new Tecnico[50];
    public static int total = 0;

    static {
        tecnicos[total++] = new Tecnico(1, "Pedro Soporte", "tecnico@uce.com", "Tecnico123", "EMP-001", "Hardware y Redes", 1);
    }

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- GESTIONAR PERSONAL TÉCNICO (ADMINISTRADOR) ---");
            System.out.println("1. Registrar personal técnico");
            System.out.println("2. Actualizar datos del personal técnico");
            System.out.println("3. Consultar personal técnico");
            System.out.println("4. Eliminar personal técnico");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            String entrada = sc.nextLine();
            if (!entrada.matches("[0-4]")) { opcion = -1; continue; }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: registrar(sc); break;
                case 2: actualizar(sc); break;
                case 3: consultar(); break;
                case 4: eliminar(sc); break;
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        System.out.println("\n--- Registrar Nuevo Técnico ---");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        System.out.print("Especialidad (Ej. Hardware, Software): ");
        String especialidad = sc.nextLine();

        tecnicos[total++] = new Tecnico(total + 1, nombre, email, pass, "EMP-00" + (total + 1), especialidad, 1);
        System.out.println("✅ Técnico registrado exitosamente.");
    }

    private static void consultar() {
        System.out.println("\n--- Nómina de Técnicos ---");
        boolean hay = false;
        for(int i = 0; i < total; i++) {
            if (tecnicos[i] != null) {
                System.out.println("ID: " + tecnicos[i].getIdUsuario() + " | Nombre: " + tecnicos[i].getNombre() + " | Especialidad: " + tecnicos[i].getEspecialidad());
                hay = true;
            }
        }
        if(!hay) System.out.println("No hay técnicos en el sistema.");
    }

    private static void actualizar(Scanner sc) {
        consultar();
        System.out.print("\nIngrese el ID del técnico a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < total; i++) {
            if(tecnicos[i] != null && tecnicos[i].getIdUsuario() == id) {
                System.out.print("Nueva Especialidad (Actual: " + tecnicos[i].getEspecialidad() + ") [Enter para omitir]: ");
                String esp = sc.nextLine();
                if(!esp.isEmpty()) tecnicos[i].setEspecialidad(esp);
                System.out.println("✅ Datos actualizados correctamente.");
                return;
            }
        }
        System.out.println("❌ Técnico no encontrado.");
    }

    private static void eliminar(Scanner sc) {
        consultar();
        System.out.print("\nIngrese el ID del técnico a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("¿Está seguro de eliminar este técnico? (s/n): ");
        if(sc.nextLine().equalsIgnoreCase("s")) {
            for(int i = 0; i < total; i++) {
                if(tecnicos[i] != null && tecnicos[i].getIdUsuario() == id) {
                    tecnicos[i] = null; // Borrado lógico
                    System.out.println("✅ Técnico eliminado del sistema.");
                    return;
                }
            }
        }
    }
}