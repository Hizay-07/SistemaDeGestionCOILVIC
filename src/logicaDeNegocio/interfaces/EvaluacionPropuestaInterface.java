package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.EvaluacionPropuesta;

public interface EvaluacionPropuestaInterface {
    public int registrarEvaluacionPropuesta(EvaluacionPropuesta evaluacionPropuesta);
    
    public List<EvaluacionPropuesta> consultarEvaluacionesDePropuesta();    
}
