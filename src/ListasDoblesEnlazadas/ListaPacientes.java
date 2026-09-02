package ListasDoblesEnlazadas;
import java.io.Serializable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class ListaPacientes implements Serializable{
   public Nodo ini;
   public Nodo fin;
   public ListaPacientes(){
       ini=fin=null;
   }
  //metodo que muestra los datos del nodo en un tabla
   public void MostrarAsistentes(JTable tabla){
       String titulos[]= {"N° Historia Clinica","DNI","Nombres Completos","Télefono"};
       DefaultTableModel mt =  new DefaultTableModel(null,titulos);
       tabla.setModel(mt);     
       int num=0;
       for( Nodo aux=ini;aux!=null;aux=aux.sig){
           num++;
           mt.addRow(aux.pas.Registro());
       }
   }
   
   //metodo que busca una asistente en la lista doble
   public Nodo BuscarPorCodigo(String codbuscado){
       Nodo encontrado=ini;
       while(encontrado!=null){
           if(encontrado.pas.getDni().equalsIgnoreCase(codbuscado))
               return encontrado;
           encontrado=encontrado.sig;
       }
       return null; // solo si el dni no existe
   }
   
   //metodo que elimina un nodo de la lista doble
   public void EliminarAsistente(Nodo actual){
       if(actual!=null){
           if(actual==ini){
               ini=actual.sig;
               if(actual.sig!=null)
                   actual.sig.ant=null;
           }else if(actual.sig!=null){
             actual.ant.sig = actual.sig;
             actual.sig.ant = actual.ant;
           }else{
               actual.ant.sig=null;
               fin=actual.ant;
           }
           actual=null;
       }
   }
   
   //metodo que inserta datos al final del nodo
   public void InsertarAsistente(Paciente asis){
       Nodo nuevo = new Nodo(asis);
       if(ini==null){
           ini=fin=nuevo;
       }else{
           nuevo.ant=fin;
           fin.sig=nuevo;
       }
       fin=nuevo;
       fin.sig=null;
   }
}