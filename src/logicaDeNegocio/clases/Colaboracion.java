package logicaDeNegocio.clases;


public class Colaboracion {
    private String tipoColaboracion;
    private String fechaInicio;
    private String fechaCierre;
    private String idioma;
    private String experienciaEducativa;
    private int idColaboracion;

    public Colaboracion() {
    }
    
    public String getTipoColaboracion() {
        return tipoColaboracion;
    }

    public void setTipoColaboracion(String tipoColaboracion) {
        this.tipoColaboracion = tipoColaboracion;
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

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }       
    
    public boolean equals(Object obj){
        Colaboracion colaboracionTemporal=(Colaboracion)obj;
        return tipoColaboracion.equals(colaboracionTemporal.getTipoColaboracion())&&
                fechaInicio.equals(colaboracionTemporal.getFechaInicio())&&
                fechaCierre.equals(colaboracionTemporal.getFechaCierre())&&
                idioma.equals(colaboracionTemporal.getIdioma())&&
                experienciaEducativa.equals(colaboracionTemporal.getExperienciaEducativa());        
    }
    
    
}
