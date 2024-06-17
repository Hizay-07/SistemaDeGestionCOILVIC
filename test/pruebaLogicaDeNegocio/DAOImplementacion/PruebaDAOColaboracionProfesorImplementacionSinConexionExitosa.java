package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOColaboracionProfesorImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarColaboracionProfesorSinConexionExitosa() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        int resultado = dao.registrarColaboracionProfesor(profesor, colaboracion);
        assertEquals(-1, resultado);
    }
    
    @Test 
    public void pruebaObtenerProfesoresPorIdColaboracionSinConexionExitosa() {                        
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesoresObtenidos = dao.obtenerProfesoresPorIdColaboracion(colaboracion);
        Profesor profesorEsperado = new Profesor();
        profesorEsperado.setNombre("Excepcion");
        assertEquals(profesorEsperado.getNombre(),profesoresObtenidos.get(0).getNombre());
    }
      
    @Test
    public void pruebaObtenerColaboracionPorIdProfesorSinConexionExitosa(){
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);                
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        Colaboracion colaboracionObtenida = dao.obtenerColaboracionPorIdProfesor(profesor,"Activa");   
        Colaboracion colaboracionEsperada = new Colaboracion();
        colaboracionEsperada.setEstadoColaboracion("Excepcion");
        assertEquals(colaboracionEsperada.getEstadoColaboracion(),colaboracionObtenida.getEstadoColaboracion());
    }    
}
