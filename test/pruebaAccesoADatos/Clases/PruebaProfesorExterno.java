package pruebaAccesoADatos.Clases;

import logicaDeNegocio.clases.ProfesorExterno;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class PruebaProfesorExterno {
    @Test
    public void pruebaSetIdProfesorExternoExitosa() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdProfesorExterno(1);
        assertNotNull(profesorPrueba.getIdProfesorExterno());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdProfesorExternoInvalido() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdProfesorExterno(-1);
        assertNull(profesorPrueba.getIdProfesorExterno());
    }
    
    @Test
    public void pruebaSetIdRepresentanteInstitucionalExitosa() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdRepresentanteInstitucional(1);
        assertNotNull(profesorPrueba.getIdRepresentanteInstitucional());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdRepresentanteInstitucionalInvalida() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdRepresentanteInstitucional(-1);
        assertNull(profesorPrueba.getIdRepresentanteInstitucional());
    }
}
