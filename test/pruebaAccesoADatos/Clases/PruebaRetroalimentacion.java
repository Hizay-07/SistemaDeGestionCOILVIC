package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Retroalimentacion;
import org.junit.Test;

public class PruebaRetroalimentacion {

    @Test
    public void pruebaSetRetroalimentacionExitoso(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setRetroalimentacion("Retroalimentación válida");
        assertNotNull(retroalimentacionPrueba.getRetroalimentacion());
    }
    
    @Test
    public void pruebaSetRetroalimentacionInvalido(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setRetroalimentacion("Retro1"); 
        assertNull(retroalimentacionPrueba.getRetroalimentacion());
    }
    
    @Test
    public void pruebaSetEmisorExitoso(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setEmisor("Emisor válido");
        assertNotNull(retroalimentacionPrueba.getEmisor());
    }
    
    @Test
    public void pruebaSetEmisorInvalido(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setEmisor("EmisorN1"); 
        assertNull(retroalimentacionPrueba.getEmisor());
    }
    
    @Test
    public void pruebaSetIdColaboracionExitoso(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setIdColaboracion(123); 
        assertNotNull(retroalimentacionPrueba.getIdColaboracion());
    }
    
    @Test
    public void pruebaSetIdColaboracionInvalido(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setIdColaboracion(-1);
        assertNull(retroalimentacionPrueba.getIdColaboracion()); 
    }
    
    @Test
    public void pruebaSetIdRetroalimentacionExitoso(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setIdRetroalimentacion(456);
        assertNotNull(retroalimentacionPrueba.getIdRetroalimentacion());
    }
    
    @Test
    public void pruebaSetIdRetroalimentacionInvalido(){
        Retroalimentacion retroalimentacionPrueba = new Retroalimentacion();
        retroalimentacionPrueba.setIdRetroalimentacion(-1);
        assertNull(retroalimentacionPrueba.getIdRetroalimentacion()); 
    }
    
    
}
