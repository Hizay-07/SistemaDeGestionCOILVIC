package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.interfaces.ActividadInterface;
import logicaDeNegocio.clases.Actividad;
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
    private static final Logger LOG=Logger.getLogger(DAOActividadImplementacion.class);
    
    /**
    *Registrar una nueva actividad dentro de la base de datos
    *@param actividadNueva Actividad nueva a registrar dentro de la base datos
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarActividad(Actividad actividadNueva){
        int resultadoRegistro=0;        
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO actividad (nombre,descripcion,fechaDeInicio,fechaDeCierre,idColaboracion,numeroDeActividad,estadoActividad) VALUES (?,?,?,?,?,?,?)")){
            sentencia.setString(1,actividadNueva.getNombre());
            sentencia.setString(2,actividadNueva.getDescripcion());
            sentencia.setString(3,actividadNueva.getFechaDeInicio());
            sentencia.setString(4, actividadNueva.getFechaDeCierre());
            sentencia.setInt(5, actividadNueva.getIdColaboracion());
            sentencia.setInt(6,actividadNueva.getNumeroActividad());
            sentencia.setString(7, actividadNueva.getEstado());
            resultadoRegistro = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoRegistro = -1;
        }
        return resultadoRegistro;
    }

    /**
    *Modificar los datos de una actividad registrada en la base de datos 
    *@param actividadActualizada Actividad que contiene datos nuevos a modificar
    *@return Regresa el numero de filas afectadas
    * 
    **/
    @Override
    public int modificarActividad(Actividad actividadActualizada) {
        int resultadoModificacion=0;   
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE actividad SET nombre = ?, descripcion = ?, estadoActividad = ? WHERE idActividad = ? ")){
            sentencia.setString(1, actividadActualizada.getNombre());
            sentencia.setString(2, actividadActualizada.getDescripcion());
            sentencia.setString(3, actividadActualizada.getEstado());
            sentencia.setInt(4, actividadActualizada.getIdActividad());
            resultadoModificacion = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }     
        return resultadoModificacion;
    }
    
    /**
    *Modificar las fechas de una actividad registrada en la base de datos 
    *@param actividadActualizada Actividad que contiene las fechas nuevas a modificar
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int modificarFechaActividad(Actividad actividadActualizada) {
        int resultadoModificacion=0;      
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE actividad SET fechaDeInicio = ?, FechaDeCierre = ? WHERE idActividad = ? ")){
            sentencia.setString(1, actividadActualizada.getFechaDeInicio());
            sentencia.setString(2, actividadActualizada.getFechaDeCierre());
            sentencia.setInt(3, actividadActualizada.getIdActividad());
            resultadoModificacion = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }      
        return resultadoModificacion;
    }
    
    
    /**
    *Obtener la lista de actividades de una coolaboración a través de su ID 
    *@param idColaboracion id de la colaboración de la cual se desea obtener actividades
    *@return Regresa la lista de actividades recuperadas de la base de datos
    **/
    @Override
    public List<Actividad> obtenerActividades(int idColaboracion) {
        List<Actividad> actividades = new ArrayList();       
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM actividad WHERE idColaboracion = ?")){
            sentencia.setInt(1, idColaboracion);
            ResultSet actividadesObtenidas = sentencia.executeQuery();
            if(actividadesObtenidas.isBeforeFirst()){
                while(actividadesObtenidas.next()){
                    Actividad actividadConsultada = new Actividad();
                    actividadConsultada.setIdActividad(actividadesObtenidas.getInt("idActividad"));
                    actividadConsultada.setNombre(actividadesObtenidas.getString("nombre"));
                    actividadConsultada.setNumeroActividad(actividadesObtenidas.getInt("numeroDeActividad"));
                    actividadConsultada.setDescripcion(actividadesObtenidas.getString("descripcion"));
                    actividadConsultada.setFechaDeInicio(actividadesObtenidas.getString("fechaDeInicio"));
                    actividadConsultada.setFechaDeCierre(actividadesObtenidas.getString("fechaDeCierre"));
                    actividadConsultada.setIdColaboracion(actividadesObtenidas.getInt("idColaboracion"));
                    actividadConsultada.setEstado(actividadesObtenidas.getString("estadoActividad"));
                    actividades.add(actividadConsultada);
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            Actividad excepcionEnConsulta = new Actividad();
            excepcionEnConsulta.setNombre("Excepcion");
            actividades.add(0,excepcionEnConsulta);
        }      
        return actividades;
    }
    
    /**
    *Obtener el numero de una actividad deseada
    *@param actividad Actividad de la cual se requiere conocer su número
    *@return Regresa el numero de la actividad deseada
    **/
    @Override
    public int obtenerNumeroDeActividad(Actividad actividad){
        int numeroDeActividad=0;       
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT numeroDeActividad FROM actividad WHERE nombre = ? AND descripcion = ?")){
            sentencia.setString(1, actividad.getNombre());
            sentencia.setString(2, actividad.getDescripcion());
            ResultSet numeroObtenido = sentencia.executeQuery();
            if(numeroObtenido.isBeforeFirst()){
                while(numeroObtenido.next()){
                    numeroDeActividad =numeroObtenido.getInt("numeroDeActividad");
                }
            }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            numeroDeActividad = -1;
        }      
        return numeroDeActividad;
    }

    /**
    *Validar si una actividad ya se encuentra registrada dentro de la base de datos
    *@param actividad Actividad de la cual se desea saber si se encuentra registrada en la base de datos
    *@return Regresa un booleano que indica verdadero si la actividad existe o falso si no existe
    **/
    @Override
    public boolean validarInexistenciaDeActividad(Actividad actividad) {
        boolean resultadoValidacion=true;       
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT COUNT(*) FROM actividad WHERE idColaboracion = ? AND (numeroDeActividad = ? OR nombre = ?)")){
            sentencia.setInt(1, actividad.getIdColaboracion());
            sentencia.setInt(2, actividad.getNumeroActividad());
            sentencia.setString(3, actividad.getNombre());
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
            LOG.error(excepcion.getMessage());
        }
        return resultadoValidacion;
    }

    /**
    *Actualizar el estado de una actividad 
    *@param actividad Actividad de la cual se desea cambiar su estado
    *@param estado Estado de la actividad que se desea actualizar
    *@return Regresa el número de filas afectadas
    **/
    @Override
    public int actualizarEstadoActividad(Actividad actividad, String estado) {
        int resultadoActualizacion=0;      
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE actividad SET estadoActividad = ? where numeroDeActividad = ? AND idActividad = ?")){
            sentencia.setString(1, estado);
            sentencia.setInt(2, actividad.getNumeroActividad());
            sentencia.setInt(3, actividad.getIdActividad());
            resultadoActualizacion = sentencia.executeUpdate();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoActualizacion = -1;
        }                
        return resultadoActualizacion;
    }
        
}
