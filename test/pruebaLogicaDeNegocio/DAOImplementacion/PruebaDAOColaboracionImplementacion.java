package pruebaLogicaDeNegocio.DAOImplementacion;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
=======
import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

>>>>>>> 9fda9407ddcf920dd702160fd57cbfce28ad6525

public class PruebaDAOColaboracionImplementacion {
    
    @Test
    public void pruebaRegistrarColaboracionExitosa(){
<<<<<<< HEAD
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setEstadoColaboracion("En proceso");
        colaboracion.setIdPropuestaColaboracion(1);
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarColaboracion(colaboracion);
=======
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setFechaInicio("2024-08-10");
        colaboracion.setFechaCierre("2024-12-16");
        colaboracion.setIdioma("Ingles");
        colaboracion.setTipoColaboracion("Clase espejo");
        colaboracion.setExperienciaEducativa("Base de datos no relacionales");
        instancia.setColaboracion(colaboracion);
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarColaboracion();
>>>>>>> 9fda9407ddcf920dd702160fd57cbfce28ad6525
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarColaboracionesExitosa(){
<<<<<<< HEAD
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setIdColaboracion(1);
        colaboracion.setIdPropuestaColaboracion(1);
        List<Colaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(colaboracion);
        List<Colaboracion> listaObtenida=new ArrayList<>();
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        listaObtenida=instancia.consultarColaboraciones();
        assertEquals(listaEsperada,listaObtenida);                
    }
    
    @Test
    public void pruebaRegistrarRetroalimentacionColaboracionPorId(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        String retroalimentacion="La colaboracion obtuvo los resultados esperados";
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarRetroalimentacionColaboracionPorId(1, retroalimentacion);
        assertEquals(resultadoEsperado,resultadoObtenido);                 
    }        
=======
        
    }
    
    @Test
    public void pruebaConsultarColaboracionPorFechaDeInicioExitosa(){
    }
    
    @Test
    public void pruebaEditarFechaDeInicioDeColaboracionExitosa(){
    }
    
>>>>>>> 9fda9407ddcf920dd702160fd57cbfce28ad6525
    
}
