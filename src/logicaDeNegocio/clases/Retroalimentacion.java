package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Retroalimentacion {
    private String retroalimentacion;
    private String emisor;
    private int idColaboracion;
    private int idRetroalimentacion;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion)throws IllegalArgumentException {
        if(retroalimentacion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, retroalimentacion.trim())&&retroalimentacion.trim().length()<=255){
            this.retroalimentacion = retroalimentacion.trim().replaceAll("[ \t]+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor)throws IllegalArgumentException {
        if(emisor!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, emisor.trim())&&emisor.trim().length()<=100){
            this.emisor = emisor.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idColaboracion))){
            this.idColaboracion = idColaboracion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    public void setIdRetroalimentacion(int idRetroalimentacion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idRetroalimentacion))){
            this.idRetroalimentacion = idRetroalimentacion;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    
    
}
