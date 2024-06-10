package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.Actividad;

public final class ActividadAuxiliar {
    
    private static ActividadAuxiliar instancia;
    private int idActividad;
    private int numeroActividad;
    private String nombre;
    private String descripcion;
    private String fechaDeInicio;
    private String fechaDeCierre;
    private String estado;
    private int idColaboracion;

    private ActividadAuxiliar(Actividad actividad) {
        setIdActividad(actividad.getIdActividad());
        setNumeroActividad(actividad.getNumeroActividad());
        setNombre(actividad.getNombre());
        setDescripcion(actividad.getDescripcion());
        setFechaDeInicio(actividad.getFechaDeInicio());
        setFechaDeCierre(actividad.getFechaDeCierre());
        setEstado(actividad.getEstado());
        setIdColaboracion(actividad.getIdColaboracion());
    }
    
    public static void setInstancia(Actividad actividad){
        instancia = new ActividadAuxiliar(actividad);
    }
    
    public static ActividadAuxiliar getInstancia(){
        return instancia;
    }
    
    public static void resetInstancia(){
        instancia = null;
    }
    
    public int getIdActividad() {
        return idActividad;
    }

    private void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getNumeroActividad() {
        return numeroActividad;
    }

    private void setNumeroActividad(int numeroActividad) {
        this.numeroActividad = numeroActividad;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDeInicio() {
        return fechaDeInicio;
    }

    private void setFechaDeInicio(String fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public String getFechaDeCierre() {
        return fechaDeCierre;
    }

    private void setFechaDeCierre(String fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }

    public String getEstado() {
        return estado;
    }

    private void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    private void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }
    
}

