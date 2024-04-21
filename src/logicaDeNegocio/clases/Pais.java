package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Pais{
    private String nombrePais;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";

    public Pais(){
    }

    
    public String getNombrePais(){
        return nombrePais;
    }

    public void setNombrePais(String nombrePais)throws IllegalArgumentException{
        if(nombrePais!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombrePais)){
            this.nombrePais = nombrePais;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        Pais paisTemporal = (Pais)obj;
        return nombrePais.equals(paisTemporal.getNombrePais());
    }
    
}
