
package logicaDeNegocio.clases;

public class Colaboracion {
<<<<<<< HEAD
    private int idColaboracion;
    private String retroalimentacion;
    private String estadoColaboracion;
    private int idPropuestaColaboracion;
=======
    private String tipoColaboracion;
    private String fechaInicio;
    private String fechaCierre;
    private String idioma;
    private String experienciaEducativa;
    private int idColaboracion;
>>>>>>> 9fda9407ddcf920dd702160fd57cbfce28ad6525

    public Colaboracion() {
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    public String getEstadoColaboracion() {
        return estadoColaboracion;
    }

    public void setEstadoColaboracion(String estadoColaboracion) {
        this.estadoColaboracion = estadoColaboracion;
    }

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }

    public void setIdPropuestaColaboracion(int idPropuestaColaboracion) {
        this.idPropuestaColaboracion = idPropuestaColaboracion;
    }
    
    public boolean equals(Object obj) {
        Colaboracion colaboracionTemporal=(Colaboracion)obj;
        return idColaboracion==colaboracionTemporal.getIdColaboracion()&&
                idPropuestaColaboracion==colaboracionTemporal.getIdPropuestaColaboracion();                        
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
