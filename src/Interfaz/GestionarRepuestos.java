package Interfaz;

import Dominio.Repuesto;
import Dominio.Tienda;
import Util.ProcesadorLambdas; // 🔥 Importamos nuestra clase de lambdas

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel de gestión de repuestos e inventario con ordenamiento por lambda.
 * @author Jhoel
 */
public class GestionarRepuestos extends PanelGestionBase {

    private static final Tienda tienda = Tienda.getInstancia();
    private static final int UMBRAL_STOCK_BAJO = 3;

    static {
        if (tienda.getRepuestosRegistrados().length == 0) {
            tienda.agregarRepuesto(new Repuesto(1, "RAM 8GB", "Memoria DDR4", 10, 25.5));
            tienda.agregarRepuesto(new Repuesto(2, "SSD 240GB", "Unidad de almacenamiento", 5, 35.0));
            tienda.agregarRepuesto(new Repuesto(3, "Pantalla LCD", "Repuesto portátil", 2, 85.0));
        }
    }

    private final JTextField campoNombre = TemaUI.crearCampoTexto();
    private final JTextField campoDescripcion = TemaUI.crearCampoTexto();
    private final JSpinner spinnerStock = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));
    private final JSpinner spinnerCosto = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1_000_000.0, 0.5));

    private final JCheckBox chkSoloStockBajo = new JCheckBox("Mostrar solo stock crítico (< " + UMBRAL_STOCK_BAJO + ")");

    // 🔥 NUEVO: Checkbox que activa la Lambda de Ordenamiento por Precio/Costo 🔥
    private final JCheckBox chkOrdenarPorCosto = new JCheckBox("📊 Ordenar por costo (menor a mayor)");

    public GestionarRepuestos() {
        super("⚙️ Gestión de Repuestos e Inventario", new String[]{"ID", "Nombre", "Descripción", "Stock", "Costo ($)"});

        add(construirFormulario(), BorderLayout.EAST);
        construirBotones();

        // Listeners con Lambdas para refrescar la tabla al marcar las casillas
        chkSoloStockBajo.addActionListener(e -> refrescarTabla());
        chkOrdenarPorCosto.addActionListener(e -> refrescarTabla());

        refrescarTabla();

        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccionEnFormulario());
    }

    private JPanel construirFormulario() {
        JPanel form = new JPanel(new GridLayout(0, 1, 4, 8));

        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TemaUI.AZUL_OSCURO), "Datos del repuesto",
                TitledBorder.LEFT, TitledBorder.TOP, TemaUI.FUENTE_SUBTITULO, TemaUI.TEXTO_CLARO);

        form.setBorder(BorderFactory.createCompoundBorder(borde, new EmptyBorder(10, 10, 10, 10)));
        form.setPreferredSize(new Dimension(280, 0));
        form.setBackground(TemaUI.FONDO_PRINCIPAL);

        chkSoloStockBajo.setBackground(TemaUI.FONDO_PRINCIPAL);
        chkSoloStockBajo.setForeground(TemaUI.TEXTO_CLARO);
        chkSoloStockBajo.setFont(TemaUI.FUENTE_TEXTO);

        // 🔥 Estilos del nuevo checkbox de ordenamiento por precio 🔥
        chkOrdenarPorCosto.setBackground(TemaUI.FONDO_PRINCIPAL);
        chkOrdenarPorCosto.setForeground(TemaUI.TEXTO_CLARO);
        chkOrdenarPorCosto.setFont(TemaUI.FUENTE_TEXTO);

        form.add(TemaUI.crearEtiqueta("Nombre:"));
        form.add(campoNombre);
        form.add(TemaUI.crearEtiqueta("Descripción:"));
        form.add(campoDescripcion);
        form.add(TemaUI.crearEtiqueta("Stock disponible:"));
        form.add(spinnerStock);
        form.add(TemaUI.crearEtiqueta("Costo unitario ($):"));
        form.add(spinnerCosto);
        form.add(chkSoloStockBajo);
        form.add(chkOrdenarPorCosto); // Agregado a la interfaz visual
        return form;
    }

    private void construirBotones() {
        JButton btnNuevo = TemaUI.crearBoton("➕ Registrar", TemaUI.VERDE);
        JButton btnActualizar = TemaUI.crearBoton("✏️ Actualizar stock", TemaUI.AZUL);
        JButton btnEliminar = TemaUI.crearBoton("🗑️ Eliminar", TemaUI.ROJO);
        JButton btnLimpiar = TemaUI.crearBoton("🧹 Limpiar", TemaUI.AMARILLO);

        btnNuevo.addActionListener(e -> registrar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
    }

    @Override
    public void refrescarTabla() {
        modeloTabla.setRowCount(0);

        // 1. Convertimos el arreglo a una Lista dinámica
        List<Repuesto> listaRepuestos = new ArrayList<>();
        for (Repuesto r : tienda.getRepuestosRegistrados()) {
            listaRepuestos.add(r);
        }

        // 2. 🔥 APLICAMOS LA LAMBDA DE ORDENAMIENTO SI EL CHECKBOX ESTÁ MARCADO 🔥
        if (chkOrdenarPorCosto.isSelected()) {
            ProcesadorLambdas.ordenarRepuestosPorCosto(listaRepuestos);
        }

        // 3. Llenamos la tabla visualmente con los datos ya ordenados (y filtrados si aplica)
        for (Repuesto r : listaRepuestos) {
            if (chkSoloStockBajo.isSelected() && r.getStockDisponible() >= UMBRAL_STOCK_BAJO) continue;
            modeloTabla.addRow(new Object[]{
                    r.getIdRepuesto(),
                    r.getNombreRepuesto(),
                    r.getDescripcionRepuesto(),
                    r.getStockDisponible(),
                    r.getCostoUnitario()
            });
        }
    }

    private void cargarSeleccionEnFormulario() {
        int id = obtenerIdSeleccionado(0);
        Repuesto repuesto = tienda.buscarRepuestoPorId(id);
        if (repuesto == null) return;
        campoNombre.setText(repuesto.getNombreRepuesto());
        campoDescripcion.setText(repuesto.getDescripcionRepuesto());
        spinnerStock.setValue(repuesto.getStockDisponible());
        spinnerCosto.setValue(repuesto.getCostoUnitario());
    }

    private void registrar() {
        String nombre = campoNombre.getText().trim();
        String descripcion = campoDescripcion.getText().trim();
        int stock = (Integer) spinnerStock.getValue();
        double costo = (Double) spinnerCosto.getValue();

        if (nombre.length() < 2) {
            TemaUI.mostrarError(this, "Nombre inválido o muy corto (Ejemplo: Pantalla LCD).");
            return;
        }

        try {
            tienda.agregarRepuesto(new Repuesto(0, nombre, descripcion.isEmpty() ? "Sin descripcion" : descripcion, stock, costo));
            TemaUI.mostrarExito(this, "¡Repuesto guardado en bodega correctamente!");
            limpiarFormulario();
            refrescarTabla();
        } catch (IllegalArgumentException ex) {
            TemaUI.mostrarAdvertencia(this, ex.getMessage());
        }
    }

    private void actualizar() {
        int id = obtenerIdSeleccionado(0);
        Repuesto repuesto = tienda.buscarRepuestoPorId(id);
        if (repuesto == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un repuesto de la tabla para actualizar.");
            return;
        }

        try {
            repuesto.setStockDisponible((Integer) spinnerStock.getValue());
            repuesto.setCostoUnitario((Double) spinnerCosto.getValue());
            String descripcion = campoDescripcion.getText().trim();
            if (!descripcion.isEmpty()) repuesto.setDescripcionRepuesto(descripcion);

            tienda.editarRepuesto(repuesto);
            TemaUI.mostrarExito(this, "¡Bodega actualizada exitosamente! Nuevo stock: " + repuesto.getStockDisponible());
            limpiarFormulario();
            refrescarTabla();
        } catch (IllegalArgumentException ex) {
            TemaUI.mostrarAdvertencia(this, ex.getMessage());
        }
    }

    private void eliminar() {
        int id = obtenerIdSeleccionado(0);
        Repuesto repuesto = tienda.buscarRepuestoPorId(id);
        if (repuesto == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un repuesto de la tabla para eliminar.");
            return;
        }
        if (TemaUI.confirmar(this, "¿Está seguro de eliminar '" + repuesto.getNombreRepuesto() + "' permanentemente?")) {
            tienda.eliminarRepuesto(id);
            TemaUI.mostrarExito(this, "Repuesto retirado del sistema exitosamente.");
            limpiarFormulario();
            refrescarTabla();
        }
    }

    private void limpiarFormulario() {
        campoNombre.setText("");
        campoDescripcion.setText("");
        spinnerStock.setValue(0);
        spinnerCosto.setValue(0.0);
        tabla.clearSelection();
    }
}