package Interfaz;

import Dominio.Tienda;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel de inicio (dashboard) rediseñado y moderno.
 * @author Jhoel
 */
public class PanelInicio extends JPanel {

    private static final Tienda tienda = Tienda.getInstancia();
    private final JPanel panelTarjetas = new JPanel(new GridLayout(2, 3, 20, 20));

    public PanelInicio() {
        super(new BorderLayout(15, 20));
        setBorder(new EmptyBorder(25, 25, 25, 25));
        setBackground(TemaUI.FONDO_PRINCIPAL);

        // 🔥 Título con ícono dibujado a color (no depende de emojis del sistema) 🔥
        JComponent icono = crearIconoGrafico();

        JLabel titulo = TemaUI.crearTitulo("Panel de Control Principal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        panelTitulo.setOpaque(false);
        panelTitulo.add(icono);
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        panelTarjetas.setBackground(TemaUI.FONDO_PRINCIPAL);
        add(panelTarjetas, BorderLayout.CENTER);

        JLabel pie = new JLabel(" Sistema de Gestión de Tickets de Soporte — Facultad de Ingeniería y Ciencias Aplicadas, UCE");
        pie.setFont(TemaUI.FUENTE_TEXTO);
        pie.setForeground(TemaUI.GRIS_TEXTO);
        pie.setHorizontalAlignment(SwingConstants.CENTER);
        add(pie, BorderLayout.SOUTH);

        actualizarResumen();
    }

    // 🔥 Ícono de barritas dibujado manualmente, siempre a color 🔥
    private JComponent crearIconoGrafico() {
        JPanel icono = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arco = 4;
                g2.setColor(TemaUI.AZUL);
                g2.fillRoundRect(2, 16, 8, 12, arco, arco);

                g2.setColor(TemaUI.VERDE);
                g2.fillRoundRect(13, 8, 8, 20, arco, arco);

                g2.setColor(TemaUI.AMARILLO);
                g2.fillRoundRect(24, 2, 8, 26, arco, arco);
            }
        };
        icono.setOpaque(false);
        icono.setPreferredSize(new Dimension(34, 30));
        return icono;
    }

    public void actualizarResumen() {
        panelTarjetas.removeAll();
        // 🔥 Iconos limpios universales y nombres en mayúscula para más elegancia 🔥
        panelTarjetas.add(crearTarjeta("👤 CLIENTES", tienda.getClientesRegistrados().length, TemaUI.AZUL));
        panelTarjetas.add(crearTarjeta("⚙ TÉCNICOS", tienda.getTecnicosRegistrados().length, TemaUI.VERDE));
        panelTarjetas.add(crearTarjeta("✉ TICKETS", tienda.getTicketsGestionados().length, TemaUI.AMARILLO));
        panelTarjetas.add(crearTarjeta("💻 EQUIPOS", tienda.getEquiposRegistrados().length, TemaUI.AZUL_OSCURO));
        panelTarjetas.add(crearTarjeta("🔧 REPUESTOS", tienda.getRepuestosRegistrados().length, TemaUI.ROJO));
        panelTarjetas.add(crearTarjeta("🛠 REPARACIONES", tienda.getReparacionesRegistradas().length, TemaUI.TEXTO_CLARO));
        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    // 🔥 Rediseño total de la tarjeta para que luzca moderna 🔥
    private JPanel crearTarjeta(String titulo, int valor, Color colorLinea) {
        JPanel tarjeta = new JPanel(new BorderLayout(10, 10));
        tarjeta.setBackground(TemaUI.FONDO_SECUNDARIO);

        // Borde moderno: Solo una línea gruesa de color en la parte de arriba
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 1, 1, 1, colorLinea),
                new EmptyBorder(20, 15, 15, 15)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        lblTitulo.setForeground(TemaUI.GRIS_TEXTO);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblValor = new JLabel(String.valueOf(valor));
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 48)); // Número mucho más grande
        lblValor.setForeground(TemaUI.BLANCO);
        lblValor.setHorizontalAlignment(SwingConstants.CENTER);

        tarjeta.add(lblTitulo, BorderLayout.NORTH);
        tarjeta.add(lblValor, BorderLayout.CENTER);
        return tarjeta;
    }
}