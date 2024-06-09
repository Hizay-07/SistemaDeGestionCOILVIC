package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class RepresentanteInstitucional{    
    private int idRepresentanteInstitucional;
    private String nombreInstitucion;
    private String claveInstitucional;
    private String contacto;
    private Pais pais;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+)*$";
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
        if(nombreInstitucion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, nombreInstitucion.trim())&&nombreInstitucion.trim().length()<=45){
            this.nombreInstitucion = nombreInstitucion.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getClaveInstitucional() {
        return claveInstitucional;
    }

    public void setClaveInstitucional(String claveInstitucional)throws IllegalArgumentException {
        if(claveInstitucional!=null&&Pattern.matches(SOLO_LETRAS_Y_NUMEROS_PATTERN, claveInstitucional.trim())&&claveInstitucional.trim().length()<=20){
            this.claveInstitucional = claveInstitucional.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto)throws IllegalArgumentException{
        if(contacto!=null&&Pattern.matches(EMAIL_PATTERN, contacto.trim())&&contacto.trim().length()<=60){
            this.contacto = contacto.trim().replaceAll("\\s+", " ");
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
        if(!(obj instanceof RepresentanteInstitucional)){
            return false;
        }                        
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
