package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.AreaAcademica;

public interface AreaAcademicaInterface {          
    
    public List<AreaAcademica> consultarAreasAcademicas();
    
    public int consultarIdDeAreaAcademicaPorArea(String area);
    
    public int verificarAreaAcademica();
    
}
