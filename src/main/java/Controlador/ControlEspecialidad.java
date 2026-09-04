package Controlador;
import Almacenamiento.*;
import Colas.*;
import Ordenamiento.*;
import Procesos.*;
import Vista.FrmEspecialidades;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlEspecialidad implements ActionListener {

    FrmEspecialidades vista;
    ColaEspecialidades cola;
    Especialidad esp;

    public ControlEspecialidad(FrmEspecialidades fe) {
        vista=fe;
        cola = new ColaEspecialidades();
        vista.btnConsultar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnOrdenar.addActionListener(this);
        cola = AlmacenarEspecialistas.RecuperarLista();
        ProcesosEspecialistas.Mostrar(vista.tblDatos, cola);
        ProcesosEspecialistas.AnchoTabla(vista.tblDatos);
        ProcesosEspecialistas.Presentacion(vista);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnGuardar){
            Guardar();
        }
        if(e.getSource()==vista.btnEliminar){
           Eliminar(); 
        }
        if(e.getSource()==vista.btnConsultar){
           Consultar();
        }
        if(e.getSource()==vista.btnOrdenar){
           Ordenar();
        }
    }
    
    // ─── GUARDAR ─────────────────────────────────────────────────
    private void Guardar(){
        esp = ProcesosEspecialistas.leer(vista);
        cola.Encolar(esp);
        AlmacenarEspecialistas.GuardarLista(cola);
        ProcesosEspecialistas.Mostrar(vista.tblDatos, cola);
        ProcesosEspecialistas.AnchoTabla(vista.tblDatos);
        ProcesosEspecialistas.limpiarFormulario(vista);
    }
    
    // ─── CONSULTAR ─────────────────────────────────────────────────
    private void Consultar(){
        String cod = Mensajes.M2("Ingrese codigo a buscar..");
        if (cod == null || cod.trim().isEmpty()) {
            return;
        }  
        esp = cola.BuscarPorCod(cod.trim());
        if (esp == null) {
            Mensajes.M1("Codigo " + cod + " no existe en la cola");
        } else {
            String detalle = "─── DATOS DE LA ESPECIALIDAD ───\n" +
                             "Código: " + esp.getCodEspecialidad() + "\n" +
                             "Nombre: " + esp.getNombre() + "\n" +
                             "Descripción: " + esp.getDescripcion() + "\n" +
                             "Estado: " + esp.getEstado();
            Mensajes.M1(detalle);
        }
    }
    
    // ─── ELIMINAR ─────────────────────────────────────────────────
    private void Eliminar(){
        int resp = Mensajes.M3("Confirmar!", "Desea eliminar a ?\n"+
                                      cola.VerPrimero());
            if(resp==0){
            cola.Desencolar();
            AlmacenarEspecialistas.GuardarLista(cola);
            ProcesosEspecialistas.Mostrar(vista.tblDatos, cola);
            ProcesosEspecialistas.AnchoTabla(vista.tblDatos);
            }
    }
    
    private void Ordenar() {
        int columnaNombre = 1;  
        if (vista.tblDatos.getRowCount() <= 1) {
            return;
        }
        Ordenamiento.Ordenador.ordenarTabla(vista.tblDatos, columnaNombre, "sele");
        Mensajes.M1("Especialistas ordenados por nombre.");
    }
}