package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class EmisionPropuesta {
    
    private int idProfesor;
    private int idPropuestaColaboracion;
    private String fechaEmision;
    private static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public EmisionPropuesta() {
        
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

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        if(fechaEmision!=null&&Pattern.matches(FECHA_PATTERN, fechaEmision.trim())&&fechaEmision.trim().length()<=45){
            this.fechaEmision = fechaEmision.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }        
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof EmisionPropuesta)){        
            return false;
        }
        EmisionPropuesta emisionPropuestaTemporal=(EmisionPropuesta)obj;
        return idProfesor==emisionPropuestaTemporal.getIdProfesor()&&
                idPropuestaColaboracion==emisionPropuestaTemporal.getIdPropuestaColaboracion();                                        
    }
    
}
