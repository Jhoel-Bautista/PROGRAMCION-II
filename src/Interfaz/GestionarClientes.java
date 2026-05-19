package Interfaz;

import Dominio.Cliente;
import Util.Validador;
import java.util.Scanner;

public class GestionarClientes {
    public static Cliente[] clientes = new Cliente[50];
    public static int total = 0;

    static {
        clientes[total++] = new Cliente(1, "Juan Perez", "juan@gmail.com", "Juan123", "CLI-1", "0999999999", "Quito");
    }

    public static void mostrar(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- GESTIONAR CLIENTES ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Eliminar cliente");
            System.out.println("3. Buscar cliente (Por nombre o teléfono)");
            System.out.println("4. Actualizar información de cliente");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!entrada.matches("[0-4]")) { opcion = -1; continue; }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: registrar(sc); break;
                case 2: eliminarCliente(sc); break;
                case 3: buscarCliente(sc); break;
                case 4: actualizarCliente(sc); break;
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        System.out.println("\n--- Registrar Nuevo Cliente ---");

        String nombre;
        while (true) {
            System.out.print("Nombre completo: ");
            nombre = sc.nextLine();
            if (Validador.validarNombrePropio(nombre)) break;
            System.out.println("❌ Nombre inválido. Debe usar mayúsculas iniciales.");
        }

        String email;
        while (true) {
            System.out.print("Email: ");
            email = sc.nextLine();
            if (Validador.validarEmail(email)) break;
            System.out.println("❌ Email inválido.");
        }

        String telefono;
        while (true) {
            System.out.print("Teléfono: ");
            telefono = sc.nextLine();
            if (Validador.validarTelefono(telefono)) break;
            System.out.println("❌ Teléfono inválido.");
        }

        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        String password;
        while (true) {
            System.out.print("Password: ");
            password = sc.nextLine();
            if (Validador.validarPassword(password)) break;
            System.out.println("❌ Password inválido. Mínimo 6 caracteres, una mayúscula y un número.");
        }

        clientes[total++] = new Cliente(total + 1, nombre, email, password, "CLI-" + (total + 1), telefono, direccion);
        System.out.println("✅ Cliente registrado exitosamente.");
    }

    private static void eliminarCliente(Scanner sc) {
        if (total == 0) { System.out.println("❌ No hay clientes registrados."); return; }
        buscarCliente(sc);
        System.out.print("\nIngrese el ID del cliente a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < total; i++) {
            if (clientes[i] != null && clientes[i].getIdUsuario() == id) {
                clientes[i] = null;
                System.out.println("✅ Cliente eliminado exitosamente.");
                return;
            }
        }
        System.out.println("❌ Cliente no encontrado.");
    }

    private static void buscarCliente(Scanner sc) {
        System.out.print("\nIngrese nombre o teléfono a buscar: ");
        String filtro = sc.nextLine().toLowerCase();
        boolean encontrado = false;
        for (int i = 0; i < total; i++) {
            if (clientes[i] != null && (clientes[i].getNombre().toLowerCase().contains(filtro) || clientes[i].getTelefono().contains(filtro))) {
                System.out.println("ID: " + clientes[i].getIdUsuario() + " | Nombre: " + clientes[i].getNombre() + " | Tel: " + clientes[i].getTelefono() + " | Email: " + clientes[i].getEmail());
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("❌ No se encontraron coincidencias.");
    }

    private static void actualizarCliente(Scanner sc) {
        buscarCliente(sc);
        System.out.print("\nIngrese el ID del cliente a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < total; i++) {
            if (clientes[i] != null && clientes[i].getIdUsuario() == id) {
                String tel;
                while (true) {
                    System.out.print("Nuevo Teléfono (Actual: " + clientes[i].getTelefono() + ") [Enter para omitir]: ");
                    tel = sc.nextLine();
                    if (tel.isEmpty() || Validador.validarTelefono(tel)) break;
                    System.out.println("❌ Teléfono inválido.");
                }
                if (!tel.isEmpty()) clientes[i].setTelefono(tel);

                System.out.print("Nueva Dirección (Actual: " + clientes[i].getDireccion() + ") [Enter para omitir]: ");
                String dir = sc.nextLine();
                if (!dir.isEmpty()) clientes[i].setDireccion(dir);

                System.out.println("✅ Información actualizada correctamente.");
                return;
            }
        }
        System.out.println("❌ Cliente no encontrado.");
    }
}