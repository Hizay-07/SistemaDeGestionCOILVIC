package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.ColaboracionProfesor;
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
import org.apache.log4j.Logger;

public class DAOColaboracionProfesorImplementacion implements ColaboracionProfesorInterface {
    private static final Logger LOG=Logger.getLogger(DAOEmisionPropuestaImplementacion.class);
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private Connection conexion;

    @Override
    public List<Profesor> obtenerProfesoresPorIdColaboracion(Colaboracion colaboracion) {
       PreparedStatement sentencia;
       ResultSet resultado;
       List<Profesor> profesoresObtenidos = new ArrayList();
       try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           sentencia = conexion.prepareStatement("SELECT profesor.* from profesor,colaboracionprofesor where profesor.idProfesor = colaboracionprofesor.idProfesor and idColaboracion = ?");
           sentencia.setInt(1, colaboracion.getIdColaboracion());
           resultado = sentencia.executeQuery();
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
           BASE_DE_DATOS.cerrarConexion(conexion);
       }catch(SQLException excepcion){
           LOG.error(excepcion.getCause());
       }
       
       return profesoresObtenidos;
    }

    @Override
    public Colaboracion obtenerColaboracionPorIdProfesor(Profesor profesor) {
       PreparedStatement sentencia;
       ResultSet resultado;
       Colaboracion colaboracionObtenida = new Colaboracion();
       try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           sentencia = conexion.prepareStatement("SELECT colaboracion.* from colaboracion,colaboracionprofesor where ? = colaboracionprofesor.idProfesor and colaboracionProfesor.idColaboracion = colaboracion.idColaboracion");
           sentencia.setInt(1, profesor.getIdProfesor());
           resultado = sentencia.executeQuery();
           while(resultado.next()){
               colaboracionObtenida.setIdColaboracion(resultado.getInt("idColaboracion"));
               colaboracionObtenida.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
               colaboracionObtenida.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
           }
           BASE_DE_DATOS.cerrarConexion(conexion);
       }catch(SQLException excepcion){
           LOG.error(excepcion.getCause());
       }
       return colaboracionObtenida;
    }

    @Override
    public int registrarColaboracionProfesor(Profesor profesor, Colaboracion colaboracion) {
        int resultadoInsercion;   
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement sentencia = (CallableStatement) conexion.prepareCall("call registrarColaboracionProfesor(?,?,?)");
            sentencia.setInt(1, profesor.getIdProfesor());
            sentencia.setInt(2, colaboracion.getIdColaboracion());
            sentencia.registerOutParameter(3, Types.INTEGER);
            sentencia.execute();
            resultadoInsercion = sentencia.getInt(3);
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            LOG.error(excepcion.getCause());
            resultadoInsercion = -1;
        }
        return resultadoInsercion;
    }
    
}