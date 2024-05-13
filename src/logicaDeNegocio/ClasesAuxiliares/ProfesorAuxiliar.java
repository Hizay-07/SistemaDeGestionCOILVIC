package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.Profesor;

public final class ProfesorAuxiliar {
    
    private static ProfesorAuxiliar instancia;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo; 
    private String estado;
    private int idProfesor;
    
    private ProfesorAuxiliar(Profesor profesor){
        setNombre(profesor.getNombre());
        setApellidoPaterno(profesor.getApellidoPaterno());
        setApellidoMaterno(profesor.getApellidoMaterno());
        setCorreo(profesor.getCorreo());
        setEstado(profesor.getEstado());
        setIdProfesor(profesor.getIdProfesor());
    }

    public static ProfesorAuxiliar getInstancia() {
        return instancia;
    }

    public static void setInstancia(Profesor profesor) {
        ProfesorAuxiliar.instancia = new ProfesorAuxiliar(profesor);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    private void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    private void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    private void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    private void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    private void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
    
}
