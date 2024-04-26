package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class Actividad {
    private int idActividad;
    private int numeroActividad;
    private String nombre;
    private String descripcion;
    private String fechaDeInicio;
    private String fechaDeCierre;
    private String estado;
    private int idColaboracion;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";


    public Actividad() {
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idActividad))){
            this.idActividad = idActividad;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getNumeroActividad() {
        return numeroActividad;
    }

    public void setNumeroActividad(int numeroActividad)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(numeroActividad))){
            this.numeroActividad = numeroActividad;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre)throws IllegalArgumentException {
        if(nombre!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombre)){
            this.nombre = nombre;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion)throws IllegalArgumentException {
        if(descripcion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, descripcion)){
            this.descripcion = descripcion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(String fechaDeInicio)throws IllegalArgumentException {
        if(fechaDeInicio!=null&&Pattern.matches(FECHA_PATTERN, fechaDeInicio)){
            this.fechaDeInicio = fechaDeInicio;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getFechaDeCierre() {
        return fechaDeCierre;
    }

    public void setFechaDeCierre(String fechaDeCierre)throws IllegalArgumentException {
        if(fechaDeCierre!=null&&Pattern.matches(FECHA_PATTERN, fechaDeCierre)){
            this.fechaDeCierre = fechaDeCierre;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado)throws IllegalArgumentException {
        if(estado!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estado)){
            this.estado = estado;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdColaboracion() {
        return idColaboracion;
    }

    public void setIdColaboracion(int idColaboracion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idColaboracion))){
            this.idColaboracion = idColaboracion;
        }else{
            throw new IllegalArgumentException();
        }
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
