package logicaDeNegocio.clases;

public class ProfesorUV extends Profesor {
    private String numeroDePersonal;
    private String tipoDeContratacion;
    private String categoriaDeContratacion;
    private int idAreaAcademica;
    private int idRegion;
    private int idProfesorUV;
    
    public ProfesorUV() {
    }

    public String getNumeroDePersonal() {
        return numeroDePersonal;
    }

    public void setNumeroDePersonal(String numeroDePersonal) {
        this.numeroDePersonal = numeroDePersonal;
    }       
    
    public String getTipoDeContratacion() {
        return tipoDeContratacion;
    }

    public void setTipoDeContratacion(String tipoDeContratacion) {
        this.tipoDeContratacion = tipoDeContratacion;
    }

    public String getCategoriaDeContratacion() {
        return categoriaDeContratacion;
    }

    public void setCategoriaDeContratacion(String categoriaDeContratacion) {
        this.categoriaDeContratacion = categoriaDeContratacion;
    }

    public int getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(int idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }  

    public int getIdProfesorUV() {
        return idProfesorUV;
    }

    public void setIdProfesorUV(int idProfesorUV) {
        this.idProfesorUV = idProfesorUV;
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
            

    
}
