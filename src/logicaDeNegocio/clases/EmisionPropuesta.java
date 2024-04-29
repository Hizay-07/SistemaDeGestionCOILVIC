package logicaDeNegocio.clases;

public class EmisionPropuesta {
    private int idProfesor;
    private int idPropuestaColaboracion;
    private String fechaEmision;

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
        this.fechaEmision = fechaEmision;
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
