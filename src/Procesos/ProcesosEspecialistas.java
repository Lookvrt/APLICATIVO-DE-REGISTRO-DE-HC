package Procesos;

import Colas.*;
import Vista.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProcesosEspecialistas {  
    //metodo que lee un especialista
    public static Especialidad leer(FrmEspecialidades fe) {
        Especialidad esp = new Especialidad();
        esp.setCodEspecialidad(fe.txtCodigo.getText());
        esp.setNombre(fe.txtNombres.getText());
        esp.setDescripcion(fe.txtDescripcion.getText());
        esp.setEstado(fe.cbxEstado.getSelectedItem().toString());
        return esp;
    }
    //metodo que limpia las entradas
    public static void limpiarFormulario(FrmEspecialidades fe) {
        fe.txtCodigo.setText("");
        fe.txtNombres.setText("");
        fe.txtDescripcion.setText("");
        fe.cbxEstado.setSelectedIndex(0);
        fe.txtCodigo.requestFocus();
    }
    
    public static void Presentacion(FrmEspecialidades fe){
        String LISTATIPOS[]={"Activo","Inactivo"};
        String TITULOFRAME="Especialidades";
        fe.setTitle(TITULOFRAME);
        fe.cbxEstado.removeAllItems();
        for(String tipo:LISTATIPOS) fe.cbxEstado.addItem(tipo);
    }
    
    public static void AnchoTabla(JTable tabla){
        int ANCHOTABLA[]={80,150,250,100};
        for(int i=0;i<ANCHOTABLA.length;i++){
            tabla.getColumnModel().getColumn(i).setPreferredWidth(ANCHOTABLA[i]);
        }
    }
    
    public static void Mostrar(JTable tabla, ColaEspecialidades cola){
        String TITULOTABLA[] = {"Código", "Nombres", "Descripción", "Estado"};
        DefaultTableModel mt = new DefaultTableModel(null, TITULOTABLA);
        for(Especialidad so1 : cola.getCola()){
            Object[] fila = {
                so1.getCodEspecialidad(),
                so1.getNombre(),
                so1.getDescripcion(),
                so1.getEstado()
            };
            mt.addRow(fila); 
        }
        tabla.setModel(mt);
    }
}
