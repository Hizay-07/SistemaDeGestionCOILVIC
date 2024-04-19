package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.RegionAcademica;

public interface RegionAcademicaInterface {
    public int registrarRegionAcademica(RegionAcademica regionAcademica);
    
    public List<RegionAcademica> consultarRegionesAcademicas();                
}
