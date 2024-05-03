package pruebaAccesoADatos.Clases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Profesor;
import org.junit.Test;

public class PruebaPropuestaColaboracion {

    @Test
    public void pruebaSetIdPropuestaColaboracionExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setIdPropuestaColaboracion(1);
        assertNotNull(propuestaPrueba.getIdPropuestaColaboracion());
    }
    
    @Test
    public void pruebaSetIdPropuestaColaboracionInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setIdPropuestaColaboracion(-1);
        assertNotNull(propuestaPrueba.getIdPropuestaColaboracion());
    }
    
    @Test
    public void pruebaSetObjetivoExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setObjetivo("Fomentar el intercambio cultural");
        assertNotNull(propuestaPrueba.getObjetivo());
    }
    
    @Test
    public void pruebaSetObjetivoInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setObjetivo("Colaboración forzosa");
        assertNull(propuestaPrueba.getObjetivo());
    }
    
    @Test
    public void pruebaSetFechaInicioExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setFechaInicio("2024-05-01");
        assertNotNull(propuestaPrueba.getFechaInicio());
    }
    
    @Test
    public void pruebaSetFechaInicioInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setFechaInicio("01/05/2024");
        assertNull(propuestaPrueba.getFechaInicio());
    }
    
    @Test
    public void pruebaSetFechaCierreExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setFechaCierre("2024-05-15");
        assertNotNull(propuestaPrueba.getFechaCierre());
    }
    
    @Test
    public void pruebaSetFechaCierreInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setFechaCierre("15/05/2024");
        assertNull(propuestaPrueba.getFechaCierre());
    }
    
    @Test
    public void pruebaSetIdiomaExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setIdioma("Español");
        assertNotNull(propuestaPrueba.getIdioma());
    }
    
    @Test
    public void pruebaSetIdiomaInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setIdioma("12345");
        assertNull(propuestaPrueba.getIdioma());
    }
    
    @Test
    public void pruebaSetExperienciaEducativaExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setExperienciaEducativa("Matemáticas");
        assertNotNull(propuestaPrueba.getExperienciaEducativa());
    }
    
    @Test
    public void pruebaSetExperienciaEducativaInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setExperienciaEducativa("12345");
        assertNull(propuestaPrueba.getExperienciaEducativa());
    }
    
    @Test
    public void pruebaSetProgramaEducativoEstudiantilExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setProgramaEducativoEstudiantil("Bachillerato");
        assertNotNull(propuestaPrueba.getProgramaEducativoEstudiantil());
    }
    
    @Test
    public void pruebaSetProgramaEducativoEstudiantilInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setProgramaEducativoEstudiantil("12345");
        assertNull(propuestaPrueba.getProgramaEducativoEstudiantil());
    }
    
    @Test
    public void pruebaSetEstadoPropuestaExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setEstadoPropuesta("Activa");
        assertNotNull(propuestaPrueba.getEstadoPropuesta());
    }
    
    @Test
    public void pruebaSetEstadoPropuestaInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setEstadoPropuesta("12345");
        assertNull(propuestaPrueba.getEstadoPropuesta());
    }
    
    @Test
    public void pruebaSetTipoColaboracionExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        TipoColaboracion tipo = new TipoColaboracion();
        propuestaPrueba.setTipoColaboracion(tipo);
        assertNotNull(propuestaPrueba.getTipoColaboracion());
    }
    
    @Test
    public void pruebaSetTipoColaboracionInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setTipoColaboracion(null); 
        assertNull(propuestaPrueba.getTipoColaboracion());
    }
    
    @Test
    public void pruebaSetProfesorExitoso(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        Profesor profesor = new Profesor();
        propuestaPrueba.setProfesor(profesor);
        assertNotNull(propuestaPrueba.getProfesor());
    }
    
    @Test
    public void pruebaSetProfesorInvalido(){
        PropuestaColaboracion propuestaPrueba = new PropuestaColaboracion();
        propuestaPrueba.setProfesor(null); 
        assertNull(propuestaPrueba.getProfesor());
    }
    
}
