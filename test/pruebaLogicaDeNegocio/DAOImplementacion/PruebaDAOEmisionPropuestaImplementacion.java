package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOEmisionPropuestaImplementacion;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.Profesor;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAOEmisionPropuestaImplementacion {
    
    @Test
    public void pruebaRegistrarEmisionPropuestaExitosa(){
        EmisionPropuesta emisionPropuesta=new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(21);
        emisionPropuesta.setIdPropuestaColaboracion(4);
        emisionPropuesta.setFechaEmision("2024-04-28");
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=daoEmisionPropuesta.registrarEmisionPropuesta(emisionPropuesta);
        assertEquals(resultadoEsperado,resultadoObtenido);
    } 
    
    @Test
    public void pruebaConsultarEmisionesDePropuestasExitosa(){
        EmisionPropuesta emisionPropuesta=new EmisionPropuesta();
        emisionPropuesta.setIdProfesor(1);
        emisionPropuesta.setIdPropuestaColaboracion(1);
        EmisionPropuesta emisionPropuesta2=new EmisionPropuesta();
        emisionPropuesta2.setIdProfesor(2);
        emisionPropuesta2.setIdPropuestaColaboracion(3);
        List<EmisionPropuesta> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(emisionPropuesta);
        resultadoEsperado.add(emisionPropuesta2);   
        List<EmisionPropuesta> resultadoObtenido=new ArrayList<>();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        resultadoObtenido=daoEmisionPropuesta.consultarEmisionesDePropuestas();
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarIdProfesorPorIdPropuestaColaboracionExitosa(){
        int idPropuestaColaboracion=1;
        int resultadoEsperado=1;
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        int resultadoObtenido=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(idPropuestaColaboracion);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarIdPropuestaColaboracionProIdProfesor(){
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        int resultadoEsperado=1;
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        int resultadoObtenido=daoEmisionPropuesta.consultarIdPropuestaDeColaboracionPorIdProfesor(profesor);
        assertEquals(resultadoEsperado,resultadoObtenido);  
        
    }
}