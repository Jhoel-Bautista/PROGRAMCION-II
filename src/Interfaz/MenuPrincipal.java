package Interfaz;

import Dominio.Cliente;
import Dominio.Tecnico;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema (JFrame).
 * @author Jhoel
 */
public class MenuPrincipal extends JFrame {

    private static final String INICIO = "INICIO";
    private static final String CLIENTES = "CLIENTES";
    private static final String TECNICOS = "TECNICOS";
    private static final String EQUIPOS = "EQUIPOS";
    private static final String REPUESTOS = "REPUESTOS";
    private static final String REPARACIONES = "REPARACIONES";
    private static final String TICKETS = "TICKETS";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel panelContenido = new JPanel(cardLayout);
    private final JLabel etiquetaSesion = new JLabel();

    private final PanelInicio panelInicio = new PanelInicio();
    private final GestionarClientes panelClientes = new GestionarClientes();
    private final GestionarTecnicos panelTecnicos = new GestionarTecnicos();
    private final GestionarEquipos panelEquipos = new GestionarEquipos();
    private final GestionarRepuestos panelRepuestos = new GestionarRepuestos();
    private final GestionarReparaciones panelReparaciones = new GestionarReparaciones();
    private final GestionarTickets panelTickets = new GestionarTickets();

    public MenuPrincipal() {
        super("Sistema de Gestión de Tickets de Soporte - Tienda de Tecnología");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setMinimumSize(new Dimension(900, 550));
        setLocationRelativeTo(null);

        panelContenido.setBackground(TemaUI.FONDO_PRINCIPAL);

        setJMenuBar(construirMenu());
        add(construirEncabezado(), BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);

        registrarPaneles();


        panelInicio.actualizarResumen();

        cardLayout.show(panelContenido, INICIO);
    }

    public static void mostrar() {
        boolean autenticado = GestionarInicioSesion.mostrarLogin(null);
        if (!autenticado) {
            System.exit(0);
            return;
        }
        MenuPrincipal frame = new MenuPrincipal();
        frame.setVisible(true);
    }

    private void registrarPaneles() {
        panelContenido.add(panelInicio, INICIO);
        panelContenido.add(panelClientes, CLIENTES);
        panelContenido.add(panelTecnicos, TECNICOS);
        panelContenido.add(panelEquipos, EQUIPOS);
        panelContenido.add(panelRepuestos, REPUESTOS);
        panelContenido.add(panelReparaciones, REPARACIONES);
        panelContenido.add(panelTickets, TICKETS);
    }

    private JPanel construirEncabezado() {
        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(TemaUI.AZUL_OSCURO);
        encabezado.setPreferredSize(new Dimension(0, 55));

        JLabel titulo = new JLabel("  💻 SISTEMA DE GESTIÓN DE TICKETS DE SOPORTE");
        titulo.setFont(TemaUI.FUENTE_TITULO);
        titulo.setForeground(Color.WHITE);
        encabezado.add(titulo, BorderLayout.WEST);

        actualizarEtiquetaSesion();
        etiquetaSesion.setFont(TemaUI.FUENTE_SUBTITULO);
        etiquetaSesion.setForeground(Color.WHITE);
        etiquetaSesion.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
        encabezado.add(etiquetaSesion, BorderLayout.EAST);

        return encabezado;
    }

    private void actualizarEtiquetaSesion() {
        String texto = "👤 Administrador";
        Cliente cliente = GestionarInicioSesion.clienteActual;
        Tecnico tecnico = GestionarInicioSesion.tecnicoActual;
        if (cliente != null) {
            texto = "👤 " + cliente.getNombre() + " (" + cliente.obtenerRol() + ")";
        } else if (tecnico != null) {
            texto = "👤 " + tecnico.getNombre() + " (" + tecnico.obtenerRol() + ")";
        }
        etiquetaSesion.setText(texto + "  ");
    }

    private JMenuBar construirMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuGestion = new JMenu("Gestión");
        agregarItem(menuGestion, "🏠 Inicio", e -> mostrarPanel(INICIO));
        menuGestion.addSeparator();
        agregarItem(menuGestion, "👥 Clientes", e -> mostrarPanel(CLIENTES));
        agregarItem(menuGestion, "🧑‍🔧 Personal Técnico", e -> mostrarPanel(TECNICOS));
        agregarItem(menuGestion, "💻 Equipos", e -> mostrarPanel(EQUIPOS));
        agregarItem(menuGestion, "⚙️ Repuestos e Inventario", e -> mostrarPanel(REPUESTOS));
        agregarItem(menuGestion, "🛠️ Reparaciones", e -> mostrarPanel(REPARACIONES));
        agregarItem(menuGestion, "🎫 Tickets de Soporte", e -> mostrarPanel(TICKETS));
        menuGestion.addSeparator();
        agregarItem(menuGestion, "🚪 Salir", e -> System.exit(0));

        JMenu menuSesion = new JMenu("Sesión");
        agregarItem(menuSesion, "🔒 Cerrar sesión", e -> cerrarSesion());

        JMenu menuAyuda = new JMenu("Ayuda");
        agregarItem(menuAyuda, "ℹ️ Acerca de", e -> mostrarAcercaDe());

        menuBar.add(menuGestion);
        menuBar.add(menuSesion);
        menuBar.add(menuAyuda);
        return menuBar;
    }

    private void agregarItem(JMenu menu, String texto, java.awt.event.ActionListener accion) {
        JMenuItem item = new JMenuItem(texto);
        item.addActionListener(accion);
        menu.add(item);
    }

    private void mostrarPanel(String nombre) {
        if (INICIO.equals(nombre)) panelInicio.actualizarResumen();
        else if (CLIENTES.equals(nombre)) panelClientes.refrescarTabla();
        else if (TECNICOS.equals(nombre)) panelTecnicos.refrescarTabla();
        else if (EQUIPOS.equals(nombre)) panelEquipos.refrescarTabla();
        else if (REPUESTOS.equals(nombre)) panelRepuestos.refrescarTabla();
        else if (REPARACIONES.equals(nombre)) panelReparaciones.refrescarTabla();
        else if (TICKETS.equals(nombre)) panelTickets.refrescarTabla();
        cardLayout.show(panelContenido, nombre);
    }

    private void cerrarSesion() {
        GestionarInicioSesion.limpiarSesion();
        GestionarInicioSesion.rolActual = "";
        dispose();
        MenuPrincipal.mostrar();
    }

    private void mostrarAcercaDe() {
        JOptionPane.showMessageDialog(this,
                "Sistema de Gestión de Tickets de Soporte\nTienda de Tecnología\n\n" +
                        "Universidad Central del Ecuador\nFacultad de Ingeniería y Ciencias Aplicadas\n" +
                        "Nuevas Tecnologías e Innovación en Sistemas de Información",
                "Acerca del sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}