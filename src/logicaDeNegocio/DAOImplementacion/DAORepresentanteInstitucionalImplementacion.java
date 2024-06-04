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
    private static final Logger LOG=Logger.getLogger(DAORepresentanteInstitucionalImplementacion.class);

    @Override
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado) {
        int resultadoRegistro;
        DAOPaisImplementacion paisRepresentante = new DAOPaisImplementacion();
        int numeroDePais = paisRepresentante.obtenerNumeroDePais(representanteIngresado.getPais());
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO representanteInstitucional (nombreInstitucion,claveInstitucional,contacto,Pais_numerodepais) values (?,?,?,?)")){
            sentencia.setString(1, representanteIngresado.getNombreInstitucion());
            sentencia.setString(2, representanteIngresado.getClaveInstitucional());
            sentencia.setString(3, representanteIngresado.getContacto());            
            sentencia.setInt(4, numeroDePais);
            resultadoRegistro = sentencia.executeUpdate();       
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoRegistro = -1;
        }
        return resultadoRegistro;
    }
  
    

    @Override
    public int modificarNombreRepresentanteInstitucional(String nombreActualizado, RepresentanteInstitucional representanteAActualizar) {
        int resultadoModificacion=0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET nombreInstitucion = ? WHERE idRepresentanteInstitucional = ?")){
            sentencia.setString(1, nombreActualizado);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarClaveRepresentanteInstitucional(String claveActualizada, RepresentanteInstitucional representanteAActualizar) {
        int resultadoModificacion=0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET claveInstitucional = ? WHERE idRepresentanteInstitucional = ?")){
            sentencia.setString(1, claveActualizada);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public int modificarContactoRepresentanteInstitucional(String contactoActualizado, RepresentanteInstitucional representanteAActualizar) {
       int resultadoModificacion=0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET contacto = ? WHERE idRepresentanteInstitucional = ?")){
            sentencia.setString(1, contactoActualizado);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    @Override
    public int modificarPaisRepresentanteInstitucional(RepresentanteInstitucional representanteAActualizar){
        int resultadoModificacion=0;
        DAOPaisImplementacion paisRepresentante = new DAOPaisImplementacion();
        int numeroDePais = paisRepresentante.obtenerNumeroDePais(representanteAActualizar.getPais());
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE representanteinstitucional SET Pais_numeroDePais = ? WHERE idRepresentanteInstitucional = ?")){
            sentencia.setInt(1, numeroDePais);
            sentencia.setInt(2,representanteAActualizar.getIdRepresentanteInstitucional());
            resultadoModificacion = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
    
    @Override
    public int verificarExistenciaClaveInstitucionalRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        int validacionDeExistencia=0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT COUNT(*) FROM representanteinstitucional WHERE  claveInstitucional = ?")){
            sentenciaConsulta.setString(1, representanteAConsultar.getClaveInstitucional());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            while(resultadoDeConsulta.next()){
                validacionDeExistencia = resultadoDeConsulta.getInt(1);
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            validacionDeExistencia = -1;
        }
        return validacionDeExistencia;
    }
    
    @Override
    public int verificarExistenciaNombreInstitucionRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        int validacionDeExistencia=0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT COUNT(*) FROM representanteinstitucional WHERE  nombreInstitucion = ?")){
            sentenciaConsulta.setString(1, representanteAConsultar.getNombreInstitucion());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            while(resultadoDeConsulta.next()){
                validacionDeExistencia = resultadoDeConsulta.getInt(1);
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            validacionDeExistencia = -1;
        }
        return validacionDeExistencia;
    }
    
     @Override
    public int verificarExistenciaContactoInstitucionRepresentanteInstitucional(RepresentanteInstitucional representanteAConsultar){
        int validacionDeExistencia=0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaConsulta = conexion.prepareStatement("SELECT COUNT(*) FROM representanteinstitucional WHERE  contacto = ?")){
            sentenciaConsulta.setString(1, representanteAConsultar.getContacto());
            ResultSet resultadoDeConsulta = sentenciaConsulta.executeQuery();
            while(resultadoDeConsulta.next()){
                validacionDeExistencia = resultadoDeConsulta.getInt(1);
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            validacionDeExistencia = -1;
        }
        return validacionDeExistencia;
    }
    
    
    
    @Override
    public List<RepresentanteInstitucional> obtenerRepresentantesInstitucionales(){
       List<RepresentanteInstitucional> representantes = new ArrayList();
       try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement sentencia = conexion.prepareStatement("SELECT idRepresentanteInstitucional,nombreInstitucion,claveInstitucional,contacto,nombrePais FROM representanteinstitucional,pais WHERE pais.numeroDePais = Pais_numeroDePais")){
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
       }catch(SQLException | NullPointerException excepcion){
          LOG.error(excepcion.getMessage());
       }
       return representantes;
    }
    
    
    
    @Override
    public int consultarIdRepresentanteInstitucionalPorUniversidad(String universidad){
        ResultSet resultado;
        int idRepresentanteInstitucional=0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idRepresentanteInstitucional from RepresentanteInstitucional where nombreInstitucion=?")){
            declaracion.setString(1, universidad);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idRepresentanteInstitucional=resultado.getInt("idRepresentanteInstitucional");                
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return idRepresentanteInstitucional;        
    }
    
    @Override
    public String consultarNombreInstitucionPorIdRepresentanteInstitucional(int idRepresentanteInstitucional){
        ResultSet resultado;
        String nombreInstitucion=new String();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT nombreInstitucion from RepresentanteInstitucional where idRepresentanteInstitucional=?;")){
            declaracion.setInt(1, idRepresentanteInstitucional);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                nombreInstitucion=resultado.getString("nombreInstitucion");                
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return nombreInstitucion;        
    }
    
    @Override
    public int verificarRepresentanteInstitucional(){
        int resultadoVerificacion=0;
        ResultSet resultado;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select count(*) from representanteInstitucional")){
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                resultadoVerificacion=resultado.getInt(1);                
            }        
        }catch(SQLException excepcion){
            LOG.error(excepcion);
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;            
    }
}
