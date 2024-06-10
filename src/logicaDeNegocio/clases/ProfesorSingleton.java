package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public final class ProfesorSingleton {

    private static ProfesorSingleton instancia;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String estado;
    private int idProfesor;

    private ProfesorSingleton(Profesor profesor) {
        setNombre(profesor.getNombre());
        setApellidoPaterno(profesor.getApellidoPaterno());
        setApellidoMaterno(profesor.getApellidoMaterno());
        setCorreo(profesor.getCorreo());
        setEstado(profesor.getEstado());
        setIdProfesor(profesor.getIdProfesor());
    }

    public static void setInstancia(Profesor profesor) {
        instancia = new ProfesorSingleton(profesor);
    }

    public static ProfesorSingleton getInstancia() {
        return instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) throws IllegalArgumentException {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) throws IllegalArgumentException {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) throws IllegalArgumentException {
        this.correo = correo;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) throws IllegalArgumentException {
        this.idProfesor = idProfesor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) throws IllegalArgumentException {
        this.estado = estado;
    }

    public static void resetSingleton() {
        ProfesorSingleton.instancia = null;
    }
    
}
