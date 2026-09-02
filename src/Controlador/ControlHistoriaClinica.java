package Controlador;
import Almacenamiento.*;
import Arboles.*;
import EstructuraDinamica.*;
import Ordenamiento.Ordenador;
import Procesos.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControlHistoriaClinica implements ActionListener {

    private FrmHistoriaClinica fhc;
    private ArbolHistoria arbol;
    private DefaultTableModel modelo;
    private ListaDoctores listaDoctores;

    public ControlHistoriaClinica(FrmHistoriaClinica fhc) {
        this.fhc = fhc;
        this.arbol = AlmacenarHistoriasClinicas.RecuperarLista();
        this.modelo = (DefaultTableModel) fhc.tblDatos.getModel();
        this.listaDoctores = AlmacenarDoctores.RecuperarLista(); 

        cargarDoctores();
        
        fhc.txtEspecialidad.setEditable(false);

        // fecha automática
        fhc.txtFecha.setText(LocalDate.now().toString());
        fhc.txtFecha.setEditable(false);

        actualizarEspecialidadPorDoctor();

        ProcesosHistoria.LimpiarTabla(modelo);
        arbol.MostrarEnOrden(arbol.getRaiz(), modelo);
        
        fhc.btnOrdenar.addActionListener(this);
        fhc.btnGuardar.addActionListener(this);
        fhc.btnEliminar.addActionListener(this);
        fhc.btnBuscar.addActionListener(this);
        fhc.btnImprimirPDF.addActionListener(this);

        fhc.cbxDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEspecialidadPorDoctor();
            }
        });
    }

    // ─── CARGAR DOCTORES EN EL COMBO ────────────────────
    private void cargarDoctores() {
    javax.swing.DefaultComboBoxModel<String> comboModelo = new javax.swing.DefaultComboBoxModel<>();
        if (listaDoctores != null && listaDoctores.getLista() != null) {
            for (Doctor doc : listaDoctores.getLista()) {
            // Solo agrega el doctor si está activo
                if (doc.getEstado() != null && doc.getEstado().equalsIgnoreCase("Activo")) { 
                    comboModelo.addElement(doc.getNombres() + " " + doc.getApellidos());
                }
            }
        }
        fhc.cbxDoctor.setModel(comboModelo);
    }

    // ─── ACTUALIZAR ESPECIALIDAD DINÁMICAMENTE ──────────
    private void actualizarEspecialidadPorDoctor() {
        if (fhc.cbxDoctor.getSelectedItem() == null) {
            fhc.txtEspecialidad.setText("");
            return;
        }

        String doctorSeleccionado = fhc.cbxDoctor.getSelectedItem().toString();

        // Buscar al doctor en la lista para obtener su especialidad asignada
        if (listaDoctores != null && listaDoctores.getLista() != null) {
            for (Doctor doc : listaDoctores.getLista()) {
                String nombreCompleto = doc.getNombres() + " " + doc.getApellidos();
                if (nombreCompleto.equals(doctorSeleccionado)) {
                    fhc.txtEspecialidad.setText(doc.getEspecialidad());
                    return;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fhc.btnGuardar) {
            guardar();
        } else if (e.getSource() == fhc.btnEliminar) {
            eliminar();
        } else if (e.getSource() == fhc.btnBuscar) {
            buscar();
        } else if (e.getSource() == fhc.btnImprimirPDF) {
            generarPDF();
        } else if (e.getSource() == fhc.btnOrdenar) {
            ordenar();
        }
    }
    // ─── GENERAR PDF ─────────────────────────────────────────
    private void generarPDF() {
        if (fhc.txtDNI.getText().isEmpty()) {
            Mensajes.M1("Ingrese o busque un paciente antes de generar el PDF");
            return;
        }
        try {
            List<String[]> campos = new ArrayList<>();
            campos.add(new String[]{"N° Historia", fhc.txtHistoria.getText()});
            campos.add(new String[]{"DNI", fhc.txtDNI.getText()});
            campos.add(new String[]{"Paciente", fhc.txtPaciente.getText()});
            campos.add(new String[]{"Fecha", fhc.txtFecha.getText()});
            campos.add(new String[]{"Especialidad",
                fhc.txtEspecialidad.getText() == null ? "" : fhc.txtEspecialidad.getText()});
            campos.add(new String[]{"Diagnóstico", fhc.txtDiagnostico.getText()});
            campos.add(new String[]{"Tratamiento", fhc.txtTratamiento.getText()});
            campos.add(new String[]{"Observaciones", fhc.txtObservaciones.getText()});

            String nombreArchivo = "Historia_" + fhc.txtDNI.getText() + ".pdf";
            String ruta = GeneradorPDF.generar(nombreArchivo, "HISTORIA CLÍNICA", campos);

            Mensajes.M1("PDF generado correctamente en:\n" + ruta);
            java.awt.Desktop.getDesktop().open(new java.io.File(ruta));
        } catch (Exception ex) {
            Mensajes.M1("Error al generar el PDF: " + ex.getMessage());
        }
    }
    
    // ─── GUARDAR ─────────────────────────────────────────────
    private void guardar() {
        if (fhc.txtDNI.getText().isEmpty()) {
            Mensajes.M1("El DNI del paciente es obligatorio");
            return;
        }
        if (fhc.txtHistoria.getText().isEmpty()) {
            Mensajes.M1("El número de historia es obligatorio");
            return;
        }

        HistoriaClinica hc = new HistoriaClinica();
        hc.setNumeroHistoria(fhc.txtHistoria.getText());
        hc.setDni(fhc.txtDNI.getText());
        hc.setPaciente(fhc.txtPaciente.getText());
        hc.setFecha(fhc.txtFecha.getText());
        hc.setEspecialidad(fhc.txtEspecialidad.getText());       
        hc.setDoctor(fhc.cbxDoctor.getSelectedItem().toString());
        hc.setDiagnostico(fhc.txtDiagnostico.getText());
        hc.setTratamiento(fhc.txtTratamiento.getText());
        hc.setObservaciones(fhc.txtObservaciones.getText());

        arbol.setRaiz(arbol.AgregarCliente(arbol.getRaiz(), hc));
        AlmacenarHistoriasClinicas.GuardarLista(arbol);
        ProcesosHistoria.LimpiarTabla(modelo);
        arbol.MostrarEnOrden(arbol.getRaiz(), modelo);
        ProcesosHistoria.LimpiarEntradas(fhc);
        actualizarEspecialidadPorDoctor();
        
        Mensajes.M1("Historia clínica guardada correctamente");
    }

    // ─── BUSCAR ──────────────────────────────────────────────
    private void buscar() {
        String dniBuscar = Mensajes.M2("Ingrese el DNI a buscar:");
        if (dniBuscar == null || dniBuscar.trim().isEmpty()) {
            return; 
        }
        NodoHistoria encontrado = arbol.BuscarPorDni(dniBuscar.trim());
        if (encontrado == null) {
            Mensajes.M1("No se encontró la historia clínica con el DNI ingresado");
            return;
        }
        ProcesosHistoria.MostrarDatosNodo(encontrado, fhc);
        Mensajes.M1("Historia clínica encontrada y cargada correctamente.");
    }
    
    // ─── ELIMINAR ──────────────────────────────────────────────
    private void eliminar() {
        int fila = fhc.tblDatos.getSelectedRow();
        if (fila == -1) {
            Mensajes.M1("Seleccione una fila de la tabla");
            return;
        }
        int resp = Mensajes.M3("Eliminar",
                "¿Está seguro de eliminar esta historia clínica?");
        if (resp == 0) {
            String dni = (String) modelo.getValueAt(fila, 0);
            arbol.setRaiz(arbol.Eliminar(arbol.getRaiz(), dni));
            AlmacenarHistoriasClinicas.GuardarLista(arbol);
            ProcesosHistoria.LimpiarTabla(modelo);
            arbol.MostrarEnOrden(arbol.getRaiz(), modelo);
            ProcesosHistoria.LimpiarEntradas(fhc);
            
            actualizarEspecialidadPorDoctor();
            
            Mensajes.M1("Historia clínica eliminada correctamente");
        }
    }
    // ─── ORDENAR ──────────────────────────────────────────────
    private void ordenar() {
        int columnaEspecialidad = 4;  
        if (modelo.getRowCount() <= 1) {
            return;
        }
        Ordenamiento.Ordenador.ordenarTabla(fhc.tblDatos, columnaEspecialidad, "inser");
        Mensajes.M1("Historial ordenado por especialidad correctamente");
    }
}

