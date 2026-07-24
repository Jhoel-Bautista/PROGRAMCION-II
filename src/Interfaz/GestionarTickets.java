package Interfaz;

import Dominio.*;
import Util.Validador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Date;
import java.util.Calendar;

/**
 * Panel de gestión de tickets: Muestra la comunicación de doble vía (Teléfono Cliente <-> Teléfono Técnico)
 */
public class GestionarTickets extends PanelGestionBase {

    private static final Tienda tienda = Tienda.getInstancia();

    private final JComboBox<Cliente> comboCliente = new JComboBox<>();
    private final JComboBox<Equipo> comboEquipo = new JComboBox<>();
    private final JComboBox<Tecnico> comboTecnico = new JComboBox<>();
    private final JComboBox<Estado> comboEstado = new JComboBox<>(Estado.values());
    private final JSpinner spinnerPrioridad = new JSpinner(new SpinnerNumberModel(1, 1, 3, 1));
    private final JTextArea campoProblema = new JTextArea(4, 15);

    public GestionarTickets() {
        super("🎫 Gestión de Tickets de Soporte", new String[]{"ID", "Estado", "Prioridad", "Cliente", "Equipo", "Técnico", "Problema"});

        add(construirFormulario(), BorderLayout.EAST);
        construirBotones();

        tabla.setRowHeight(65);
        // Aplicamos multilínea a Cliente (3), Equipo (4), Técnico (5) y Problema (6)
        tabla.getColumnModel().getColumn(3).setCellRenderer(new RenderMultilinea());
        tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderMultilinea());
        tabla.getColumnModel().getColumn(5).setCellRenderer(new RenderMultilinea());
        tabla.getColumnModel().getColumn(6).setCellRenderer(new RenderMultilinea());

        cargarCombos();
        refrescarTabla();

        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccionEnFormulario());
    }

    // 🔥 MAGIA ESTÉTICA: Método nuevo para alinear todas las etiquetas a la izquierda 🔥
    private JLabel crearEtiquetaAlineada(String texto) {
        JLabel label = TemaUI.crearEtiqueta(texto);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JPanel construirFormulario() {
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TemaUI.AZUL_OSCURO), "Datos del ticket",
                TitledBorder.LEFT, TitledBorder.TOP, TemaUI.FUENTE_SUBTITULO, TemaUI.TEXTO_CLARO);

        form.setBorder(BorderFactory.createCompoundBorder(borde, new EmptyBorder(10, 10, 10, 10)));
        form.setPreferredSize(new Dimension(280, 0));
        form.setBackground(TemaUI.FONDO_PRINCIPAL);

        comboCliente.setRenderer(new NombreComboRenderer());
        comboEquipo.setRenderer(new NombreComboRenderer());
        comboTecnico.setRenderer(new NombreComboRenderer());

        // 🔥 MAGIA ESTÉTICA 2: Alineamos a la izquierda y fijamos el alto máximo a 30px para que no se estiren 🔥
        comboCliente.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboCliente.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        comboEquipo.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboEquipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        comboTecnico.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboTecnico.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        spinnerPrioridad.setAlignmentX(Component.LEFT_ALIGNMENT);
        spinnerPrioridad.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        comboEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboEstado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        form.add(crearEtiquetaAlineada("Cliente:"));
        form.add(comboCliente);
        form.add(Box.createVerticalStrut(8));

        form.add(crearEtiquetaAlineada("Equipo afectado:"));
        form.add(comboEquipo);
        form.add(Box.createVerticalStrut(8));

        form.add(crearEtiquetaAlineada("Técnico asignado:"));
        form.add(comboTecnico);
        form.add(Box.createVerticalStrut(8));

        form.add(crearEtiquetaAlineada("Prioridad (1 Alta - 3 Baja):"));
        form.add(spinnerPrioridad);
        form.add(Box.createVerticalStrut(8));

        form.add(crearEtiquetaAlineada("Estado:"));
        form.add(comboEstado);
        form.add(Box.createVerticalStrut(8));

        form.add(crearEtiquetaAlineada("Problema reportado:"));

        campoProblema.setLineWrap(true);
        campoProblema.setWrapStyleWord(true);
        campoProblema.setFont(TemaUI.FUENTE_TEXTO);
        campoProblema.setBackground(TemaUI.FONDO_SECUNDARIO);
        campoProblema.setForeground(TemaUI.BLANCO);
        campoProblema.setCaretColor(TemaUI.BLANCO);

        JScrollPane scrollProblema = new JScrollPane(campoProblema);
        scrollProblema.setAlignmentX(Component.LEFT_ALIGNMENT);
        form.add(scrollProblema);

        return form;
    }

    private void construirBotones() {
        JButton btnNuevo = TemaUI.crearBoton("💾 Registrar", TemaUI.VERDE);
        JButton btnAsignar = TemaUI.crearBoton("⚙ Asignar técnico", TemaUI.AZUL);
        JButton btnEstado = TemaUI.crearBoton("✎ Actualizar estado", TemaUI.AZUL);
        JButton btnActualizarTodo = TemaUI.crearBoton("📝 Actualizar ticket", TemaUI.AZUL_OSCURO);
        JButton btnCerrar = TemaUI.crearBoton("✖ Cerrar ticket", TemaUI.ROJO);
        JButton btnLimpiar = TemaUI.crearBoton("♻ Limpiar", TemaUI.AMARILLO);

        btnNuevo.addActionListener(e -> registrar());
        btnAsignar.addActionListener(e -> asignarTecnico());
        btnEstado.addActionListener(e -> actualizarEstado());
        btnActualizarTodo.addActionListener(e -> actualizarTicket());
        btnCerrar.addActionListener(e -> cerrarTicket());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panelBotones.add(btnNuevo);
        panelBotones.add(btnAsignar);
        panelBotones.add(btnEstado);
        panelBotones.add(btnActualizarTodo);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnLimpiar);
    }

    private void cargarCombos() {
        comboCliente.removeAllItems();
        for (Cliente c : tienda.getClientesRegistrados()) comboCliente.addItem(c);

        comboEquipo.removeAllItems();
        for (Equipo eq : tienda.getEquiposRegistrados()) comboEquipo.addItem(eq);

        comboTecnico.removeAllItems();
        comboTecnico.addItem(null);
        for (Tecnico t : tienda.getTecnicosRegistrados()) comboTecnico.addItem(t);
    }

    @Override
    public void refrescarTabla() {
        cargarCombos();
        modeloTabla.setRowCount(0);
        for (Ticket t : tienda.getTicketsGestionados()) {

            // 🔥 AQUÍ SE REFLEJA EL TELÉFONO DEL CLIENTE 🔥
            String infoCliente = "Sin asignar";
            if (t.getClienteAsociado() != null) {
                infoCliente = t.getClienteAsociado().getNombre() + "\n📞 " + t.getClienteAsociado().getTelefono();
            }

            String infoEquipo = "Sin asignar";
            if (t.getEquipoAfectado() != null) {
                infoEquipo = t.getEquipoAfectado().getNumeroSerie() + "\n" + t.getEquipoAfectado().getModelo();
            }

            // 🔥 AQUÍ SE REFLEJA EL TELÉFONO DEL TÉCNICO 🔥
            String infoTecnico = "Sin asignar";
            if (t.getTecnicoAsignado() != null) {
                infoTecnico = t.getTecnicoAsignado().getNombre() + "\n📞 " + t.getTecnicoAsignado().getTelefono();
            }

            modeloTabla.addRow(new Object[]{
                    t.getIdTicket(), t.getEstadoActual(), t.getPrioridad(),
                    infoCliente, // El técnico ya puede ver a quién llamar
                    infoEquipo,
                    infoTecnico, // El cliente ya puede ver a quién llamar
                    t.getDescripcion()});
        }
    }

    private void cargarSeleccionEnFormulario() {
        int id = obtenerIdSeleccionado(0);
        Ticket ticket = tienda.buscarTicketPorId(id);
        if (ticket == null) return;
        seleccionarEnCombo(comboCliente, ticket.getClienteAsociado());
        seleccionarEnCombo(comboEquipo, ticket.getEquipoAfectado());
        seleccionarEnCombo(comboTecnico, ticket.getTecnicoAsignado());
        comboEstado.setSelectedItem(ticket.getEstadoActual());
        spinnerPrioridad.setValue(ticket.getPrioridad() >= 1 && ticket.getPrioridad() <= 3 ? ticket.getPrioridad() : 1);
        campoProblema.setText(ticket.getDescripcion());
    }

    private <T> void seleccionarEnCombo(JComboBox<T> combo, T valor) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (java.util.Objects.equals(combo.getItemAt(i), valor)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    private void registrar() {
        Cliente cliente = (Cliente) comboCliente.getSelectedItem();
        Equipo equipo = (Equipo) comboEquipo.getSelectedItem();
        String problema = campoProblema.getText().trim();
        int prioridad = (Integer) spinnerPrioridad.getValue();

        if (cliente == null || equipo == null) {
            TemaUI.mostrarError(this, "Debe registrar clientes y equipos primero.");
            return;
        }
        if (!Validador.validarDescripcion(problema)) {
            TemaUI.mostrarError(this, "Descripción del problema inválida (mín. 10 caracteres).");
            return;
        }

        for (Ticket t : tienda.getTicketsGestionados()) {
            if (t.getEquipoAfectado() != null && t.getEquipoAfectado().getIdEquipo() == equipo.getIdEquipo()
                    && t.getDescripcion().equalsIgnoreCase(problema)
                    && t.getEstadoActual() != Estado.CERRADO) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Este equipo ya tiene un ticket abierto por este mismo problema.");
                return;
            }
        }

        Ticket nuevo = new Ticket(0, new Date(), problema, prioridad, cliente, null, equipo, Estado.PENDIENTE);
        tienda.agregarTicket(nuevo);
        cliente.agregarTicket(nuevo);
        tienda.editarCliente(cliente);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 3);

        int idRep = tienda.getReparacionesRegistradas().length + 1;
        Reparacion reparacionAuto = new Reparacion(idRep, new Date(), cal.getTime(), problema);
        reparacionAuto.setEstadoReparacion("Pendiente de Revisión");

        reparacionAuto.setHistorialCambios("Equipo: " + equipo.getModelo());

        tienda.agregarReparacion(reparacionAuto);

        TemaUI.mostrarExito(this, "¡Ticket registrado y enviado automáticamente al área de Reparaciones!");
        limpiarFormulario();
        refrescarTabla();
    }

    private void asignarTecnico() {
        int id = obtenerIdSeleccionado(0);
        Ticket ticket = tienda.buscarTicketPorId(id);
        if (ticket == null || ticket.getEstadoActual() == Estado.CERRADO) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un ticket válido y no cerrado.");
            return;
        }
        Tecnico tecnico = (Tecnico) comboTecnico.getSelectedItem();
        if (tecnico == null) {
            TemaUI.mostrarError(this, "Seleccione un técnico válido.");
            return;
        }
        ticket.setTecnicoAsignado(tecnico);
        tecnico.asignarTicket(ticket);
        tienda.editarTicket(ticket);
        tienda.editarTecnico(tecnico);
        TemaUI.mostrarExito(this, "¡Técnico asignado al ticket exitosamente!");
        refrescarTabla();
    }

    private void actualizarEstado() {
        int id = obtenerIdSeleccionado(0);
        Ticket ticket = tienda.buscarTicketPorId(id);
        if (ticket == null || ticket.getEstadoActual() == Estado.CERRADO) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un ticket válido y no cerrado.");
            return;
        }

        Estado nuevoEstado = (Estado) comboEstado.getSelectedItem();
        ticket.setEstadoActual(nuevoEstado);
        tienda.editarTicket(ticket);

        for (Reparacion r : tienda.getReparacionesRegistradas()) {
            if (r.getDescripcionProblema().equalsIgnoreCase(ticket.getDescripcion())) {
                r.setEstadoReparacion(nuevoEstado.toString());
                tienda.editarReparacion(r);
                break;
            }
        }

        TemaUI.mostrarExito(this, "¡Estado del ticket actualizado y sincronizado con taller!");
        refrescarTabla();
    }

    private void actualizarTicket() {
        int id = obtenerIdSeleccionado(0);
        Ticket ticket = tienda.buscarTicketPorId(id);
        if (ticket == null || ticket.getEstadoActual() == Estado.CERRADO) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un ticket válido y no cerrado para modificar.");
            return;
        }

        Cliente clienteNuevo = (Cliente) comboCliente.getSelectedItem();
        Equipo equipoNuevo = (Equipo) comboEquipo.getSelectedItem();
        String problemaNuevo = campoProblema.getText().trim();
        int prioridadNueva = (Integer) spinnerPrioridad.getValue();

        if (clienteNuevo == null || equipoNuevo == null) {
            TemaUI.mostrarError(this, "Debe seleccionar un cliente y un equipo válido.");
            return;
        }
        if (!Validador.validarDescripcion(problemaNuevo)) {
            TemaUI.mostrarError(this, "Descripción del problema inválida (mín. 10 caracteres).");
            return;
        }

        String descAntigua = ticket.getDescripcion();

        ticket.setClienteAsociado(clienteNuevo);
        ticket.setEquipoAfectado(equipoNuevo);
        ticket.setPrioridad(prioridadNueva);
        ticket.setDescripcion(problemaNuevo);

        tienda.editarTicket(ticket);

        for (Reparacion r : tienda.getReparacionesRegistradas()) {
            if (r.getDescripcionProblema().equalsIgnoreCase(descAntigua)) {
                try {
                    r.setDescripcionProblema(problemaNuevo);
                    tienda.editarReparacion(r);
                } catch (Exception ex) { }
                break;
            }
        }

        TemaUI.mostrarExito(this, "¡Datos del ticket corregidos y enlazados exitosamente!");
        refrescarTabla();
    }

    private void cerrarTicket() {
        int id = obtenerIdSeleccionado(0);
        Ticket ticket = tienda.buscarTicketPorId(id);
        if (ticket == null || ticket.getEstadoActual() == Estado.CERRADO) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un ticket válido y no cerrado.");
            return;
        }

        if (TemaUI.confirmar(this, "¿Está seguro de cerrar este ticket definitivamente?")) {
            ticket.cerrarTicket();
            tienda.editarTicket(ticket);

            for (Reparacion r : tienda.getReparacionesRegistradas()) {
                if (r.getDescripcionProblema().equalsIgnoreCase(ticket.getDescripcion())) {
                    r.setEstadoReparacion("Cerrado");
                    tienda.editarReparacion(r);
                    break;
                }
            }

            TemaUI.mostrarExito(this, "¡Ticket cerrado en todas las áreas exitosamente!");
            refrescarTabla();
        }
    }

    private void limpiarFormulario() {
        campoProblema.setText("");
        spinnerPrioridad.setValue(1);
        tabla.clearSelection();
        cargarCombos();
    }

    // 🔥 Combobox para elegir y que salgan los teléfonos 🔥
    private static class NombreComboRenderer extends javax.swing.DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            String texto = "Sin asignar";
            if (value instanceof Cliente c) {
                // El cliente hereda el teléfono de Usuario
                texto = c.getNombre() + " - Telf: " + c.getTelefono();
            } else if (value instanceof Equipo eq) {
                texto = eq.getModelo() + " (" + eq.getNumeroSerie() + ")";
            } else if (value instanceof Tecnico t) {
                // El técnico hereda el teléfono de Usuario
                texto = t.getNombre() + " - Telf: " + t.getTelefono();
            }
            return super.getListCellRendererComponent(list, texto, index, isSelected, cellHasFocus);
        }
    }

    private class RenderMultilinea extends JTextArea implements javax.swing.table.TableCellRenderer {
        public RenderMultilinea() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setFont(TemaUI.FUENTE_TEXTO);
            setBorder(new EmptyBorder(5, 5, 5, 5));
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