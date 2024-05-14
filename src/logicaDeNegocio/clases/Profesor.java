package logicaDeNegocio.clases;
import java.util.regex.Pattern;

public class Profesor{
    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo; 
    private String estado;
    private int idProfesor;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+)*$";;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    public Profesor(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre)throws IllegalArgumentException{
        if(nombre!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombre.trim())&&nombre.trim().length()<=45){
            this.nombre = nombre.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }
  
    public String getApellidoPaterno(){
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno)throws IllegalArgumentException{
        if(apellidoPaterno!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, apellidoPaterno)){
            this.apellidoPaterno = apellidoPaterno;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno)throws IllegalArgumentException {
        if(apellidoMaterno!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, apellidoMaterno)){
            this.apellidoMaterno = apellidoMaterno;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo)throws IllegalArgumentException{
        if(correo!=null&&Pattern.matches(EMAIL_PATTERN, correo)){
            this.correo = correo;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idProfesor))){
            this.idProfesor = idProfesor;
        }else{
            throw new IllegalArgumentException();
        }
    }   
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado)throws IllegalArgumentException{
       if(estado!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estado)){
            this.estado = estado;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public boolean validarAtributos(){
        return nombre!=null&&!nombre.isEmpty()&&
                apellidoPaterno!=null&&!apellidoPaterno.isEmpty()&&
                apellidoMaterno!=null&&!apellidoMaterno.isEmpty()&&
                correo!=null&&!correo.isEmpty();
                
    }    
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Profesor)){        
            return false;
        }
        Profesor profesorTemporal=(Profesor)obj;
        return nombre.equals(profesorTemporal.getNombre())&&
                apellidoPaterno.equals(profesorTemporal.getApellidoPaterno())&&
                apellidoMaterno.equals(profesorTemporal.getApellidoMaterno())&&
                correo.equals(profesorTemporal.getCorreo());
    }
    
    @Override
    public String toString(){
        return nombre+" "+apellidoPaterno+" "+apellidoMaterno;
    }
}
