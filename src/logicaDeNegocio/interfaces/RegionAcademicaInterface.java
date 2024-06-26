package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.RegionAcademica;

public interface RegionAcademicaInterface { 
    
    public List<RegionAcademica> consultarRegionesAcademicas();                
    
    public int consultarIdDeRegionPorRegion(String region);
    
    public int verificarRegion();
    
}
