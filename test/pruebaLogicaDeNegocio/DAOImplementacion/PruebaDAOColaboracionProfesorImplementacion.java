package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionProfesorImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.PropuestaColaboracion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class PruebaDAOColaboracionProfesorImplementacion {
    @Test
    public void pruebaInsertarColaboracionProfesorExitosa(){
        DAOColaboracionProfesorImplementacion daoPrueba = new DAOColaboracionProfesorImplementacion();
        Profesor profesorPrueba = new Profesor();
        Colaboracion colaboracionPrueba = new Colaboracion();
        profesorPrueba.setIdProfesor(2);
        colaboracionPrueba.setIdColaboracion(2);
        int resultadoEsperado = 1;
        int resultadoObtenido = daoPrueba.registrarColaboracionProfesor(profesorPrueba, colaboracionPrueba);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaFlujoAlternoInsertarColaboracionProfesorExitosa(){
        DAOColaboracionProfesorImplementacion daoPrueba = new DAOColaboracionProfesorImplementacion();
        Profesor profesorPrueba = new Profesor();
        Colaboracion colaboracionPrueba = new Colaboracion();
        profesorPrueba.setIdProfesor(4);
        colaboracionPrueba.setIdColaboracion(0);
        int resultadoEsperado = -1;
        int resultadoObtenido = daoPrueba.registrarColaboracionProfesor(profesorPrueba, colaboracionPrueba);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaObtenerColaboracionPorIdProfesorExitosa(){
        DAOColaboracionProfesorImplementacion daoPrueba = new DAOColaboracionProfesorImplementacion();
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(1);
        Profesor profesorPrueba = new Profesor();
        Colaboracion colaboracionEsperada = new Colaboracion();
        colaboracionEsperada.setEstadoColaboracion("Activa");
        colaboracionEsperada.setPropuestaColaboracion(propuestaDeCOlaboracion);
        colaboracionEsperada.setIdColaboracion(1);
        profesorPrueba.setIdProfesor(1);
        Colaboracion colaboracionObtenida = new Colaboracion();
        colaboracionObtenida = daoPrueba.obtenerColaboracionPorIdProfesor(profesorPrueba);
        assertEquals(colaboracionObtenida,colaboracionObtenida);
    }
    
    @Test
    public void pruebaFlujoFallidoObtenerColaboracionPorIdProfesorExitosa(){
        DAOColaboracionProfesorImplementacion daoPrueba = new DAOColaboracionProfesorImplementacion();
        PropuestaColaboracion propuestaDeCOlaboracion = new PropuestaColaboracion();
        propuestaDeCOlaboracion.setIdPropuestaColaboracion(1);
        Profesor profesorPrueba = new Profesor();
        Colaboracion colaboracionEsperada = new Colaboracion();
        colaboracionEsperada.setEstadoColaboracion("Activa");
        colaboracionEsperada.setPropuestaColaboracion(propuestaDeCOlaboracion);
        colaboracionEsperada.setIdColaboracion(1);
        profesorPrueba.setIdProfesor(4);
        Colaboracion colaboracionObtenida = new Colaboracion();
        colaboracionObtenida = daoPrueba.obtenerColaboracionPorIdProfesor(profesorPrueba);
        assertNotEquals(colaboracionObtenida,colaboracionEsperada);
    }
    
    @Test
    public void pruebaObtenerProfesoresPorIdColaboracionExitosa(){
        DAOColaboracionProfesorImplementacion daoPrueba = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesoresEsperados = new ArrayList();
        List<Profesor> profesoresObtenidos = new ArrayList();
        Profesor profesorEsperado1 = new Profesor();
        Profesor profesorEsperado2 = new Profesor();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        profesorEsperado1.setNombre("Mario Miguel");
        profesorEsperado1.setApellidoMaterno("Cabrera");
        profesorEsperado1.setApellidoPaterno("Limon");
        profesorEsperado1.setCorreo("mario@gmail.com");
        profesorEsperado1.setEstado("Colaborando");
        profesorEsperado1.setIdProfesor(1);
        profesorEsperado2.setNombre("Elizabeth");
        profesorEsperado2.setApellidoMaterno("Rodriguez");
        profesorEsperado2.setApellidoPaterno("Zapata");
        profesorEsperado2.setCorreo("ez.rzapata08@gmail.com");
        profesorEsperado2.setEstado("Colaborando");
        profesorEsperado2.setIdProfesor(2);
        profesoresEsperados.add(profesorEsperado1);
        profesoresEsperados.add(profesorEsperado2);
        profesoresObtenidos = daoPrueba.obtenerProfesoresPorIdColaboracion(colaboracionPrueba);
        assertEquals(profesoresEsperados,profesoresObtenidos);
    }
    
    @Test
    public void pruebaFlujoAlternoObtenerProfesoresPorIdColaboracion(){
        DAOColaboracionProfesorImplementacion daoPrueba = new DAOColaboracionProfesorImplementacion();
        List<Profesor> profesoresEsperados = new ArrayList();
        List<Profesor> profesoresObtenidos = new ArrayList();
        Profesor profesorEsperado1 = new Profesor();
        Profesor profesorEsperado2 = new Profesor();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(2);
        profesorEsperado1.setNombre("Mario Miguel");
        profesorEsperado1.setApellidoMaterno("Cabrera");
        profesorEsperado1.setApellidoPaterno("Limon");
        profesorEsperado1.setCorreo("mario@gmail.com");
        profesorEsperado1.setEstado("Colaborando");
        profesorEsperado1.setIdProfesor(1);
        profesorEsperado2.setNombre("Elizabeth");
        profesorEsperado2.setApellidoMaterno("Rodriguez");
        profesorEsperado2.setApellidoPaterno("Zapata");
        profesorEsperado2.setCorreo("ez.rzapata08@gmail.com");
        profesorEsperado2.setEstado("Colaborando");
        profesorEsperado2.setIdProfesor(2);
        profesoresEsperados.add(profesorEsperado1);
        profesoresEsperados.add(profesorEsperado2);
        profesoresObtenidos = daoPrueba.obtenerProfesoresPorIdColaboracion(colaboracionPrueba);
        assertNotEquals(profesoresEsperados,profesoresObtenidos);
    }
}
