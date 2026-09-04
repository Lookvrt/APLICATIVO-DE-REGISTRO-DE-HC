package Controlador;
import Almacenamiento.*;
import Colas.ColaEspecialidades;
import Colas.Especialidad;
import EstructuraDinamica.*;
import Ordenamiento.*;
import Procesos.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;

public class ControlDoctor implements ActionListener {

    private FrmDoctor fd;
    private ListaDoctores lista;

    public ControlDoctor(FrmDoctor fd) {
        this.fd = fd;
        this.lista = AlmacenarDoctores.RecuperarLista();
        
        cargarEspecialidades();
        
        ProcesosDoctores.MostrarDatos(fd.tblDatos, lista);
        fd.btnGuardar.addActionListener(this);
        fd.btnConsultar.addActionListener(this);
        fd.btnEliminar.addActionListener(this);
        fd.btnOrdenar.addActionListener(this);
    }

    // ─── CARGAR ESPECIALIDADES EN EL COMBO ────────────────────
    private void cargarEspecialidades() {
    ColaEspecialidades lista = AlmacenarEspecialistas.RecuperarLista();
    javax.swing.DefaultComboBoxModel<String> comboModelo = new javax.swing.DefaultComboBoxModel<>();

        if (lista != null && lista.getCola() != null) {
            for (Especialidad esp : lista.getCola()) {
                if (esp.getEstado() != null && esp.getEstado().equalsIgnoreCase("Activo")) { 
                    comboModelo.addElement(esp.getNombre());
                }
            }
        }
        fd.cbxEspecialidad.setModel(comboModelo);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fd.btnGuardar) {
            guardar();
        } else if (e.getSource() == fd.btnConsultar) {
            consultar();
        } else if (e.getSource() == fd.btnEliminar) {
            eliminar();
        } else if (e.getSource() == fd.btnOrdenar) {
            ordenar();
        }
    }

    // ─── GUARDAR ─────────────────────────────────────────────────
    private void guardar() {
        if (fd.txtDNI.getText().isEmpty()) {
            Mensajes.M1("El DNI es obligatorio");
            return;
        }
        // BuscarDoc devuelve -1 si no existe
        if (lista.BuscarDoc(fd.txtDNI.getText()) != -1) {
            Mensajes.M1("Ya existe un doctor con ese DNI");
            return;
        }
        Doctor doc = ProcesosDoctores.Leer(fd);
        lista.AgregarDoc(doc);
        AlmacenarDoctores.GuardarLista(lista);
        ProcesosDoctores.MostrarDatos(fd.tblDatos, lista);
        ProcesosDoctores.LimpiarEntradas(fd);
        Mensajes.M1("Doctor guardado correctamente");
    }

    // ─── CONSULTAR ─────────────────────────────────────────────────
    private void consultar() {
        String dniBuscar = Mensajes.M2("Ingrese el DNI del doctor a consultar:");
        if (dniBuscar == null || dniBuscar.trim().isEmpty()) {
            return; 
        }
        int pos = lista.BuscarDoc(dniBuscar.trim());
        if (pos == -1) {
            Mensajes.M1("Doctor no encontrado");
            return;
        }
        Doctor doc = lista.RecuperarDoc(pos);
        ProcesosDoctores.Mostrar(doc, fd);
        
        Mensajes.M1("Doctor encontrado y cargado correctamente.");
    }

    // ─── ELIMINAR ─────────────────────────────────────────────────
    private void eliminar() {
        if (fd.txtDNI.getText().isEmpty()) {
            Mensajes.M1("Ingrese el DNI a eliminar");
            return;
        }
        int resp = Mensajes.M3("Eliminar",
                "¿Está seguro de eliminar este doctor?");
        if (resp == 0) {
            int pos = lista.BuscarDoc(fd.txtDNI.getText());
            if (pos == -1) {
                Mensajes.M1("Doctor no encontrado");
                return;
            }
            lista.EliminarDoc(pos);
            AlmacenarDoctores.GuardarLista(lista);
            ProcesosDoctores.MostrarDatos(fd.tblDatos, lista);
            ProcesosDoctores.LimpiarEntradas(fd);
            Mensajes.M1("Doctor eliminado correctamente");
        }
    }

    // ─── ORDENAR ─────────────────────────────────────────────────
    private void ordenar() {
        Burbuja.ordenar(lista.getLista(),
            (a, b) -> a.getApellidos()
                       .compareToIgnoreCase(b.getApellidos()));
        AlmacenarDoctores.GuardarLista(lista);
        ProcesosDoctores.MostrarDatos(fd.tblDatos, lista);
    }
}