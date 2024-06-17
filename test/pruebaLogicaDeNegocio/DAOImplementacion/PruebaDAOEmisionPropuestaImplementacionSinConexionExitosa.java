package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOEmisionPropuestaImplementacionSinConexionExitosa {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarEmisionPropuestaSinConexionExitosa(){
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        EmisionPropuesta emisionPropuesta = new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(1);
        emisionPropuesta.setIdPropuestaColaboracion(1);
        emisionPropuesta.setFechaEmision("2024-05-26");
        int resultado = dao.registrarEmisionPropuesta(emisionPropuesta);
        assertEquals(-1, resultado);
    }
    
    @Test
    public void pruebaConsultarEmisionesDePropuestasSinConexionExitosa(){
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<EmisionPropuesta> resultadoObtenido = dao.consultarEmisionesDePropuestas();
        EmisionPropuesta emisionEsperada = new EmisionPropuesta();
        emisionEsperada.setIdProfesor(0);
        assertEquals(emisionEsperada.getIdProfesor(),resultadoObtenido.get(0).getIdProfesor());
    }
    
    @Test 
    public void pruebaConsultarIdProfesorPorIdPropuestaColaboracionSinConexionExitosa(){
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        int idPropuestaColaboracion = 1;
        int resultadoObtenido = dao.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(-1, resultadoObtenido);
    }
    
    @Test
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorSinConexionExitosa(){
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<Integer> resultadoObtenido = dao.consultarIdPropuestaDeColaboracionPorIdProfesor(profesor);
        Integer resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultadoObtenido.get(0));
    }        
}
