package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOProfesorExternoImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarProfesorExternoExitosa() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setIdProfesor(1); 
        profesorExterno.setIdRepresentanteInstitucional(1); 
        int resultado = dao.registrarProfesorExterno(profesorExterno);
        assertEquals(1, resultado);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void pruebaRegistrarProfesorExternoFallida() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setIdProfesor(-1);        
        int resultado = dao.registrarProfesorExterno(profesorExterno);
        assertEquals(0, resultado);
    }               
    
    @Test
    public void pruebaConsultarProfesoresExternosExitosa() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternos();
        assertNotNull(profesoresExternos);
        assertFalse(profesoresExternos.isEmpty());
    }
    
    @Test(expected = AssertionError.class)
    public void pruebaConsultarProfesoresExternosFallida() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternos();
        assertTrue(profesoresExternos.isEmpty());
    }   
    
    @Test
    public void pruebaObtenerProfesorExternoPorIdProfesorExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        ProfesorExterno profesorObtenido = dao.obtenerProfesorExternoPorIDProfesor(1);
        ProfesorExterno profesorEsperado = new ProfesorExterno();
        profesorEsperado.setIdProfesorExterno(1);
        profesorEsperado.setIdRepresentanteInstitucional(1);
        profesorEsperado.setIdProfesor(1);
        assertEquals(profesorObtenido,profesorEsperado);
    }
    
    @Test
    public void pruebaObtenerProfesorExternoPorIdProfesorFallida(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        ProfesorExterno profesorObtenido = dao.obtenerProfesorExternoPorIDProfesor(5);
        ProfesorExterno profesorEsperado = new ProfesorExterno();
        profesorEsperado.setIdProfesorExterno(1);
        profesorEsperado.setIdRepresentanteInstitucional(1);
        profesorEsperado.setIdProfesor(1);
        assertNotEquals(profesorObtenido,profesorEsperado);
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosPorRepresentanteInstitucionalExitosa() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional = 1; 
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternosPorRepresentanteInstitucional(idRepresentanteInstitucional);
        assertNotNull(profesoresExternos);
        assertFalse(profesoresExternos.isEmpty());
    }

    @Test
    public void pruebaConsultarProfesoresExternosPorRepresentanteInstitucionalFallida() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional = 999; 
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternosPorRepresentanteInstitucional(idRepresentanteInstitucional);
        assertTrue(profesoresExternos.isEmpty());
    }       

    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorIdProfesorExitosa() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idProfesor = 1;
        int idRepresentanteInstitucional = dao.consultarIdRepresentanteInstitucionalPorIdProfesor(idProfesor);
        assertTrue(idRepresentanteInstitucional > 0);
    }
    
    @Test 
    public void pruebaConsultarIdRepresentanteInstitucionalPorIdProfesorFallida() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idProfesor = 99; 
        int idRepresentanteInstitucional = dao.consultarIdRepresentanteInstitucionalPorIdProfesor(idProfesor);
        assertEquals(0, idRepresentanteInstitucional);
    }        

    @Test
    public void pruebaEditarInstitucionProfesorExternoPorIdProfesorExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional = 1;
        int idProfesor = 1;
        int resultado = dao.editarInstitucionProfesorExternoPorIdProfesor(idRepresentanteInstitucional, idProfesor);
        assertEquals(1,resultado);
    }
    
    @Test
    public void pruebaEditarInstitucionProfesorExternoPorIdProfesorFallida(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional = 0;
        int idProfesor = 0;
        int resultado = dao.editarInstitucionProfesorExternoPorIdProfesor(idRepresentanteInstitucional, idProfesor);
        assertEquals(0,resultado);
    }            
}
