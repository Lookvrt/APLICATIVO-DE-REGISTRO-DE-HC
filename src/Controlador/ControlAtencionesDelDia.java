package Controlador;

import Almacenamiento.AlmacenarDoctores;
import Almacenamiento.AlmacenarHistoriasClinicas;
import Arboles.ArbolHistoria;
import EstructuraDinamica.Doctor;
import EstructuraDinamica.ListaDoctores;
import Vista.FrmAtencionesDelDia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class ControlAtencionesDelDia implements ActionListener {

    private FrmAtencionesDelDia fad;
    private ArbolHistoria arbol;
    private DefaultTableModel modelo;

    public ControlAtencionesDelDia(FrmAtencionesDelDia fad) {
        this.fad = fad;

        arbol = AlmacenarHistoriasClinicas.RecuperarLista();
        modelo = (DefaultTableModel) fad.tblDatos.getModel();

        cargarDoctores();
        fad.txtFecha.setText(java.time.LocalDate.now().toString());
        fad.txtFecha.setEditable(false);

        fad.btnBuscar.addActionListener(this);
    }

    private void cargarDoctores() {

        ListaDoctores lista = AlmacenarDoctores.RecuperarLista();

        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

        if (lista != null) {
            for (Doctor doc : lista.getLista()) {
                modelo.addElement(doc.getNombres() + " " + doc.getApellidos());
            }
        }

        fad.cbxDoctor.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fad.btnBuscar) {
            buscar();
        }

    }

    private void buscar() {

        modelo.setRowCount(0);

        String doctor = fad.cbxDoctor.getSelectedItem().toString();
        String fecha = fad.txtFecha.getText();
        arbol.MostrarAtencionesDelDia(arbol.getRaiz(), modelo, doctor, fecha);

        // Cambia lblTotal por el nombre real de tu JLabel
        fad.lblTotal.setText("TOTAL DE ATENCIONES: " + modelo.getRowCount());

    }

}