package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.Pais;
import org.junit.Test;

public class PruebaPais {

    @Test
    public void pruebaSetNombrePaisExitoso(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNombrePais("España");
        assertNotNull(paisPrueba.getNombrePais());
    }
    
    @Test
    public void pruebaSetNombrePaisInvalido(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNombrePais("12345");
        assertNull(paisPrueba.getNombrePais());
    }
    
    @Test
    public void pruebaSetNumeroDePaisExitoso(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNumeroDePais(1);
        assertNotNull(paisPrueba.getNumeroDePais());
    }
    
    @Test
    public void pruebaSetNumeroDePaisInvalido(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNumeroDePais(-1);
        assertNotNull(paisPrueba.getNumeroDePais());
    }
}
