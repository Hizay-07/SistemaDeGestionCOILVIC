package logicaDeNegocio.clases;
import logicaDeNegocio.enums.EnumEstados;

    

public class Profesor{
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo; 
    private String estado;
    private int idProfesor;
    
    public Profesor(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
