package logicaDeNegocio.clases;

public class ProfesorExterno extends Profesor {
    
    private int idProfesorExterno;
    private int idRepresentanteInstitucional;
    
    public ProfesorExterno() {
    }
       
    public int getIdProfesorExterno() {
        return idProfesorExterno;
    }

    public void setIdProfesorExterno(int idProfesorExterno) {
        this.idProfesorExterno = idProfesorExterno;
    }
    
    public int getIdRepresentanteInstitucional() {
        return idRepresentanteInstitucional;
    }

    public void setIdRepresentanteInstitucional(int idRepresentanteInstitucional) {
        this.idRepresentanteInstitucional = idRepresentanteInstitucional;
    }
   
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
}
