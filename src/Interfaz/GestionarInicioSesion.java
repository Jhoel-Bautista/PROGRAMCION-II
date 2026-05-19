package Interfaz;

import Dominio.Cliente;
import Dominio.Tecnico;
import Util.Validador;
import java.util.Scanner;

public class GestionarInicioSesion {
    private static final String EMAIL_ADMIN = "admin@uce.com";
    private static final String PASSWORD_ADMIN = "Admin123";

    public static String rolActual = "";
    public static Cliente clienteActual = null;
    public static Tecnico tecnicoActual = null;


    public static void mostrar(Scanner sc) {
        System.out.println("\n=== INICIAR SESIÓN ===");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse como Cliente");
        System.out.print("Opción: ");
        String opcion = sc.nextLine();

        if (opcion.equals("2")) {
            registrarCliente(sc);
            return;
        }

        int intentos = 3;
        while (intentos > 0) {
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            if (email.equals(EMAIL_ADMIN) && password.equals(PASSWORD_ADMIN)) {
                rolActual = "SESION_ACTIVA";
                limpiarSesion();
                System.out.println("✅ Bienvenido Administrador!");
                return;
            }

            for (int i = 0; i < GestionarTecnicos.total; i++) {
                if (GestionarTecnicos.tecnicos[i] != null &&
                        GestionarTecnicos.tecnicos[i].getEmail().equals(email) &&
                        GestionarTecnicos.tecnicos[i].getPassword().equals(password)) {
                    rolActual = "SESION_ACTIVA";
                    tecnicoActual = GestionarTecnicos.tecnicos[i];
                    System.out.println("✅ Bienvenido Técnico " + tecnicoActual.getNombre() + "!");
                    return;
                }
            }

            for (int i = 0; i < GestionarClientes.total; i++) {
                if (GestionarClientes.clientes[i] != null &&
                        GestionarClientes.clientes[i].getEmail().equals(email) &&
                        GestionarClientes.clientes[i].getPassword().equals(password)) {
                    rolActual = "SESION_ACTIVA";
                    clienteActual = GestionarClientes.clientes[i];
                    System.out.println("✅ Bienvenido " + clienteActual.getNombre() + "!");
                    return;
                }
            }

            intentos--;
            System.out.println("❌ Credenciales incorrectas. Intentos restantes: " + intentos);
        }
    }

    private static void registrarCliente(Scanner sc) {
        System.out.println("\n--- Registro de Cliente ---");

        String nom;
        while (true) {
            System.out.print("Nombre: "); nom = sc.nextLine();
            if (Validador.validarNombrePropio(nom)) break;
            System.out.println("❌ Nombre inválido.");
        }

        String mail;
        while (true) {
            System.out.print("Email: "); mail = sc.nextLine();
            if (Validador.validarEmail(mail)) break;
            System.out.println("❌ Email inválido.");
        }

        String pass;
        while (true) {
            System.out.print("Password: "); pass = sc.nextLine();
            if (Validador.validarPassword(pass)) break;
            System.out.println("❌ Password inválido.");
        }

        String tel;
        while (true) {
            System.out.print("Teléfono: "); tel = sc.nextLine();
            if (Validador.validarTelefono(tel)) break;
            System.out.println("❌ Teléfono inválido.");
        }

        System.out.print("Dirección: "); String dir = sc.nextLine();

        Cliente nuevo = new Cliente(GestionarClientes.total + 1, nom, mail, pass, "CLI-" + (GestionarClientes.total + 1), tel, dir);
        GestionarClientes.clientes[GestionarClientes.total++] = nuevo;

        rolActual = "SESION_ACTIVA";
        clienteActual = nuevo;
        System.out.println("✅ Cuenta creada y sesión iniciada.");
    }

    public static void limpiarSesion() {
        clienteActual = null;
        tecnicoActual = null;
    }
}