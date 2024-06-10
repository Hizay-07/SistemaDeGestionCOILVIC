package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    public void pruebaRegistrarColaboracionProfesorExitosa() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1); 
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        int resultado = dao.registrarColaboracionProfesor(profesor, colaboracion);
        assertEquals(1,resultado);
    }

    @Test 
    public void pruebaRegistrarColaboracionProfesorFallida() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        int resultado = dao.registrarColaboracionProfesor(profesor, colaboracion);
        assertEquals(0, resultado);
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
    public void pruebaObtenerProfesoresPorIdColaboracionExitosa() {
        Profesor profesor=new Profesor();
        profesor.setNombre("Beto");
        profesor.setApellidoPaterno("Gómez");
        profesor.setApellidoMaterno("Peréz");
        profesor.setCorreo("profesorpruebaunocambio@gmail.com");
        List<Profesor> profesoresEsperados=new ArrayList();
        profesoresEsperados.add(profesor);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesoresObtenidos = dao.obtenerProfesoresPorIdColaboracion(colaboracion);
        assertEquals(profesoresEsperados,profesoresObtenidos);
    }
    
    @Test 
    public void pruebaObtenerProfesoresPorIdColaboracionFallida() {                
        List<Profesor> profesoresEsperados=new ArrayList();        
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesoresObtenidos = dao.obtenerProfesoresPorIdColaboracion(colaboracion);
        assertNotEquals(profesoresEsperados,profesoresObtenidos);       
    }
        
    @Test 
    public void pruebaObtenerProfesoresPorIdColaboracionSinConexionExitosa() {                        
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesoresObtenidos = dao.obtenerProfesoresPorIdColaboracion(colaboracion);
        assertTrue(profesoresObtenidos.isEmpty());
    }
                
    @Test
    public void pruebaObtenerColaboracionPorIdProfesorExitosa() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        Colaboracion colaboracionEsperada=new Colaboracion();
        colaboracionEsperada.setIdColaboracion(1);
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        Colaboracion colaboracionObtenida = dao.obtenerColaboracionPorIdProfesor(profesor);
        assertEquals(colaboracionEsperada,colaboracionObtenida);        
    }

    @Test 
    public void pruebaObtenerColaboracionPorIdProfesorFallida() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        Colaboracion colaboracionEsperada=new Colaboracion();        
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        Colaboracion colaboracionObtenida = dao.obtenerColaboracionPorIdProfesor(profesor);
        assertNotEquals(colaboracionEsperada,colaboracionObtenida);
    }
      
    @Test
    public void pruebaObtenerColaboracionPorIdProfesorSinConexionExitosa(){
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);                
        DAOColaboracionProfesorImplementacion dao = new DAOColaboracionProfesorImplementacion();
        Colaboracion colaboracionObtenida = dao.obtenerColaboracionPorIdProfesor(profesor);        
        assertNull(colaboracionObtenida);
    }
    
    

}
