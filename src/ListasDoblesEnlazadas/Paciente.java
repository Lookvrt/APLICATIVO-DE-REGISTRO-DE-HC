package ListasDoblesEnlazadas;
import java.io.Serializable;
public class Paciente implements Serializable {
    private String numHistoria;
    private String dni;
    private String nombres;
    private String papellido;
    private String sapellido;
    private String fechaNacimiento;
    private String genero;       
    private String grupoSanguineo;
    private String estadoCivil;
    private String telefono;
    private String celular;
    private String email;
    private String direccion;
    private String estado; 
    private String localidad;
    
    public String nombreCompleto(){
        return papellido+" "+sapellido+" "+nombres;
    }
    public Object[] Registro(){
        Object[] fila = { numHistoria, dni, nombreCompleto(), telefono};
        return fila;
    }

    // metodos get y set
    public String getNumHistoria() { return numHistoria;}
    public void setNumHistoria(String numHistoria) { this.numHistoria = numHistoria;}
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getPapellido() { return papellido; }
    public void setPapellido(String papellido) { this.papellido = papellido; }
    public String getSapellido() { return sapellido; }
    public void setSapellido(String sapellido) { this.sapellido = sapellido; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getGrupoSanguineo() { return grupoSanguineo; }
    public void setGrupoSanguineo(String grupoSanguineo) { this.grupoSanguineo = grupoSanguineo; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
}
    
