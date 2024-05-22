package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class RegionAcademica {
    private int idRegionAcademica;
    private String region;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ']+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public RegionAcademica() {
        
    }        

    public int getIdRegionAcademica() {
        return idRegionAcademica;
    }

    public void setIdRegionAcademica(int idRegionAcademica)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idRegionAcademica))){
            this.idRegionAcademica = idRegionAcademica;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region)throws IllegalArgumentException {
        if(region!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, region.trim())&&region.trim().length()>=100){
            this.region = region.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
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
