package Interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel base con la estructura común de toda pantalla de gestión CRUD:
 * título, tabla de resultados (JTable + JScrollPane) y una franja de
 * botones de acción en la parte inferior.
 *
 * @author Jhoel
 */
public abstract class PanelGestionBase extends JPanel {

    protected final DefaultTableModel modeloTabla;
    protected final JTable tabla;
    protected final JPanel panelBotones;

    protected PanelGestionBase(String titulo, String[] columnas) {
        super(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(TemaUI.FONDO_PRINCIPAL); // Fondo oscuro principal

        add(TemaUI.crearTitulo(titulo), BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        TemaUI.estilizarTabla(tabla);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(60, 65, 75)));
        scroll.getViewport().setBackground(TemaUI.FONDO_PRINCIPAL); // Fondo oscuro para el espacio vacío de la tabla
        add(scroll, BorderLayout.CENTER);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelBotones.setBackground(TemaUI.FONDO_PRINCIPAL); // Fondo oscuro para el contenedor de botones
        add(panelBotones, BorderLayout.SOUTH);
    }

    public abstract void refrescarTabla();

    protected int obtenerIdSeleccionado(int columnaId) {
        int fila = tabla.getSelectedRow();
        if (fila < 0) return -1;
        try {
            return Integer.parseInt(modeloTabla.getValueAt(fila, columnaId).toString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}