package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorUVImplementacion;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOProfesorUVImplementacion {        
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setIdProfesor(2);                
        profesorUV.setNumeroDePersonal("1234");
        profesorUV.setCategoriaDeContratacion("Docente");
        profesorUV.setTipoDeContratacion("Planta");        
        profesorUV.setIdRegion(1);       
        profesorUV.setIdAreaAcademica(1);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();        
        instancia.registrarProfesorUV(profesorUV);
    }                    
    
    @Test
    public void pruebaConsultarProfesoresUVExitoso() {
        DAOProfesorUVImplementacion dao=new DAOProfesorUVImplementacion(); 
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setNumeroDePersonal("1234");
        List<ProfesorUV> profesoresEsperados=new ArrayList<>();
        profesoresEsperados.add(profesorUV);        
        List<ProfesorUV> profesoresObtenidos = dao.consultarProfesoresUV();        
        assertEquals(profesoresEsperados,profesoresObtenidos);
    }
    
    @Test
    public void pruebaConsultarProfesoresUVFallida(){
        List<ProfesorUV> resultadoEsperado=new ArrayList<>();
        ProfesorUV profesorUV=new ProfesorUV();        
        resultadoEsperado.add(profesorUV);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        List<ProfesorUV> resultadoObtenido=instancia.consultarProfesoresUV();        
        assertNotEquals(resultadoEsperado,resultadoObtenido);            
    }           
    
    @Test
    public void pruebaObtenerProfesorUVPorIDProfesorExitosa(){
        ProfesorUV profesorEsperado=new ProfesorUV();
        profesorEsperado.setNumeroDePersonal("3333");
        DAOProfesorUVImplementacion daoProfesorUV=new DAOProfesorUVImplementacion();        
        ProfesorUV profesorObtenido=daoProfesorUV.obtenerProfesorUVPorIDProfesor(2);
        assertEquals(profesorEsperado,profesorObtenido);        
    }
    
    @Test
    public void pruebaObtenerProfesorUVPorIDProfesorFallida(){
        ProfesorUV profesorEsperado=new ProfesorUV();
        profesorEsperado.setNumeroDePersonal("1234");
        DAOProfesorUVImplementacion daoProfesorUV=new DAOProfesorUVImplementacion();        
        ProfesorUV profesorObtenido=daoProfesorUV.obtenerProfesorUVPorIDProfesor(0);
        assertNotEquals(profesorEsperado,profesorObtenido);        
    }            
    
    @Test
    public void pruebaValidarInexistenciaDeProfesorUVExitosa(){
         DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
         int resultado = dao.validarInexistenciaProfesorUV("1111");
         assertEquals(0,resultado);
    }
    
    @Test
    public void pruebaValidarInexistenciaDeProfesorUVFallida(){
         DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
         int resultado = dao.validarInexistenciaProfesorUV("3333");
         assertEquals(1,resultado);
    }        
        
    @Test
    public void pruebaEditarTipoDeContratacionDeProfesorUVPorIdProfesorUVExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarTipoDeContratacionDeProfesorUVPorIdProfesorUV("Suplente", 1);
        assertEquals(1, resultado);
    }

    @Test
    public void pruebaEditarTipoDeContratacionDeProfesorUVPorIdProfesorUVFallida() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarTipoDeContratacionDeProfesorUVPorIdProfesorUV("Eventual", 0);
        assertEquals(0, resultado);
    }            
        
    @Test
    public void pruebaEditarCategoriaDeContratacionDeProfesorUVPorIdProfesorUVExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarCategoriaDeContratacionDeProfesorUVPorIdProfesorUV("Investigador", 1);
        assertEquals(1, resultado);        
    }
    
    @Test 
    public void pruebaEditarCategoriaDeContratacionDeProfesorUVPorIdProfesorUVFallida() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarCategoriaDeContratacionDeProfesorUVPorIdProfesorUV("Técnico académico", 0);
        assertEquals(0, resultado);
    }        
        
    @Test
    public void pruebaEditarAreaAcademicaDeProfesorUVPorIdProfesorUVExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarAreaAcademicaDeProfesorUVPorIdProfesorUV(2, 1);
        assertEquals(1, resultado);       
    }
    
    @Test
    public void pruebaEditarAreaAcademicaDeProfesorUVPorIdProfesorUVFallida() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarAreaAcademicaDeProfesorUVPorIdProfesorUV(3, 0);
        assertEquals(0, resultado);                 
    }        
                
    @Test
    public void pruebaEditarRegionDeProfesorUVPorIdProfesorUVExitosa() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarRegionDeProfesorUVPorIdProfesorUV(2, 1);
        assertEquals(1, resultado);        
    }
    
    @Test
    public void pruebaEditarRegionDeProfesorUVPorIdProfesorUVExitosaFallida() {
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultado = dao.editarRegionDeProfesorUVPorIdProfesorUV(3, 0);
        assertEquals(0, resultado);
    }

    @Test
    public void pruebaEditarNumeroDePersonalPorIdProfesorUVExitosa(){
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultadoObtenido=dao.editarNumeroDePersonalPorIdProfesorUV("3333", 1);
        assertEquals(1,resultadoObtenido);
    }

    
    @Test
    public void pruebaEditarNumeroDePersonalPorIdProfesorUVFallida(){
        DAOProfesorUVImplementacion dao = new DAOProfesorUVImplementacion();
        int resultadoObtenido=dao.editarNumeroDePersonalPorIdProfesorUV("4444", 0);
        assertEquals(0,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRegistrarProfesorUVExitosa(){
        ProfesorUV profesorUV=new ProfesorUV();
        profesorUV.setNombre("Carlos");
        profesorUV.setApellidoPaterno("García");
        profesorUV.setApellidoMaterno("Acevedo");
        profesorUV.setCorreo("carlos@gmail.com");        
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        daoProfesor.registrarProfesor(profesorUV);
        profesorUV.setIdProfesor(3);                
        profesorUV.setNumeroDePersonal("8181");
        profesorUV.setCategoriaDeContratacion("Docente");
        profesorUV.setTipoDeContratacion("Planta");        
        profesorUV.setIdRegion(1);       
        profesorUV.setIdAreaAcademica(1);
        DAOProfesorUVImplementacion instancia=new DAOProfesorUVImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarProfesorUV(profesorUV);
        assertEquals(resultadoEsperado,resultadoObtenido);                                   
    }
    
    @Test
    public void pruebaRegistrarProfesorUVFallida() {
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
}
