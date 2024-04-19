package logicaDeNegocio.clases;

public class Colaboracion {
    private int idColaboracion;
    private String retroalimentacion;
    private String estadoColaboracion;
    private int idPropuestaColaboracion;

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
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Colaboracion)){
            return false;
        }
        Colaboracion colaboracionTemporal=(Colaboracion)obj;
        return idColaboracion==colaboracionTemporal.getIdColaboracion()&&
                idPropuestaColaboracion==colaboracionTemporal.getIdPropuestaColaboracion();                        
    }
    
    
    
}
