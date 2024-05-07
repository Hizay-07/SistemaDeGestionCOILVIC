package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOEvaluacionPropuestaImplementacion;
import logicaDeNegocio.clases.EvaluacionPropuesta;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PruebaDAOEvaluacionPropuestaImplementacion {
    @Test
    public void pruebaRegistrarEvaluacionPropuestaExitosa(){
        EvaluacionPropuesta evaluacionPropuesta=new EvaluacionPropuesta();
        evaluacionPropuesta.setIdUsuario(1);
        evaluacionPropuesta.setIdPropuestaColaboracion(1);
        evaluacionPropuesta.setEvaluacion("Aprobada");
        evaluacionPropuesta.setFechaEvaluacion("2024-05-02");
        evaluacionPropuesta.setJustificacion("Me parece una propuesta interesante");
        DAOEvaluacionPropuestaImplementacion daoEvaluacionPropuesta=new DAOEvaluacionPropuestaImplementacion();
        int resultadoEsperado=1;
        int resultadoObtenido=daoEvaluacionPropuesta.registrarEvaluacionPropuesta(evaluacionPropuesta);
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarEvaluacionesDePropuestaExitosa(){
        EvaluacionPropuesta evaluacionPropuesta=new EvaluacionPropuesta();
        evaluacionPropuesta.setIdEvaluacionPropuesta(1);
        evaluacionPropuesta.setIdUsuario(1);
        evaluacionPropuesta.setIdPropuestaColaboracion(1);
        evaluacionPropuesta.setEvaluacion("Aprobada");
        List<EvaluacionPropuesta> resultadoEsperado=new ArrayList<>();
        resultadoEsperado.add(evaluacionPropuesta);
        DAOEvaluacionPropuestaImplementacion daoEvaluacionPropuesta=new DAOEvaluacionPropuestaImplementacion();
        List<EvaluacionPropuesta> resultadoObtenido=new ArrayList<>();
        resultadoObtenido=daoEvaluacionPropuesta.consultarEvaluacionesDePropuesta();
        assertEquals(resultadoEsperado,resultadoObtenido);         
    }
    
}
