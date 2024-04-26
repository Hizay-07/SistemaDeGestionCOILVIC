package logicaDeNegocio.clases;
import java.util.regex.Pattern;

public class ProfesorExterno extends Profesor {
    
    private int idProfesorExterno;
    private int idRepresentanteInstitucional;
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    public ProfesorExterno() {
    }
       
    public int getIdProfesorExterno() {
        return idProfesorExterno;
    }

    public void setIdProfesorExterno(int idProfesorExterno)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idProfesorExterno))){
            this.idProfesorExterno = idProfesorExterno;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public int getIdRepresentanteInstitucional() {
        return idRepresentanteInstitucional;
    }

    public void setIdRepresentanteInstitucional(int idRepresentanteInstitucional)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idRepresentanteInstitucional))){
            this.idRepresentanteInstitucional = idRepresentanteInstitucional;
        }else{
            throw new IllegalArgumentException();
        }
    }
   
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProfesorExterno)) {
            return false;
        }
        ProfesorExterno profesorExternoTemporal = (ProfesorExterno) obj;
        return idProfesorExterno == profesorExternoTemporal.getIdProfesorExterno();
    }
    
    @Override
    public boolean validarAtributos(){
        return idRepresentanteInstitucional>0;
    }
    
}
