package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class EvaluacionPropuesta {
    private int idEvaluacionPropuesta;
    private int idPropuestaColaboracion;
    private int idUsuario;
    private String evaluacion;
    private String fechaEvaluacion;
    private String justificacion;    
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    public EvaluacionPropuesta() {
        
    }

    public int getIdEvaluacionPropuesta() {
        return idEvaluacionPropuesta;
    }

    public void setIdEvaluacionPropuesta(int idEvaluacionPropuesta) throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idEvaluacionPropuesta))){
            this.idEvaluacionPropuesta = idEvaluacionPropuesta;
        }else{
            throw new IllegalArgumentException();
        }        
    }

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }

    public void setIdPropuestaColaboracion(int idPropuestaColaboracion) throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idPropuestaColaboracion))){
            this.idPropuestaColaboracion = idPropuestaColaboracion;
        }else{
            throw new IllegalArgumentException();
        }        
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idUsuario))){
            this.idUsuario = idUsuario;
        }else{
            throw new IllegalArgumentException();
        }        
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion)throws IllegalArgumentException {
        if(evaluacion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, evaluacion.trim())&&evaluacion.trim().length()<=45){
            this.evaluacion = evaluacion.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }       
    }        

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) throws IllegalArgumentException{
        if(fechaEvaluacion!=null&&Pattern.matches(FECHA_PATTERN, fechaEvaluacion.trim())&&fechaEvaluacion.trim().length()<=45){
            this.fechaEvaluacion = fechaEvaluacion.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }        
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        if(justificacion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, justificacion.trim())&&justificacion.trim().length()<=255){
            this.justificacion = justificacion.trim().replaceAll("[ \t]+", " ");
        }else{
            throw new IllegalArgumentException();
        }        
    }     
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof EvaluacionPropuesta)) {
            return false;
        }
        EvaluacionPropuesta evaluacionTemporal=(EvaluacionPropuesta)obj;
        return idEvaluacionPropuesta==evaluacionTemporal.getIdEvaluacionPropuesta()&&
                idPropuestaColaboracion==evaluacionTemporal.getIdPropuestaColaboracion()&&
                idUsuario==evaluacionTemporal.getIdUsuario()&&
                evaluacion.equals(evaluacionTemporal.getEvaluacion());
    }
    
}
