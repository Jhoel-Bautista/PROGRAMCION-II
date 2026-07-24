package Interfaz;

import Dominio.Reparacion;
import Dominio.Tienda;
import Dominio.Ticket;
import Dominio.Estado;
import Dominio.Repuesto;
import Dominio.Diagnostico;
import Dominio.Solucion;
import Util.Validador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GestionarReparaciones extends PanelGestionBase {

    private static final Tienda tienda = Tienda.getInstancia();
    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");

    private final JTextArea campoDescripcion = new JTextArea(2, 15);
    private final JComboBox<Repuesto> comboRepuestos = new JComboBox<>();
    private final JSpinner spinnerDiasEstimados = new JSpinner(new SpinnerNumberModel(1, 0, 90, 1));
    private final JComboBox<Object> comboEstado = new JComboBox<>();
    private final JTextArea campoNota = new JTextArea(2, 15);

    public GestionarReparaciones() {
        super("🛠️ Gestión de Reparaciones", new String[]{"ID", "Estado", "F. Ingreso", "F. Entrega", "Problema", "Diagnóstico", "Solución", "Historial"});

        configurarComboEstado();
        add(construirFormulario(), BorderLayout.EAST);
        construirBotones();

        tabla.setRowHeight(90);
        tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderMultilinea());
        tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderMultilinea());
        tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderMultilinea());
        tabla.getColumnModel().getColumn(7).setCellRenderer(new RenderMultilinea());

        refrescarTabla();
        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccionEnFormulario());
    }

    private void configurarComboEstado() {
        comboEstado.addItem("Mantener estado actual");
        for (Estado e : Estado.values()) {
            comboEstado.addItem(e);
        }
    }

    private JLabel crearLabel(String texto) {
        JLabel label = TemaUI.crearEtiqueta(texto);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JPanel construirFormulario() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TemaUI.AZUL_OSCURO), "Datos de la reparación",
                TitledBorder.LEFT, TitledBorder.TOP, TemaUI.FUENTE_SUBTITULO, TemaUI.TEXTO_CLARO);

        form.setBorder(BorderFactory.createCompoundBorder(borde, new EmptyBorder(10, 10, 10, 10)));
        form.setPreferredSize(new Dimension(360, 0));
        form.setBackground(TemaUI.FONDO_PRINCIPAL);

        form.add(crearLabel("Falla reportada (Cliente):"));
        campoDescripcion.setLineWrap(true);
        campoDescripcion.setWrapStyleWord(true);
        campoDescripcion.setFont(TemaUI.FUENTE_TEXTO);
        campoDescripcion.setBackground(TemaUI.FONDO_SECUNDARIO);
        campoDescripcion.setForeground(TemaUI.BLANCO);
        JScrollPane scrollDesc = new JScrollPane(campoDescripcion);
        scrollDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(scrollDesc);
        form.add(Box.createVerticalStrut(15));

        form.add(crearLabel("Gestión Detallada (Sub-módulos):"));
        JPanel panelSubModulos = new JPanel(new GridLayout(1, 2, 10, 0));
        panelSubModulos.setBackground(TemaUI.FONDO_PRINCIPAL);
        panelSubModulos.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnGestionarDiag = TemaUI.crearBoton("🔍 Diagnóstico", TemaUI.AZUL_OSCURO);
        JButton btnGestionarSol = TemaUI.crearBoton("✅ Solución", TemaUI.VERDE);

        btnGestionarDiag.addActionListener(e -> abrirVentanaDiagnostico());
        btnGestionarSol.addActionListener(e -> abrirVentanaSolucion());

        panelSubModulos.add(btnGestionarDiag);
        panelSubModulos.add(btnGestionarSol);
        panelSubModulos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        form.add(panelSubModulos);
        form.add(Box.createVerticalStrut(15));

        form.add(crearLabel("Repuestos e Inventario:"));
        JPanel panelRepuestos = new JPanel(new BorderLayout(5, 0));
        panelRepuestos.setBackground(TemaUI.FONDO_PRINCIPAL);
        panelRepuestos.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboRepuestos.setRenderer(new RepuestoComboRenderer());

        JButton btnUsarRepuesto = TemaUI.crearBoton("🔧 Usar", TemaUI.AZUL);
        btnUsarRepuesto.addActionListener(e -> descontarRepuesto());

        panelRepuestos.add(comboRepuestos, BorderLayout.CENTER);
        panelRepuestos.add(btnUsarRepuesto, BorderLayout.EAST);
        panelRepuestos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        form.add(panelRepuestos);
        form.add(Box.createVerticalStrut(6));

        form.add(crearLabel("Días estimados (desde el ingreso):"));
        spinnerDiasEstimados.setAlignmentX(Component.LEFT_ALIGNMENT);
        spinnerDiasEstimados.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        form.add(spinnerDiasEstimados);
        form.add(Box.createVerticalStrut(6));

        form.add(crearLabel("Nuevo estado:"));
        comboEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboEstado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        form.add(comboEstado);
        form.add(Box.createVerticalStrut(6));

        form.add(crearLabel("Historial de cambios y repuestos:"));
        campoNota.setLineWrap(true);
        campoNota.setWrapStyleWord(true);
        campoNota.setFont(TemaUI.FUENTE_TEXTO);
        campoNota.setBackground(TemaUI.FONDO_SECUNDARIO);
        campoNota.setForeground(TemaUI.BLANCO);
        JScrollPane scrollNota = new JScrollPane(campoNota);
        scrollNota.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(scrollNota);

        return form;
    }

    private void construirBotones() {
        JButton btnNuevo = TemaUI.crearBoton("➕ Ingresar a taller", TemaUI.VERDE);
        JButton btnActualizar = TemaUI.crearBoton("✎ Actualizar bitácora", TemaUI.AZUL);
        JButton btnEliminar = TemaUI.crearBoton("✖ Eliminar", TemaUI.ROJO);
        JButton btnLimpiar = TemaUI.crearBoton("♻ Limpiar", TemaUI.AMARILLO);

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
        comboRepuestos.removeAllItems();
        try {
            for (Repuesto r : tienda.getRepuestosRegistrados()) {
                comboRepuestos.addItem(r);
            }
        } catch (Exception ignored) {}

        modeloTabla.setRowCount(0);
        for (Reparacion r : tienda.getReparacionesRegistradas()) {
            String fechaIn = r.getFechaInicio() != null ? FORMATO_FECHA.format(r.getFechaInicio()) : "N/A";
            String fechaEst = r.getFechaEntregaEstimada() != null ? FORMATO_FECHA.format(r.getFechaEntregaEstimada()) : "N/A";

            // 🔥 SE EXTRAEN LOS DATOS CON TUS MÉTODOS 🔥
            String infoDiag = r.getDiagnostico() != null ?
                    "F: " + FORMATO_FECHA.format(r.getDiagnostico().getFechaDiagnostico()) + "\n" + r.getDiagnostico().getDetalleDiagnostico() : "Sin diagnóstico";

            String infoSol = r.getSolucion() != null ?
                    "F: " + FORMATO_FECHA.format(r.getSolucion().getFechaSolucion()) + "\n" + r.getSolucion().getDetalleSolucion() : "Sin solución";

            modeloTabla.addRow(new Object[]{r.getIdReparacion(), r.getEstadoReparacion(), fechaIn, fechaEst,
                    r.getDescripcionProblema(), infoDiag, infoSol, r.getHistorialCambios()});
        }
    }

    private void cargarSeleccionEnFormulario() {
        int id = obtenerIdSeleccionado(0);
        Reparacion reparacion = tienda.buscarReparacionPorId(id);
        if (reparacion == null) return;
        campoDescripcion.setText(reparacion.getDescripcionProblema());
        comboEstado.setSelectedIndex(0);
        campoNota.setText(reparacion.getHistorialCambios().equals("Sin cambios") ? "" : reparacion.getHistorialCambios());
        try {
            long diff = reparacion.getFechaEntregaEstimada().getTime() - reparacion.getFechaInicio().getTime();
            int dias = (int) (diff / (1000 * 60 * 60 * 24));
            spinnerDiasEstimados.setValue(Math.max(dias, 1));
        } catch (Exception ex) { spinnerDiasEstimados.setValue(1); }
    }

    private void abrirVentanaDiagnostico() {
        int id = obtenerIdSeleccionado(0);
        Reparacion reparacion = tienda.buscarReparacionPorId(id);
        if (reparacion == null) {
            TemaUI.mostrarAdvertencia(this, "⚠️ Seleccione una reparación de la tabla."); return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Gestión de Diagnóstico", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(TemaUI.FONDO_PRINCIPAL);

        JTextArea areaDetalle = new JTextArea();
        areaDetalle.setLineWrap(true);
        areaDetalle.setWrapStyleWord(true);
        areaDetalle.setFont(TemaUI.FUENTE_TEXTO);

        // 🔥 LLAMA A TU MÉTODO getDetalleDiagnostico() 🔥
        if (reparacion.getDiagnostico() != null) {
            areaDetalle.setText(reparacion.getDiagnostico().getDetalleDiagnostico());
        }

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(new EmptyBorder(10,10,10,10));
        panelCentro.setBackground(TemaUI.FONDO_PRINCIPAL);
        panelCentro.add(crearLabel("Detalles del diagnóstico técnico (Mín. 10 caracteres):"), BorderLayout.NORTH);
        panelCentro.add(new JScrollPane(areaDetalle), BorderLayout.CENTER);

        JPanel panelBotonesDialog = new JPanel();
        panelBotonesDialog.setBackground(TemaUI.FONDO_PRINCIPAL);

        JButton btnGuardar = TemaUI.crearBoton("💾 Guardar", TemaUI.AZUL);
        JButton btnEliminar = TemaUI.crearBoton("🗑️ Borrar", TemaUI.ROJO);

        btnGuardar.addActionListener(e -> {
            String texto = areaDetalle.getText().trim();
            if (texto.length() < 10) {
                TemaUI.mostrarError(dialog, "⚠️ Detalle muy corto. Escriba un diagnóstico real."); return;
            }
            if (reparacion.getDiagnostico() != null) {
                reparacion.getDiagnostico().setDetalleDiagnostico(texto);
            } else {
                int idNuevo = Integer.parseInt(reparacion.getIdReparacion() + "01");
                // 🔥 LLAMA A TU CONSTRUCTOR 🔥
                reparacion.setDiagnostico(new Diagnostico(idNuevo, new Date(), texto));
            }
            tienda.editarReparacion(reparacion);
            TemaUI.mostrarExito(dialog, "¡Diagnóstico guardado exitosamente!");
            refrescarTabla();
            dialog.dispose();
        });

        btnEliminar.addActionListener(e -> {
            if (reparacion.getDiagnostico() == null) return;
            if (TemaUI.confirmar(dialog, "¿Seguro que desea ELIMINAR el diagnóstico?")) {
                reparacion.setDiagnostico(null);
                tienda.editarReparacion(reparacion);
                TemaUI.mostrarExito(dialog, "¡Diagnóstico eliminado!");
                refrescarTabla();
                dialog.dispose();
            }
        });

        panelBotonesDialog.add(btnGuardar);
        panelBotonesDialog.add(btnEliminar);
        dialog.add(panelCentro, BorderLayout.CENTER);
        dialog.add(panelBotonesDialog, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void abrirVentanaSolucion() {
        int id = obtenerIdSeleccionado(0);
        Reparacion reparacion = tienda.buscarReparacionPorId(id);
        if (reparacion == null) {
            TemaUI.mostrarAdvertencia(this, "⚠️ Seleccione una reparación de la tabla."); return;
        }

        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Gestión de Solución", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.getContentPane().setBackground(TemaUI.FONDO_PRINCIPAL);

        JTextArea areaDetalle = new JTextArea();
        areaDetalle.setLineWrap(true);
        areaDetalle.setWrapStyleWord(true);
        areaDetalle.setFont(TemaUI.FUENTE_TEXTO);

        // 🔥 LLAMA A TU MÉTODO getDetalleSolucion() 🔥
        if (reparacion.getSolucion() != null) {
            areaDetalle.setText(reparacion.getSolucion().getDetalleSolucion());
        }

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(new EmptyBorder(10,10,10,10));
        panelCentro.setBackground(TemaUI.FONDO_PRINCIPAL);
        panelCentro.add(crearLabel("Detalles de la solución aplicada (Mín. 10 caracteres):"), BorderLayout.NORTH);
        panelCentro.add(new JScrollPane(areaDetalle), BorderLayout.CENTER);

        JPanel panelBotonesDialog = new JPanel();
        panelBotonesDialog.setBackground(TemaUI.FONDO_PRINCIPAL);

        JButton btnGuardar = TemaUI.crearBoton("💾 Guardar", TemaUI.VERDE);
        JButton btnEliminar = TemaUI.crearBoton("🗑️ Borrar", TemaUI.ROJO);

        btnGuardar.addActionListener(e -> {
            String texto = areaDetalle.getText().trim();
            if (texto.length() < 10) {
                TemaUI.mostrarError(dialog, "⚠️ Detalle muy corto. Escriba una solución real."); return;
            }
            if (reparacion.getSolucion() != null) {
                reparacion.getSolucion().setDetalleSolucion(texto);
            } else {
                int idNuevo = Integer.parseInt(reparacion.getIdReparacion() + "02");
                // 🔥 LLAMA A TU CONSTRUCTOR 🔥
                reparacion.setSolucion(new Solucion(idNuevo, new Date(), texto));
            }
            tienda.editarReparacion(reparacion);
            TemaUI.mostrarExito(dialog, "¡Solución guardada exitosamente!");
            refrescarTabla();
            dialog.dispose();
        });

        btnEliminar.addActionListener(e -> {
            if (reparacion.getSolucion() == null) return;
            if (TemaUI.confirmar(dialog, "¿Seguro que desea ELIMINAR la solución?")) {
                reparacion.setSolucion(null);
                tienda.editarReparacion(reparacion);
                TemaUI.mostrarExito(dialog, "¡Solución eliminada!");
                refrescarTabla();
                dialog.dispose();
            }
        });

        panelBotonesDialog.add(btnGuardar);
        panelBotonesDialog.add(btnEliminar);
        dialog.add(panelCentro, BorderLayout.CENTER);
        dialog.add(panelBotonesDialog, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void descontarRepuesto() {
        int id = obtenerIdSeleccionado(0);
        Reparacion reparacion = tienda.buscarReparacionPorId(id);
        if (reparacion == null) {
            TemaUI.mostrarAdvertencia(this, "Debe seleccionar una reparación primero."); return;
        }
        Repuesto repuesto = (Repuesto) comboRepuestos.getSelectedItem();
        if (repuesto == null || repuesto.getStockDisponible() <= 0) {
            TemaUI.mostrarError(this, "Inventario agotado o no seleccionado."); return;
        }
        if (TemaUI.confirmar(this, "¿Usar 1x [" + repuesto.getNombreRepuesto() + "]?")) {
            repuesto.setStockDisponible(repuesto.getStockDisponible() - 1);
            tienda.editarRepuesto(repuesto);
            String nota = campoNota.getText().trim();
            campoNota.setText(nota + "\n• Repuesto instalado: " + repuesto.getNombreRepuesto());
            reparacion.setHistorialCambios(campoNota.getText().trim());
            tienda.editarReparacion(reparacion);
            TemaUI.mostrarExito(this, "¡Repuesto asignado!");
            refrescarTabla();
        }
    }

    private void registrar() {
        String descripcion = campoDescripcion.getText().trim();
        int diasEstimados = (Integer) spinnerDiasEstimados.getValue();

        if (!Validador.validarDescripcion(descripcion)) {
            TemaUI.mostrarError(this, "Descripción muy corta (mín. 10 caracteres)."); return;
        }
        for (Reparacion r : tienda.getReparacionesRegistradas()) {
            if (r.getDescripcionProblema().equalsIgnoreCase(descripcion)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Ya existe una reparación con esta descripción."); return;
            }
        }

        Date fechaInicio = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.DAY_OF_MONTH, diasEstimados);
        Date fechaEntrega = calendar.getTime();

        int id = tienda.getReparacionesRegistradas().length + 1;
        Reparacion nueva = new Reparacion(id, fechaInicio, fechaEntrega, descripcion);
        tienda.agregarReparacion(nueva);

        TemaUI.mostrarExito(this, "¡Ingreso a taller registrado! (Use los botones para añadir diagnóstico).");
        limpiarFormulario();
        refrescarTabla();
    }

    private void actualizar() {
        int id = obtenerIdSeleccionado(0);
        Reparacion reparacion = tienda.buscarReparacionPorId(id);
        if (reparacion == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione una reparación."); return;
        }

        int diasNuevos = (Integer) spinnerDiasEstimados.getValue();
        Calendar cal = Calendar.getInstance();
        cal.setTime(reparacion.getFechaInicio());
        cal.add(Calendar.DAY_OF_MONTH, diasNuevos);
        reparacion.setFechaEntregaEstimada(cal.getTime());

        Object estadoSeleccionado = comboEstado.getSelectedItem();
        if (estadoSeleccionado instanceof Estado nuevoEstadoOficial) {
            reparacion.setEstadoReparacion(nuevoEstadoOficial.toString());
            for (Ticket t : tienda.getTicketsGestionados()) {
                if (t.getDescripcion().trim().equalsIgnoreCase(reparacion.getDescripcionProblema().trim())) {
                    t.setEstadoActual(nuevoEstadoOficial);
                    tienda.editarTicket(t);
                    break;
                }
            }
        }

        String historialEditado = campoNota.getText().trim();
        reparacion.setHistorialCambios(historialEditado.isEmpty() ? "Sin cambios" : historialEditado);

        tienda.editarReparacion(reparacion);
        TemaUI.mostrarExito(this, "¡Bitácora actualizada exitosamente!");
        limpiarFormulario();
        refrescarTabla();
    }

    private void eliminar() {
        int id = obtenerIdSeleccionado(0);
        Reparacion reparacion = tienda.buscarReparacionPorId(id);
        if (reparacion == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione una reparación."); return;
        }
        if (TemaUI.confirmar(this, "¿Eliminar esta reparación?\n(Se cerrará el ticket y se borrarán diagnóstico y solución ligados).")) {
            for (Ticket t : tienda.getTicketsGestionados()) {
                if (t.getDescripcion().trim().equalsIgnoreCase(reparacion.getDescripcionProblema().trim())) {
                    t.setEstadoActual(Estado.CERRADO);
                    tienda.editarTicket(t);
                    break;
                }
            }
            tienda.eliminarReparacion(id);
            TemaUI.mostrarExito(this, "¡Reparación y dependencias eliminadas!");
            limpiarFormulario();
            refrescarTabla();
        }
    }

    private void limpiarFormulario() {
        campoDescripcion.setText("");
        spinnerDiasEstimados.setValue(1);
        comboEstado.setSelectedIndex(0);
        campoNota.setText("");
        tabla.clearSelection();
    }

    private static class RepuestoComboRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            String texto = "Inventario vacío";
            if (value instanceof Repuesto r) { texto = r.getNombreRepuesto() + " (Stock: " + r.getStockDisponible() + ")"; }
            return super.getListCellRendererComponent(list, texto, index, isSelected, cellHasFocus);
        }
    }

    private class RenderMultilinea extends JTextArea implements javax.swing.table.TableCellRenderer {
        public RenderMultilinea() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setFont(TemaUI.FUENTE_TEXTO);
            setBorder(new EmptyBorder(8, 8, 8, 8));
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            if (isSelected) {
                setBackground(TemaUI.AZUL);
                setForeground(TemaUI.BLANCO);
            } else {
                setBackground(TemaUI.FONDO_SECUNDARIO);
                setForeground(TemaUI.TEXTO_CLARO);
            }
            return this;
        }
    }
}