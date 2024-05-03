package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.Pais;
import java.util.List;

public interface RepresentanteInstitucionalInterface{
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado);
    public int desactivarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado);
    public int activarRepresentanteInstitucional (RepresentanteInstitucional representanteIngresado);
    public int modificarNombreRepresentanteInstitucional(String nombreActualizado, RepresentanteInstitucional representanteAActualizar);
    public int modificarClaveRepresentanteInstitucional(String claveActualizada, RepresentanteInstitucional representanteAActualizar);
    public int modificarContactoRepresentanteInstitucional(String contactoActualizado, RepresentanteInstitucional representanteAActualizar);
    public int verificarExistenciaRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar);
    public boolean obtenerEstadoRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar);
    public List<RepresentanteInstitucional> obtenerRepresentantesInstitucionales();
    public List<RepresentanteInstitucional> obtenerRepresentantesInstitucionalesPorPais(Pais paisIngresado);
    public int consultarIdRepresentanteInstitucionalPorUniversidad(String universidad);
    public String consultarNombreInstitucionPorIdRepresentanteInstitucional(int idRepresentanteInstitucional);
}
