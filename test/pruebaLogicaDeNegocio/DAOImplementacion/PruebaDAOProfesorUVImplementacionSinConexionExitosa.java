package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.clases.ProfesorUV;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PruebaDAOProfesorUVImplementacionSinConexionExitosa {
    
    @Test
    public void pruebaRegistrarProfesorUVSinConexionExitosa(){
        ProfesorUV profesorUV = new ProfesorUV();              
        profesorUV.setIdProfesor(0);                
        profesorUV.setNumeroDePersonal("1234");
        profesorUV.setCategoriaDeContratacion("Docente");
        profesorUV.setTipoDeContratacion("Planta");        
        profesorUV.setIdRegion(1);       
        profesorUV.setIdAreaAcademica(1);
        DAOProfesorUVImplementacion dao=new DAOProfesorUVImplementacion();        
        int resultado = dao.registrarProfesorUV(profesorUV);
        assertEquals(-1, resultado);    
    }
    
    @Test
    public void pruebaConsultarProfesoresUVSinConexionExitosa(){
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        List<ProfesorUV> resultadoObtenido=instancia.consultarProfesoresUV();   
        assertTrue(resultadoObtenido.isEmpty());                
    }
    
    @Test
    public void pruebaObtenerProfesorUVPorIDProfesorSinConexionExitosa(){                
        DAOProfesorUVImplementacion daoProfesorUV=new DAOProfesorUVImplementacion();        
        ProfesorUV profesorObtenido=daoProfesorUV.obtenerProfesorUVPorIDProfesor(0);
        assertNull(profesorObtenido);        
    }
    
    @Test
    public void pruebaValidarInexistenciaDeProfesorUVSinConexionExitosa(){
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.validarInexistenciaProfesorUV("1111");
        assertEquals(-1,resultado);        
    }
    
    @Test
    public void pruebaEditarTipoDeContratacionDeProfesorUVPorIdProfesorUVSinConexionExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarTipoDeContratacionDeProfesorUVPorIdProfesorUV("Eventual", 0);
        assertEquals(-1, resultado);
    }
    
    @Test 
    public void pruebaEditarCategoriaDeContratacionDeProfesorUVPorIdProfesorUVSinConexionExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarCategoriaDeContratacionDeProfesorUVPorIdProfesorUV("Técnico académico", 0);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaEditarAreaAcademicaDeProfesorUVPorIdProfesorUVsinConexionExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarAreaAcademicaDeProfesorUVPorIdProfesorUV(3, 0);
        assertEquals(-1, resultado);                 
    }
    
    @Test
    public void pruebaEditarRegionDeProfesorUVPorIdProfesorUVExitosasinConexionExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarRegionDeProfesorUVPorIdProfesorUV(3, 0);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaEditarNumeroDePersonalPorIdProfesorUVSinConexionExitosa(){
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultadoObtenido=dao.editarNumeroDePersonalPorIdProfesorUV("4444", 0);
        assertEquals(-1,resultadoObtenido);        
    }
    
}
