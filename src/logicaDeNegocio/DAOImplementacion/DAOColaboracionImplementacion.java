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
    
    /**
    *Registrar una colaboración dentro de la base de datos
    *@param colaboracion Colaboración con los datos a registrar dentro de la base de datos
    *@return Regresa el número de filas afectadas
    **/
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

    /**
    *Obtener las colaboraciones registradas dentro de la base de datos
    *@return Regresa la lista de colaboraciones registradas en la base de datos
    **/
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
            Colaboracion colaboracion=new Colaboracion();
            colaboracion.setEstadoColaboracion("Excepcion");
            colaboraciones.add(0,colaboracion);
        }
        return colaboraciones;
    }
    
    /**
    *Obtener las colaboraciones con un estado específico que se encuentran registradas
    *en la base de datos
    *@param estado String con el estado de la colaboración
    *@return Regresa la lista de colaboraciones con un estado específico 
    * encontradas en la base de datos
    **/
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
            Colaboracion colaboracion=new Colaboracion();
            colaboracion.setEstadoColaboracion("Excepcion");
            colaboraciones.add(colaboracion);
        }
        return colaboraciones;
    }
    
    /**
    *Cambiar el estado de una colaboración registrada en la base de datos
    *@param estado String con el estado nuevo a actualizar
    *@param colaboracion Colaboración a actualizar registrada en la base de datos
    *@return Regresa el número de filas afectadas
    **/
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
    
    /**
    *Obtener el ID de una colaboración a través del ID de su propuesta
    *@param idPropuestaColaboracion Int con el ID de propuesta relacionada a una
    *colaboración registrada en la base de datos
    *@return Regresa el ID de colaboración coincidente con el ID de propuesta ingresado
    **/
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
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion);   
            idColaboracion=-1;
        }
        return idColaboracion;                
    }
    
    /**
    *Permite asignar una retroalimentación a una colaboración registrada en la base de datos
    * @param colaboracion Colaboracion con los datos a registrar en la base de datos
    * @return regresa un Int con el número de filas afectadas
    **/
    @Override
    public int realizarRetroalimentacionColaboracion(Colaboracion colaboracion) {
        int resultadoModifiacion=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("update colaboracion set retroalimentacion = ? where idColaboracion = ? and (estadoColaboracion='Cerrada' or estadoColaboracion='Finalizada')")){
            declaracion.setString(1, colaboracion.getRetroalimentacion());
            declaracion.setInt(2, colaboracion.getIdColaboracion());
            resultadoModifiacion = declaracion.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion);   
            resultadoModifiacion=-1;
        }
        return resultadoModifiacion;
    }

    /**
    *Poder subir la ruta del syllabus de una colaboración registrada en la base de datos
    * @param colaboracion Colaboracion con los datos a registrar en la base de datos
    * @return Regresa un Int con el número de filas afectadas
    **/
    @Override
    public int subirSyllabusColaboracion(Colaboracion colaboracion) {
        int resultadoModifiacion=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("update colaboracion set syllabus = ? where idColaboracion = ? and estadoColaboracion = 'Cerrada'")){
            declaracion.setString(1, colaboracion.getSyllabus());
            declaracion.setInt(2, colaboracion.getIdColaboracion());
            resultadoModifiacion = declaracion.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion);   
            resultadoModifiacion=-1;
        }
        return resultadoModifiacion;
    }
    
    /**
    *Permite obtener la ruta del archivo Syllabus asociado a una base de datos registrada en la base de datos
    * @param colaboracion Colaboracion con los datos para realizar la consulta
    * @return Regresa un String con la ruta del Syllabus de colaboracion asociada en la base de datos
    **/
    @Override
    public String obtenerSyllabusColaboracion(Colaboracion colaboracion){
        String rutaEncontrada="";
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("Select syllabus from colaboracion where idColaboracion = ?")){
            declaracion.setInt(1, colaboracion.getIdColaboracion());
            ResultSet resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    rutaEncontrada = resultado.getString("syllabus");
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion);   
            rutaEncontrada="Excepcion";
        }
        return rutaEncontrada;
    }
}
