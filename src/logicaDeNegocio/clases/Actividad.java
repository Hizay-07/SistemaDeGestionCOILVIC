package logicaDeNegocio.clases;

public class Actividad {
    private int idActividad;
    private int numeroActividad;
    private String nombre;
    private String descripcion;
    private String fechaDeInicio;
    private String fechaDeCierre;
    private int idColaboracion;

    public Actividad() {
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getNumeroActividad() {
        return numeroActividad;
    }

    public void setNumeroActividad(int numeroActividad) {
        this.numeroActividad = numeroActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(String fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public String getFechaDeCierre() {
        return fechaDeCierre;
    }

    public void setFechaDeCierre(String fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion) {
        this.idColaboracion = idColaboracion;
    }        
    
    @Override
    public boolean equals(Object obj){
        Actividad actividadTemp = (Actividad)obj;
        return this.nombre.equals(actividadTemp.getNombre())&&this.descripcion.equals(actividadTemp.getDescripcion())&&
               this.fechaDeCierre.equals(actividadTemp.getFechaDeCierre())&&this.fechaDeInicio.equals(actividadTemp.getFechaDeInicio())&&
               this.idActividad == actividadTemp.getIdActividad()&&this.idColaboracion == actividadTemp.getIdColaboracion()&&
               this.numeroActividad == actividadTemp.getNumeroActividad();
    }
    
}
