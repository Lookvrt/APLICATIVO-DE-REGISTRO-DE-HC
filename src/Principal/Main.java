package Principal;
import Controlador.*;
import Vista.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1) Mostrar login (modal: bloquea hasta que se cierre)
            FrmLogin fl = new FrmLogin(null, true);
            ControlLogin cl = new ControlLogin(fl);
            fl.setLocationRelativeTo(null);
            fl.setVisible(true);

            // 2) Si el login fue exitoso, abrir el menú principal
            if (cl.isAutenticado()) {
                FrmMenu fm = new FrmMenu();
                ControlMenu cm = new ControlMenu(fm);
                fm.setTitle("BIOVITAL");
                fm.setVisible(true);
                fm.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else {
                System.exit(0);
            }
        });
    }
}