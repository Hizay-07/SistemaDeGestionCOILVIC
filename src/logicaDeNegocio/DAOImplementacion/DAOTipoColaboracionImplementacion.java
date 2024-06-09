package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.interfaces.TipoColaboracionInterface;
import org.apache.log4j.Logger;

public class DAOTipoColaboracionImplementacion implements TipoColaboracionInterface{
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOTipoColaboracionImplementacion.class);
    
    /**
    *Obtener los tipos de colaboración registrados en la base de datos
    *@return Regresa la lista de tipos de colaboración
    **/
    @Override
    public List<TipoColaboracion> consultarTiposDeColaboracion() {
        ResultSet resultado;
        List<TipoColaboracion> tiposColaboracion=new ArrayList<>();
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * from TipoColaboracion")){
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    TipoColaboracion tipoColaboracion=new TipoColaboracion();
                    tipoColaboracion.setTipo(resultado.getString("tipo"));
                    tipoColaboracion.setIdTipoColaboracion(resultado.getInt("idTipoColaboracion"));
                    tiposColaboracion.add(tipoColaboracion);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return tiposColaboracion;
    }     
    
    /**
    *Obtener el tipo de colaboración asignado a un tipo de colaboración registrada en
    * la base de datos
    * @param idTipoColaboracion int con el ID del tipo de colaboración 
    *@return Regresa el tipo de colaboración 
    **/
    @Override
    public String consultarTipoColaboracionPorId(int idTipoColaboracion){
        ResultSet resultado;
        String tipo=new String();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT tipo from TipoColaboracion where idTipoColaboracion=?;")){
            declaracion.setInt(1, idTipoColaboracion);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    tipo=resultado.getString("tipo");                
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return tipo;        
    }
    
    /**
    *Obtener el ID de tipo de colaboración asignado a un tipo de colaboración registrada en
    * la base de datos
    * @param tipo String con el tipo de colaboración 
    *@return Regresa el ID del tipo de colaboración 
    **/
    @Override
    public int consultarIdTipoColaboracionPorTipo(String tipo){
        ResultSet resultado;
        int idTipoColaboracion=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idTipoColaboracion from TipoColaboracion where tipo=?;")){
            declaracion.setString(1, tipo);
            resultado=declaracion.executeQuery();
            if(resultado.next()){
                idTipoColaboracion=resultado.getInt("idTipoColaboracion");                               
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            idTipoColaboracion = -1;
        }
        return idTipoColaboracion;                
    }
    
    /**
    *Obtener el numero de tipos de colaboración registrados en la base de datos
    *@return Regresa el numero de coincidencias encontradas 
    **/
    @Override
    public int verificarTipoColaboracion(){        
        int resultadoVerificacion=0;
        ResultSet resultado;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select count(*) from tipoColaboracion")){
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                resultadoVerificacion=resultado.getInt(1);                
            }            
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion);
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;           
    }
}
