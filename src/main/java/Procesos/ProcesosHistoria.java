package Procesos;
import Arboles.*;
import Vista.*;
import javax.swing.table.DefaultTableModel;

public class ProcesosHistoria {
   public static void LimpiarEntradas(FrmHistoriaClinica f){
       f.txtHistoria.setText("");
       f.txtPaciente.setText("");
       f.txtDNI.setText("");
       f.txtEspecialidad.setText("");
       f.txtFecha.setText(java.time.LocalDate.now().toString()); 
       f.txtDiagnostico.setText("");
       f.txtObservaciones.setText("");
       f.txtTratamiento.setText("");
       f.txtHistoria.requestFocus();
   }
   
   public static void LimpiarTabla(DefaultTableModel modtabla){
       modtabla.setRowCount(0);
   }   
   
   public static void MostrarDatosNodo(NodoHistoria actual,FrmHistoriaClinica f1){
       f1.txtHistoria.setText(actual.getElemento().getNumeroHistoria());
       f1.txtDNI.setText(actual.getElemento().getDni());              
       f1.txtPaciente.setText(actual.getElemento().getPaciente());    
       f1.txtEspecialidad.setText(actual.getElemento().getEspecialidad());
       f1.txtFecha.setText(actual.getElemento().getFecha());
       f1.txtDiagnostico.setText(actual.getElemento().getDiagnostico());
       f1.txtObservaciones.setText(actual.getElemento().getObservaciones());
       f1.txtTratamiento.setText(actual.getElemento().getTratamiento());
       f1.txtHistoria.requestFocus();
   }
}
