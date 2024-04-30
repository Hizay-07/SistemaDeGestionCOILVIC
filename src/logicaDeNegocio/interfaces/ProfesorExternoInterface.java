package logicaDeNegocio.interfaces;

import java.util.List;
import logicaDeNegocio.clases.ProfesorExterno;

public interface ProfesorExternoInterface {
    public int registrarProfesorExterno(ProfesorExterno profesorExterno);
    public List<ProfesorExterno> consultarProfesoresExternos();    
    public List<ProfesorExterno> consultarProfesoresExternosPorRepresentanteInstitucional(int idRepresentanteInstitucional);
    public int consultarIdRepresentanteInstitucionalPorIdProfesor(int idProfesor);
}
