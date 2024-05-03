package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.EmisionPropuesta;
import org.junit.Test;

public class PruebaEmisionPropuesta {

    @Test
    public void pruebaSetIdProfesorExitosa(){
        EmisionPropuesta emisionPropuestaPrueba = new EmisionPropuesta();
        emisionPropuestaPrueba.setIdProfesor(1);
        assertNotNull(emisionPropuestaPrueba.getIdProfesor());
    }
    
    @Test
    public void pruebaSetIdProfesorInvalida(){
        EmisionPropuesta emisionPropuestaPrueba = new EmisionPropuesta();
        emisionPropuestaPrueba.setIdProfesor(-1);
        assertNull(emisionPropuestaPrueba.getIdProfesor());
    }
    
    @Test
    public void pruebaSetIdPropuestaColaboracionExitosa(){
        EmisionPropuesta emisionPropuestaPrueba = new EmisionPropuesta();
        emisionPropuestaPrueba.setIdPropuestaColaboracion(1);
        assertNotNull(emisionPropuestaPrueba.getIdPropuestaColaboracion());
    }
    
    @Test
    public void pruebaSetIdPropuestaColaboracionInvalida(){
        EmisionPropuesta emisionPropuestaPrueba = new EmisionPropuesta();
        emisionPropuestaPrueba.setIdPropuestaColaboracion(-1);
        assertNull(emisionPropuestaPrueba.getIdPropuestaColaboracion());
    }
    
    @Test
    public void pruebaSetFechaEmisionExitosa(){
        EmisionPropuesta emisionPropuestaPrueba = new EmisionPropuesta();
        emisionPropuestaPrueba.setFechaEmision("2024-05-01");
        assertNotNull(emisionPropuestaPrueba.getFechaEmision());
    }
    
    @Test
    public void pruebaSetFechaEmisionInvalida(){
        EmisionPropuesta emisionPropuestaPrueba = new EmisionPropuesta();
        emisionPropuestaPrueba.setFechaEmision("01-05-2024");
        assertNull(emisionPropuestaPrueba.getFechaEmision());
    }
}
