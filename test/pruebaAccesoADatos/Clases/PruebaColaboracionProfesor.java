package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import logicaDeNegocio.clases.ColaboracionProfesor;
import org.junit.Test;

public class PruebaColaboracionProfesor {
    
    @Test
    public void pruebaSetIdProfesorExitosa(){        
        ColaboracionProfesor colaboracionProfesorPrueba = new ColaboracionProfesor();
        colaboracionProfesorPrueba.setIdProfesor(1);
        assertNotNull(colaboracionProfesorPrueba.getIdProfesor());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdProfesorFallida(){
        ColaboracionProfesor colaboracionProfesorPrueba = new ColaboracionProfesor();
        colaboracionProfesorPrueba.setIdProfesor(-1);
        assertNotNull(colaboracionProfesorPrueba.getIdProfesor());
    }   
    
    @Test
     public void pruebaSetIdColaboracionExitosa(){        
        ColaboracionProfesor colaboracionProfesorPrueba = new ColaboracionProfesor();
        colaboracionProfesorPrueba.setIdColaboracion(1);
        assertNotNull(colaboracionProfesorPrueba.getIdColaboracion());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdColaboracionFallida(){
        ColaboracionProfesor colaboracionProfesorPrueba = new ColaboracionProfesor();
        colaboracionProfesorPrueba.setIdColaboracion(-1);
        assertNotNull(colaboracionProfesorPrueba.getIdColaboracion());
    } 
}
