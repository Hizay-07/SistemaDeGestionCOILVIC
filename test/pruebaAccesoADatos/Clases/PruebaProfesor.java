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
        profesorPrueba.setNombre("Juan");
        assertNull(profesorPrueba.getNombre());
    }
    
    @Test
    public void pruebaSetApellidoPaternoProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setApellidoPaterno("III");
        assertNotNull(profesorPrueba.getApellidoPaterno());
    }
    
    @Test
    public void pruebaSetApellidoPaternoInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setApellidoPaterno("iII°*");
        assertNotNull(profesorPrueba.getApellidoPaterno());
    }
    
    @Test
    public void pruebaSetApellidoMaternoProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setApellidoMaterno("Magno");
        assertNotNull(profesorPrueba.getApellidoMaterno());
    }
    
    @Test
    public void pruebaSetApellidoMaternoInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setApellidoMaterno("Magno*");
        assertNotNull(profesorPrueba.getApellidoMaterno());
    }
    
    
    @Test
    public void pruebaSetCorreoProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setCorreo("Oscar@example.com");
        assertNotNull(profesorPrueba.getCorreo());
    }
    
    
    @Test
    public void pruebaSetCorreoInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setCorreo("Juan@ejemplo.com.mx1");
        assertNull(profesorPrueba.getCorreo());
    }
    
    @Test
    public void pruebaSetEstadoProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setEstado("Aceptado");
        assertNotNull(profesorPrueba.getEstado());
    }
    
    
    @Test
    public void pruebaSetEstadoInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setEstado("coil");
        assertNotNull(profesorPrueba.getEstado());
    }
    
    @Test
    public void pruebaSetIdProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setIdProfesor(22);
        assertNotNull(profesorPrueba.getIdProfesor());
    }
    
    
    @Test
    public void pruebaSetIdInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setIdProfesor(-1);
        assertNull(profesorPrueba.getIdProfesor());
    }
}
