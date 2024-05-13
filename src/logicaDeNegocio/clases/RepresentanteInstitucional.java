package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class RepresentanteInstitucional{
    
    private int idRepresentanteInstitucional;
    private String nombreInstitucion;
    private String claveInstitucional;
    private String contacto;
    private String estadoRepresentante;
    private Pais pais;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String SOLO_LETRAS_Y_NUMEROS_PATTERN ="^[\\p{L}0-9]+$";
    
    public RepresentanteInstitucional(){
        
    }

    public int getIdRepresentanteInstitucional() {
        return idRepresentanteInstitucional;
    }

    public void setIdRepresentanteInstitucional(int idRepresentanteInstitucional) {
        this.idRepresentanteInstitucional = idRepresentanteInstitucional;
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
        if(claveInstitucional!=null&&Pattern.matches(SOLO_LETRAS_Y_NUMEROS_PATTERN, claveInstitucional)){
            this.claveInstitucional = claveInstitucional;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto)throws IllegalArgumentException{
        if(contacto!=null&&Pattern.matches(EMAIL_PATTERN, contacto)){
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
    
    public boolean validarDatos(){
        return nombreInstitucion!=null&&!nombreInstitucion.isEmpty()&&
                claveInstitucional!=null&&!claveInstitucional.isEmpty()&&
                contacto!=null&&!contacto.isEmpty()&&
                pais!=null;                                
    }
    
}
