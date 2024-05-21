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
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+)*$";
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
        if(objetivo!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, objetivo.trim())&&objetivo.trim().length()<=255){
            this.objetivo = objetivo.trim().replaceAll("[ \t]+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }    
        
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio)throws IllegalArgumentException {
        if(fechaInicio!=null&&Pattern.matches(FECHA_PATTERN, fechaInicio.trim())&&fechaInicio.trim().length()<=45){
            this.fechaInicio = fechaInicio.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre)throws IllegalArgumentException {
        if(fechaCierre!=null&&Pattern.matches(FECHA_PATTERN, fechaCierre.trim())&&fechaCierre.trim().length()<=45){
            this.fechaCierre = fechaCierre.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma)throws IllegalArgumentException {
        if(idioma!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, idioma.trim())&&idioma.trim().length()<=40){
            this.idioma = idioma.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(String experienciaEducativa)throws IllegalArgumentException {
        if(experienciaEducativa!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, experienciaEducativa.trim())&&experienciaEducativa.trim().length()<=50){
            this.experienciaEducativa = experienciaEducativa.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }
  
    public String getProgramaEducativoEstudiantil() {
        return programaEducativoEstudiantil;
    }

    public void setProgramaEducativoEstudiantil(String programaEducativoEstudiantil)throws IllegalArgumentException {
        if(programaEducativoEstudiantil!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, programaEducativoEstudiantil.trim())&&programaEducativoEstudiantil.trim().length()<=150){
            this.programaEducativoEstudiantil = programaEducativoEstudiantil.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEstadoPropuesta() {
        return estadoPropuesta;
    }

    public void setEstadoPropuesta(String estadoPropuesta)throws IllegalArgumentException {
        if(estadoPropuesta!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estadoPropuesta.trim())&&estadoPropuesta.trim().length()<=45){
            this.estadoPropuesta = estadoPropuesta.trim().replaceAll("\\s+", " ");;
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
