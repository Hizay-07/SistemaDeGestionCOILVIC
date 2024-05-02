package logicaDeNegocio.clases;
import java.util.regex.Pattern;

public class PropuestaColaboracion {
    private int idPropuestaColaboracion;    
    private String fechaInicio;
    private String fechaCierre;
    private String idioma;
    private String experienciaEducativa;    
    private String objetivo;    
    private String programaEducativoEstudiantil;
    private String estadoPropuesta;    
    private TipoColaboracion tipoColaboracion;
    private Profesor profesor;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
    
    public PropuestaColaboracion() {
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo)throws IllegalArgumentException {
        if(objetivo!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, objetivo)){
            this.objetivo = objetivo;
        }else{
            throw new IllegalArgumentException();
        }
    }    
        
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio)throws IllegalArgumentException {
        if(fechaInicio!=null&&Pattern.matches(FECHA_PATTERN, fechaInicio)){
            this.fechaInicio = fechaInicio;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre)throws IllegalArgumentException {
        if(fechaCierre!=null&&Pattern.matches(FECHA_PATTERN, fechaCierre)){
            this.fechaCierre = fechaCierre;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma)throws IllegalArgumentException {
        if(idioma!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, idioma)){
            this.idioma = idioma;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(String experienciaEducativa)throws IllegalArgumentException {
        if(experienciaEducativa!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, experienciaEducativa)){
            this.experienciaEducativa = experienciaEducativa;
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public String getProgramaEducativoEstudiantil() {
        return programaEducativoEstudiantil;
    }

    public void setProgramaEducativoEstudiantil(String programaEducativoEstudiantil)throws IllegalArgumentException {
        if(programaEducativoEstudiantil!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, programaEducativoEstudiantil)){
            this.programaEducativoEstudiantil = programaEducativoEstudiantil;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEstadoPropuesta() {
        return estadoPropuesta;
    }

    public void setEstadoPropuesta(String estadoPropuesta)throws IllegalArgumentException {
        if(estadoPropuesta!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estadoPropuesta)){
            this.estadoPropuesta = estadoPropuesta;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public TipoColaboracion getTipoColaboracion() {
        return tipoColaboracion;
    }

    public void setTipoColaboracion(TipoColaboracion tipoColaboracion) {
        this.tipoColaboracion = tipoColaboracion;
    }     

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }        
        
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof PropuestaColaboracion)){
            return false;
        }
        PropuestaColaboracion propuestaColaboracionTemporal=(PropuestaColaboracion)obj;
        return experienciaEducativa.equals(propuestaColaboracionTemporal.getExperienciaEducativa())&&
                tipoColaboracion.equals(propuestaColaboracionTemporal.getTipoColaboracion())&&
                objetivo.equals(propuestaColaboracionTemporal.getObjetivo());                
    }
    
    
}
