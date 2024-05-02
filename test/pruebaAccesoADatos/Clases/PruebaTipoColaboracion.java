package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.TipoColaboracion;
import org.junit.Test;

public class PruebaTipoColaboracion {

    @Test
    public void pruebaSetTipoExitoso(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setTipo("Colaboración Académica"); 
        assertNotNull(tipoColaboracionPrueba.getTipo());
    }
    
    @Test
    public void pruebaSetTipoInvalido(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setTipo("");
        assertNull(tipoColaboracionPrueba.getTipo());
    }
    
    @Test
    public void pruebaSetIdTipoColaboracionExitoso(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setIdTipoColaboracion(123); 
        assertNotNull(tipoColaboracionPrueba.getIdTipoColaboracion());
    }
    
    @Test
    public void pruebaSetIdTipoColaboracionInvalido(){
        TipoColaboracion tipoColaboracionPrueba = new TipoColaboracion();
        tipoColaboracionPrueba.setIdTipoColaboracion(-1); 
        assertNull(tipoColaboracionPrueba.getIdTipoColaboracion()); 
    }
    

    
}
