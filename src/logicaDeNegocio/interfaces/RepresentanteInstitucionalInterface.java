package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import java.util.List;

public interface RepresentanteInstitucionalInterface{
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado);        
    public int modificarNombreRepresentanteInstitucional(String nombreActualizado, RepresentanteInstitucional representanteAActualizar);
    public int modificarClaveRepresentanteInstitucional(String claveActualizada, RepresentanteInstitucional representanteAActualizar);
    public int modificarContactoRepresentanteInstitucional(String contactoActualizado, RepresentanteInstitucional representanteAActualizar);
    public int modificarPaisRepresentanteInstitucional(RepresentanteInstitucional representanteAActualizar);
    public int verificarExistenciaClaveInstitucionalRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar);
    public int verificarExistenciaNombreInstitucionRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar);
    public int verificarExistenciaContactoInstitucionRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar);
    public List<RepresentanteInstitucional> obtenerRepresentantesInstitucionales();
    public int consultarIdRepresentanteInstitucionalPorUniversidad(String universidad);
    public String consultarNombreInstitucionPorIdRepresentanteInstitucional(int idRepresentanteInstitucional);
    public int verificarRepresentanteInstitucional();
}
