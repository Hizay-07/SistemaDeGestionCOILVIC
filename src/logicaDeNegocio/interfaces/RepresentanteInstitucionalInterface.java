package logicaDeNegocio.interfaces;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import java.sql.ResultSet;

public interface RepresentanteInstitucionalInterface{
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado);
    public String eliminarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado);
    public String modificarNombreInstitucionalDeInstitucion(String nombreActualizado, RepresentanteInstitucional representanteAActualizar);
    public String modificarClaveInstitucionalDeInstitucion(String claveActualizada, RepresentanteInstitucional representanteAActualizar);
    public String modificarContactoInstintucionalDeInstitucion(String contactoActualizado, RepresentanteInstitucional representanteAActualizar);
}
