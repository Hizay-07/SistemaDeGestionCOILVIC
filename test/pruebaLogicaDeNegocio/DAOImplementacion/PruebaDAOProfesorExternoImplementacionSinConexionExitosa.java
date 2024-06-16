package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOProfesorExternoImplementacion;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOProfesorExternoImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarProfesorExternoSinConexionExitosa() {
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        ProfesorExterno profesorExterno = new ProfesorExterno();
        profesorExterno.setIdProfesor(1); 
        profesorExterno.setIdRepresentanteInstitucional(1); 
        int resultado = dao.registrarProfesorExterno(profesorExterno);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternos();
        ProfesorExterno profesorEsperado = new ProfesorExterno();
        profesorEsperado.setNombre("Excepcion");
        assertEquals(profesorEsperado.getNombre(),profesoresExternos.get(0).getNombre());
    }
    
    @Test
    public void pruebaConsultarProfesoresExternosPorRepresentanteInstitucionalSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        List<ProfesorExterno> profesoresExternos = dao.consultarProfesoresExternosPorRepresentanteInstitucional(1);
        ProfesorExterno profesorEsperado = new ProfesorExterno();
        profesorEsperado.setNombre("Excepcion");
        assertEquals(profesorEsperado.getNombre(),profesoresExternos.get(0).getNombre());
    }
    
    @Test
    public void pruebaObtenerProfesorExternoPorIdProfesorSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        ProfesorExterno profesorObtenido = dao.obtenerProfesorExternoPorIDProfesor(1);
        ProfesorExterno profesorEsperado = new ProfesorExterno();
        profesorEsperado.setIdRepresentanteInstitucional(0);
        assertEquals(profesorEsperado.getIdProfesorExterno(),profesorObtenido.getIdRepresentanteInstitucional());
    }
    
    @Test
    public void pruebaConsultarIdRepresentanteInstitucionalPorIdProfesorSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int idRepresentanteInstitucional = dao.consultarIdRepresentanteInstitucionalPorIdProfesor(1);
        assertEquals(-1, idRepresentanteInstitucional);
    }
    
    @Test
    public void pruebaEditarInstitucionProfesorExternoPorIdProfesorSinConexionExitosa(){
        DAOProfesorExternoImplementacion dao = new DAOProfesorExternoImplementacion();
        int resultado = dao.editarInstitucionProfesorExternoPorIdProfesor(1, 1);
        assertEquals(-1, resultado);
    }
    
}
