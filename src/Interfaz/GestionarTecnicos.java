package Interfaz;

import Dominio.Tecnico;
import Dominio.Tienda;
import Dominio.Ticket;
import Util.Validador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Panel de gestión de personal técnico.
 * @author Jhoel
 */
public class GestionarTecnicos extends PanelGestionBase {

    private static final Tienda tienda = Tienda.getInstancia();

    private final JTextField campoNombre = TemaUI.crearCampoTexto();
    private final JTextField campoEmail = TemaUI.crearCampoTexto();
    private final JTextField campoPassword = TemaUI.crearCampoTexto();
    private final JTextField campoTelefono = TemaUI.crearCampoTexto();
    private final JTextField campoEspecialidad = TemaUI.crearCampoTexto();

    public GestionarTecnicos() {
        super("🧑‍🔧 Gestión de Personal Técnico", new String[]{"ID", "Código", "Nombre", "Email", "Teléfono", "Especialidad"});

        add(construirFormulario(), BorderLayout.EAST);
        construirBotones();
        refrescarTabla();

        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccionEnFormulario());
    }

    private JPanel construirFormulario() {
        JPanel form = new JPanel(new GridLayout(0, 1, 4, 8));

        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TemaUI.AZUL_OSCURO), "Datos del técnico",
                TitledBorder.LEFT, TitledBorder.TOP, TemaUI.FUENTE_SUBTITULO, TemaUI.TEXTO_CLARO);

        form.setBorder(BorderFactory.createCompoundBorder(borde, new EmptyBorder(10, 10, 10, 10)));
        form.setPreferredSize(new Dimension(260, 0));
        form.setBackground(TemaUI.FONDO_PRINCIPAL);
        form.setOpaque(true);

        form.add(TemaUI.crearEtiqueta("Nombre completo:"));
        form.add(campoNombre);
        form.add(TemaUI.crearEtiqueta("Email:"));
        form.add(campoEmail);
        form.add(TemaUI.crearEtiqueta("Password:"));
        form.add(campoPassword);

        form.add(TemaUI.crearEtiqueta("Teléfono de contacto:"));
        form.add(campoTelefono);

        form.add(TemaUI.crearEtiqueta("Especialidad:"));
        form.add(campoEspecialidad);

        return form;
    }

    private void construirBotones() {
        JButton btnNuevo = TemaUI.crearBoton("➕ Contratar", TemaUI.VERDE);
        JButton btnActualizar = TemaUI.crearBoton("✏️ Actualizar", TemaUI.AZUL);
        JButton btnEliminar = TemaUI.crearBoton("🗑️ Dar de baja", TemaUI.ROJO);
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
        for (Tecnico t : tienda.getTecnicosRegistrados()) {
            modeloTabla.addRow(new Object[]{t.getIdUsuario(), t.getCodigoTecnico(), t.getNombre(),
                    t.getEmail(), t.getTelefono(), t.getEspecialidad()});
        }
    }

    private void cargarSeleccionEnFormulario() {
        int id = obtenerIdSeleccionado(0);
        Tecnico tecnico = tienda.buscarTecnicoPorId(id);
        if (tecnico == null) return;
        campoNombre.setText(tecnico.getNombre());
        campoEmail.setText(tecnico.getEmail());
        campoPassword.setText("");
        campoTelefono.setText(tecnico.getTelefono());
        campoEspecialidad.setText(tecnico.getEspecialidad());
    }

    private void registrar() {
        String nombre = campoNombre.getText().trim();
        String email = campoEmail.getText().trim();
        String password = campoPassword.getText().trim();
        String telefono = campoTelefono.getText().trim();
        String especialidad = campoEspecialidad.getText().trim();

        // 🔥 VALIDACIÓN ESTRICTA DE MAYÚSCULAS Y MINÚSCULAS 🔥
        if (!nombre.matches("^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$")) {
            TemaUI.mostrarError(this, "⚠️ Nombre inválido. Cada palabra debe iniciar con MAYÚSCULA seguida de minúsculas (Ej: Jhoel Bautista). No se admiten números ni símbolos.");
            return;
        }

        if (!email.matches("^[a-zA-Z0-9._]{5,}@(gmail\\.com|hotmail\\.com|outlook\\.com)$")) {
            TemaUI.mostrarError(this, "⚠️ Email inválido. Use un correo real de @gmail.com, @hotmail.com o @outlook.com (Mínimo 5 caracteres antes del @).");
            return;
        }

        if (!Validador.validarPassword(password)) {
            TemaUI.mostrarError(this, "⚠️ Password inválido. Mínimo 6 caracteres, 1 mayúscula y 1 número.");
            return;
        }

        if (!telefono.matches("^09\\d{8}$")) {
            TemaUI.mostrarError(this, "⚠️ Teléfono inválido. Debe ser un celular ecuatoriano válido de exactamente 10 dígitos (Ej: 0987654321).");
            return;
        }

        if (especialidad.length() < 10) {
            TemaUI.mostrarError(this, "⚠️ Especialidad inválida. Debe escribir una descripción real de al menos 10 caracteres.");
            return;
        }

        for (Tecnico t : tienda.getTecnicosRegistrados()) {
            if (t.getEmail().equalsIgnoreCase(email)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Este email ya está asignado a otro técnico del sistema.");
                return;
            }
            if (t.getNombre().equalsIgnoreCase(nombre)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Ya existe un técnico registrado con ese mismo nombre exacto.");
                return;
            }
            if (t.getTelefono() != null && t.getTelefono().equals(telefono)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Ese número de teléfono ya pertenece a otro técnico.");
                return;
            }
        }

        int proximoId = tienda.getTecnicosRegistrados().length + 1;
        String codigo = "EMP-00" + proximoId;

        tienda.agregarTecnico(new Tecnico(proximoId, nombre, email, password, telefono, codigo, especialidad, 1));

        TemaUI.mostrarExito(this, "¡Técnico registrado exitosamente!");
        limpiarFormulario();
        refrescarTabla();
    }

    private void actualizar() {
        int id = obtenerIdSeleccionado(0);
        Tecnico tecnico = tienda.buscarTecnicoPorId(id);
        if (tecnico == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un técnico de la tabla para actualizar.");
            return;
        }

        String nombre = campoNombre.getText().trim();
        String email = campoEmail.getText().trim();
        String password = campoPassword.getText().trim();
        String telefono = campoTelefono.getText().trim();
        String especialidad = campoEspecialidad.getText().trim();

        // 🔥 VALIDACIÓN ESTRICTA DE MAYÚSCULAS Y MINÚSCULAS AL ACTUALIZAR 🔥
        if (!nombre.matches("^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)(\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$")) {
            TemaUI.mostrarError(this, "⚠️ Nombre inválido. Cada palabra debe iniciar con MAYÚSCULA seguida de minúsculas (Ej: Jhoel Bautista). No se admiten números ni símbolos.");
            return;
        }

        if (!email.matches("^[a-zA-Z0-9._]{5,}@(gmail\\.com|hotmail\\.com|outlook\\.com)$")) {
            TemaUI.mostrarError(this, "⚠️ Email inválido. Use un correo real de @gmail.com, @hotmail.com o @outlook.com (Mínimo 5 caracteres antes del @).");
            return;
        }

        if (!password.isEmpty() && !Validador.validarPassword(password)) {
            TemaUI.mostrarError(this, "⚠️ Password nuevo inválido. Mínimo 6 caracteres, 1 mayúscula y 1 número.");
            return;
        }

        if (!telefono.matches("^09\\d{8}$")) {
            TemaUI.mostrarError(this, "⚠️ Teléfono inválido. Debe ser un celular ecuatoriano válido de exactamente 10 dígitos (Ej: 0987654321).");
            return;
        }

        if (especialidad.length() < 10) {
            TemaUI.mostrarError(this, "⚠️ Especialidad inválida. Debe escribir una descripción real de al menos 10 caracteres.");
            return;
        }

        for (Tecnico t : tienda.getTecnicosRegistrados()) {
            if (t.getIdUsuario() != id) {
                if (t.getEmail().equalsIgnoreCase(email)) {
                    TemaUI.mostrarAdvertencia(this, "⚠️ Este email ya está ocupado por otro usuario en el sistema.");
                    return;
                }
                if (t.getNombre().equalsIgnoreCase(nombre)) {
                    TemaUI.mostrarAdvertencia(this, "⚠️ Ya existe otro técnico registrado con ese nombre exacto.");
                    return;
                }
                if (t.getTelefono() != null && t.getTelefono().equals(telefono)) {
                    TemaUI.mostrarAdvertencia(this, "⚠️ Ese número de teléfono ya está ocupado por otro técnico.");
                    return;
                }
            }
        }

        tecnico.setNombre(nombre);
        tecnico.setEmail(email);
        if (!password.isEmpty()) {
            tecnico.setPassword(password);
        }
        tecnico.setTelefono(telefono);
        tecnico.setEspecialidad(especialidad);

        tienda.editarTecnico(tecnico);
        TemaUI.mostrarExito(this, "¡Todos los datos del técnico fueron actualizados y validados exitosamente!");
        limpiarFormulario();
        refrescarTabla();
    }

    private void eliminar() {
        int id = obtenerIdSeleccionado(0);
        Tecnico tecnico = tienda.buscarTecnicoPorId(id);
        if (tecnico == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un técnico de la tabla para eliminar.");
            return;
        }

        if (TemaUI.confirmar(this, "¿Está seguro de dar de baja a '" + tecnico.getNombre() + "'?\nCualquier ticket asignado a él quedará 'Sin asignar'.")) {

            for (Ticket t : tienda.getTicketsGestionados()) {
                if (t.getTecnicoAsignado() != null && t.getTecnicoAsignado().getIdUsuario() == id) {
                    t.setTecnicoAsignado(null);
                    tienda.editarTicket(t);
                }
            }

            tienda.eliminarTecnico(id);
            TemaUI.mostrarExito(this, "Técnico eliminado y sistema de tickets sincronizado a la perfección.");
            limpiarFormulario();
            refrescarTabla();
        }
    }

    private void limpiarFormulario() {
        campoNombre.setText("");
        campoEmail.setText("");
        campoPassword.setText("");
        campoTelefono.setText("");
        campoEspecialidad.setText("");
        tabla.clearSelection();
    }
}