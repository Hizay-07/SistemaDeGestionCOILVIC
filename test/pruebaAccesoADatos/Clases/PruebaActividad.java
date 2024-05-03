package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import logicaDeNegocio.clases.Actividad;
import org.junit.Test;

public class PruebaActividad {

    @Test
    public void pruebaSetIdActividadExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setIdActividad(1);
        assertNotNull(actividadPrueba.getIdActividad());
    }
    
    @Test
    public void pruebaSetIdActividadInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setIdActividad(-1);
        assertNotNull(actividadPrueba.getIdActividad());
    }
    
    @Test
    public void pruebaSetNumeroActividadExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNumeroActividad(1);
        assertNotNull(actividadPrueba.getNumeroActividad());
    }
    
    @Test
    public void pruebaSetNumeroActividadInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNumeroActividad(-1);
        assertNull(actividadPrueba.getNumeroActividad());
    }
    
    @Test
    public void pruebaSetNombreActividadExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("PSP");
        assertNotNull(actividadPrueba.getNombre());
    }
    
    @Test
    public void pruebaSetNombreActividadInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setNombre("PSP*");
        assertNotNull(actividadPrueba.getNombre());
    }
    
    @Test
    public void pruebaSetDescripcionActividadExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setDescripcion("Elaborar un PSP sobre el proyecto de contrucción");
        assertNotNull(actividadPrueba.getDescripcion());
    }
    
    @Test
    public void pruebaSetDescripcionActividadInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setDescripcion("Elaborar un PSP sobre el proyecto de principios de construccion*");
        assertNotNull(actividadPrueba.getDescripcion());
    }
    
    
    @Test
    public void pruebaSetFechaInicioActividadExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setFechaDeInicio("2024-05-02");
        assertNotNull(actividadPrueba.getFechaDeInicio());
    }
    
    @Test
    public void pruebaSetFechaInicioActividadInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setFechaDeInicio("01-05-2024");
        assertNotNull(actividadPrueba.getFechaDeInicio());
    }
    
    @Test
    public void pruebaSetFechaCierreActividadExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setFechaDeCierre("2024-05-02");
        assertNotNull(actividadPrueba.getFechaDeCierre());
    }
    
    @Test
    public void pruebaSetFechaCierreActividadInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setFechaDeCierre("01-05-2024");
        assertNotNull(actividadPrueba.getFechaDeCierre());
    }
    
    
    @Test
    public void pruebaSetEstadoActividadExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setEstado("Activa");
        assertNotNull(actividadPrueba.getEstado());
    }
    
    @Test
    public void pruebaSetEstadoActividadInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setEstado("No activa*");
        assertNotNull(actividadPrueba.getEstado());
    }
    
    @Test
    public void pruebaSetIdColaboracionExitosa(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setIdColaboracion(1);
        assertNotNull(actividadPrueba.getIdColaboracion());
    }
    
    @Test
    public void pruebaSetIdColaboracionInvalido(){
        Actividad actividadPrueba = new Actividad();
        actividadPrueba.setIdColaboracion(-1);
        assertNotNull(actividadPrueba.getIdColaboracion());
    }
}
