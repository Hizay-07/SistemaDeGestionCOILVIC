package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.ProfesorUV;

public interface ProfesorUVInterface {
    public int registrarProfesorUV(ProfesorUV profesorUV);
        
    public List<ProfesorUV> consultarProfesoresUV();
    
    public List<ProfesorUV> consultarProfesoresUVPorAreaAcademica(int idAreaAcademica);
    
    public List<ProfesorUV> consultarProfesoresUVPorRegion(int idRegion);
    
    public int editarTipoDeContratacionDeProfesorUVPorNumeroDePersonal(String tipoDeContratacion,String numeroDePersonal);
    
    public int editarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonal(String categoriaDeContratacion,String numeroDePersonal);
    
    public int editarAreaAcademicaDeProfesorUVPorNumeroDePersonal(int areaAcademica,String numeroDePersonal);
    
    public int editarRegionDeProfesorUVPorNumeroDePersonal(int region,String numeroDePersonal);
    
     public int eliminarProfesorUV(String correo);
              
}
