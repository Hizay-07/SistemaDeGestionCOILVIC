package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public final class ProfesorSingleton {
    
    private static ProfesorSingleton instancia;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo; 
    private String estado;
    private int idProfesor;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    private ProfesorSingleton(Profesor profesor){
        setNombre(profesor.getNombre());
        setApellidoPaterno(profesor.getApellidoPaterno());
        setApellidoMaterno(profesor.getApellidoMaterno());
        setCorreo(profesor.getCorreo());
        setEstado(profesor.getEstado());
        setIdProfesor(profesor.getIdProfesor());
    }
    
    public static ProfesorSingleton getInstancia(Profesor profesor){
        if(instancia==null){
            instancia = new ProfesorSingleton(profesor);
        }
        return instancia;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre)throws IllegalArgumentException{
        if(nombre!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombre)){
            this.nombre = nombre;
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
}
