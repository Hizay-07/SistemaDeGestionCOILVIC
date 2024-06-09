package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOEmisionPropuestaImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarEmisionPropuestaExitoso() {
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        EmisionPropuesta emisionPropuesta = new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(1);
        emisionPropuesta.setIdPropuestaColaboracion(1);
        emisionPropuesta.setFechaEmision("2024-05-26");
        int resultado = dao.registrarEmisionPropuesta(emisionPropuesta);
        assertEquals(1, resultado);
        List<EmisionPropuesta> emisiones = dao.consultarEmisionesDePropuestas();
        assertTrue(emisiones.contains(emisionPropuesta));
    }
    
    @Test
    public void pruebaRegistrarEmisionPropuestaFallido() {
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        EmisionPropuesta emisionPropuesta = null;
        int resultado = dao.registrarEmisionPropuesta(emisionPropuesta);
        assertEquals(-1, resultado);
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
    public void pruebaConsultarEmisionesDePropuestasExitosa() {
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<EmisionPropuesta> resultadoObtenido = dao.consultarEmisionesDePropuestas();
        assertFalse(resultadoObtenido.isEmpty());
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaConsultarEmisionesDePropuestasFallida() {
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<EmisionPropuesta> resultadoObtenido = dao.consultarEmisionesDePropuestas();
        assertTrue(resultadoObtenido.isEmpty()); 
    }
    
    @Test
    public void pruebaConsultarEmisionesDePropuestasSinConexionExitosa(){
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<EmisionPropuesta> resultadoObtenido = dao.consultarEmisionesDePropuestas();
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test
    public void pruebaConsultarIdProfesorPorIdPropuestaColaboracionExitosa() {
        int idPropuestaColaboracion = 1;
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        int resultadoObtenido = dao.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(1, resultadoObtenido);
    }
    
    @Test (expected = AssertionError.class)
    public void pruebaConsultarIdProfesorPorIdPropuestaColaboracionFallida() {
        int idPropuestaColaboracion = -1;
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        int resultadoObtenido = dao.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(-1, resultadoObtenido);
    }
    
    @Test 
    public void pruebaConsultarIdProfesorPorIdPropuestaColaboracionSinConexionExitosa(){
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        int idPropuestaColaboracion = 1;
        int resultadoObtenido = dao.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(-1, resultadoObtenido);
    }

    @Test
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorExitosa() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<Integer> resultadoObtenido = dao.consultarIdPropuestaDeColaboracionPorIdProfesor(profesor);
        assertFalse(resultadoObtenido.isEmpty());
    }

    @Test (expected = IllegalArgumentException.class)
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorFallida() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(-1);
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<Integer> resultadoObtenido = dao.consultarIdPropuestaDeColaboracionPorIdProfesor(profesor);
        assertTrue(resultadoObtenido.isEmpty());
    }
    
    @Test
    public void pruebaConsultarIdPropuestaDeColaboracionPorIdProfesorSinConexionExitosa(){
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        DAOEmisionPropuestaImplementacion dao = new DAOEmisionPropuestaImplementacion();
        List<Integer> resultadoObtenido = dao.consultarIdPropuestaDeColaboracionPorIdProfesor(profesor);
        assertTrue(resultadoObtenido.isEmpty());
    }
  
}
