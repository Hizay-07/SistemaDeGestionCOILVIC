package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Colaboracion;
import org.junit.Test;

public class PruebaColaboracion {

    @Test
    public void pruebaSetIdColaboracionExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        assertNotNull(colaboracionPrueba.getIdColaboracion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdColaboracionInvalido(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(-1);
        assertNotNull(colaboracionPrueba.getIdColaboracion());
    }       
    
    @Test
    public void pruebaSetEstadoColaboracionExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setEstadoColaboracion("En progreso");
        assertNotNull(colaboracionPrueba.getEstadoColaboracion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetEstadoColaboracionInvalida(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setEstadoColaboracion("Estado*");
        assertNull(colaboracionPrueba.getEstadoColaboracion());
    }
}
