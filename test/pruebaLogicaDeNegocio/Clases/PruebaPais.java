package pruebaLogicaDeNegocio.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Pais;
import org.junit.Test;

public class PruebaPais {

    @Test
    public void pruebaSetNombrePaisExitoso(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNombrePais("Papua-Nueva Guinea");
        assertNotNull(paisPrueba.getNombrePais());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetNombrePaisInvalido(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNombrePais("España12");
        assertNull(paisPrueba.getNombrePais());
    }
    
    @Test 
    public void pruebaSetNumeroDePaisExitoso(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNumeroDePais(1);
        assertNotNull(paisPrueba.getNumeroDePais());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetNumeroDePaisInvalido(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNumeroDePais(-1);
        assertNotNull(paisPrueba.getNumeroDePais());
    }
}
