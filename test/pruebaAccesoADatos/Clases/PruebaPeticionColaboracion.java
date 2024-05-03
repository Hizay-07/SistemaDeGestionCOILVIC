package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.PeticionColaboracion;
import org.junit.Test;

public class PruebaPeticionColaboracion {

    @Test
    public void pruebaSetIdProfesorExitoso(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        peticionPrueba.setIdProfesor(1);
        assertNotNull(peticionPrueba.getIdProfesor());
    }
    
    @Test
    public void pruebaSetIdProfesorInvalido(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        peticionPrueba.setIdProfesor(-1);
        assertNotNull(peticionPrueba.getIdProfesor());
    }
    
    @Test
    public void pruebaSetEstadoExitoso(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        peticionPrueba.setEstado("Activa");
        assertNotNull(peticionPrueba.getEstado());
    }
    
    @Test
    public void pruebaSetEstadoInvalido(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        peticionPrueba.setEstado("Inactiva*");
        assertNull(peticionPrueba.getEstado());
    }
    
    @Test
    public void pruebaSetFechaEnvioExitoso(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        peticionPrueba.setFechaEnvio("2024-05-01");
        assertNotNull(peticionPrueba.getFechaEnvio());
    }
    
    @Test
    public void pruebaSetFechaEnvioInvalido(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        peticionPrueba.setFechaEnvio("01/05/2024");
        assertNull(peticionPrueba.getFechaEnvio());
    }
}
