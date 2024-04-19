package logicaDeNegocio.clases;

public class RegionAcademica {
    private int idRegionAcademica;
    private String region;

    public RegionAcademica() {
    }        

    public int getIdRegionAcademica() {
        return idRegionAcademica;
    }

    public void setIdRegionAcademica(int idRegionAcademica) {
        this.idRegionAcademica = idRegionAcademica;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof RegionAcademica)){
            return false;
        }
        RegionAcademica regionAcademicaTemporal=(RegionAcademica)obj;
        return region.equals(regionAcademicaTemporal.getRegion());    
    }
    
}
