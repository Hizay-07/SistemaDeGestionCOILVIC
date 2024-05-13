package logicaDeNegocio.ClasesAuxiliares;

import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.RepresentanteInstitucional;

public final class RepresentanteAuxiliar {
    
    private static RepresentanteAuxiliar instancia;
    private String nombreInstitucion;
    private int idRepresentanteInstitucional;
    private String claveInstitucional;
    private String contacto;
    private String estadoRepresentante;
    private Pais pais;
    
    private RepresentanteAuxiliar(RepresentanteInstitucional representante){
        setNombreInstitucion(representante.getNombreInstitucion());
        setClaveInstitucional(representante.getClaveInstitucional());
        setContacto(representante.getContacto());
        setEstadoRepresentante(representante.getEstadoRepresentante());
        setPais(representante.getPais());
        setIdRepresentanteInstitucional(representante.getIdRepresentanteInstitucional());
    }

    public static RepresentanteAuxiliar getInstancia() {
        return instancia;
    }

    public static void setInstancia(RepresentanteInstitucional representante) {
        RepresentanteAuxiliar.instancia = new RepresentanteAuxiliar(representante);
    }

    public int getIdRepresentanteInstitucional() {
        return idRepresentanteInstitucional;
    }

    public void setIdRepresentanteInstitucional(int idRepresentanteInstitucional) {
        this.idRepresentanteInstitucional = idRepresentanteInstitucional;
    }
   
    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    private void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getClaveInstitucional() {
        return claveInstitucional;
    }

    private void setClaveInstitucional(String claveInstitucional) {
        this.claveInstitucional = claveInstitucional;
    }

    public String getContacto() {
        return contacto;
    }

    private void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEstadoRepresentante() {
        return estadoRepresentante;
    }

    private void setEstadoRepresentante(String estadoRepresentante) {
        this.estadoRepresentante = estadoRepresentante;
    }

    public Pais getPais() {
        return pais;
    }

    private void setPais(Pais pais) {
        this.pais = pais;
    }
    
    
}
