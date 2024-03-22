package pruebaLogicaDeNegocio.DAOImplementacion;


import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPropuestaColaboracionImplementacion;
import logicaDeNegocio.clases.PropuestaColaboracion;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PruebaDAOPropuestaColaboracionImplementacion {
    
    @Test
    public void pruebaRegistrarPropuestaColaboracionExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion.setFechaInicio("2024-08-10");
        propuestaColaboracion.setFechaCierre("2024-12-16");
        propuestaColaboracion.setIdioma("Ingles");
        propuestaColaboracion.setExperienciaEducativa("Bases de datos");
        propuestaColaboracion.setObjetivo("Usar buenas practicas en las bases de datos");
        propuestaColaboracion.setCantidadEstudiantes(20);
        propuestaColaboracion.setProgramaEducativoEstudiantil("Ingenieria de software");
        propuestaColaboracion.setEstadoPropuesta("En proceso");
        propuestaColaboracion.setIdTipoColaboracion(1);
        int resultadoEsperado=1;       
        int resultadoObtenido=instancia.registrarPropuestaColaboracion(propuestaColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);                                
    }
    
    @Test
    public void pruebaConsultarPropuestasColaboracionExitosa(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion.setIdPropuestaColaboracion(1);
        propuestaColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setObjetivo("Usar buenas practicas en las bases de datos");                        
        List<PropuestaColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        listaObtenida=instancia.consultarPropuestasColaboracion();
        assertEquals(listaEsperada,listaObtenida);                
    }
    
    
    @Test
    public void pruebaConsultarPropuestasColaboracionPorFechaDeInicioExitosa(){
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        propuestaColaboracion.setIdPropuestaColaboracion(1);
        propuestaColaboracion.setIdTipoColaboracion(1);
        propuestaColaboracion.setObjetivo("Usar buenas practicas en las bases de datos");                        
        List<PropuestaColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(propuestaColaboracion);
        List<PropuestaColaboracion> listaObtenida=new ArrayList<>();
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        listaObtenida=instancia.consultarPropuestasColaboracionPorFechaDeInicio("2024-08-10");
        assertEquals(listaEsperada,listaObtenida);                                
    }
    
    @Test
    public void pruebaEditarFechaDeInicioDePropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarFechaDeInicioDePropuestaColaboracionPorId("2024-10-16", 1);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
        
    @Test
    public void pruebaEditarFechaDeCierreDePropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.editarFechaDeCierreDePropuestaColaboracionPorId("2024-12-12", resultadoEsperado);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaAprobarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.aprobarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado,resultadoObtenido);                        
    }
        
    @Test
    public void pruebaRechazarPropuestaColaboracionPorIdExitosa(){
        DAOPropuestaColaboracionImplementacion instancia=new DAOPropuestaColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.rechazarPropuestaColaboracionPorId(1);
        assertEquals(resultadoEsperado,resultadoObtenido);                                
    }            
    
}
