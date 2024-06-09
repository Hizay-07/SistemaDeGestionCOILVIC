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
import logicaDeNegocio.clases.Profesor;
import org.apache.log4j.Logger;


public class DAOUsuarioImplementacion implements UsuarioInterface{
   
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOUsuarioImplementacion.class);
    
    /**
    *Registrar un usuario dentro de la base de datos
    *@param usuario Usuario con los datos a registrar dentro de la base de datos
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarUsuario(Usuario usuario) {
        int resultadoInsercion;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement sentencia = conexion.prepareCall("call registrarUsuario(?,?,?,?)")){
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setString(3, usuario.getTipoDeUsuario());
            sentencia.registerOutParameter(4,Types.INTEGER);
            sentencia.execute();
            resultadoInsercion = sentencia.getInt(4);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoInsercion = -1;
        }
        return resultadoInsercion;
    }
    
    /**
    *Validar la existencia de un usuario dentro de la base de datos
    *@param logger Usuario que se encarga de validar credenciales
    *@param usuarioAIngresar Usuario con los datos a validar
    *@return Regresa el numero de coincidencias encontradas
    **/
    @Override
    public int validarCredenciales(Usuario usuarioAIngresar, Usuario logger) {
        int resultadoValidacion=0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM usuario where nombreDeUsuario = ? AND contrasenia = sha2(?,?)")){
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
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoValidacion = -1;
        }
        return resultadoValidacion;
    }

    /**
    *Obtener el tipo de usuario a ingresar al sistema
    *@param logger Usuario que se encarga de validar credenciales
    *@param usuario Usuario con los datos a obtener
    *@return Regresa el tipo de usuario encontrado
    **/
    @Override
    public String obtenerTipoDeUsuario(Usuario usuario,Usuario logger){
        String resultadoTipoDeUsuario="";
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement sentencia = conexion.prepareStatement("SELECT tipodeusuario.tipodeusuario from usuario,tipodeusuario where nombreDeUsuario = ? AND contrasenia = sha2(?,?) AND usuario.TipoDeUsuario_idTipoDeUsuario = tipodeusuario.idTipoDeUsuario")){
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setInt(3,256);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    resultadoTipoDeUsuario = resultado.getString(1);
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
        }
        return resultadoTipoDeUsuario;
    }
    
    /**
    *Obtener el ID de un usuario a ingresar al sistema
    *@param logger Usuario que se encarga de validar credenciales
    *@param usuario Usuario con los datos a obtener
    *@return Regresa el ID de usuario encontrado
    **/
    @Override
    public int obtenerIdUsuario(Usuario usuario, Usuario logger){
        int resultadoId=0;    
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement sentencia = conexion.prepareStatement("SELECT idUsuario from usuario where nombreDeUsuario = ? AND contrasenia = sha2(?,?)")){
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setInt(3,256);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    resultadoId = resultado.getInt("idUsuario");
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoId = -1;
        }
        return resultadoId;
    }

    /**
    *Confirmar la conexión a la base de datos
    *@param logger Usuario que se encarga de validar credenciales
    *@return Regresa un booleano con verdadero si hay conexión o falso si no hay conexión
    **/
    @Override
    public boolean confirmarConexionDeInicioDeSesion(Usuario logger) {
        boolean resultadoDeConfirmacionDeConexion=false;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger)){
           resultadoDeConfirmacionDeConexion=true;
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoDeConfirmacionDeConexion = false;
        }
        return resultadoDeConfirmacionDeConexion;
    }
    
    /**
    *Confirmar la conexión de un usuario a la base de datos
    *@return Regresa un booleano con verdadero si hay conexión o falso si no hay conexión
    **/
    @Override 
    public boolean confirmarConexionDeUsuario(){
         boolean resultadoDeConfirmacionDeConexion=false;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos()){
           if(Objects.isNull(conexion)){
               resultadoDeConfirmacionDeConexion=false;
           }else{
              resultadoDeConfirmacionDeConexion=true; 
           }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
        }
        return resultadoDeConfirmacionDeConexion;

    }      
    

 
    
    /**
    *Verificar la existencia de un nombre de usuario registrado en la base de datos
    *@param nombre Strinf con el nombre de usuario 
    *@return Regresa el número de coincidencias encontradas
    **/

    @Override
    public int verificarDuplicidadNombreDeUsuario(String nombre){
        ResultSet resultado;
        int coincidenciasEncontradas = 0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT count(*) as 'coincidencias encontradas' from usuario where nombreDeUsuario=?;")){
            declaracion.setString(1, nombre);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    coincidenciasEncontradas = resultado.getInt("coincidencias encontradas");
                }
            }
        }catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            coincidenciasEncontradas = -1;
        }
        return coincidenciasEncontradas;
    }
    


    /**
    *Actualizar las credenciales de un usuario registrado en la base de datos
    *@param profesor Profesor con los datos para modificar su usuario
    *@param contrasenia String con la nueva contrasenia del usuario
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int actualizarUsuarioPorIdUsuario(Profesor profesor, String contrasenia){
        int resultadoModificacion = 0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("update usuario set nombreDeUsuario = ?, contrasenia = sha2(?,256) where idUsuario = ?")){
            declaracion.setString(1, profesor.getCorreo());
            declaracion.setString(2, contrasenia);
            declaracion.setInt(3, profesor.getUsuario().getIdUsuario());
            resultadoModificacion = declaracion.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
}