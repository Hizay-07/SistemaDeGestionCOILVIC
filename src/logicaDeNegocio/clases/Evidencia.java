package logicaDeNegocio.clases;

public class Evidencia {
    private int idEvidencia;
    private String nombre;
    private String rutaEvidencia;
    private int idActividad;

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaEvidencia() {
        return rutaEvidencia;
    }

    public void setRutaEvidencia(String evidencia) {
        this.rutaEvidencia = evidencia;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
    
    @Override
    public boolean equals(Object obj){
        Evidencia evidenciaTemp = (Evidencia)obj;
        return this.idEvidencia == evidenciaTemp.getIdEvidencia() && this.nombre.equals(evidenciaTemp.getNombre()) && this.rutaEvidencia.equals(evidenciaTemp.getRutaEvidencia())
                &&this.idActividad == evidenciaTemp.getIdActividad();
    }
}
