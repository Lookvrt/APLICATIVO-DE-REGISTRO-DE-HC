package Almacenamiento;
import EstructuraDinamica.*;
import java.io.*;
import Procesos.*;

public class AlmacenarDoctores {
    public static String archivo="ListaDoctores.bin";
    //guardando en un archivo binario
    public static void GuardarLista(ListaDoctores ld){
        try{
            FileOutputStream fos = new FileOutputStream(archivo);
            ObjectOutputStream oos =  new ObjectOutputStream(fos);
            oos.writeObject(ld);
            oos.close();            
        }catch(Exception ex){
            Mensajes.M1("ERROR no se puede guardar Doctor");
        }
    }
   //recuperar los datos del archivo binario
    public static ListaDoctores RecuperarLista(){
        ListaDoctores lista=new ListaDoctores();
        try{
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            lista = (ListaDoctores)ois.readObject();
            ois.close();            
        }catch(Exception ex){
          Mensajes.M1("ERROR no se puede recuperar Doctores");
         }
        return lista;
    }    
}

