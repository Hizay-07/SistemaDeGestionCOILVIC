package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.Colaboracion;

public final class ColaboracionAuxiliar {
    
    private static ColaboracionAuxiliar instancia;
    private int idColaboracion;
    private String retroalimentacion;
    private String estadoColaboracion;
    private int idPropuestaColaboracion;

    private ColaboracionAuxiliar(Colaboracion colaboracion) {
        setIdColaboracion(colaboracion.getIdColaboracion());
        setRetroalimentacion(colaboracion.getRetroalimentacion());
        setEstadoColaboracion(colaboracion.getEstadoColaboracion());
        setIdPropuestaColaboracion(colaboracion.getIdPropuestaColaboracion());
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

    public int getIdPropuestaColaboracion() {
        return idPropuestaColaboracion;
    }

    private void setIdPropuestaColaboracion(int idPropuestaColaboracion) {
        this.idPropuestaColaboracion = idPropuestaColaboracion;
    }
    
}
