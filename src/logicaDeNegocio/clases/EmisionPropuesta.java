package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class EmisionPropuesta {
    private int idProfesor;
    private int idPropuestaColaboracion;
    private String fechaEmision;
    private static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    public EmisionPropuesta() {
        
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }

    public void setIdPropuestaColaboracion(int idPropuestaColaboracion) {
        this.idPropuestaColaboracion = idPropuestaColaboracion;
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
