package Interfaz;

import Dominio.Cliente;
import Dominio.Tecnico;
import Util.Validador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Diálogo modal de inicio de sesión exclusivo para Administrador.
 *
 * @author Jhoel
 */
public class GestionarInicioSesion extends JDialog {

    // 🔥 Credenciales exclusivas del Administrador 🔥
    private static final String EMAIL_ADMIN = "admin@uce.com";
    private static final String PASSWORD_ADMIN = "Admin123";

    public static String rolActual = "";

    // Se mantienen estas variables en null para que MenuPrincipal no marque error
    public static Cliente clienteActual = null;
    public static Tecnico tecnicoActual = null;

    private boolean autenticado = false;

    private final JTextField campoEmail = TemaUI.crearCampoTexto();
    private final JPasswordField campoPassword = new JPasswordField();

    private GestionarInicioSesion(Frame padre) {
        super(padre, "🔐 Iniciar Sesión - Sistema de Gestión de Tickets", true);
        construirUI();
        setSize(420, 380); // 👈 Altura ajustada a 380 para que entre el nuevo botón cómodamente
        setLocationRelativeTo(padre);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /** Muestra el diálogo de login y devuelve true si la autenticación fue exitosa. */
    public static boolean mostrarLogin(Frame padre) {
        GestionarInicioSesion dialogo = new GestionarInicioSesion(padre);
        dialogo.setVisible(true);
        return dialogo.autenticado;
    }

    private void construirUI() {
        JPanel panel = new JPanel(new BorderLayout(10, 15));
        panel.setBorder(new EmptyBorder(25, 30, 25, 30));
        panel.setBackground(TemaUI.FONDO_PRINCIPAL);
        panel.setOpaque(true);

        JLabel titulo = TemaUI.crearTitulo("✨ Bienvenido Administrador");
        panel.add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(TemaUI.FONDO_PRINCIPAL);
        form.setOpaque(true);

        // Configuración exacta para que el cuadro del Email respire
        JLabel lblEmail = TemaUI.crearEtiqueta("Email:");
        lblEmail.setAlignmentX(Component.LEFT_ALIGNMENT);

        campoEmail.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        campoEmail.setPreferredSize(new Dimension(300, 38));
        campoEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 85, 95)),
                new EmptyBorder(8, 10, 8, 10)));

        // Configuración exacta para el Password
        JLabel lblPass = TemaUI.crearEtiqueta("Password:");
        lblPass.setAlignmentX(Component.LEFT_ALIGNMENT);

        campoPassword.setFont(TemaUI.FUENTE_TEXTO);
        campoPassword.setBackground(TemaUI.FONDO_SECUNDARIO);
        campoPassword.setForeground(TemaUI.BLANCO);
        campoPassword.setCaretColor(TemaUI.BLANCO);
        campoPassword.setAlignmentX(Component.LEFT_ALIGNMENT);
        campoPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        campoPassword.setPreferredSize(new Dimension(300, 38));
        campoPassword.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 85, 95)),
                new EmptyBorder(8, 10, 8, 10)));

        // 🔥 NUEVO: Casilla para visualizar la contraseña 🔥
        JCheckBox chkMostrarPass = new JCheckBox("👁 Visualizar contraseña");
        chkMostrarPass.setFont(TemaUI.FUENTE_TEXTO);
        chkMostrarPass.setBackground(TemaUI.FONDO_PRINCIPAL);
        chkMostrarPass.setForeground(TemaUI.TEXTO_CLARO);
        chkMostrarPass.setFocusPainted(false);
        chkMostrarPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        chkMostrarPass.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Guardamos el símbolo original (los puntitos) para volver a ponerlos si desmarca la casilla
        char defaultEchoChar = campoPassword.getEchoChar();

        chkMostrarPass.addActionListener(e -> {
            if (chkMostrarPass.isSelected()) {
                campoPassword.setEchoChar((char) 0); // (char) 0 hace que el texto se vuelva visible
            } else {
                campoPassword.setEchoChar(defaultEchoChar); // Vuelve a poner los puntitos
            }
        });

        // Armamos el formulario con espacios (struts) fijos
        form.add(lblEmail);
        form.add(Box.createVerticalStrut(5));
        form.add(campoEmail);
        form.add(Box.createVerticalStrut(15));
        form.add(lblPass);
        form.add(Box.createVerticalStrut(5));
        form.add(campoPassword);
        form.add(Box.createVerticalStrut(8)); // Espacio entre el cuadro de password y la casilla
        form.add(chkMostrarPass); // Agregamos la casilla de verificación

        form.setBorder(new EmptyBorder(10, 0, 15, 0));
        panel.add(form, BorderLayout.CENTER);

        JPanel botones = new JPanel(new GridLayout(1, 1, 6, 6));
        botones.setBackground(TemaUI.FONDO_PRINCIPAL);
        botones.setOpaque(true);

        JButton btnLogin = TemaUI.crearBoton("🔓 Iniciar sesión", TemaUI.AZUL);
        botones.add(btnLogin);
        panel.add(botones, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> intentarLogin());

        setContentPane(panel);
    }

    private void intentarLogin() {
        String email = campoEmail.getText().trim();
        String password = new String(campoPassword.getPassword()).trim();

        if (!Validador.validarEmail(email) || !Validador.validarPassword(password)) {
            TemaUI.mostrarError(this, "Formato de email o password incorrecto.");
            return;
        }

        if (email.equals(EMAIL_ADMIN) && password.equals(PASSWORD_ADMIN)) {
            rolActual = "SESION_ACTIVA";
            limpiarSesion();
            autenticado = true;
            TemaUI.mostrarExito(this, "¡Acceso concedido al sistema principal!");
            dispose();
        } else {
            TemaUI.mostrarError(this, "Credenciales incorrectas. Solo el administrador tiene acceso.");
        }
    }

    public static void limpiarSesion() {
        clienteActual = null;
        tecnicoActual = null;
    }
}