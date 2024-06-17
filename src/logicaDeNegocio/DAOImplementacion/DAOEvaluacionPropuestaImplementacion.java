package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logicaDeNegocio.clases.EvaluacionPropuesta;
import logicaDeNegocio.interfaces.EvaluacionPropuestaInterface;
import org.apache.log4j.Logger;

public class DAOEvaluacionPropuestaImplementacion implements EvaluacionPropuestaInterface{
    
    private static final Logger LOG=Logger.getLogger(DAOEvaluacionPropuestaImplementacion.class);
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    
    /**
    *Registrar una evaluación de propuesta dentro de la base de datos
    *@param evaluacionPropuesta Objeto EvaluacionPropuesta con los datos a registrar dentro de la
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
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas=-1;
        }
        return numeroFilasAfectadas;                        
    }         
}
