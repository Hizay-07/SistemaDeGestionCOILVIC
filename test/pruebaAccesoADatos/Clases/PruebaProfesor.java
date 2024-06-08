package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import logicaDeNegocio.clases.Profesor;
import org.junit.Test;

public class PruebaProfesor {
    
    @Test
    public void pruebaSetNombreProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setNombre("Brian O'Connor");
        assertNotNull(profesorPrueba.getNombre());
    }
        
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetNombreInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setNombre("Juan°1");
        assertNotNull(profesorPrueba.getNombre());
    }
    
    @Test
    public void pruebaSetApellidoPaternoProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setApellidoPaterno("Velasquez");
        assertNotNull(profesorPrueba.getApellidoPaterno());
    }
    
    @Test (expected = IllegalArgumentException.class)
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
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetApellidoMaternoInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setApellidoMaterno("Magno*");
        assertNotNull(profesorPrueba.getApellidoMaterno());
    }
        
    @Test
    public void pruebaSetCorreoProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setCorreo("alguien.correo@gmail.com");
        assertNotNull(profesorPrueba.getCorreo());
    }
        
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetCorreoInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setCorreo("******.correo@gmail.com");
        assertNull(profesorPrueba.getCorreo());
    }
    
    @Test
    public void pruebaSetEstadoProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setEstado("Aceptado");
        assertNotNull(profesorPrueba.getEstado());
    }    
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetEstadoInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setEstado("Esperando...");
        assertNotNull(profesorPrueba.getEstado());
    }
    
    @Test
    public void pruebaSetIdProfesorExitosa(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setIdProfesor(22);
        assertNotNull(profesorPrueba.getIdProfesor());
    }
        
    @Test (expected = IllegalArgumentException.class)
    public void pruebaSetIdInvalidoProfesor(){
        Profesor profesorPrueba = new Profesor();
        profesorPrueba.setIdProfesor(-1);
        assertNull(profesorPrueba.getIdProfesor());
    }
}
