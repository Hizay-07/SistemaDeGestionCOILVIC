package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import com.mysql.cj.jdbc.CallableStatement;
import logicaDeNegocio.clases.PropuestaColaboracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.interfaces.PropuestaColaboracionInterface;

public class DAOPropuestaColaboracionImplementacion implements PropuestaColaboracionInterface {
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(DAOPropuestaColaboracionImplementacion.class);
    
    /**
    *Registrar una propuesta de colaboración dentro de la base de datos
    *@param propuestaColaboracion PropuestaColaboracion con los datos a registrar
    * dentro de la base de datos
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion) {        
        int idPropuestaColaboracion=0;        
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("call registrarPropuestaColaboracion(?,?,?,?,?,?,?,?,?)")){
            declaracion.setString(1, propuestaColaboracion.getFechaInicio());
            declaracion.setString(2, propuestaColaboracion.getFechaCierre());
            declaracion.setString(3, propuestaColaboracion.getIdioma());
            declaracion.setString(4, propuestaColaboracion.getExperienciaEducativa());
            declaracion.setString(5,propuestaColaboracion.getObjetivo());
            declaracion.setString(6, propuestaColaboracion.getProgramaEducativoEstudiantil());
            declaracion.setString(7, propuestaColaboracion.getEstadoPropuesta());
            declaracion.setInt(8, propuestaColaboracion.getTipoColaboracion().getIdTipoColaboracion());
            declaracion.registerOutParameter(9, Types.INTEGER);
            declaracion.execute();            
            idPropuestaColaboracion=declaracion.getInt(9);           
        } catch (SQLException | NullPointerException excepcion) {
            LOG.fatal(excepcion.getMessage());
            System.out.println(excepcion.getMessage());
            idPropuestaColaboracion = -1;
        }
        return idPropuestaColaboracion;        
    }
       
    /**
    *Aprobar una propuesta de colaboración por medio de su ID asignado en la base
    * de datos
    *@param idPropuestaColaboracion int con el ID de una propuesta de colaboración
    * registrada en la base de datos 
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int aprobarPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;  
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Aprobada' where idPropuestaColaboracion=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.error(ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;        
    }

    /**
    *Rechazar una propuesta de colaboración por medio de su ID asignado en la base
    * de datos
    *@param idPropuestaColaboracion int con el ID de una propuesta de colaboración
    * registrada en la base de datos 
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int rechazarPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0; 
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Rechazada' where idPropuestaColaboracion=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.error(ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;         
    }
    
    /**
    *Obtener las propuestas de colaboración aceptadas registradas dentro de la base de datos
    *@return Regresa la lista de Propuestas de colaboración registradas dentro de la
    * base de datos
    **/
    @Override
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadas(){
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where estadoPropuesta='Aprobada';")){      
            resultado=declaracion.executeQuery();            
            while(resultado.next()){
                PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));                
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));                
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));                
                TipoColaboracion tipoColaboracion=new TipoColaboracion();
                int idTipoColaboracion=resultado.getInt("idTipoColaboracion");
                tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                String tipo=daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                tipoColaboracion.setTipo(tipo);
                propuestaColaboracion.setTipoColaboracion(tipoColaboracion);                
                int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                Profesor profesor=daoProfesor.consultarProfesorPorId(idProfesor);            
                propuestaColaboracion.setProfesor(profesor);                
                propuestasColaboracion.add(propuestaColaboracion);                
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.warn(excepcion);
            PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();   
            propuestaColaboracion.setEstadoPropuesta("Excepcion");
            propuestasColaboracion.add(0,propuestaColaboracion);
        }
        return propuestasColaboracion;        
    }
    
    /**
    *Obtener las propuestas de colaboración registradas dentro de la base de datos
    * a través del ID de propuesta de colaboración asociado
    * @param idPropuestaColaboracion int con el ID de propuesta de colaboracion asociado
    * a una propuesta de colaboración registrada dentro de la base de datos
    *@return Regresa la Propuesta de colaboración registrada dentro de la base de datos
    **/
    @Override
    public PropuestaColaboracion obtenerPropuestaDeColaboracionPorId(int idPropuestaColaboracion){
        ResultSet resultado;
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        DAOTipoColaboracionImplementacion daoTipoColaboracion = new DAOTipoColaboracionImplementacion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where idPropuestaColaboracion=?")){
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));
                int idTipoColaboracion = resultado.getInt("idTipoColaboracion");
                tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                String tipo = daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                tipoColaboracion.setTipo(tipo);
                propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
            }
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
            propuestaColaboracion.setEstadoPropuesta("Excepcion");
        }
        return propuestaColaboracion;
    }
    
    /**
    *Obtener las propuestas de colaboración registradas dentro de la base de datos
    *@return Regresa la lista de Propuestas de colaboración registradas dentro de la
    * base de datos
    **/
    @Override
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionRegistradas(){
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where estadoPropuesta='Registrada';")){       
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                
                    propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                    propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                    propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                    propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                    propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                    propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));                
                    propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));                
                    propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));                
                    TipoColaboracion tipoColaboracion=new TipoColaboracion();
                    int idTipoColaboracion=resultado.getInt("idTipoColaboracion");
                    tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                    String tipo=daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                    tipoColaboracion.setTipo(tipo);
                    propuestaColaboracion.setTipoColaboracion(tipoColaboracion);                
                    int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                    Profesor profesor=daoProfesor.consultarProfesorPorId(idProfesor);            
                    propuestaColaboracion.setProfesor(profesor);                
                    propuestasColaboracion.add(propuestaColaboracion);                
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion);
            PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();   
            propuestaColaboracion.setEstadoPropuesta("Excepcion");
            propuestasColaboracion.add(0,propuestaColaboracion);
        }
        return propuestasColaboracion;                
    }
    
    /**
    *Obtener el ID de propuesta de colaboración aprobada a través de un ID de profesor
    * asociado en la base de datos
    * @param idProfesor int con el ID de profesor registrado en la base de datos
    *@return Regresa el ID de propuesta de colaboración encontrada
    **/
    @Override
    public int obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(int idProfesor){
        ResultSet resultado;
        int idPropuestaColaboracion=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("select p.idPropuestaColaboracion from emisionPropuesta e, propuestaColaboracion p where idProfesor=? AND p.idPropuestaColaboracion=e.idPropuestaColaboracion AND p.estadoPropuesta='Aprobada';")){
            declaracion.setInt(1, idProfesor);
            resultado=declaracion.executeQuery();           
            if(resultado.next()){
                idPropuestaColaboracion=resultado.getInt("idPropuestaColaboracion");                
            }      
        }catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
            return -1;
        }
        return idPropuestaColaboracion;                                        
    }
    
    /**
    *Obtener las propuestas de colaboración registradas dentro de la base de datos sin 
    * peticiones asociadas
    * @param identificadorProfesor int con ID de profesor registrado dentro de la base de datos
    *@return Regresa la lista de Propuestas de colaboración registradas dentro de la
    * base de datos
    **/
    @Override
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadasSinPeticiones(int identificadorProfesor){
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM propuestaColaboracion pc WHERE pc.estadoPropuesta = 'Aprobada' AND pc.idPropuestaColaboracion NOT IN (SELECT idPropuestaColaboracion FROM peticionColaboracion WHERE idProfesor = ?);")){      
            declaracion.setInt(1, identificadorProfesor);
            resultado=declaracion.executeQuery();            
            while(resultado.next()){
                PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));                
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));                
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));                
                TipoColaboracion tipoColaboracion=new TipoColaboracion();
                int idTipoColaboracion=resultado.getInt("idTipoColaboracion");
                tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                String tipo=daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                tipoColaboracion.setTipo(tipo);
                propuestaColaboracion.setTipoColaboracion(tipoColaboracion);                
                int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                Profesor profesor=daoProfesor.consultarProfesorPorId(idProfesor);            
                propuestaColaboracion.setProfesor(profesor);                
                propuestasColaboracion.add(propuestaColaboracion);                
            }
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
            PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();   
            propuestaColaboracion.setEstadoPropuesta("Excepcion");
            propuestasColaboracion.add(0,propuestaColaboracion);
        }
        return propuestasColaboracion; 
    }
    
     /**
    *Modificar el estado de una propuesta de colaboración a travpes del ID de propuesta
    * de colaboración
    *@param idPropuestaColaboracion int con el ID de una propuesta de colaboración
    * registrada en la base de datos 
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int cambiarEstadoIniciadaPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0; 
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Iniciada' where idPropuestaColaboracion=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.error(ex);
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;        
    }

}
