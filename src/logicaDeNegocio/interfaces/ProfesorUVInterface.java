package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.ProfesorUV;

public interface ProfesorUVInterface {
    public int registrarProfesorUV(ProfesorUV profesorUV);
        
    public List<ProfesorUV> consultarProfesoresUV();
    
    public List<ProfesorUV> consultarProfesoresUVPorAreaAcademica(int idAreaAcademica);
    
    public List<ProfesorUV> consultarProfesoresUVPorRegion(int idRegion);
    
    public int editarTipoDeContratacionDeProfesorUVPorIdProfesorUV(String tipoDeContratacion,int idProfesorUV);
    
    public int editarCategoriaDeContratacionDeProfesorUVPorIdProfesorUV(String categoriaDeContratacion,int idProfesorUV);
    
    public int editarAreaAcademicaDeProfesorUVPorIdProfesorUV(int areaAcademica,int idProfesorUV);
    
    public int editarRegionDeProfesorUVPorIdProfesorUV(int region,int idProfesorUV);
    
    public int editarNumeroDePersonalPorIdProfesorUV(String numeroDePersonal, int idProfesorUV);
    
    public ProfesorUV obtenerProfesorUVPorIDProfesor(int idProfesor);
    
    public int validarInexistenciaProfesorUV(String noPersonal);
    
    public int eliminarProfesorUV(String correo);
              
}
