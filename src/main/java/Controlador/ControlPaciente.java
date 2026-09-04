package Controlador;
import Almacenamiento.*;
import ListasDoblesEnlazadas.*;
import Ordenamiento.*;
import Procesos.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlPaciente implements ActionListener {

    private FrmPaciente fp;
    private ListaPacientes lista;

    public ControlPaciente(FrmPaciente fp) {
        this.fp = fp;
        this.lista = AlmacenarPacientes.RecuperarLista();
        lista.MostrarAsistentes(fp.tblDatos);
        fp.btnGuardar.addActionListener(this);
        fp.btnConsultar.addActionListener(this);
        fp.btnEliminar.addActionListener(this);
        fp.btnOrdenar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fp.btnGuardar) {
            guardar();
        } else if (e.getSource() == fp.btnConsultar) {
            consultar();
        } else if (e.getSource() == fp.btnEliminar) {
            eliminar();
        } else if (e.getSource() == fp.btnOrdenar) {
            ordenar();
        }
    }

    // ─── GUARDAR ─────────────────────────────────────────────
    private void guardar() {
        if (fp.txtDNI.getText().isEmpty()) {
            Mensajes.M1("El DNI es obligatorio");
            return;
        }
        if (lista.BuscarPorCodigo(fp.txtDNI.getText()) != null) {
            Mensajes.M1("Ya existe un paciente con ese DNI");
            return;
        }
        Paciente p = ProcesosPacientes.leerFormulario(fp);
        lista.InsertarAsistente(p);
        AlmacenarPacientes.GuardarLista(lista);
        lista.MostrarAsistentes(fp.tblDatos);
        ProcesosPacientes.limpiarFormulario(fp);
        Mensajes.M1("Paciente guardado correctamente");
    }

    // ─── CONSULTAR ───────────────────────────────────────────
    private void consultar() {
        String dniBuscar = Mensajes.M2("Ingrese el DNI a consultar:");
        if (dniBuscar == null || dniBuscar.trim().isEmpty()) {
            return; 
        }
        Nodo nodo = lista.BuscarPorCodigo(dniBuscar.trim());
        if (nodo == null) {
            Mensajes.M1("Paciente no encontrado");
            return;
        }
        Paciente p = nodo.pas;
        fp.txtNumHistClinica.setText(p.getNumHistoria());
        fp.txtDNI.setText(p.getDni());
        fp.txtNombres.setText(p.getNombres());
        fp.txtPrimerApellido.setText(p.getPapellido());
        fp.txtSegundoApellido.setText (p.getSapellido());
        fp.txtNacimiento.setText(p.getFechaNacimiento());
        fp.txtTelefono.setText(p.getTelefono());
        fp.txtEmail.setText(p.getEmail());
        fp.txtDireccion.setText(p.getDireccion());
        fp.txtLocalidad.setText(p.getLocalidad());
        fp.btnGrupoSanguineo.setSelectedItem(p.getGrupoSanguineo());
        fp.cbxEstadoCivil.setSelectedItem(p.getEstadoCivil());
        if (p.getGenero().equals("Varón")) {
            fp.jrbVaron.setSelected(true);
        } else {
            fp.jrbMujer.setSelected(true);
        }
        if (p.getEstado().equals("Alto")) {
            fp.jrbAlto.setSelected(true);
        } else {
            fp.jrbBajo.setSelected(true);
        }
        Mensajes.M1("Paciente encontrado y cargado en el formulario.");
    }

    // ─── ELIMINAR ────────────────────────────────────────────
    private void eliminar() {
        if (fp.txtDNI.getText().isEmpty()) {
            Mensajes.M1("Ingrese el DNI a eliminar");
            return;
        }
        int resp = Mensajes.M3("Eliminar",
                "¿Está seguro de eliminar este paciente?");
        if (resp == 0) {
            Nodo nodo = lista.BuscarPorCodigo(fp.txtDNI.getText());
            if (nodo == null) {
                Mensajes.M1("Paciente no encontrado");
                return;
            }
            lista.EliminarAsistente(nodo);
            AlmacenarPacientes.GuardarLista(lista);
            lista.MostrarAsistentes(fp.tblDatos);
            ProcesosPacientes.limpiarFormulario(fp);
            Mensajes.M1("Paciente eliminado correctamente");
        }
    }

    // ─── ORDENAR ─────────────────────────────────────────────
    private void ordenar() {
        ArrayList<Paciente> arr = new ArrayList<>();
        for (Nodo aux = lista.ini; aux != null; aux = aux.sig) {
            arr.add(aux.pas);
        }
        QuickSort.ordenar(arr,
            (a, b) -> a.getPapellido().compareToIgnoreCase(b.getPapellido()));
        
        lista = new ListaPacientes();
        for (Paciente p : arr) {
            lista.InsertarAsistente(p);
        }
        AlmacenarPacientes.GuardarLista(lista);
        lista.MostrarAsistentes(fp.tblDatos);
    }
}