
package logicaDeNegocio.clases;

public class ParticipanteCOILVIC {
    
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String correo;
    protected RepresentanteInstitucional institucion;

    public ParticipanteCOILVIC(){
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public RepresentanteInstitucional getInstitucion() {
        return institucion;
    }

    public void setInstitucion(RepresentanteInstitucional institucion) {
        this.institucion = institucion;
    }
    
    
}
