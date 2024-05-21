package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.interfaces.ProfesorInterface;
import logicaDeNegocio.clases.Profesor;
import accesoADatos.ManejadorBaseDeDatos;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import logicaDeNegocio.clases.Usuario;
import org.apache.log4j.Logger;

public class DAOProfesorImplementacion implements ProfesorInterface {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private Connection conexion;
    private static final Logger LOG=Logger.getLogger(DAOProfesorImplementacion.class);
    
    @Override
    public int registrarProfesor(Profesor profesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("INSERT INTO profesor(nombre, apellidoPaterno, apellidoMaterno, correo, estadoProfesor) VALUES (?, ?, ?, ?, ?)");
            declaracion.setString(1, profesor.getNombre());
            declaracion.setString(2, profesor.getApellidoPaterno());
            declaracion.setString(3, profesor.getApellidoMaterno());
            declaracion.setString(4, profesor.getCorreo());
            declaracion.setString(5, "Activo");
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int cambiarEstadoProfesor(int idProfesor, String nuevoEstado) {
        int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("UPDATE profesor SET estadoProfesor=? WHERE idProfesor=?;");
            declaracion.setString(1, nuevoEstado);
            declaracion.setInt(2, idProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion);
        }
        return numeroFilasAfectadas;
    }

    @Override
    public int modificarNombreProfesor(String nombreActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("UPDATE profesor SET nombre = ? WHERE correo = ?");
            declaracion.setString(1, nombreActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int modificarApellidoPaternoProfesor(String apellidoPaternoActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("UPDATE profesor SET apellidoPaterno = ? WHERE correo = ?");
            declaracion.setString(1, apellidoPaternoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int modificarApellidoMaternoProfesor(String apellidoMaternoActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("UPDATE profesor SET apellidoMaterno = ? WHERE correo = ?");
            declaracion.setString(1, apellidoMaternoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int modificarCorreoProfesor(String correoActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("UPDATE profesor SET correo = ? WHERE correo = ?");
            declaracion.setString(1, correoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }
    
    @Override
    public int obtenerIdProfesorPorCorreo(String correo){
        PreparedStatement declaracion;
        ResultSet resultado;
        int idProfesor=0;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT idProfesor from Profesor where correo=?;");
            declaracion.setString(1, correo);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idProfesor=resultado.getInt("idProfesor");
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            idProfesor = -1;
        }
        return idProfesor;                
    }
    

    public Profesor consultarProfesorPorId(int idProfesor){
        PreparedStatement declaracion;
        ResultSet resultado;
        Profesor profesor=new Profesor();
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT * from Profesor where idProfesor=?;");
            declaracion.setInt(1, idProfesor);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    profesor.setIdProfesor(resultado.getInt("idProfesor"));
                    profesor.setNombre(resultado.getString("nombre"));
                    profesor.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesor.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesor.setCorreo(resultado.getString("correo"));
                    profesor.setEstado(resultado.getString("estadoProfesor"));
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return profesor;        
    }    
    

   @Override
   public int asignarUsuarioDeProfesorPorCorreo(String correo){
       int resultadoModificacion;
       try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           CallableStatement declaracion = (CallableStatement) conexion.prepareCall("call asignarCuentaProfesor(?,?)");
           declaracion.setString(1, correo);
           declaracion.registerOutParameter(2, Types.INTEGER);
           declaracion.execute();
           resultadoModificacion = declaracion.getInt(2);
           BASE_DE_DATOS.cerrarConexion(conexion);
       }catch(SQLException | NullPointerException excepcion){
           LOG.error(excepcion.getMessage());
           resultadoModificacion = -1;
       }
       return resultadoModificacion;
   }

   @Override
   public Profesor obtenerProfesorPorIdUsuario(int idUsuario,Usuario logger){
       PreparedStatement declaracion;
        ResultSet resultado;
        Profesor profesor=new Profesor();
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            declaracion=conexion.prepareStatement("SELECT * from Profesor where Usuario_idUsuario=?;");
            declaracion.setInt(1, idUsuario);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    profesor.setIdProfesor(resultado.getInt("idProfesor"));
                    profesor.setNombre(resultado.getString("nombre"));
                    profesor.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesor.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesor.setCorreo(resultado.getString("correo")); 
                    profesor.setEstado(resultado.getString("estadoProfesor"));
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return profesor; 
   }
   
   @Override
   public int validarDuplicidadDeCorreo(String correo){
        PreparedStatement declaracion;
        ResultSet resultado;
        int coincidenciasEncontradas = 0;
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT count(*) as 'coincidencias encontradas' from Profesor where correo=?;");
            declaracion.setString(1, correo);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    coincidenciasEncontradas = resultado.getInt("coincidencias encontradas");
                }
            }
        }catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            coincidenciasEncontradas = -1;
        }
        return coincidenciasEncontradas;
   }
   
   @Override
   public int eliminarCuentaAsignadaAProfesor(String correoProfesor){
        int resultadoModificacion;
       try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement declaracion = conexion.prepareStatement(" update profesor set Usuario_idUsuario = null where correo = ?");
           declaracion.setString(1, correoProfesor);
           resultadoModificacion = declaracion.executeUpdate();
           BASE_DE_DATOS.cerrarConexion(conexion);
       }catch(SQLException | NullPointerException excepcion){
           LOG.error(excepcion.getMessage());
           resultadoModificacion = -1;
       }
       return resultadoModificacion;
   }
   
   @Override 
   public int eliminarProfesor(String correo){
       int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("DELETE FROM profesor where correo = ?");
            declaracion.setString(1, correo);
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;
   }

}
