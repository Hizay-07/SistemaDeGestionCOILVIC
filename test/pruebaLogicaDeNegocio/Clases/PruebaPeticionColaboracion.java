package pruebaLogicaDeNegocio.Clases;

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
    
    @Test (expected = IllegalArgumentException.class)
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
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetEstadoInvalido(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        peticionPrueba.setEstado("En-Espera");
        assertNull(peticionPrueba.getEstado());
    }
    
    @Test
    public void pruebaSetFechaEnvioExitoso(){
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        String fechaValida = "12/12/2020";
        peticionPrueba.setFechaEnvio(fechaValida);
        assertNotNull(fechaValida, peticionPrueba.getFechaEnvio());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetFechaEnvioInvalido() {
        PeticionColaboracion peticionPrueba = new PeticionColaboracion();
        String fechaInvalida = "2020/12/12";
        peticionPrueba.setFechaEnvio(fechaInvalida);
    }
    
    @Test
    public void pruebaSetIdPropuestaColaboracionValido() {
        PeticionColaboracion peticion = new PeticionColaboracion();
        int idValido = 123456;
        peticion.setIdPropuestaColaboracion(idValido);        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdPropuestaColaboracionInvalido() {
        PeticionColaboracion peticion = new PeticionColaboracion();
        int idInvalido = -123456; 
        peticion.setIdPropuestaColaboracion(idInvalido);
    }
}
