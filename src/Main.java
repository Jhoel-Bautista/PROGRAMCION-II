import Interfaz.MenuPrincipal;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                // Aplica el diseño visual de Windows
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 🔥 Abre tu menú principal directamente, sin login ni archivos 🔥
            new MenuPrincipal().setVisible(true);

        });
    }
}