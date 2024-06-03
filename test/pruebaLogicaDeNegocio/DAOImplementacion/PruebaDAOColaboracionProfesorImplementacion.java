package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOColaboracionProfesorImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaObtenerProfesoresPorIdColaboracionExitosa() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(2); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesores = dao.obtenerProfesoresPorIdColaboracion(colaboracion);
        assertNotNull(profesores);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaObtenerProfesoresPorIdColaboracionFallida() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(-1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesores = dao.obtenerProfesoresPorIdColaboracion(colaboracion);
        assertEquals(0, profesores.size());
    }
    
    @Test
    public void pruebaObtenerColaboracionPorIdProfesorExitosa() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        Colaboracion colaboracion = dao.obtenerColaboracionPorIdProfesor(profesor);
        assertNotNull(colaboracion);
    }

    @Test (expected = IllegalArgumentException.class)
    public void pruebaObtenerColaboracionPorIdProfesorFallida() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(-1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        Colaboracion colaboracion = dao.obtenerColaboracionPorIdProfesor(profesor);
        assertNull(colaboracion);
    }
    
    @Test
    public void pruebaRegistrarColaboracionProfesorExitosa() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1); 
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        int resultado = dao.registrarColaboracionProfesor(profesor, colaboracion);
        assertTrue(resultado > 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void pruebaRegistrarColaboracionProfesorFallida() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(-1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(-1);
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        int resultado = dao.registrarColaboracionProfesor(profesor, colaboracion);
        assertEquals(-1, resultado);
    }

}
