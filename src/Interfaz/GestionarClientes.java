package Interfaz;

import Dominio.Cliente;
import Dominio.Ticket;
import Dominio.Tienda;
import Util.Validador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Panel de gestión de clientes.
 * @author Jhoel
 */
public class GestionarClientes extends PanelGestionBase {

    private static final Tienda tienda = Tienda.getInstancia();

    static {
        if (tienda.getClientesRegistrados().length == 0) {
            tienda.agregarCliente(new Cliente(1, "Juan Perez", "juan@gmail.com", "Juan123", "CLI-1", "0999999999", "Quito", new Ticket[0]));
        }
    }

    private final JTextField campoNombre = TemaUI.crearCampoTexto();
    private final JTextField campoEmail = TemaUI.crearCampoTexto();
    private final JTextField campoTelefono = TemaUI.crearCampoTexto();
    private final JTextField campoDireccion = TemaUI.crearCampoTexto();
    private final JTextField campoPassword = TemaUI.crearCampoTexto();

    public GestionarClientes() {
        super("👥 Gestión de Clientes", new String[]{"ID", "Código", "Nombre", "Email", "Teléfono", "Dirección"});

        add(construirFormulario(), BorderLayout.EAST);
        construirBotones();
        refrescarTabla();

        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccionEnFormulario());
    }

    private JPanel construirFormulario() {
        JPanel form = new JPanel(new GridLayout(0, 1, 4, 8));

        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TemaUI.AZUL_OSCURO), "Datos del cliente",
                TitledBorder.LEFT, TitledBorder.TOP, TemaUI.FUENTE_SUBTITULO, TemaUI.TEXTO_CLARO);

        form.setBorder(BorderFactory.createCompoundBorder(borde, new EmptyBorder(10, 10, 10, 10)));
        form.setPreferredSize(new Dimension(260, 0));
        form.setBackground(TemaUI.FONDO_PRINCIPAL);

        estilizarCampo(campoNombre);
        estilizarCampo(campoEmail);
        estilizarCampo(campoTelefono);
        estilizarCampo(campoDireccion);
        estilizarCampo(campoPassword);

        form.add(crearEtiquetaClara("Nombre completo:"));
        form.add(campoNombre);
        form.add(crearEtiquetaClara("Email:"));
        form.add(campoEmail);
        form.add(crearEtiquetaClara("Teléfono:"));
        form.add(campoTelefono);
        form.add(crearEtiquetaClara("Dirección:"));
        form.add(campoDireccion);
        form.add(crearEtiquetaClara("Password:"));
        form.add(campoPassword);
        return form;
    }

    private JLabel crearEtiquetaClara(String texto) {
        JLabel etiqueta = TemaUI.crearEtiqueta(texto);
        etiqueta.setForeground(TemaUI.TEXTO_CLARO);
        etiqueta.setFont(TemaUI.FUENTE_TEXTO);
        return etiqueta;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setBackground(TemaUI.FONDO_SECUNDARIO);
        campo.setForeground(TemaUI.BLANCO);
        campo.setCaretColor(TemaUI.BLANCO);
        campo.setFont(TemaUI.FUENTE_TEXTO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(60, 65, 75)),
                BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
    }

    private void construirBotones() {
        JButton btnNuevo = TemaUI.crearBoton("➕ Registrar", TemaUI.VERDE);
        JButton btnActualizar = TemaUI.crearBoton("✏️ Actualizar", TemaUI.AZUL);
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
        for (Cliente c : tienda.getClientesRegistrados()) {
            modeloTabla.addRow(new Object[]{c.getIdUsuario(), c.getNumeroCliente(), c.getNombre(),
                    c.getEmail(), c.getTelefono(), c.getDireccion()});
        }
    }

    private void cargarSeleccionEnFormulario() {
        int id = obtenerIdSeleccionado(0);
        Cliente cliente = tienda.buscarClientePorId(id);
        if (cliente == null) return;
        campoNombre.setText(cliente.getNombre());
        campoEmail.setText(cliente.getEmail());
        campoTelefono.setText(cliente.getTelefono());
        campoDireccion.setText(cliente.getDireccion());
        campoPassword.setText("");
    }

    private void registrar() {
        String nombre = campoNombre.getText().trim();
        String email = campoEmail.getText().trim();
        String telefono = campoTelefono.getText().trim();
        String direccion = campoDireccion.getText().trim();
        String password = campoPassword.getText().trim();

        if (!Validador.validarNombrePropio(nombre)) {
            TemaUI.mostrarError(this, "Nombre inválido (Ejemplo: Juan Perez).");
            return;
        }
        if (!Validador.validarEmail(email)) {
            TemaUI.mostrarError(this, "Email inválido (Ejemplo: usuario@dominio.com).");
            return;
        }
        if (!Validador.validarTelefono(telefono)) {
            TemaUI.mostrarError(this, "Teléfono inválido (Ejemplo: 0991234567).");
            return;
        }
        if (!Validador.validarPassword(password)) {
            TemaUI.mostrarError(this, "Password inválido. Mín. 6 caracteres, 1 mayúscula y 1 número.");
            return;
        }

        // 🔥 AHORA SÍ: VALIDACIÓN EXTREMA DE NOMBRE, EMAIL Y TELÉFONO 🔥
        for (Cliente c : tienda.getClientesRegistrados()) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Ya existe un cliente registrado exactamente con el nombre: " + nombre);
                return;
            }
            if (c.getEmail().equalsIgnoreCase(email)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Este correo electrónico ya pertenece a otro cliente.");
                return;
            }
            if (c.getTelefono().equals(telefono)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ Este número de teléfono ya está registrado por otra persona.");
                return;
            }
        }

        try {
            int proximoId = tienda.getClientesRegistrados().length + 1;
            Cliente cliente = new Cliente(proximoId, nombre, email, password, "CLI-" + proximoId,
                    telefono, direccion.isEmpty() ? "Sin direccion" : direccion, new Ticket[0]);
            tienda.agregarCliente(cliente);
            TemaUI.mostrarExito(this, "¡Cliente registrado exitosamente!");
            limpiarFormulario();
            refrescarTabla();
        } catch (IllegalArgumentException ex) {
            TemaUI.mostrarError(this, ex.getMessage());
        }
    }

    private void actualizar() {
        int id = obtenerIdSeleccionado(0);
        Cliente cliente = tienda.buscarClientePorId(id);
        if (cliente == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un cliente de la tabla para actualizar.");
            return;
        }
        String telefono = campoTelefono.getText().trim();
        String direccion = campoDireccion.getText().trim();

        if (!telefono.isEmpty()) {
            if (Validador.validarTelefono(telefono)) {
                // Validación para que no robe el teléfono de otro
                for (Cliente c : tienda.getClientesRegistrados()) {
                    if (c.getIdUsuario() != id && c.getTelefono().equals(telefono)) {
                        TemaUI.mostrarAdvertencia(this, "⚠️ Este teléfono ya lo está usando otro cliente.");
                        return;
                    }
                }
                cliente.setTelefono(telefono);
            } else {
                TemaUI.mostrarAdvertencia(this, "Teléfono inválido. Se mantuvo el anterior.");
            }
        }
        if (!direccion.isEmpty()) cliente.setDireccion(direccion);

        tienda.editarCliente(cliente);
        TemaUI.mostrarExito(this, "¡Cliente actualizado!");
        limpiarFormulario();
        refrescarTabla();
    }

    private void eliminar() {
        int id = obtenerIdSeleccionado(0);
        Cliente cliente = tienda.buscarClientePorId(id);
        if (cliente == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un cliente de la tabla para eliminar.");
            return;
        }
        if (TemaUI.confirmar(this, "¿Está seguro de eliminar a '" + cliente.getNombre() + "' permanentemente?")) {
            tienda.eliminarCliente(id);
            TemaUI.mostrarExito(this, "Cliente eliminado del sistema.");
            limpiarFormulario();
            refrescarTabla();
        }
    }

    private void limpiarFormulario() {
        campoNombre.setText("");
        campoEmail.setText("");
        campoTelefono.setText("");
        campoDireccion.setText("");
        campoPassword.setText("");
        tabla.clearSelection();
    }

}