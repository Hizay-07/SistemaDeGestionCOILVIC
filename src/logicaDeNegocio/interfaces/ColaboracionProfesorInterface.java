package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;


public interface ColaboracionProfesorInterface {
    
    public List<Profesor> obtenerProfesoresPorIdColaboracion(Colaboracion colaboracion);

    public Colaboracion obtenerColaboracionPorIdProfesor(Profesor profesor);

    public int registrarColaboracionProfesor(Profesor profesor, Colaboracion colaboracion);
}
