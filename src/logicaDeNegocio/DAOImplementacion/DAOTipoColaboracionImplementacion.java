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

    @Override
    public int registrarTipoColaboracion(TipoColaboracion tipoColaboracion) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("INSERT INTO TipoColaboracion(tipo) VALUES (?)")){
            declaracion.setString(1, tipoColaboracion.getTipo());
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion);
        }
        return numeroFilasAfectadas;               
    }

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
        }catch(SQLException excepcion){
            LOG.error(excepcion);
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;           
    }
}
