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
        }
        return peticiones;
    }

    @Override
    public int aceptarColaboracion(int idColaboracion, String nuevoEstado) {
        int numeroFilasAfectadas = 0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE peticioncolaboracion SET estadoPeticion=? WHERE idColaboracion=?;")){
            declaracion.setString(1, nuevoEstado);
            declaracion.setInt(2, idColaboracion);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;
    }

    @Override
    public int rechazarColaboracion(int idColaboracion, String nuevoEstado) {
        int numeroFilasAfectadas = 0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE peticioncolaboracion SET estadoPeticion=? WHERE idColaboracion=?;")){
            declaracion.setString(1, nuevoEstado);
            declaracion.setInt(2, idColaboracion);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;
    }
    
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
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return idProfesores;                        
    }
    
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
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return idProfesores;                        
    }
    
    public int revisarPrecondicionEvaluarPeticionesPorIdProfesor(int idProfesor){
        int resultadoPrecondicion=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("CALL revisarPeticionesColaboracion(?,?); ")){
            declaracion.setInt(1, idProfesor);
            declaracion.registerOutParameter(2, Types.INTEGER);
            declaracion.execute();
            resultadoPrecondicion=declaracion.getInt(2);  
        }catch(SQLException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoPrecondicion=-1;
        }
        return resultadoPrecondicion;        
    }
    
    public int cambiarEstadoPeticionesRegistradasPorIdPropuesta(int idPropuestaColaboracion){
        int resultadoCambioEstado=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("CALL CambiarEstadoPeticiones(?); ")){
            declaracion.setInt(1, idPropuestaColaboracion);            
            declaracion.execute();   
            resultadoCambioEstado=1;        
        }catch(SQLException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoCambioEstado=-1;
        }
        return resultadoCambioEstado;  
        
        
    }
    
    
    
    
}
