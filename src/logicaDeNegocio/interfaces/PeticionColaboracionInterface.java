package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.PeticionColaboracion;


public interface PeticionColaboracionInterface {
    int registrarPeticionColaboracion(PeticionColaboracion peticion);
    List<PeticionColaboracion> consultarPeticiones();
    int aceptarColaboracion(int idColaboracion, String nuevoEstado);
    int rechazarColaboracion(int idColaboracion, String nuevoEstado);
    public int consultarIdPropuestaDeColaboracionPorIdProfesor(int idProfesor);
    public List<Integer> consultarIdProfesoresPorIdPropuestaColaboracion(int idPropuestaColaboracion);
    public int aceptarPeticionColaboracion(int idPropuestaColaboracion,int idProfesor);
    public int rechazarPeticionColaboracion(int idPropuestaColaboracion,int idProfesor);    
    public List<Integer> consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(int idPropuestaColaboracion);
    public int revisarPrecondicionEvaluarPeticionesPorIdProfesor(int idProfesor);
    public int cambiarEstadoPeticionesRegistradasPorIdPropuesta(int idPropuestaColaboracion);
}
