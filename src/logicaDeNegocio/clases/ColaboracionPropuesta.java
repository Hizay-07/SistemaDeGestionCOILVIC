package logicaDeNegocio.clases;

public class ColaboracionPropuesta {
    
    private Colaboracion colaboracion;
    private PropuestaColaboracion propuestaColaboracion;

    public ColaboracionPropuesta() {
    }

    public Colaboracion getColaboracion() {
        return colaboracion;
    }

    public void setColaboracion(Colaboracion colaboracion) {
        this.colaboracion = colaboracion;
    }

    public PropuestaColaboracion getPropuestaColaboracion() {
        return propuestaColaboracion;
    }

    public void setPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion) {
        this.propuestaColaboracion = propuestaColaboracion;
    }
       
}
