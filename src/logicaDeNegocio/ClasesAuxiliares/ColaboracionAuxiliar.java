package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.PropuestaColaboracion;

public final class ColaboracionAuxiliar {
    
    private static ColaboracionAuxiliar instancia;
    private int idColaboracion;
    private String retroalimentacion;
    private String estadoColaboracion;
    private int cantidadDeEstudiantes;
    private PropuestaColaboracion propuestaColaboracion;

    private ColaboracionAuxiliar(Colaboracion colaboracion) {
        setIdColaboracion(colaboracion.getIdColaboracion());
        setRetroalimentacion(colaboracion.getRetroalimentacion());
        setEstadoColaboracion(colaboracion.getEstadoColaboracion());
        setPropuestaColaboracion(colaboracion.getPropuestaColaboracion());
        setCantidadEstudiantes(colaboracion.getCantidadEstudiantes());
    }
    
    public static void setInstancia(Colaboracion colaboracion){
        instancia = new ColaboracionAuxiliar(colaboracion);
    }
    
    public static ColaboracionAuxiliar getInstancia(){
        return instancia;
    }
    
    public void resetInstancia(){
        instancia = null;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    private void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }
    
    private void setCantidadEstudiantes(int cantidadEstudiantes){
        this.cantidadDeEstudiantes = cantidadEstudiantes;
    }
    
    public int getCantidadEstudiantes(){
        return cantidadDeEstudiantes;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    private void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    public String getEstadoColaboracion() {
        return estadoColaboracion;
    }

    private void setEstadoColaboracion(String estadoColaboracion) {
        this.estadoColaboracion = estadoColaboracion;
    }

    public PropuestaColaboracion getPropuestaColaboracion() {
        return propuestaColaboracion;
    }

    private void setPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion) {
        this.propuestaColaboracion = propuestaColaboracion;
    }
    
}
