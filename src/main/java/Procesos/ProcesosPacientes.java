package Procesos;
import ListasDoblesEnlazadas.*;
import Vista.*;
public class ProcesosPacientes {
    //metodo que lee un especialista
    public static Paciente leerFormulario(FrmPaciente fp) {
        Paciente p = new Paciente();
        p.setNumHistoria(fp.txtNumHistClinica.getText());
        p.setDni(fp.txtDNI.getText());
        p.setNombres(fp.txtNombres.getText());
        p.setPapellido(fp.txtPrimerApellido.getText());
        p.setSapellido(fp.txtSegundoApellido.getText());
        p.setFechaNacimiento(fp.txtNacimiento.getText());
        p.setTelefono(fp.txtTelefono.getText());
        p.setEmail(fp.txtEmail.getText());
        p.setDireccion(fp.txtDireccion.getText());
        p.setLocalidad(fp.txtLocalidad.getText());
        p.setGrupoSanguineo(fp.btnGrupoSanguineo.getSelectedItem().toString());
        p.setEstadoCivil(fp.cbxEstadoCivil.getSelectedItem().toString());
        if (fp.jrbVaron.isSelected()){
            p.setGenero("Varón");
        } else {
            p.setGenero("Mujer");
        }
        if (fp.jrbAlto.isSelected()){
            p.setEstado("Alto");
        } else {
            p.setEstado("Bajo");
        }
        return p;
    }
    //metodo que limpia las entradas
    public static void limpiarFormulario(FrmPaciente fp) {
        fp.txtNumHistClinica.setText("");
        fp.txtDNI.setText("");
        fp.txtNombres.setText("");
        fp.txtPrimerApellido.setText("");
        fp.txtSegundoApellido.setText("");
        fp.txtNacimiento.setText("");
        fp.txtTelefono.setText("");
        fp.txtEmail.setText("");
        fp.txtDireccion.setText("");
        fp.txtLocalidad.setText("");
        fp.jrbVaron.setSelected(true);
        fp.jrbAlto.setSelected(true);
        fp.txtPrimerApellido.requestFocus();
    }
}
