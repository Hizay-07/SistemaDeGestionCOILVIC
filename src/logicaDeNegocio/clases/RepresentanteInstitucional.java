package logicaDeNegocio.clases;


public class RepresentanteInstitucional{
    private String nombreInstitucion;
    private String claveInstitucional;
    private String contacto;
    private Pais pais;

    public RepresentanteInstitucional(){
        
    }
    
    
    public String getNombreInstitucion(){
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getClaveInstitucional() {
        return claveInstitucional;
    }

    public void setClaveInstitucional(String claveInstitucional) {
        this.claveInstitucional = claveInstitucional;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    @Override
    public boolean equals(Object obj){
        RepresentanteInstitucional representanteTemporal = (RepresentanteInstitucional)obj;
        return nombreInstitucion.equals(representanteTemporal.getNombreInstitucion())&&
                claveInstitucional.equals(representanteTemporal.getClaveInstitucional())&&
                contacto.equals(representanteTemporal.getContacto())&&
                pais.equals(representanteTemporal.getPais());
    }
    
}
