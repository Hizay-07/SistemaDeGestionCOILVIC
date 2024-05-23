package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Pais{
    private String nombrePais;
    private int numeroDePais;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+)*$";

    public Pais(){
        
    }

    
    public String getNombrePais(){
        return nombrePais;
    }

    public void setNombrePais(String nombrePais)throws IllegalArgumentException{
        if(nombrePais!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombrePais.trim())&&nombrePais.trim().length()<=45){
            this.nombrePais = nombrePais.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getNumeroDePais() {
        return numeroDePais;
    }

    public void setNumeroDePais(int numeroDePais) {
        this.numeroDePais = numeroDePais;
    }        
    
    @Override
    public boolean equals(Object obj){
        Pais paisTemporal = (Pais)obj;
        return nombrePais.equals(paisTemporal.getNombrePais());
    }
    
}
