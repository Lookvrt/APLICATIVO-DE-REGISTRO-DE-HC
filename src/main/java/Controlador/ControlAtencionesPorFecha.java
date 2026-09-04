package Controlador;
import Vista.FrmAtencionesPorFecha;
import Almacenamiento.*;
import Arboles.*;
import Colas.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
public class ControlAtencionesPorFecha implements ActionListener {
    
    private FrmAtencionesPorFecha fpf;
    private ArbolHistoria arbol;
    private DefaultTableModel modelo;

    public ControlAtencionesPorFecha(FrmAtencionesPorFecha fpf) {
        this.fpf = fpf;
        arbol = AlmacenarHistoriasClinicas.RecuperarLista();
        modelo = (DefaultTableModel) fpf.tblDatos.getModel();
        
        cargarEspecialistas();
        
        fpf.txtFechaDesde.setText(java.time.LocalDate.now().withDayOfMonth(1).toString());
        fpf.txtFechaHasta.setText(java.time.LocalDate.now().toString());
        fpf.txtFechaDesde.setEditable(true);
        fpf.txtFechaHasta.setEditable(true);

        fpf.btnBuscar.addActionListener(this);
}

    private void cargarEspecialistas() {
        ColaEspecialidades lista = AlmacenarEspecialistas.RecuperarLista();
        DefaultComboBoxModel<String> comboModelo = new DefaultComboBoxModel<>();

        if (lista != null && lista.getCola() != null) {
            for (Especialidad esp : lista.getCola()) {
                comboModelo.addElement(esp.getNombre());
            }
        }
        fpf.cbxEspecialista.setModel(comboModelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fpf.btnBuscar) {
            buscar();
        }
    }

    private void buscar() {
        modelo.setRowCount(0);
        String especialidad = fpf.cbxEspecialista.getSelectedItem().toString();
        String fechadesde = fpf.txtFechaDesde.getText().trim();
        String fechahasta = fpf.txtFechaHasta.getText().trim();
        arbol.MostrarAtencionesPorRango(arbol.getRaiz(), modelo, especialidad, fechadesde, fechahasta);
        fpf.lblTotal.setText("TOTAL DE ATENCIONES: " + modelo.getRowCount());
    }
}
