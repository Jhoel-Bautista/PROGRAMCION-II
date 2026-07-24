package Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Clase utilitaria con la paleta de colores, tipografías y componentes.
 * @author Jhoel
 */
public final class TemaUI {

    public static final Color FONDO_PRINCIPAL = new Color(18, 20, 24);
    public static final Color FONDO_SECUNDARIO = new Color(28, 32, 38);
    public static final Color AZUL_OSCURO = new Color(64, 114, 184);
    public static final Color AZUL = new Color(41, 98, 168);
    public static final Color CELESTE = new Color(43, 47, 54);
    public static final Color VERDE = new Color(39, 141, 89);
    public static final Color ROJO = new Color(196, 58, 58);
    public static final Color AMARILLO = new Color(196, 142, 24);
    public static final Color GRIS_TEXTO = new Color(150, 156, 165); // 🔥 AQUÍ ESTÁ EL COLOR QUE FALTABA 🔥
    public static final Color TEXTO_CLARO = new Color(220, 224, 230);
    public static final Color BLANCO = Color.WHITE;

    public static final Font FUENTE_TITULO = new Font("Segoe UI Emoji", Font.BOLD, 20);
    public static final Font FUENTE_SUBTITULO = new Font("Segoe UI Emoji", Font.BOLD, 14);
    public static final Font FUENTE_TEXTO = new Font("Segoe UI Emoji", Font.PLAIN, 13);
    public static final Font FUENTE_BOTON = new Font("Segoe UI Emoji", Font.BOLD, 13);

    private TemaUI() { }

    public static JLabel crearTitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_TITULO);
        label.setForeground(BLANCO);
        label.setBorder(new EmptyBorder(4, 4, 10, 4));
        return label;
    }

    public static JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_BOTON);
        boton.setBackground(color);
        boton.setForeground(BLANCO);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(new EmptyBorder(8, 16, 8, 16));
        return boton;
    }

    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_TEXTO);
        tabla.setRowHeight(26);
        tabla.setBackground(FONDO_SECUNDARIO);
        tabla.setForeground(TEXTO_CLARO);
        tabla.setGridColor(new Color(60, 65, 75));
        tabla.setSelectionBackground(AZUL);
        tabla.setSelectionForeground(BLANCO);
        tabla.setShowVerticalLines(true);
        tabla.setFillsViewportHeight(true);

        JTableHeader header = tabla.getTableHeader();
        header.setFont(FUENTE_SUBTITULO);

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(AZUL_OSCURO);
                label.setForeground(BLANCO);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(40, 45, 55)));
                return label;
            }
        });
        header.setOpaque(true);

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }
    }

    public static JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FUENTE_TEXTO);
        label.setForeground(TEXTO_CLARO);
        label.setOpaque(false);
        return label;
    }

    public static JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE_TEXTO);
        campo.setBackground(FONDO_SECUNDARIO);
        campo.setForeground(BLANCO);
        campo.setCaretColor(BLANCO);
        campo.setOpaque(true);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 85, 95)),
                new EmptyBorder(5, 8, 5, 8)));
        return campo;
    }

    public static void mostrarExito(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "✅ Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void mostrarError(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "❌ Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarAdvertencia(Component padre, String mensaje) {
        JOptionPane.showMessageDialog(padre, mensaje, "⚠️ Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public static boolean confirmar(Component padre, String mensaje) {
        int resultado = JOptionPane.showConfirmDialog(padre, mensaje, "Confirmar acción",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return resultado == JOptionPane.YES_OPTION;
    }
}