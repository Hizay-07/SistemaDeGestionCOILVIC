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

    /**
    *Registrar un representante institucional dentro de la base de datos
    *@param representanteIngresado RepresentanteInstitucional con los datos a registrar
    * dentro de la base de datos
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarRepresentanteInstitucional(RepresentanteInstitucional representanteIngresado) {
        int resultadoRegistro=0;
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
    
    /**
    *Editar el nombre asociado a un representante institucional registrado en la base de datos
    *@param representanteAActualizar RepresentanteInstitucional a actualizar
    *@param nombreActualizado String con el nuevo nombre a asignar
    *@return Regresa el numero de filas afectadas
    **/  
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

    /**
    *Editar la clave institucional asociada a un representante institucional registrado en la base de datos
    *@param representanteAActualizar RepresentanteInstitucional a actualizar
    *@param claveActualizada String con la nueva clave institucional a asignar
    *@return Regresa el numero de filas afectadas
    **/  
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

    /**
    *Editar el contacto asociado a un representante institucional registrado en la base de datos
    *@param representanteAActualizar RepresentanteInstitucional a actualizar
    *@param contactoActualizado String con el contacto nombre a asignar
    *@return Regresa el numero de filas afectadas
    **/ 
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
    
    /**
    *Editar el país asociado a un representante institucional registrado en la base de datos
    *@param representanteAActualizar RepresentanteInstitucional a actualizar
    *@return Regresa el numero de filas afectadas
    **/ 
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
    
    /**
    *Verificar la existencia de un representante institucional en la base de datos
    * a través de su clave institucional asociado
    *@param representanteAConsultar RepresentanteInstitucional a consultar
    *@return Regresa el numero de coincidencias encontradas
    **/ 
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
    
    /**
    *Verificar la existencia de un representante institucional en la base de datos
    * a través de su nombre asociado
    *@param representanteAConsultar RepresentanteInstitucional a consultar
    *@return Regresa el numero de coincidencias encontradas
    **/ 
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
    
    /**
    *Verificar la existencia de un representante institucional en la base de datos
    * a través de su contacto asociado
    *@param representanteAConsultar RepresentanteInstitucional a consultar
    *@return Regresa el numero de coincidencias encontradas
    **/ 
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

            

    
    
    /**
    *Obtener los representantes institucionales registrados en la base de datos
    *@return Regresa la lista de representantes institucionales
    **/

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
    
    /**
    *Obtener el ID de un representante institucional a través de su nombre asignado
    * @param universidad String con el nombre asociado a un representante
    * institucional
    *@return Regresa el ID de un representante institucional
    **/
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
            idRepresentanteInstitucional=-1;
        }
        return idRepresentanteInstitucional;        
    }
    
    /**
    *Obtener el nombre de un representante institucional a través de su ID asignado
    * @param idRepresentanteInstitucional Int con el ID asociado a un representante
    * institucional
    *@return Regresa el nombre de un representante institucional
    **/
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
    
    /**
    *Obtener el numero de representantes institucionales registrados dentro de la base de datos
    *@return Regresa el numero de representantes institucionales
    **/
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
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion);
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;            
    }
    
}
