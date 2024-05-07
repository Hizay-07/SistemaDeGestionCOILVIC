package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.interfaces.ActividadInterface;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.enums.EnumActividades;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class DAOActividadImplementacion implements ActividadInterface {
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private Connection conexion;
    private static final Logger LOG=Logger.getLogger(DAOActividadImplementacion.class);
    
    @Override
    public int registrarActividad(Actividad actividadNueva){
        int resultadoRegistro;        
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO actividad (nombre,descripcion,fechaDeInicio,fechaDeCierre,idColaboracion,numeroActividad,estadoActividad) VALUES (?,?,?,?,?,?,?)");
            sentencia.setString(1,actividadNueva.getNombre());
            sentencia.setString(2,actividadNueva.getDescripcion());
            sentencia.setString(3,actividadNueva.getFechaDeInicio());
            sentencia.setString(4, actividadNueva.getFechaDeCierre());
            sentencia.setInt(5, actividadNueva.getIdColaboracion());
            sentencia.setInt(6,actividadNueva.getNumeroActividad());
            sentencia.setString(7, actividadNueva.getEstado());
            resultadoRegistro = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoRegistro = -1;
        }
        return resultadoRegistro;
    }

    @Override
    public int modificarActividad(Actividad actividadActualizada) {
        int resultadoModificacion;   
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE actividad SET nombre = ?, descripcion = ?, estadoActividad = ? WHERE idActividad = ? ");
            sentencia.setString(1, actividadActualizada.getNombre());
            sentencia.setString(2, actividadActualizada.getDescripcion());
            sentencia.setString(3, actividadActualizada.getEstado());
            sentencia.setInt(4, actividadActualizada.getIdActividad());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }catch(NullPointerException excepcion){
            LOG.fatal(excepcion.getCause());
            resultadoModificacion = -1;
        }        
        return resultadoModificacion;
    }

    @Override
    public int modificarFechaActividad(Actividad actividadActualizada) {
        int resultadoModificacion;      
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE actividad SET fechaDeInicio = ?, FechaDeCierre = ? WHERE idActividad = ? ");
            sentencia.setString(1, actividadActualizada.getFechaDeInicio());
            sentencia.setString(2, actividadActualizada.getFechaDeCierre());
            sentencia.setInt(3, actividadActualizada.getIdActividad());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }      
        return resultadoModificacion;
    }

    @Override
    public List<Actividad> obtenerActividades(int idColaboracion) {
        List<Actividad> actividades = new ArrayList();       
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM actividad WHERE idColaboracion = ?");
            sentencia.setInt(1, idColaboracion);
            ResultSet actividadesObtenidas = sentencia.executeQuery();
            if(actividadesObtenidas.isBeforeFirst()){
                while(actividadesObtenidas.next()){
                    Actividad actividadConsultada = new Actividad();
                    actividadConsultada.setIdActividad(actividadesObtenidas.getInt(1));
                    actividadConsultada.setNombre(actividadesObtenidas.getString(2));
                    actividadConsultada.setDescripcion(actividadesObtenidas.getString(3));
                    actividadConsultada.setFechaDeInicio(actividadesObtenidas.getString(4));
                    actividadConsultada.setFechaDeCierre(actividadesObtenidas.getString(5));
                    actividadConsultada.setIdColaboracion(actividadesObtenidas.getInt(7));
                    actividadConsultada.setNumeroActividad(actividadesObtenidas.getInt(8));
                    actividadConsultada.setEstado(actividadesObtenidas.getString(6));
                    actividades.add(actividadConsultada);
                }
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }      
        return actividades;
    }
    
    @Override
    public int obtenerNumeroDeActividad(Actividad actividad){
        int numeroDeActividad=0;       
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT numeroActividad FROM actividad WHERE nombre = ? AND descripcion = ?");
            sentencia.setString(1, actividad.getNombre());
            sentencia.setString(2, actividad.getDescripcion());
            ResultSet numeroObtenido = sentencia.executeQuery();
            if(numeroObtenido.isBeforeFirst()){
                while(numeroObtenido.next()){
                    numeroDeActividad = (int)numeroObtenido.getObject(1);
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            numeroDeActividad = -1;
        }      
        return numeroDeActividad;
    }

    @Override
    public boolean validarInexistenciaDeActividad(Actividad actividad) {
        boolean resultadoValidacion;       
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT COUNT(*) FROM actividad WHERE numeroActividad = ? or nombre = ?");
            sentencia.setInt(1, actividad.getNumeroActividad());
            sentencia.setString(2, actividad.getNombre());
            ResultSet resultadoConsulta = sentencia.executeQuery();          
            int numeroDeResultados=0;
            while(resultadoConsulta.next()){
               numeroDeResultados = (int)resultadoConsulta.getLong(1);
            }            
            if(numeroDeResultados>=1){
                resultadoValidacion=true;
            }else{
                resultadoValidacion=false;
            }    
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoValidacion = false;
        }
        return resultadoValidacion;
    }

    @Override
    public int actualizarEstadoActividad(Actividad actividad, String estado) {
        int resultadoActualizacion;      
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE actividad SET estadoActividad = ? where numeroActividad = ? AND idActividad = ?");
            sentencia.setString(1, estado);
            sentencia.setInt(2, actividad.getNumeroActividad());
            sentencia.setInt(3, actividad.getIdActividad());
            resultadoActualizacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoActualizacion = -1;
        }                
        return resultadoActualizacion;
    }
        
}
