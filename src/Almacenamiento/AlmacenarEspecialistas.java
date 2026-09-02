package Almacenamiento;
import Colas.*;
import java.io.*;
import Procesos.Mensajes;

public class AlmacenarEspecialistas {
    public static String archivo="ListaEspecialistas.bin";
    //guardando en un archivo binario
    public static void GuardarLista(ColaEspecialidades le){
        try{
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos =  new ObjectOutputStream(fos);
            oos.writeObject(le);
            oos.close();            
        }catch(Exception ex){
            Mensajes.M1("ERROR no se puede guardar Especialista");
        }
    }//fin metodo
   //recuperar los datos del archivo binario
    public static ColaEspecialidades RecuperarLista(){
        ColaEspecialidades lista=new ColaEspecialidades();
        try{
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ColaEspecialidades)ois.readObject();
            ois.close();            
        }catch(Exception ex){
          Mensajes.M1("ERROR no se puede recuperar Especialistas");
         }
        return lista;
    }    
}//fin de class

