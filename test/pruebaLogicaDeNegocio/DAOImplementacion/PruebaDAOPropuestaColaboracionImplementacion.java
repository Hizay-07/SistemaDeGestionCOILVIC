package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;

public class PruebaDAOPropuestaColaboracionImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio("2024-06-02");
        propuestaColaboracion.setFechaCierre("2024-07-23");
        propuestaColaboracion.setIdioma("Español");
        propuestaColaboracion.setExperienciaEducativa("Programacion");
        propuestaColaboracion.setObjetivo("Fundamentos de la programación");
        propuestaColaboracion.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuestaColaboracion.setEstadoPropuesta("Registrada");
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);        
        instancia.registrarPropuestaColaboracion(propuestaColaboracion);
    }
                   
    @Test
    public void pruebaObtenerPropuestaDeColaboracionPorIdExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaObtenida=daoPropuesta.obtenerPropuestaDeColaboracionPorId(1);
        assertEquals(propuestaEsperada,propuestaObtenida);                        
    }
    
    @Test
    public void pruebaObtenerPropuestaDeColaboracionPorIdFallida(){               
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaObtenida=daoPropuesta.obtenerPropuestaDeColaboracionPorId(0);
        assertNotEquals(propuestaEsperada,propuestaObtenida);         
    }            
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Bases de datos");
        propuestaEsperada.setObjetivo("Conocer las bases de datos");      
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionRegistradas();                                
        assertEquals(propuestasEsperadas,propuestasObtenidas);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionRegistradasFallida(){        
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();   
        PropuestaColaboracion propuesta=new PropuestaColaboracion();
        propuesta.setExperienciaEducativa("Ninguna");
        propuestasEsperadas.add(propuesta);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionRegistradas();
        assertNotEquals(propuestasEsperadas,propuestasObtenidas);         
    }            
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.rechazarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado, resultadoObtenido);                                
    }            
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.rechazarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }        
    
    @Test
    public void pruebaCambiarEstadoIniciadaPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuesta.cambiarEstadoIniciadaPropuestaColaboracionPorId(1);
        assertEquals(1,resultadoObtenido);        
    }
    
    @Test
    public void pruebaCambiarEstadoIniciadaPropuestaColaboracionPorIdFallida(){
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuesta.cambiarEstadoIniciadaPropuestaColaboracionPorId(0);
        assertEquals(0,resultadoObtenido);                
    }                 
    
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.aprobarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado, resultadoObtenido);                        
    }        
        
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.aprobarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }        
           
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);
        DAOPropuestaColaboracionImplementacion daoPropuesta=new DAOPropuestaColaboracionImplementacion();
        List<PropuestaColaboracion> propuestasObtenidas=daoPropuesta.consultarPropuestasDeColaboracionAprobadas();
        assertEquals(propuestasEsperadas,propuestasObtenidas);      
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();        
        List<PropuestaColaboracion> resultadoObtenido = instancia.consultarPropuestasDeColaboracionAprobadas();
        assertFalse(resultadoObtenido.isEmpty());        
    }            
                         
    @Test
    public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 1;
        int resultadoEsperado = 1;
        int resultadoObtenido = instancia.obtenerIdPropuestaColaboracionPorEstadoPorIdProfesor(idProfesor,"Aprobada");
        assertEquals(resultadoEsperado, resultadoObtenido);                        
    }
    
    @Test
        public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = instancia.obtenerIdPropuestaColaboracionPorEstadoPorIdProfesor(0,"Aprobada");
        assertEquals(resultadoEsperado, resultadoObtenido);                        
    }                            
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinPeticionesExitosa(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);        
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 1;        
        List<PropuestaColaboracion> propuestasObtenidas= instancia.consultarPropuestasDeColaboracionAprobadasSinPeticiones(idProfesor);
        assertEquals(propuestasEsperadas,propuestasObtenidas);                
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasSinPeticionesFallida(){
        PropuestaColaboracion propuestaEsperada=new PropuestaColaboracion();
        propuestaEsperada.setExperienciaEducativa("Programacion");
        propuestaEsperada.setObjetivo("Fundamentos de la programación");       
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        tipoColaboracion.setTipo("Clase espejo");
        propuestaEsperada.setTipoColaboracion(tipoColaboracion);
        List<PropuestaColaboracion> propuestasEsperadas=new ArrayList<>();
        propuestasEsperadas.add(propuestaEsperada);        
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        int idProfesor = 0;        
        List<PropuestaColaboracion> propuestasObtenidas= instancia.consultarPropuestasDeColaboracionAprobadasSinPeticiones(idProfesor);
        assertNotEquals(propuestasEsperadas,propuestasObtenidas);         
    } 
    
    @Test
    public void pruebaRegistrarPropuestaColaboracionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio("2024-08-02");
        propuestaColaboracion.setFechaCierre("2024-10-23");
        propuestaColaboracion.setIdioma("Inglés");
        propuestaColaboracion.setExperienciaEducativa("Bases de datos");
        propuestaColaboracion.setObjetivo("Conocer las bases de datos");
        propuestaColaboracion.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuestaColaboracion.setEstadoPropuesta("Registrada");
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        int resultadoEsperado = 2;       
        int resultadoObtenido = instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);                                
    }
    
    @Test(expected = AssertionError.class)
    public void pruebaRegistrarPropuestaColaboracionFallida(){
        DAOPropuestaColaboracionImplementacion instancia = new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion = new PropuestaColaboracion();       
        int resultadoEsperado = 0;       
        int resultadoObtenido = instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado, resultadoObtenido);        
    }
}
