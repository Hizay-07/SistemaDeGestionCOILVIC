package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.interfaces.RepresentanteInstitucionalInterface;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class DAORepresentanteInstitucionalImplementacion implements RepresentanteInstitucionalInterface {
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOAreaAcademicaImplementacion.class);

    @Override
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado) {
        int resultadoRegistro;
        DAOPaisImplementacion paisRepresentante = new DAOPaisImplementacion();
        int numeroDePais = paisRepresentante.obtenerNumeroDePais(representanteIngresado.getPais());
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO representanteInstitucional (nombreInstitucion,claveInstitucional,contacto,estadoRepresentante ,Pais_numerodepais) values (?,?,?,?,?)") ;
            sentencia.setString(1, representanteIngresado.getNombreInstitucion());
            sentencia.setString(2, representanteIngresado.getClaveInstitucional());
            sentencia.setString(3, representanteIngresado.getContacto());            
            sentencia.setInt(5, numeroDePais);
            resultadoRegistro = sentencia.executeUpdate();       
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoRegistro = -1;
        }
        return resultadoRegistro;
    }
  
    

    @Override
    public int modificarNombreRepresentanteInstitucional(String nombreActualizado, RepresentanteInstitucional representanteAActualizar) {
        int resultadoModificacion=0;
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET nombreInstitucion = ? WHERE idRepresentanteInstitucional = ?");
            sentencia.setString(1, nombreActualizado);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarClaveRepresentanteInstitucional(String claveActualizada, RepresentanteInstitucional representanteAActualizar) {
        int resultadoModificacion=0;
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET claveInstitucional = ? WHERE idRepresentanteInstitucional = ?");
            sentencia.setString(1, claveActualizada);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarContactoRepresentanteInstitucional(String contactoActualizado, RepresentanteInstitucional representanteAActualizar) {
       int resultadoModificacion=0;
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET contacto = ? WHERE idRepresentanteInstitucional = ?");
            sentencia.setString(1, contactoActualizado);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    @Override
    public int modificarPaisRepresentanteInstitucional(RepresentanteInstitucional representanteAActualizar){
        int resultadoModificacion=0;
        DAOPaisImplementacion paisRepresentante = new DAOPaisImplementacion();
        int numeroDePais = paisRepresentante.obtenerNumeroDePais(representanteAActualizar.getPais());
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET Pais_numeroDePais = ? WHERE idRepresentanteInstitucional = ?");
            sentencia.setInt(1, numeroDePais);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    @Override
    public int verificarExistenciaClaveInstitucionalRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        int validacionDeExistencia=0;
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT COUNT(*) FROM representanteinstitucional WHERE  claveInstitucional = ?");
            sentenciaConsulta.setString(1, representanteAConsultar.getClaveInstitucional());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            while(resultadoDeConsulta.next()){
                validacionDeExistencia = resultadoDeConsulta.getInt(1);
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            validacionDeExistencia = -1;
        }
        return validacionDeExistencia;
    }
    
    @Override
    public int verificarExistenciaNombreInstitucionRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        int validacionDeExistencia=0;
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT COUNT(*) FROM representanteinstitucional WHERE  nombreInstitucion = ?");
            sentenciaConsulta.setString(1, representanteAConsultar.getNombreInstitucion());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            while(resultadoDeConsulta.next()){
                validacionDeExistencia = resultadoDeConsulta.getInt(1);
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            validacionDeExistencia = -1;
        }
        return validacionDeExistencia;
    }
    
     @Override
    public int verificarExistenciaContactoInstitucionRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        int validacionDeExistencia=0;
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT COUNT(*) FROM representanteinstitucional WHERE  contacto = ?");
            sentenciaConsulta.setString(1, representanteAConsultar.getContacto());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            while(resultadoDeConsulta.next()){
                validacionDeExistencia = resultadoDeConsulta.getInt(1);
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            validacionDeExistencia = -1;
        }
        return validacionDeExistencia;
    }
    
    @Override
    public boolean obtenerEstadoRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        boolean estadoRepresentanteInstitucional = false;
        String resultadoDeEstado="";
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT estadoRepresentante from representanteinstitucional where nombreInstitucion = ? AND claveInstitucional = ? AND contacto = ?");
            sentenciaConsulta.setString(1, representanteAConsultar.getNombreInstitucion());
            sentenciaConsulta.setString(2, representanteAConsultar.getClaveInstitucional());
            sentenciaConsulta.setString(3, representanteAConsultar.getContacto());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            if(resultadoDeConsulta.isBeforeFirst()){
                while(resultadoDeConsulta.next()){
                    resultadoDeEstado = (String)resultadoDeConsulta.getString(1);
                }
            }
            estadoRepresentanteInstitucional = resultadoDeEstado.equals("Activo");
        }catch(SQLException | NullPointerException excepcion){
             LOG.error(excepcion.getCause());
             estadoRepresentanteInstitucional = false;
        }
       return  estadoRepresentanteInstitucional;
    }
    
    @Override
    public List<RepresentanteInstitucional> obtenerRepresentantesInstitucionales(){
       List<RepresentanteInstitucional> representantes = new ArrayList();
       try{
           Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement sentencia = conexion.prepareStatement("SELECT idRepresentanteInstitucional,nombreInstitucion,claveInstitucional,contacto,nombrePais FROM representanteinstitucional,pais WHERE pais.numeroDePais = Pais_numeroDePais");
           ResultSet resultado = sentencia.executeQuery();
           if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    RepresentanteInstitucional representanteConsultado = new RepresentanteInstitucional();
                    Pais paisRepresentante = new Pais();
                    representanteConsultado.setNombreInstitucion(resultado.getString("nombreInstitucion"));
                    representanteConsultado.setClaveInstitucional(resultado.getString("claveInstitucional"));
                    representanteConsultado.setIdRepresentanteInstitucional(resultado.getInt("idRepresentanteInstitucional"));
                    representanteConsultado.setContacto(resultado.getString("contacto"));
                    paisRepresentante.setNombrePais(resultado.getString("nombrePais"));
                    representanteConsultado.setPais(paisRepresentante);
                    representantes.add(representanteConsultado);
                }
            }
           BASE_DE_DATOS.cerrarConexion(conexion);
       }catch(SQLException | NullPointerException excepcion){
          LOG.error(excepcion.getCause());
       }
       return representantes;
    }
    
    @Override
    public List<RepresentanteInstitucional> obtenerRepresentantesInstitucionalesPorPais(Pais paisIngresado){
        List<RepresentanteInstitucional> representantes = new ArrayList();
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT idRepresentanteInstitucional,nombreInstitucion,claveInstitucional,contacto,nombrePais FROM representanteinstitucional,pais where nombrepais = ? AND numeroDePais = Pais_numeroDePais");
            sentencia.setString(1, paisIngresado.getNombrePais());
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                   RepresentanteInstitucional representanteConsultado = new RepresentanteInstitucional();
                   Pais paisRepresentante = new Pais();
                   representanteConsultado.setIdRepresentanteInstitucional(resultado.getInt("idRepresentanteInstitucional"));
                   representanteConsultado.setNombreInstitucion(resultado.getString("nombreInstitucion"));
                   representanteConsultado.setClaveInstitucional(resultado.getString("claveInstitucional"));
                   representanteConsultado.setContacto(resultado.getString("contacto"));
                   paisRepresentante.setNombrePais(resultado.getString("nombrePais"));
                   representanteConsultado.setPais(paisRepresentante);
                   representantes.add(representanteConsultado);
                }
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }
        return representantes;
    }
    
    @Override
    public int consultarIdRepresentanteInstitucionalPorUniversidad(String universidad){
        PreparedStatement declaracion;
        ResultSet resultado;
        int idRepresentanteInstitucional=0;
        try {
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT idRepresentanteInstitucional from RepresentanteInstitucional where nombreInstitucion=?");
            declaracion.setString(1, universidad);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idRepresentanteInstitucional=resultado.getInt("idRepresentanteInstitucional");                
                }
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return idRepresentanteInstitucional;        
    }
    
    @Override
    public String consultarNombreInstitucionPorIdRepresentanteInstitucional(int idRepresentanteInstitucional){
        PreparedStatement declaracion;
        ResultSet resultado;
        String nombreInstitucion=new String();
        try {
            Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT nombreInstitucion from RepresentanteInstitucional where idRepresentanteInstitucional=?;");
            declaracion.setInt(1, idRepresentanteInstitucional);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                nombreInstitucion=resultado.getString("nombreInstitucion");                
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return nombreInstitucion;        
    }
}
