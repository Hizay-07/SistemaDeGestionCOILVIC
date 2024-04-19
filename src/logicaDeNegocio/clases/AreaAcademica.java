package logicaDeNegocio.clases;

public class AreaAcademica {
    private int idAreaAcademica;
    private String area;

    public AreaAcademica() {
    }        

    public int getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(int idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof AreaAcademica)){
            return false;
        }
        AreaAcademica areaAcademicaTemporal=(AreaAcademica)obj;
        return area.equals(areaAcademicaTemporal.getArea());
    }                
    
}
