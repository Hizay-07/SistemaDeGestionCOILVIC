package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Evidencia {
    private int idEvidencia;
    private String nombre;
    private String rutaEvidencia;
    private int idActividad;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idEvidencia))){
            this.idEvidencia = idEvidencia;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre)throws IllegalArgumentException {
        if(nombre!=null&&Pattern.matches(SOLO_LETRAS_PATTERN,nombre)){
            this.nombre = nombre;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getRutaEvidencia() {
        return rutaEvidencia;
    }

    public void setRutaEvidencia(String rutaEvidencia)throws IllegalArgumentException{
        if(rutaEvidencia!=null){
            this.rutaEvidencia = rutaEvidencia;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idActividad))){
            this.idActividad = idActividad;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        Evidencia evidenciaTemp = (Evidencia)obj;
        return this.idEvidencia == evidenciaTemp.getIdEvidencia() && this.nombre.equals(evidenciaTemp.getNombre()) && this.rutaEvidencia.equals(evidenciaTemp.getRutaEvidencia())
                &&this.idActividad == evidenciaTemp.getIdActividad();
    }
}
