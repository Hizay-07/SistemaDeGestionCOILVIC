package pruebaLogicaDeNegocio.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.TipoColaboracion;
import org.junit.Test;

public class PruebaTipoColaboracion {

    @Test
    public void pruebaSetTipoExitoso(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setTipo("Implementación COIL-VIC"); 
        assertNotNull(tipoColaboracionPrueba.getTipo());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetTipoInvalido(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setTipo("Colaboracion 2");
        assertNotNull(tipoColaboracionPrueba.getTipo());
    }
    
    @Test
    public void pruebaSetIdTipoColaboracionExitoso(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setIdTipoColaboracion(123); 
        assertNotNull(tipoColaboracionPrueba.getIdTipoColaboracion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdTipoColaboracionInvalido(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setIdTipoColaboracion(-1); 
        assertNull(tipoColaboracionPrueba.getIdTipoColaboracion()); 
    }
    

    
}
