package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Colaboracion {
    private int idColaboracion;
    private String retroalimentacion;
    private String estadoColaboracion;
    private int idPropuestaColaboracion;
    private int cantidadEstudiantes;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public Colaboracion() {
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

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion)throws IllegalArgumentException {
        if(retroalimentacion==null||Pattern.matches(SOLO_LETRAS_PATTERN, retroalimentacion)){
            this.retroalimentacion = retroalimentacion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEstadoColaboracion() {
        return estadoColaboracion;
    }

    public void setEstadoColaboracion(String estadoColaboracion)throws IllegalArgumentException {
        if(estadoColaboracion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estadoColaboracion)){
            this.estadoColaboracion = estadoColaboracion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }

    public void setIdPropuestaColaboracion(int idPropuestaColaboracion)throws IllegalArgumentException {
         if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idPropuestaColaboracion))){
            this.idPropuestaColaboracion = idPropuestaColaboracion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes)throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(cantidadEstudiantes))){
            this.cantidadEstudiantes = cantidadEstudiantes;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Colaboracion)){
            return false;
        }
        Colaboracion colaboracionTemporal=(Colaboracion)obj;
        return idColaboracion==colaboracionTemporal.getIdColaboracion()&&
                idPropuestaColaboracion==colaboracionTemporal.getIdPropuestaColaboracion();                        
    }
    
    
    
}
