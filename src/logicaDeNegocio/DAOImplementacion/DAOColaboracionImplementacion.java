package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.interfaces.ColaboracionInterface;
import java.sql.ResultSet;
import logicaDeNegocio.clases.PropuestaColaboracion;

public class DAOColaboracionImplementacion implements ColaboracionInterface{
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(DAOColaboracionImplementacion.class);

    @Override
    public int registrarColaboracion(Colaboracion colaboracion) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("call registrarColaboracion(?,?,?,?)")){
            declaracion.setString(1, colaboracion.getEstadoColaboracion());
            declaracion.setInt(2, colaboracion.getPropuestaColaboracion().getIdPropuestaColaboracion());
            declaracion.setInt(3, colaboracion.getCantidadEstudiantes());
            declaracion.registerOutParameter(4, Types.INTEGER);
            declaracion.execute();
            numeroFilasAfectadas = declaracion.getInt(4);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public List<Colaboracion> consultarColaboraciones() {
        ResultSet resultado;
        List<Colaboracion> colaboraciones=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM Colaboracion")){
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Colaboracion colaboracion=new Colaboracion();
                    colaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                    colaboracion.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                    colaboracion.setCantidadEstudiantes(resultado.getInt("cantidadEstudiantes"));
                    int idPropuestaDeColaboracion = resultado.getInt("idPropuestaColaboracion");
                    DAOPropuestaColaboracionImplementacion daoPropuesta = new DAOPropuestaColaboracionImplementacion();
                    PropuestaColaboracion propuestaDeColaboracion = daoPropuesta.obtenerPropuestaDeColaboracionPorId(idPropuestaDeColaboracion);
                    colaboracion.setPropuestaColaboracion(propuestaDeColaboracion);
                    colaboraciones.add(colaboracion);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return colaboraciones;
    }
    
    @Override
    public List<Colaboracion> consultarColaboracionesPorEstado(String estado) {
        ResultSet resultado;
        List<Colaboracion> colaboraciones=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM Colaboracion where estadoColaboracion = ?")){
            declaracion.setString(1,estado);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Colaboracion colaboracion=new Colaboracion();
                    colaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                    colaboracion.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                    colaboracion.setCantidadEstudiantes(resultado.getInt("cantidadEstudiantes"));
                    int idPropuestaDeColaboracion = resultado.getInt("idPropuestaColaboracion");
                    DAOPropuestaColaboracionImplementacion daoPropuesta = new DAOPropuestaColaboracionImplementacion();
                    PropuestaColaboracion propuestaDeColaboracion = daoPropuesta.obtenerPropuestaDeColaboracionPorId(idPropuestaDeColaboracion);
                    colaboracion.setPropuestaColaboracion(propuestaDeColaboracion);
                    colaboraciones.add(colaboracion);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return colaboraciones;
    }

    @Override
    public int registrarRetroalimentacionColaboracionPorId(int idColaboracion,String retroalimentacion) {
        int numeroFilasAfectadas=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE Colaboracion set retroalimentacion=? where idColaboracion=?;")){
            declaracion.setString(1, retroalimentacion);
            declaracion.setInt(2, idColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        }catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;        
    }
    
    @Override
    public int cambiarEstadoColaboracion(String estado,Colaboracion colaboracion){
        int numeroFilasAfectadas = 0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("update colaboracion set estadoColaboracion = ? where idColaboracion = ?")){
            declaracion.setString(1, estado);
            declaracion.setInt(2,colaboracion.getIdColaboracion());
            numeroFilasAfectadas = declaracion.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;
    }
    
    @Override
    public int obtenerIdColaboracionPorIdPropuesta(int idPropuestaColaboracion){
        ResultSet resultado;
        int idColaboracion=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("select idColaboracion from colaboracion where idPropuestaColaboracion=?")){
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                idColaboracion=resultado.getInt("idColaboracion");                                                
            }
        }catch(SQLException excepcion){
            LOG.error(excepcion);        
        }
        return idColaboracion;                
    }
}
