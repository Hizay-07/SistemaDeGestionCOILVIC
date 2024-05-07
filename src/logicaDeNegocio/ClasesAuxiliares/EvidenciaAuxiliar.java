package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.Evidencia;

public final class EvidenciaAuxiliar {
    private static EvidenciaAuxiliar instancia;
    private int idEvidencia;
    private String nombre;
    private String rutaEvidencia;
    private int idActividad;
    
    private EvidenciaAuxiliar(Evidencia evidencia){
        setIdEvidencia(evidencia.getIdEvidencia());
        setIdActividad(evidencia.getIdActividad());
        setNombre(evidencia.getNombre());
        setRutaEvidencia(evidencia.getRutaEvidencia());
    }
    
    public static void setInstancia(Evidencia evidencia){
        instancia = new EvidenciaAuxiliar(evidencia);
    }
    
    public static EvidenciaAuxiliar getEvidencia(){
        return instancia;
    }
    
    public void resetInstancia(){
        instancia = null;
    }

    public int getIdEvidencia() {
        return idEvidencia;
    }

    private void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaEvidencia() {
        return rutaEvidencia;
    }

    private void setRutaEvidencia(String rutaEvidencia) {
        this.rutaEvidencia = rutaEvidencia;
    }

    public int getIdActividad() {
        return idActividad;
    }

    private void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
    
    
}
