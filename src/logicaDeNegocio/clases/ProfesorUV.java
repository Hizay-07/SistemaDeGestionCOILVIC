package logicaDeNegocio.clases;
import java.util.regex.Pattern;

public class ProfesorUV extends Profesor {
    private String numeroDePersonal;
    private String tipoDeContratacion;
    private String categoriaDeContratacion;
    private int idAreaAcademica;
    private int idRegion;
    private int idProfesorUV;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    public ProfesorUV() {
    }

    public String getNumeroDePersonal() {
        return numeroDePersonal;
    }

    public void setNumeroDePersonal(String numeroDePersonal)throws IllegalArgumentException{
        if(numeroDePersonal!=null&&Pattern.matches(SOLO_NUMEROS_PATTERN,numeroDePersonal)){
            this.numeroDePersonal = numeroDePersonal;
        }else{
            throw new IllegalArgumentException();
        }
    }       
    
    public String getTipoDeContratacion() {
        return tipoDeContratacion;
    }

    public void setTipoDeContratacion(String tipoDeContratacion)throws IllegalArgumentException {
        if(tipoDeContratacion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN,tipoDeContratacion)){
            this.tipoDeContratacion = tipoDeContratacion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getCategoriaDeContratacion() {
        return categoriaDeContratacion;
    }

    public void setCategoriaDeContratacion(String categoriaDeContratacion)throws IllegalArgumentException {
        if(categoriaDeContratacion!=null&&Pattern.matches(SOLO_LETRAS_PATTERN,categoriaDeContratacion)){
            this.categoriaDeContratacion = categoriaDeContratacion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(int idAreaAcademica)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN,String.valueOf(idAreaAcademica))){
            this.idAreaAcademica = idAreaAcademica;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion)throws IllegalArgumentException{
        if(Pattern.matches(SOLO_NUMEROS_PATTERN,String.valueOf(idRegion))){
            this.idRegion = idRegion;
        }else{
            throw new IllegalArgumentException();
        }
    }  

    public int getIdProfesorUV() {
        return idProfesorUV;
    }

    public void setIdProfesorUV(int idProfesorUV)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN,String.valueOf(idProfesorUV))){
            this.idProfesorUV = idProfesorUV;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){ 
        if(!(obj instanceof ProfesorUV)){
            return false;
        }
        ProfesorUV profesorUVTemporal=(ProfesorUV)obj;
        if(numeroDePersonal==null || profesorUVTemporal.getNumeroDePersonal()==null){
            return false;
        }
        return numeroDePersonal.equals(profesorUVTemporal.getNumeroDePersonal());
    }
    
    @Override
    public boolean validarAtributos(){
        return numeroDePersonal!=null&&!numeroDePersonal.isEmpty()&&
                tipoDeContratacion!=null&&!tipoDeContratacion.isEmpty()&&
                categoriaDeContratacion!=null&&!categoriaDeContratacion.isEmpty()&&
                idAreaAcademica>0&&
                idRegion>0;                
    }                
    
}
