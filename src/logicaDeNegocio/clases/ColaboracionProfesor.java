package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class ColaboracionProfesor {
    private int idProfesor;
    private int idColaboracion;
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public ColaboracionProfesor() {
        
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

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion)throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idColaboracion))){
             this.idColaboracion = idColaboracion;
        }else{
            throw new IllegalArgumentException();
        }   
    } 
    
}
