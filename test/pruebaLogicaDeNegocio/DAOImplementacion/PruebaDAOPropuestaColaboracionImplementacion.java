package pruebaLogicaDeNegocio.DAOImplementacion;


import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PruebaDAOPropuestaColaboracionImplementacion {
    
    @Test
    public void pruebaRegistrarPropuestaColaboracionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio("2024-05-02");
        propuestaColaboracion.setFechaCierre("2024-06-23");
        propuestaColaboracion.setIdioma("Ingles");
        propuestaColaboracion.setExperienciaEducativa("Programacion");
        propuestaColaboracion.setObjetivo("Compartir los distintos para programar en java");
        propuestaColaboracion.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuestaColaboracion.setEstadoPropuesta("Registrada");
        TipoColaboracion tipoColaboracion=new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        int resultadoEsperado=6;       
        int resultadoObtenido=instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);                                
    }
    
    @Test
    public void pruebaRegistrarPropuestaColaboracionFracaso(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();       
        int resultadoEsperado=0;       
        int resultadoObtenido=instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionExitosa(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion.setIdPropuestaColaboracion(1);
        TipoColaboracion tipoColaboracion=new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setObjetivo("Usar buenas practicas en las bases de datos");                        
        List<PropuestaColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        listaObtenida=instancia.consultarPropuestasColaboracion();
        assertEquals(listaEsperada,listaObtenida);                
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionFracaso(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                                
        List<PropuestaColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        listaObtenida=instancia.consultarPropuestasColaboracion();
        assertNotEquals(listaEsperada,listaObtenida);        
    }
    
    
    @Test
    public void pruebaConsultarPropuestasColaboracionPorFechaDeInicioExitosa(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion.setIdPropuestaColaboracion(1);
        TipoColaboracion tipoColaboracion=new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setObjetivo("Usar buenas practicas en las bases de datos");                        
        List<PropuestaColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        listaObtenida=instancia.consultarPropuestasColaboracionPorFechaDeInicio("2024-08-10");
        assertEquals(listaEsperada,listaObtenida);                                
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionPorFechaDeInicioFracaso(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                           
        List<PropuestaColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        listaObtenida=instancia.consultarPropuestasColaboracionPorFechaDeInicio("2024-08-10");
        assertNotEquals(listaEsperada,listaObtenida);        
    }        
    
    @Test
    public void pruebaEditarFechaDeInicioDePropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarFechaDeInicioDePropuestaColaboracionPorId("2024-10-16", 1);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaEditarFechaDeInicioDePropuestaColaboracionPorIdFracaso(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.editarFechaDeInicioDePropuestaColaboracionPorId("2024-10-16", 0);
        assertEquals(resultadoEsperado,resultadoObtenido);          
    }
        
    @Test
    public void pruebaEditarFechaDeCierreDePropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarFechaDeCierreDePropuestaColaboracionPorId("2024-12-12", 1);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaEditarFechaDeCierreDePropuestaColaboracionPorIdFracaso(){
         DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.editarFechaDeCierreDePropuestaColaboracionPorId("2024-12-12", 0);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.aprobarPropuestaColaboracionPorId(3);
        assertEquals(resultadoEsperado,resultadoObtenido);                        
    }
        
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdFracaso(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.aprobarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.rechazarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado,resultadoObtenido);                                
    }            
    
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdFracaso(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=0;
        int resultadoObtenido=instancia.rechazarPropuestaColaboracionPorId(0);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarPropuestasDeColaboracionAprobadasExitosa(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        TipoColaboracion tipoColaboracion=new TipoColaboracion();
        tipoColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
        propuestaColaboracion.setExperienciaEducativa("Bases de datos");
        propuestaColaboracion.setObjetivo("Usar buenas practicas en las bases de datos");
        List<PropuestaColaboracion> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(propuestaColaboracion);
        List<PropuestaColaboracion> resultadoObtenido=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
        resultadoObtenido=daoPropuestaColaboracion.consultarPropuestasDeColaboracionAprobadas();
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaObtenerIdPropuestaColaboracionAprobadaPorIdProfesorExitosa(){
        int idProfesor=2;
        int resultadoEsperado=1;
        DAOPropuestaColaboracionImplementacion daoPropuestaColaboracion=new DAOPropuestaColaboracionImplementacion();
        int resultadoObtenido=daoPropuestaColaboracion.obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(idProfesor);
        assertEquals(resultadoEsperado,resultadoObtenido);                        
    }
    
}
    

