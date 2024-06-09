package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.EvaluacionPropuesta;
import logicaDeNegocio.interfaces.EvaluacionPropuestaInterface;
import org.apache.log4j.Logger;

public class DAOEvaluacionPropuestaImplementacion implements EvaluacionPropuestaInterface{
    private static final Logger LOG=Logger.getLogger(DAOEvaluacionPropuestaImplementacion.class);
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    
    /**
    *Registrar una evaluación de propuesta dentro de la base de datos
    *@param evaluacionPropuesta EvaluacionPropuesta con los datos a registrar dentro de la
    *base de datos
    *@return Regresa el número de filas afectadas
    **/
    @Override
    public int registrarEvaluacionPropuesta(EvaluacionPropuesta evaluacionPropuesta) {
        int numeroFilasAfectadas=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("Insert into EvaluacionPropuesta(idPropuestaColaboracion,idUsuario,evaluacion,fechaEvaluacion,justificacion) values (?,?,?,?,?);")){
            declaracion.setInt(1, evaluacionPropuesta.getIdPropuestaColaboracion());
            declaracion.setInt(2,evaluacionPropuesta.getIdUsuario());
            declaracion.setString(3, evaluacionPropuesta.getEvaluacion());
            declaracion.setString(4, evaluacionPropuesta.getFechaEvaluacion());
            declaracion.setString(5, evaluacionPropuesta.getJustificacion());
            numeroFilasAfectadas=declaracion.executeUpdate();
        }catch(SQLException excepcion){
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;                        
    }
    
    /**
    *Obtener las evaluaciones de propuestas registradas en la base de datos
    *@return Regresa la lista de evaluaciones de propuesta encontradas en la base 
    * de datos
    **/
    @Override
    public List<EvaluacionPropuesta> consultarEvaluacionesDePropuesta() {
        ResultSet resultado;
        List<EvaluacionPropuesta> evaluacionesPropuestas=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * from EvaluacionPropuesta")){
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                EvaluacionPropuesta evaluacionPropuesta=new EvaluacionPropuesta();
                evaluacionPropuesta.setIdEvaluacionPropuesta(resultado.getInt("idEvaluacionPropuesta"));
                evaluacionPropuesta.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                evaluacionPropuesta.setIdUsuario(resultado.getInt("idUsuario"));
                evaluacionPropuesta.setEvaluacion(resultado.getString("evaluacion"));
                evaluacionPropuesta.setFechaEvaluacion(resultado.getString("fechaEvaluacion"));
                evaluacionPropuesta.setJustificacion(resultado.getString("justificacion"));
                evaluacionesPropuestas.add(evaluacionPropuesta);
            }
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return evaluacionesPropuestas;
    }
    

    
}
