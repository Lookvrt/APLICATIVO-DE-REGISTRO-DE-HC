
package Procesos;
import javax.swing.JOptionPane;
public class Mensajes {
    public static void M1(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }
    public static String M2(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
    public static int M3(String titulo,String mensaje){
        int resp = JOptionPane.showConfirmDialog(
                null,mensaje, titulo,JOptionPane.OK_CANCEL_OPTION);
        return resp;
    }
}
