package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.interfaces.EvidenciaInterface;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import logicaDeNegocio.clases.Actividad;
import org.apache.log4j.Logger;


public class DAOEvidenciaImplementacion implements EvidenciaInterface{
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOEvidenciaImplementacion.class);
    private Connection conexion;
    
    @Override
    public int agregarEvidencia(Evidencia evidencia){
        int resultadoInsercion;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            CallableStatement sentencia = conexion.prepareCall("call registrarEvidencia(?,?,?,?)");
            sentencia.setString(1, evidencia.getNombre());
            sentencia.setString(2, evidencia.getRutaEvidencia());
            sentencia.setInt(3, evidencia.getIdActividad());
            sentencia.registerOutParameter(4,Types.INTEGER);
            sentencia.execute();
            resultadoInsercion = sentencia.getInt(4);
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            LOG.error(excepcion.getCause());
            resultadoInsercion = -1;
        }
        return resultadoInsercion;
    }

    @Override
    public int modificarEvidencia(Evidencia evidenciaNueva) {
        int resultadoModificacion;
        try{
            conexion = BASE_DE_DATOS.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("Update evidencia set nombre = ?, ruta = ? where idEvidencia = ?");
            sentencia.setString(1, evidenciaNueva.getNombre());
            sentencia.setString(2, evidenciaNueva.getRutaEvidencia());
            sentencia.setInt(3, evidenciaNueva.getIdEvidencia());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public List<Evidencia> obtenerEvidenciasDeActividad(Actividad actividad){
        List<Evidencia> evidenciasDeActividad = new ArrayList();
        try{
            conexion = BASE_DE_DATOS.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("select * from evidencia where Actividad_idActividad = ?");
            sentencia.setInt(1, actividad.getIdActividad());
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Evidencia evidenciaObtenida = new Evidencia();
                    evidenciaObtenida.setIdEvidencia(resultado.getInt(1));
                    evidenciaObtenida.setNombre(resultado.getString(2));
                    evidenciaObtenida.setRutaEvidencia(resultado.getString(3));
                    evidenciaObtenida.setIdActividad(resultado.getInt(4));
                    evidenciasDeActividad.add(evidenciaObtenida);
                }
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            LOG.error(excepcion.getCause());
        }
        return evidenciasDeActividad;
    }
    
}
