import Interfaz.GestionarInicioSesion;
import Interfaz.MenuPrincipal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE TICKETS      ║");
        System.out.println("║        SOPORTE TÉCNICO               ║");
        System.out.println("╚══════════════════════════════════════╝");

        GestionarInicioSesion.mostrar(sc);
        MenuPrincipal.mostrar(sc);

        sc.close();
    }
}