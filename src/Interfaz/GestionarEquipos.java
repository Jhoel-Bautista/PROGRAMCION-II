package Interfaz;

import Dominio.Equipo;
import Dominio.Tienda;
import Util.Validador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Panel de gestión de equipos.
 * @author Jhoel
 */
public class GestionarEquipos extends PanelGestionBase {

    private static final Tienda tienda = Tienda.getInstancia();

    static {
        if (tienda.getEquiposRegistrados().length == 0) {
            tienda.agregarEquipo(new Equipo(1, "SN-98765", "Dell Inspiron 15", "Laptop"));
        }
    }

    private final JTextField campoSerie = TemaUI.crearCampoTexto();
    private final JTextField campoModelo = TemaUI.crearCampoTexto();
    private final JTextField campoTipo = TemaUI.crearCampoTexto();

    public GestionarEquipos() {
        super("💻 Gestión de Equipos", new String[]{"ID", "Número de Serie", "Modelo", "Tipo"});

        add(construirFormulario(), BorderLayout.EAST);
        construirBotones();
        refrescarTabla();

        tabla.getSelectionModel().addListSelectionListener(e -> cargarSeleccionEnFormulario());
    }

    private JPanel construirFormulario() {
        JPanel form = new JPanel(new GridLayout(0, 1, 4, 8));

        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(TemaUI.AZUL_OSCURO), "Datos del equipo",
                TitledBorder.LEFT, TitledBorder.TOP, TemaUI.FUENTE_SUBTITULO, TemaUI.TEXTO_CLARO);

        form.setBorder(BorderFactory.createCompoundBorder(borde, new EmptyBorder(10, 10, 10, 10)));
        form.setPreferredSize(new Dimension(260, 0));
        form.setBackground(TemaUI.FONDO_PRINCIPAL);

        form.add(TemaUI.crearEtiqueta("Número de serie:"));
        form.add(campoSerie);
        form.add(TemaUI.crearEtiqueta("Modelo:"));
        form.add(campoModelo);
        form.add(TemaUI.crearEtiqueta("Tipo de equipo:"));
        form.add(campoTipo);
        return form;
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
        for (Equipo equipo : tienda.getEquiposRegistrados()) {
            modeloTabla.addRow(new Object[]{equipo.getIdEquipo(), equipo.getNumeroSerie(),
                    equipo.getModelo(), equipo.getTipoEquipo()});
        }
    }

    private void cargarSeleccionEnFormulario() {
        int id = obtenerIdSeleccionado(0);
        Equipo equipo = tienda.buscarEquipoPorId(id);
        if (equipo == null) return;
        campoSerie.setText(equipo.getNumeroSerie());
        campoModelo.setText(equipo.getModelo());
        campoTipo.setText(equipo.getTipoEquipo());
    }

    private void registrar() {
        String serie = campoSerie.getText().trim();
        String modelo = campoModelo.getText().trim();
        String tipo = campoTipo.getText().trim();

        if (!Validador.validarNumeroSerie(serie)) {
            TemaUI.mostrarError(this, "Serie inválida. Use entre 5 y 20 caracteres (Ejemplo: SN-98765).");
            return;
        }
        if (modelo.length() < 2) {
            TemaUI.mostrarError(this, "Modelo muy corto o inválido.");
            return;
        }
        if (tipo.length() < 3) {
            TemaUI.mostrarError(this, "Tipo de equipo muy corto o inválido.");
            return;
        }

        // 🔥 VALIDACIÓN EXTREMA DE DUPLICADOS (Número de Serie) 🔥
        for (Equipo e : tienda.getEquiposRegistrados()) {
            if (e.getNumeroSerie().equalsIgnoreCase(serie)) {
                TemaUI.mostrarAdvertencia(this, "⚠️ ATENCIÓN: El número de serie '" + serie + "' ya pertenece a otro equipo.");
                return;
            }
        }

        int id = tienda.getEquiposRegistrados().length + 1;
        tienda.agregarEquipo(new Equipo(id, serie, modelo, tipo));
        TemaUI.mostrarExito(this, "¡Equipo registrado exitosamente!");
        limpiarFormulario();
        refrescarTabla();
    }

    private void actualizar() {
        int id = obtenerIdSeleccionado(0);
        Equipo equipo = tienda.buscarEquipoPorId(id);
        if (equipo == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un equipo de la tabla para actualizar.");
            return;
        }
        String modelo = campoModelo.getText().trim();
        String tipo = campoTipo.getText().trim();
        if (!modelo.isEmpty()) equipo.setModelo(modelo);
        if (!tipo.isEmpty()) equipo.setTipoEquipo(tipo);

        tienda.editarEquipo(equipo);
        TemaUI.mostrarExito(this, "¡Equipo actualizado correctamente!");
        limpiarFormulario();
        refrescarTabla();
    }

    private void eliminar() {
        int id = obtenerIdSeleccionado(0);
        Equipo equipo = tienda.buscarEquipoPorId(id);
        if (equipo == null) {
            TemaUI.mostrarAdvertencia(this, "Seleccione un equipo de la tabla para eliminar.");
            return;
        }
        if (TemaUI.confirmar(this, "¿Está seguro de eliminar el equipo '" + equipo.getModelo() + "'?")) {
            tienda.eliminarEquipo(id);
            TemaUI.mostrarExito(this, "Equipo eliminado del sistema.");
            limpiarFormulario();
            refrescarTabla();
        }
    }

    private void limpiarFormulario() {
        campoSerie.setText("");
        campoModelo.setText("");
        campoTipo.setText("");
        tabla.clearSelection();
    }
}