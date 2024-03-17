package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.interfaces.RepresentanteInstitucionalInterface;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class DAORepresentanteInstitucionalImplementacion implements RepresentanteInstitucionalInterface {
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();

    @Override
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado) {
        int resultadoRegistro;
         
        try{
            Connection conexion = BASE_DE_DATOS.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO representanteinstitucional (nombreInstitucion,claveInstitucional,contacto) values (?,?,?)") ;
            sentencia.setString(1, representanteIngresado.getNombreInstitucion());
            sentencia.setString(2, representanteIngresado.getClaveInstitucional());
            sentencia.setString(3, representanteIngresado.getContacto());
            resultadoRegistro = sentencia.executeUpdate();       
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            return -1;
        }
        
        return resultadoRegistro;
    }

    @Override
    public String eliminarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String modificarNombreInstitucionalDeInstitucion(String nombreActualizado, RepresentanteInstitucional representanteAActualizar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String modificarClaveInstitucionalDeInstitucion(String claveActualizada, RepresentanteInstitucional representanteAActualizar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String modificarContactoInstintucionalDeInstitucion(String contactoActualizado, RepresentanteInstitucional representanteAActualizar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
    
}
