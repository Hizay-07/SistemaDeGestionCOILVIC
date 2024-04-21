package logicaDeNegocio.clases;
import java.util.regex.Pattern;

public class PropuestaColaboracion {
    private int idPropuestaColaboracion;
    private int idTipoColaboracion;
    private String fechaInicio;
    private String fechaCierre;
    private String idioma;
    private String experienciaEducativa;    
    private String objetivo;
    private int cantidadEstudiantes;
    private String programaEducativoEstudiantil;
    private String estadoPropuesta;
    private String informacionAdicional;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String FECHA_PATTERN = "^\\d{2}/\\d{2}/\\d{4}$";
    
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

    public int getIdTipoColaboracion() {
        return idTipoColaboracion;
    }

    public void setIdTipoColaboracion(int idTipoColaboracion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idTipoColaboracion))){
            this.idTipoColaboracion = idTipoColaboracion;
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

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_LETRAS_PATTERN, String.valueOf(cantidadEstudiantes))){
            this.cantidadEstudiantes = cantidadEstudiantes;
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

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional)throws IllegalArgumentException {
        if(informacionAdicional!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, informacionAdicional)){
            this.informacionAdicional = informacionAdicional;
        }else{
            throw new IllegalArgumentException();
        }
    }
        
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof PropuestaColaboracion)){
            return false;
        }
        PropuestaColaboracion propuestaColaboracionTemporal=(PropuestaColaboracion)obj;
        return idPropuestaColaboracion==propuestaColaboracionTemporal.getIdPropuestaColaboracion()&&
                idTipoColaboracion==propuestaColaboracionTemporal.getIdTipoColaboracion()&&
                objetivo.equals(propuestaColaboracionTemporal.getObjetivo());                
    }
    
    
}
