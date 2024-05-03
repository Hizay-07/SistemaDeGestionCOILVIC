package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;

public final class PropuestaColaboracionAuxiliar {
    
    private static PropuestaColaboracionAuxiliar instancia;
    private int idPropuestaColaboracion;    
    private String fechaInicio;
    private String fechaCierre;
    private String idioma;
    private String experienciaEducativa;    
    private String objetivo;
    private String programaEducativoEstudiantil;
    private String estadoPropuesta;
    private TipoColaboracion tipoColaboracion;
    
    private PropuestaColaboracionAuxiliar(PropuestaColaboracion propuesta) {
        setIdPropuestaColaboracion(propuesta.getIdPropuestaColaboracion());
        setFechaInicio(propuesta.getFechaInicio());
        setFechaCierre(propuesta.getFechaCierre());
        setIdioma(propuesta.getIdioma());
        setExperienciaEducativa(propuesta.getExperienciaEducativa());
        setObjetivo(propuesta.getObjetivo());
        setProgramaEducativoEstudiantil(propuesta.getProgramaEducativoEstudiantil());
        setEstadoPropuesta(propuesta.getEstadoPropuesta());
        setTipoColaboracion(propuesta.getTipoColaboracion());
    }
     
    public static void setInstancia(PropuestaColaboracion propuesta){
        instancia = new PropuestaColaboracionAuxiliar(propuesta);
    }
    
    public static PropuestaColaboracionAuxiliar getInstnacia(){
        return instancia;
    }
    
    public void resetInstancia(){
        instancia = null;
    }

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }

    public void setIdPropuestaColaboracion(int idPropuestaColaboracion){
        this.idPropuestaColaboracion = idPropuestaColaboracion;
    }        

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }    
    
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(String experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public String getProgramaEducativoEstudiantil() {
        return programaEducativoEstudiantil;
    }

    public void setProgramaEducativoEstudiantil(String programaEducativoEstudiantil) {
        this.programaEducativoEstudiantil = programaEducativoEstudiantil;
    }

    public String getEstadoPropuesta() {
        return estadoPropuesta;
    }

    public void setEstadoPropuesta(String estadoPropuesta) {
        this.estadoPropuesta = estadoPropuesta;
    }

    public TipoColaboracion getTipoColaboracion() {
        return tipoColaboracion;
    }

    public void setTipoColaboracion(TipoColaboracion tipoColaboracion) {
        this.tipoColaboracion = tipoColaboracion;
    }  
}
