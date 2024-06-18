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
import logicaDeNegocio.enums.EnumProfesor;
import org.apache.log4j.Logger;

public class DAOProfesorImplementacion implements ProfesorInterface {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOProfesorImplementacion.class);
    
    /**
    *Registrar un profesor dentro de la base de datos
    *@param profesor Objeto Profesor con los datos a registrar dentro de la base de datos
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarProfesor(Profesor profesor) {
        int numeroFilasAfectadas = 0; 
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO profesor(nombre, apellidoPaterno, apellidoMaterno, correo, estadoProfesor) VALUES (?, ?, ?, ?, ?)")){
            declaracion.setString(1, profesor.getNombre());
            declaracion.setString(2, profesor.getApellidoPaterno());
            declaracion.setString(3, profesor.getApellidoMaterno());
            declaracion.setString(4, profesor.getCorreo());
            declaracion.setString(5, EnumProfesor.Activo.toString());            
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    /**
    *Modificar el estado de un profesor registrado en la base de datos
    *@param idProfesor int con el ID de un profesor registrado en la base de datos 
    *@param nuevoEstado String con el nuevo estado a asignar
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int cambiarEstadoProfesor(int idProfesor, String nuevoEstado) {
        int numeroFilasAfectadas = 0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE profesor SET estadoProfesor=? WHERE idProfesor=?;")){
            declaracion.setString(1, nuevoEstado);
            declaracion.setInt(2, idProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException excepcion) {
            LOG.error(excepcion);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;
    }

    /**
    *Modificar el nombre de un profesor registrado en la base de datos
    *@param correoProfesor String con el correo asociado a un profesor registrado en
    * la base de datos
    *@param nombreActualizado String con el nuevo nombre a asignar
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int modificarNombreProfesor(String nombreActualizado, String correoProfesor) {
        int numeroFilasAfectadas = 0; 
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE profesor SET nombre = ? WHERE correo = ?")){
            declaracion.setString(1, nombreActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    /**
    *Modificar el apellido paterno de un profesor registrado en la base de datos
    *@param correoProfesor String con el correo asociado a un profesor registrado en
    * la base de datos
    *@param apellidoPaternoActualizado String con el nuevo apellido paterno a asignar
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int modificarApellidoPaternoProfesor(String apellidoPaternoActualizado, String correoProfesor) {
        int numeroFilasAfectadas = 0; 
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE profesor SET apellidoPaterno = ? WHERE correo = ?")){
            declaracion.setString(1, apellidoPaternoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }
    
    /**
    *Modificar el apellido materno de un profesor registrado en la base de datos
    *@param correoProfesor String con el correo asociado a un profesor registrado en
    * la base de datos
    *@param apellidoMaternoActualizado String con el nuevo apellido materno a asignar
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int modificarApellidoMaternoProfesor(String apellidoMaternoActualizado, String correoProfesor) {
        int numeroFilasAfectadas = 0; 
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE profesor SET apellidoMaterno = ? WHERE correo = ?")){
            declaracion.setString(1, apellidoMaternoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    /**
    *Modificar el correo de un profesor registrado en la base de datos
    *@param correoProfesor String con el correo asociado a un profesor registrado en
    * la base de datos
    *@param correoActualizado String con el nuevo apellido correo a asignar
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int modificarCorreoProfesor(String correoActualizado, String correoProfesor) {
        int numeroFilasAfectadas = 0; 
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("UPDATE profesor SET correo = ? WHERE correo = ?")){
            declaracion.setString(1, correoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }
    
    /**
    *Obtener el ID de un profesor registrado en la base de datos a través de su correo
    *@param correo String con el correo asociado a un profesor registrado en
    * la base de datos
    *@return Regresa el ID del profesor registrado en la base de datos
    * asociado al correo ingresado
    **/
    @Override
    public int obtenerIdProfesorPorCorreo(String correo){
        ResultSet resultado;
        int idProfesor=0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idProfesor from Profesor where correo=?;")){
            declaracion.setString(1, correo);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idProfesor=resultado.getInt("idProfesor");
                }
            }
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            idProfesor = -1;
        }
        return idProfesor;                
    }
    
    /**
    *Obtener un profesor registrado en la base de datos por medio de su id
    *@param idProfesor int con el ID de un profesor registrado en la base de datos 
    *@return Regresa el profesor registrado en la base de datos
    **/
    @Override
    public Profesor consultarProfesorPorId(int idProfesor){
        ResultSet resultado;
        Profesor profesor=new Profesor();
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * from Profesor where idProfesor=?;")){
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
            }else{
                profesor.setNombre("Sin coincidencias");
            }
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            profesor.setNombre("Excepcion");
        }
        return profesor;        
    }    
    
    
   /**
    *Asignar un usuario a un profesor registrado en la base de datos
    *@param correo String con el correo asociado a un profesor registrado en
    * la base de datos
    *@return Regresa el numero de filas afectadas
    **/
   @Override
   public int asignarUsuarioDeProfesorPorCorreo(String correo){
       int resultadoModificacion;
       try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           CallableStatement declaracion = (CallableStatement) conexion.prepareCall("call asignarCuentaProfesor(?,?)")){
           declaracion.setString(1, correo);
           declaracion.registerOutParameter(2, Types.INTEGER);
           declaracion.execute();
           resultadoModificacion = declaracion.getInt(2);
       }catch(SQLException excepcion){
           LOG.error(excepcion.getMessage());
           resultadoModificacion = -1;
       }
       return resultadoModificacion;
   }

    /**
    *Obtener un profesor registrado en la base de datos por medio de su
    * ID usuario asociado
    *@param idUsuario Int con el ID de usuario asociado a un profesor registrado en
    * la base de datos
    *@param logger Usuario que se encarga de validar credenciales
    *@return Regresa el profesor registrado en la base de datos
    **/
   @Override
   public Profesor obtenerProfesorPorIdUsuario(int idUsuario,Usuario logger){
        ResultSet resultado;
        Profesor profesor=new Profesor();
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * from Profesor where Usuario_idUsuario=?;")){
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
            }else{
                profesor.setNombre("Sin coincidencias");
            }
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            profesor.setNombre("Excepcion");
        }
        return profesor; 
   }
   
   /**
    *Validar la duplicidad de un correo asociado a un profesor registrado en la
    * base de datos
    *@param correo String con el correo asociado a un profesor registrado en
    * la base de datos
    *@return Regresa un booleano con verdadero si se encuentra coincidencia o falso si no se 
    * encuentra coincidencia
    **/
   @Override
   public int validarDuplicidadDeCorreo(String correo){
        ResultSet resultado;
        int coincidenciasEncontradas = 0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT count(*) as 'coincidencias encontradas' from Profesor where correo=?;")){
            declaracion.setString(1, correo);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    coincidenciasEncontradas = resultado.getInt("coincidencias encontradas");
                }
            }
        }catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
            coincidenciasEncontradas = -1;
        }
        return coincidenciasEncontradas;
   }
   
   /**
    *Obtener el numero de profesores asociados a una colaboración en la base de datos
    *@param idProfesor int con el ID de un profesor registrado en la base de datos
    *@return Regresa numero de profesores asociados a una colaboración
    **/
   @Override
    public int consultarPrecondicionInicioColaboracionPorIdProfesor(int idProfesor){
        int resultadoConsulta=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("CALL precondicionIniciarColaboracion(?,?);")){
            declaracion.setInt(1, idProfesor);
            declaracion.registerOutParameter(1, Types.INTEGER);
            declaracion.execute();
            resultadoConsulta=declaracion.getInt(1);    
        }catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());    
            resultadoConsulta = -1;
        }
        return resultadoConsulta;       
   }

}
