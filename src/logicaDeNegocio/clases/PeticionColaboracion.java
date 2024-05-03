package logicaDeNegocio.clases;
import java.util.regex.Pattern;

public class PeticionColaboracion {
    


    private int idProfesor;    
    private String estado;
    private String fechaEnvio;
    private int idPropuestaColaboracion;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String FECHA_PATTERN = "^\\d{2}/\\d{2}/\\d{4}$";    
    
    public PeticionColaboracion() {
    }
    
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor)throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idProfesor))){
            this.idProfesor = idProfesor;
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado)throws IllegalArgumentException{
        if(estado!=null||Pattern.matches(SOLO_LETRAS_PATTERN, estado)){
            this.estado = estado;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio)throws IllegalArgumentException {
        if(fechaEnvio!=null||Pattern.matches(FECHA_PATTERN, fechaEnvio)){
            this.fechaEnvio = fechaEnvio;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }


    public void setIdPropuestaColaboracion(int idPropuestaColaboracion)throws IllegalArgumentException{
       if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idPropuestaColaboracion))){
            this.idPropuestaColaboracion = idPropuestaColaboracion;
        }else{
            throw new IllegalArgumentException();
        }
    }
   

}
