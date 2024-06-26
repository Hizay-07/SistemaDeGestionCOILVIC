package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.interfaces.ColaboracionProfesorInterface;
import accesoADatos.ManejadorBaseDeDatos;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import logicaDeNegocio.clases.PropuestaColaboracion;
import org.apache.log4j.Logger;

public class DAOColaboracionProfesorImplementacion implements ColaboracionProfesorInterface {
    private static final Logger LOG=Logger.getLogger(DAOColaboracionProfesorImplementacion.class);
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    
    /**
    *Obtener los profesores pertenecientes a una colaboración a través del ID de la colaboración
    *@param colaboracion Objeto Colaboración de la cual se desea obtener los profesores asociados 
    *@return Regresa la lista de profesores pertenecientes a la colaboración ingresada
    **/
    @Override
    public List<Profesor> obtenerProfesoresPorIdColaboracion(Colaboracion colaboracion) {
       ResultSet resultado;
       List<Profesor> profesoresObtenidos = new ArrayList();
       try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement sentencia = conexion.prepareStatement("SELECT profesor.* from profesor,colaboracionprofesor where profesor.idProfesor = colaboracionprofesor.idProfesor and idColaboracion = ?")){
           sentencia.setInt(1, colaboracion.getIdColaboracion());
           resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
               while(resultado.next()){
               Profesor profesorObtenido = new Profesor();
               profesorObtenido.setNombre(resultado.getString("nombre"));
               profesorObtenido.setApellidoMaterno(resultado.getString("apellidoMaterno"));
               profesorObtenido.setApellidoPaterno(resultado.getString("apellidoPaterno"));
               profesorObtenido.setCorreo(resultado.getString("correo"));
               profesorObtenido.setEstado(resultado.getString("estadoProfesor"));
               profesorObtenido.setIdProfesor(resultado.getInt("idProfesor"));
               profesoresObtenidos.add(profesorObtenido);
               }
            }
       }catch(SQLException excepcion){
           LOG.error(excepcion.getMessage());
           Profesor profesorObtenido = new Profesor();
           profesorObtenido.setNombre("Excepcion");
           profesoresObtenidos.add(0,profesorObtenido);
       }
       return profesoresObtenidos;
    }

    /**
    *Obtener el ID de colaboración asociada a un profesor
    *@param profesor Objeto Profesor del cual se desea obtener el ID de colaboración a la cual pertenece
    *@param estado String el cual indica el estado de la colaboracion de la cual se interesa obtener detalles
    *@return Regresa el ID de colaboración asociado al profesor ingresado
    **/
    @Override
    public Colaboracion obtenerColaboracionPorIdProfesor(Profesor profesor,String estado){
       ResultSet resultado;
       Colaboracion colaboracionObtenida = new Colaboracion();
       try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement sentencia = conexion.prepareStatement("SELECT colaboracion.* from colaboracion,colaboracionprofesor where ? = colaboracionprofesor.idProfesor and colaboracionProfesor.idColaboracion = colaboracion.idColaboracion and estadoColaboracion = ?")){
           sentencia.setInt(1, profesor.getIdProfesor());
           sentencia.setString(2,estado);
           resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    colaboracionObtenida.setIdColaboracion(resultado.getInt("idColaboracion"));
                    colaboracionObtenida.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                    colaboracionObtenida.setCantidadEstudiantes(resultado.getInt("cantidadEstudiantes"));
                    int idPropuestaDeColaboracion = resultado.getInt("idPropuestaColaboracion");
                    DAOPropuestaColaboracionImplementacion daoPropuesta = new DAOPropuestaColaboracionImplementacion();
                    PropuestaColaboracion propuestaDeColaboracion = daoPropuesta.obtenerPropuestaDeColaboracionPorId(idPropuestaDeColaboracion);
                    colaboracionObtenida.setPropuestaColaboracion(propuestaDeColaboracion);
                }
            }
       }catch(SQLException excepcion){
           LOG.error(excepcion.getMessage());
           colaboracionObtenida.setEstadoColaboracion("Excepcion");
       }
       return colaboracionObtenida;
    }
    
    /**
    *Registrar un profesor a una colaboración dentro de la base de datos
    *@param profesor Objeto Profesor que se desea asignar a una colaboración
    *@param colaboracion Objeto Colaboración que se desea asociar a un profesor
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarColaboracionProfesor(Profesor profesor, Colaboracion colaboracion) {
        int resultadoInsercion;   
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement sentencia = (CallableStatement) conexion.prepareCall("call registrarColaboracionProfesor(?,?,?)")){
            sentencia.setInt(1, profesor.getIdProfesor());
            sentencia.setInt(2, colaboracion.getIdColaboracion());
            sentencia.registerOutParameter(3, Types.INTEGER);
            sentencia.execute();
            resultadoInsercion = sentencia.getInt(3);
        }catch(SQLException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoInsercion = -1;
        }
        return resultadoInsercion;
    }
    
}
