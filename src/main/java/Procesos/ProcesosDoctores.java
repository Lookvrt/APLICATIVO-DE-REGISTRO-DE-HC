package Procesos;
import EstructuraDinamica.*;
import Vista.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class ProcesosDoctores {
    //metodo que muestra en un table los datos de la lista
    public static void MostrarDatos(JTable tabla,ListaDoctores lista){
        String titulos[]={"Codigo","DNI","Apellidos","Nombres","Especialidad"};
        DefaultTableModel mt =  new DefaultTableModel(null,titulos);
        tabla.setModel(mt);
        for(int i=0;i<lista.CantidadDoc();i++){
            mt.addRow(lista.RecuperarDoc(i).Registro());
        }
    }
    //metodo que lee un estudiante
    public static Doctor Leer(FrmDoctor f){
        Doctor doc = new Doctor();
        doc.setCodDoctor(f.txtCodigo.getText());
        doc.setDni(f.txtDNI.getText());
        doc.setNombres(f.txtNombres.getText());
        doc.setApellidos(f.txtApellidos.getText());
        doc.setTelefono(Integer.parseInt(f.txtTelefono.getText()));
        doc.setEmail(f.txtEmail.getText());
        doc.setEspecialidad(f.cbxEspecialidad.getSelectedItem().toString());
        doc.setEstado(f.cbxEstado.getSelectedItem().toString());
        return doc;
    }
    //metodo que limpia las entradas
    public static void LimpiarEntradas(FrmDoctor f){
        f.txtCodigo.setText("");
        f.txtDNI.setText("");
        f.txtNombres.setText("");
        f.txtApellidos.setText("");
        f.txtTelefono.setText("");
        f.txtEmail.setText("");
        f.cbxEspecialidad.setSelectedIndex(0);
        f.cbxEstado.setSelectedIndex(0);
        f.txtCodigo.requestFocus();
    } 
    public static void Mostrar(Doctor doc,FrmDoctor f){
        f.txtCodigo.setText(doc.getCodDoctor());
        f.txtDNI.setText(doc.getDni());
        f.txtNombres.setText(doc.getNombres());
        f.txtApellidos.setText(doc.getApellidos());
        f.txtTelefono.setText(String.valueOf(doc.getTelefono()));
        f.cbxEspecialidad.setSelectedItem(doc.getEspecialidad());
        if(doc.getEstado().equals("Activo")) f.cbxEstado.setSelectedIndex(0);
        else f.cbxEstado.setSelectedIndex(1);
    }
}
