package Colas;
import java.io.Serializable;
public class Especialidad implements Serializable, Comparable<Especialidad>{
    private String codEspecialidad;
    private String nombre;
    private String descripcion;
    private String estado; 
    private boolean activo;

    public Especialidad() {}
    public Object[] Registro(){
       Object[] fila ={codEspecialidad,nombre,descripcion,estado};
       return fila;
   } 
    @Override
    public int compareTo(Especialidad esp) {
        if (this.codEspecialidad == null || esp.getCodEspecialidad() == null) {
            return 0;
        }
        return this.codEspecialidad.compareToIgnoreCase(esp.getCodEspecialidad());
    }
    //metodos get y set
    public String getCodEspecialidad() { return codEspecialidad; }
    public void setCodEspecialidad(String codEspecialidad) { this.codEspecialidad = codEspecialidad; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    

    @Override
    public String toString() {
      return "Codigo        : "+codEspecialidad+
            "\nNombre       : "+nombre+
            "\nDescripcion  : "+descripcion+
            "\nEstado       : "+estado;   
   }
}
