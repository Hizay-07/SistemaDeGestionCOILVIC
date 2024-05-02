package logicaDeNegocio.clases;

import java.util.regex.Pattern;

public class AreaAcademica {
    
    private int idAreaAcademica;
    private String area;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ-]+$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    
    public AreaAcademica() {
    }        

    public int getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(int idAreaAcademica)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idAreaAcademica))){
            this.idAreaAcademica = idAreaAcademica;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area)throws IllegalArgumentException {
        if(area!=null&&Pattern.matches(SOLO_LETRAS_PATTERN, area)){
            this.area = area;
        }else{
            throw new IllegalArgumentException();
        }
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
