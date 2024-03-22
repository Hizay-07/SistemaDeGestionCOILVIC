package logicaDeNegocio.clases;


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

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }

    public void setIdPropuestaColaboracion(int idPropuestaColaboracion) {
        this.idPropuestaColaboracion = idPropuestaColaboracion;
    }
    
    public PropuestaColaboracion() {
    }    

    public int getIdTipoColaboracion() {
        return idTipoColaboracion;
    }

    public void setIdTipoColaboracion(int idTipoColaboracion) {
        this.idTipoColaboracion = idTipoColaboracion;
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

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public void setCantidadEstudiantes(int cantidadEstudiantes) {
        this.cantidadEstudiantes = cantidadEstudiantes;
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

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }
    
    
    
    public boolean equals(Object obj){
        PropuestaColaboracion propuestaColaboracionTemporal=(PropuestaColaboracion)obj;
        return idPropuestaColaboracion==propuestaColaboracionTemporal.getIdPropuestaColaboracion()&&
                idTipoColaboracion==propuestaColaboracionTemporal.getIdTipoColaboracion()&&
                objetivo.equals(propuestaColaboracionTemporal.getObjetivo());                
    }
    
    
}
