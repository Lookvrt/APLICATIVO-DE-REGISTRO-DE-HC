package Controlador;
import Vista.FrmAtencionesPorFecha;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
public class ControlMenu implements ActionListener{
    FrmMenu vista;
    public ControlMenu(FrmMenu fm){
         vista=fm;
         vista.itemDoctores.addActionListener(this);
         vista.itemEspecialidades.addActionListener(this);
         vista.itemPacientes.addActionListener(this);
         vista.itemHistoriasClinicas.addActionListener(this);
         vista.itemAtencionesDelDia.addActionListener(this);
         vista.itemAtencionesPorFecha.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {   
        if(e.getSource()== vista.itemDoctores){
            FrmDoctor f = new FrmDoctor();
            f.setTitle("Doctores");
            f.setVisible(true);           
            ControlDoctor doc = new ControlDoctor(f);
            MostrarInternalFrame(f);
        }
        if(e.getSource()==vista.itemEspecialidades){
            FrmEspecialidades f = new FrmEspecialidades();
            f.setTitle("Especialidades");
            f.setVisible(true);
            ControlEspecialidad esp = new ControlEspecialidad(f);
            MostrarInternalFrame(f);
        }
        if(e.getSource()==vista.itemPacientes){
            FrmPaciente f = new FrmPaciente();
            f.setTitle("Pacientes");
            f.setVisible(true);
            ControlPaciente pac = new ControlPaciente(f);
            MostrarInternalFrame(f);
        }
        if(e.getSource()==vista.itemHistoriasClinicas){
            FrmHistoriaClinica f = new FrmHistoriaClinica();
            f.setTitle("Historias Clinicas");
            f.setVisible(true);
            ControlHistoriaClinica htc = new ControlHistoriaClinica(f);
            MostrarInternalFrame(f);
        }
        if(e.getSource()==vista.itemAtencionesDelDia){
            FrmAtencionesDelDia f = new FrmAtencionesDelDia();
            f.setTitle("Atenciones del Día");
            f.setVisible(true);
            ControlAtencionesDelDia at = new ControlAtencionesDelDia(f);
            MostrarInternalFrame(f);
        } 
        if(e.getSource()==vista.itemAtencionesPorFecha){
            FrmAtencionesPorFecha f = new FrmAtencionesPorFecha();
            f.setTitle("Atenciones por Fecha");
            f.setVisible(true);
            ControlAtencionesPorFecha apf = new ControlAtencionesPorFecha(f);
            MostrarInternalFrame(f);
        }
    }   
    private void MostrarInternalFrame(JInternalFrame jif){
        vista.dspEscritorio.removeAll();
        vista.dspEscritorio.add(jif);   
        int x = (vista.dspEscritorio.getWidth() / 2) - (jif.getWidth() / 2);
        int y = (vista.dspEscritorio.getHeight() / 2) - (jif.getHeight() / 2);
        jif.setLocation(x, y);        
        vista.dspEscritorio.repaint();
        
    } 
}
