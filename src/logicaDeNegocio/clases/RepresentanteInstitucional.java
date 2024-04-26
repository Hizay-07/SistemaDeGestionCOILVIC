package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class RepresentanteInstitucional{
    private String nombreInstitucion;
    private String claveInstitucional;
    private String contacto;
    private String estadoRepresentante;
    private Pais pais;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    public RepresentanteInstitucional(){
        
    }
        
    public String getNombreInstitucion(){
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion)throws IllegalArgumentException {
        if(nombreInstitucion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombreInstitucion)){
            this.nombreInstitucion = nombreInstitucion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getClaveInstitucional() {
        return claveInstitucional;
    }

    public void setClaveInstitucional(String claveInstitucional)throws IllegalArgumentException {
        if(claveInstitucional!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, claveInstitucional)){
            this.claveInstitucional = claveInstitucional;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto)throws IllegalArgumentException{
        if(contacto!=null&&Pattern.matches(SOLO_NUMEROS_PATTERN, contacto)){
            this.contacto = contacto;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getEstadoRepresentante() {
        return estadoRepresentante;
    }

    public void setEstadoRepresentante(String estadoRepresentante)throws IllegalArgumentException {
        if(estadoRepresentante!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, estadoRepresentante)){
            this.estadoRepresentante = estadoRepresentante;
        }else{
            throw new IllegalArgumentException();
        }
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
