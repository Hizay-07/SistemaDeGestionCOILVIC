package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.interfaces.UsuarioInterface;
import logicaDeNegocio.clases.Usuario;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Objects;
import org.apache.log4j.Logger;


public class DAOUsuarioImplementacion implements UsuarioInterface{
   
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private Connection conexion;
    private static final Logger LOG=Logger.getLogger(DAOUsuarioImplementacion.class);
    
    @Override
    public int registrarUsuario(Usuario usuario) {
        int resultadoInsercion;
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement sentencia = conexion.prepareCall("call registrarUsuario(?,?,?,?)");
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setString(3, usuario.getTipoDeUsuario());
            sentencia.registerOutParameter(4,Types.INTEGER);
            sentencia.execute();
            resultadoInsercion = sentencia.getInt(4);
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoInsercion = -1;
        }
        return resultadoInsercion;
    }
    
    @Override
    public int validarCredenciales(Usuario usuarioAIngresar, Usuario logger) {
        int resultadoValidacion=0;
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM usuario where nombreDeUsuario = ? AND contrasenia = sha2(?,?)");
            sentencia.setString(1, usuarioAIngresar.getNombreUsuario());
            sentencia.setString(2, usuarioAIngresar.getContrasenia());
            sentencia.setInt(3, 256);
            ResultSet resultado = sentencia.executeQuery();
            int resultadoCoincidencias=0;
            while(resultado.next()){
                resultadoCoincidencias++;
            }
            if(resultadoCoincidencias==1){
                resultadoValidacion = 1;
            }else{
                resultadoValidacion = 0;
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoValidacion = -1;
        }
        return resultadoValidacion;
    }

    @Override
    public String obtenerTipoDeUsuario(Usuario usuario,Usuario logger){
        String resultadoTipoDeUsuario="";
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement sentencia = conexion.prepareStatement("SELECT tipodeusuario.tipodeusuario from usuario,tipodeusuario where nombreDeUsuario = ? AND contrasenia = sha2(?,?) AND usuario.TipoDeUsuario_idTipoDeUsuario = tipodeusuario.idTipoDeUsuario");
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setInt(3,256);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    resultadoTipoDeUsuario = resultado.getString(1);
                }
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }
        return resultadoTipoDeUsuario;
    }
    
    @Override
    public int obtenerIdUsuario(Usuario usuario, Usuario logger){
        int resultadoId=0;    
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement sentencia = conexion.prepareStatement("SELECT idUsuario from usuario where nombreDeUsuario = ? AND contrasenia = sha2(?,?)");
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setInt(3,256);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    resultadoId = resultado.getInt("idUsuario");
                }
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoId = -1;
        }
        return resultadoId;
    }

    @Override
    public boolean confirmarConexionDeInicioDeSesion(Usuario logger) {
        boolean resultadoDeConfirmacionDeConexion=false;
        try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
           resultadoDeConfirmacionDeConexion=true;
           conexion.close();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }
        return resultadoDeConfirmacionDeConexion;
    }
    
    @Override 
    public boolean confirmarConexionDeUsuario(){
         boolean resultadoDeConfirmacionDeConexion=false;
        try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           if(Objects.isNull(conexion)){
               resultadoDeConfirmacionDeConexion=false;
           }else{
              resultadoDeConfirmacionDeConexion=true; 
           }
           conexion.close();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }
        return resultadoDeConfirmacionDeConexion;
    }
    
    @Override 
    public int eliminarUsuario(String nombreDeUsuario){
        int resultadoEliminacion;
        try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement sentencia = conexion.prepareStatement("delete from usuario where nombreDeUsuario=?");
           sentencia.setString(1, nombreDeUsuario);
           resultadoEliminacion = sentencia.executeUpdate();
           conexion.close();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoEliminacion = -1;
        }
        return resultadoEliminacion;
    }
    
    @Override
    public int verificarDuplicidadNombreDeUsuario(String nombre){
        PreparedStatement declaracion;
        ResultSet resultado;
        int coincidenciasEncontradas = 0;
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT count(*) as 'coincidencias encontradas' from usuario where nombreDeUsuario=?;");
            declaracion.setString(1, nombre);
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
}