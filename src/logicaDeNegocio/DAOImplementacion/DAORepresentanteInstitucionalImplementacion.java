package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.interfaces.RepresentanteInstitucionalInterface;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAORepresentanteInstitucionalImplementacion implements RepresentanteInstitucionalInterface {
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();

    @Override
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado) {
        int resultadoRegistro;
        DAOPaisImplementacion paisRepresentante = new DAOPaisImplementacion();
        int numeroDePais = paisRepresentante.obtenerNumeroDePais(representanteIngresado.getPais());
         
        try{
            Connection conexion = BASE_DE_DATOS.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO representanteinstitucional (nombreInstitucion,claveInstitucional,contacto, Pais_numerodepais) values (?,?,?,?)") ;
            sentencia.setString(1, representanteIngresado.getNombreInstitucion());
            sentencia.setString(2, representanteIngresado.getClaveInstitucional());
            sentencia.setString(3, representanteIngresado.getContacto());
            sentencia.setInt(4, numeroDePais);
            resultadoRegistro = sentencia.executeUpdate();       
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            return -1;
        }
        
        return resultadoRegistro;
    }

    @Override
    public int eliminarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado) {
        return 0;
    }

    @Override
    public int modificarNombreRepresentanteInstitucional(String nombreActualizado, RepresentanteInstitucional representanteAActualizar) {
        int resultadoModificacion=0;
        
        try{
            Connection conexion = BASE_DE_DATOS.getConexion();
            
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET nombreInstitucion = ? WHERE nombreInstitucion = ? AND claveInstitucional = ? AND contacto = ?");
            sentencia.setString(1, nombreActualizado);
            sentencia.setString(2, representanteAActualizar.getNombreInstitucion());
            sentencia.setString(3, representanteAActualizar.getClaveInstitucional());
            sentencia.setString(4,representanteAActualizar.getContacto());
            resultadoModificacion = sentencia.executeUpdate();
            
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAORepresentanteInstitucionalImplementacion.class.getName()).log(Level.SEVERE, excepcion.getMessage(), excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarClaveRepresentanteInstitucional(String claveActualizada, RepresentanteInstitucional representanteAActualizar) {
        int resultadoModificacion=0;
        
        try{
            Connection conexion = BASE_DE_DATOS.getConexion();
            
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET claveInstitucional = ? WHERE nombreInstitucion = ? AND claveInstitucional = ? AND contacto = ?");
            sentencia.setString(1, claveActualizada);
            sentencia.setString(2, representanteAActualizar.getNombreInstitucion());
            sentencia.setString(3, representanteAActualizar.getClaveInstitucional());
            sentencia.setString(4,representanteAActualizar.getContacto());
            resultadoModificacion = sentencia.executeUpdate();
            
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAORepresentanteInstitucionalImplementacion.class.getName()).log(Level.SEVERE, excepcion.getMessage(), excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarContactoRepresentanteInstitucional(String contactoActualizado, RepresentanteInstitucional representanteAActualizar) {
       int resultadoModificacion=0;
        
        try{
            Connection conexion = BASE_DE_DATOS.getConexion();
            
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET contacto = ? WHERE nombreInstitucion = ? AND claveInstitucional = ? AND contacto = ?");
            sentencia.setString(1, contactoActualizado);
            sentencia.setString(2, representanteAActualizar.getNombreInstitucion());
            sentencia.setString(3, representanteAActualizar.getClaveInstitucional());
            sentencia.setString(4,representanteAActualizar.getContacto());
            resultadoModificacion = sentencia.executeUpdate();
            
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAORepresentanteInstitucionalImplementacion.class.getName()).log(Level.SEVERE, excepcion.getMessage(), excepcion);
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    @Override
    public int verificarExistenciaRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        int validacionDeExistencia=0;
        
        try{
            Connection conexion = BASE_DE_DATOS.getConexion();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT COUNT(*) FROM representanteinstitucional WHERE nombreInstitucion = ? AND claveInstitucional = ? AND contacto = ?");
            sentenciaConsulta.setString(1, representanteAConsultar.getNombreInstitucion());
            sentenciaConsulta.setString(2, representanteAConsultar.getClaveInstitucional());
            sentenciaConsulta.setString(3, representanteAConsultar.getContacto());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            
            while(resultadoDeConsulta.next()){
                validacionDeExistencia = (int)resultadoDeConsulta.getLong(1);
            }
            
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAORepresentanteInstitucionalImplementacion.class.getName()).log(Level.SEVERE, excepcion.getMessage(), excepcion);
            validacionDeExistencia = -1;
        }
        
        return validacionDeExistencia;
    }
    

    
    
}
