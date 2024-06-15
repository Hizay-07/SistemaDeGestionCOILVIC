package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.interfaces.PeticionColaboracionInterface;
import org.apache.log4j.Logger;

public class DAOPeticionColaboracionImplementacion implements PeticionColaboracionInterface {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOPeticionColaboracionImplementacion.class);

    /**
    *Registrar una petición de colaboracion dentro de la base de datos
    *@param peticion PeticionColaboracion con los datos a registrar en la base de
    * datos
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarPeticionColaboracion(PeticionColaboracion peticion) {
        int numeroFilasAfectadas = 0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO peticioncolaboracion (idProfesor, idPropuestaColaboracion, estadoPeticion, fechaEnvio)"
                    + "VALUES (?, ?, ?, ?);")){
            declaracion.setInt(1, peticion.getIdProfesor());
            declaracion.setInt(2, peticion.getIdPropuestaColaboracion());
            declaracion.setString(3, peticion.getEstado());
            declaracion.setString(4, peticion.getFechaEnvio());
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;
    }
    
    /**
    *Obtener las peticiones de colaboración registradas en la base de datos
    *@return Regresa la lista de peticiones de colaboración registradas en la 
    * base de datos
    **/
    @Override
    public List<PeticionColaboracion> consultarPeticiones() {
        ResultSet resultado;
        List<PeticionColaboracion> peticiones = new ArrayList<>();
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM peticioncolaboracion")){
            resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while (resultado.next()) {
                    PeticionColaboracion peticion = new PeticionColaboracion();
                    peticion.setIdProfesor(resultado.getInt("idProfesor"));
                    peticion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                    peticion.setEstado(resultado.getString("estadoPeticion"));
                    peticion.setFechaEnvio(resultado.getString("fechaEnvio"));
                    peticiones.add(peticion);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            PeticionColaboracion peticion = new PeticionColaboracion();
            peticion.setEstado("Excepcion");
            peticiones.add(0, peticion);
        }
        return peticiones;
    }

    /**
    *Consultar el ID Propuesta de una propuesta de colaboración registrada en la 
    * base de datos asociada a un profesor y a una petición de colaboración
    *@param idProfesor Int con el ID de un profesor asociado a una petición de
    * colaboración registrada en la base de datos
    *@return Regresa el ID de la propuesta de colaboración
    **/
    @Override
    public int consultarIdPropuestaDeColaboracionPorIdProfesor(int idProfesor){
        ResultSet resultado;
        int idPropuestaColaboracion=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idPropuestaColaboracion from PeticionColaboracion where idProfesor=? and estadoPeticion='Registrada';")){
            declaracion.setInt(1, idProfesor);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idPropuestaColaboracion=resultado.getInt("idPropuestaColaboracion");
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            idPropuestaColaboracion=-1;
        }
        return idPropuestaColaboracion;
    }
    
    /**
    * Obtener los ID de profesores asociados a una petición de colaboración en estado
    * registrada a través del ID de propuesta de colaboración
    *@param idPropuestaColaboracion Int con el ID de la propuesta de colaboración
    * asociada a la petición de colaboración
    *@return Regresa la lista con los ID de los profesores asociados a una petición de
    * colaboración registrada en la base de datos
    **/
    @Override
    public List<Integer> consultarIdProfesoresPorIdPropuestaColaboracion(int idPropuestaColaboracion){
        ResultSet resultado;
        List<Integer> idProfesores=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("Select idProfesor from peticionColaboracion where idPropuestaColaboracion=? and estadoPeticion='Registrada';")){
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                int idProfesor=resultado.getInt("idProfesor");
                idProfesores.add(idProfesor);                
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            idProfesores.add(0, -1);
        }
        return idProfesores;                        
    }
    
    /**
    *Registrar la aceptación de una petición de colaboración en la base de datos
    *@param idProfesor Int con el ID de un profesor asociado a una petición de
    * colaboración registrada en la base de datos
    *@param idPropuestaColaboracion Int con el ID de una propuesta de colaboración
    * asociado a una petición de colaboración registrada en la base de datos.
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int aceptarPeticionColaboracion(int idPropuestaColaboracion,int idProfesor) {
        int numeroFilasAfectadas = 0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE peticioncolaboracion SET estadoPeticion='Aceptada' WHERE idPropuestaColaboracion=? and idProfesor=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            declaracion.setInt(2, idProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;
    }
    
    /**
    *Registrar el rechazo de una petición de colaboración en la base de datos
    *@param idProfesor Int con el ID de un profesor asociado a una petición de
    * colaboración registrada en la base de datos
    *@param idPropuestaColaboracion Int con el ID de una propuesta de colaboración
    * asociado a una petición de colaboración registrada en la base de datos.
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int rechazarPeticionColaboracion(int idPropuestaColaboracion,int idProfesor) {
        int numeroFilasAfectadas = 0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE peticioncolaboracion SET estadoPeticion='Rechazada' WHERE idPropuestaColaboracion=? and idProfesor=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            declaracion.setInt(2, idProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;
    }
    
    /**
    *Consultar el ID de los profesores pertenecientes a una petición de colaboración aceptada
    * a través del ID propuesta de colaboración
    *@param idPropuestaColaboracion Int con el ID de una propuesta de colaboración asociada
    * a una peticiónd de colaboración
    *@return Regresa la lista de ID de profesores asociados a una petición de colaboración
    **/
    @Override
    public List<Integer> consultarIdProfesoresPorIdPropuestaColaboracionAceptadas(int idPropuestaColaboracion){
        ResultSet resultado;
        List<Integer> idProfesores=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("Select idProfesor from peticionColaboracion where idPropuestaColaboracion=? and estadoPeticion='Aceptada';")){
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                int idProfesor=resultado.getInt("idProfesor");
                idProfesores.add(idProfesor);                
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            idProfesores.add(0,-1);
        }
        return idProfesores;                        
    }
    
    /**
    *Consultar el numero de peticiones de colaboración aceptadas registradas en la base de datos,
    * asociadas a un profesor
    *@param idProfesor Int con el ID de un profesor asociado a una petición de colaboración
    * registrada en la base de datos
    *@return Regresa el numero de peticiones de colaboración aceptadas asociadas a un profesor
    **/
    @Override
    public int revisarPrecondicionEvaluarPeticionesPorIdProfesor(int idProfesor){
        int resultadoPrecondicion=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("CALL revisarPeticionesColaboracion(?,?); ")){
            declaracion.setInt(1, idProfesor);
            declaracion.registerOutParameter(2, Types.INTEGER);
            declaracion.execute();
            resultadoPrecondicion=declaracion.getInt(2);  
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoPrecondicion=-1;
        }
        return resultadoPrecondicion;        
    }
    
    /**
    *Actualizar el estado de peticiones de colaboración registradas en la base de datos a través
    * del ID de la propuesta de colaboración asociado
    *@param idPropuestaColaboracion Int con el ID de una propuesta de colaboración asociada
    * a una peticiónd de colaboración
    *@return Regresa el resultado del cambio de estado de la petición de colaboración
    **/
    @Override
    public int cambiarEstadoPeticionesRegistradasPorIdPropuesta(int idPropuestaColaboracion){
        int resultadoCambioEstado=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("CALL CambiarEstadoPeticiones(?); ")){
            declaracion.setInt(1, idPropuestaColaboracion);            
            resultadoCambioEstado=declaracion.executeUpdate();               
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoCambioEstado=-1;
        }
        return resultadoCambioEstado;                  
    }         
    
}
