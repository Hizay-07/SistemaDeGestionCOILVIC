package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOProfesorUVImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
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
        ProfesorUV profesorEsperado = new ProfesorUV();
        profesorEsperado.setCategoriaDeContratacion("Excepcion");
        assertEquals(profesorEsperado.getCategoriaDeContratacion(),resultadoObtenido.get(0).getCategoriaDeContratacion());            
    }
    
    @Test
    public void pruebaObtenerProfesorUVPorIDProfesorSinConexionExitosa(){                
        DAOProfesorUVImplementacion daoProfesorUV=new DAOProfesorUVImplementacion();        
        ProfesorUV profesorObtenido=daoProfesorUV.obtenerProfesorUVPorIDProfesor(2);
        ProfesorUV profesorEsperado = new ProfesorUV();
        profesorEsperado.setCategoriaDeContratacion("Excepcion");
        assertEquals(profesorEsperado.getCategoriaDeContratacion(),profesorObtenido.getCategoriaDeContratacion());        
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
