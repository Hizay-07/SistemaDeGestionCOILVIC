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
    
    @Test
    public void pruebaSetIdColaboracionInvalido(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(-1);
        assertNotNull(colaboracionPrueba.getIdColaboracion());
    }
    
    @Test
    public void pruebaSetRetroalimentacionExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setRetroalimentacion("Buena colaboración");
        assertNotNull(colaboracionPrueba.getRetroalimentacion());
    }
    
    @Test
    public void pruebaSetRetroalimentacionInvalida(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setRetroalimentacion("Retroalimentación123");
        assertNull(colaboracionPrueba.getRetroalimentacion());
    }
    
    @Test
    public void pruebaSetEstadoColaboracionExitosa(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setEstadoColaboracion("En progreso");
        assertNotNull(colaboracionPrueba.getEstadoColaboracion());
    }
    
    @Test
    public void pruebaSetEstadoColaboracionInvalida(){
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setEstadoColaboracion("Estado*");
        assertNull(colaboracionPrueba.getEstadoColaboracion());
    }
}
