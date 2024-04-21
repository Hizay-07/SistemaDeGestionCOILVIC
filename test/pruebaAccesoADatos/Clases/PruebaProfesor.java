package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import logicaDeNegocio.clases.Profesor;
import org.junit.Test;

public class PruebaProfesor {
    
    @Test
    public void pruebaSetNombreProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setNombre("José José");
        assertNotNull(profesorPrueba.getNombre());
    }
    
    @Test
    public void pruebaSetNombreInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setNombre("Juan124");
        assertNotNull(profesorPrueba.getNombre());
    }
}
