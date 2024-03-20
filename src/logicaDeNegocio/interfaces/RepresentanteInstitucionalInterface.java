package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import java.sql.ResultSet;

public interface RepresentanteInstitucionalInterface{
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado);
    public int eliminarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado);
    public int modificarNombreRepresentanteInstitucional(String nombreActualizado, RepresentanteInstitucional representanteAActualizar);
    public int modificarClaveRepresentanteInstitucional(String claveActualizada, RepresentanteInstitucional representanteAActualizar);
    public int modificarContactoRepresentanteInstitucional(String contactoActualizado, RepresentanteInstitucional representanteAActualizar);
    public int verificarExistenciaRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar);
}
