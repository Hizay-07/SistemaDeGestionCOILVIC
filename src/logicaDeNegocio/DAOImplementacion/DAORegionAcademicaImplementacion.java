package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.RegionAcademica;
import logicaDeNegocio.interfaces.RegionAcademicaInterface;
import org.apache.log4j.Logger;

public class DAORegionAcademicaImplementacion implements RegionAcademicaInterface {
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAORegionAcademicaImplementacion.class);

    /**
    *Obtener las regiones académicas registradas dentro de la base de datos
    *@return Regresa la lista de regiones académicas registradas dentro de la
    * base de datos
    **/
    @Override
    public List<RegionAcademica> consultarRegionesAcademicas() {
        ResultSet resultado;
        List<RegionAcademica> regionesAcademicas=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM RegionAcademica;")){
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){             
                while(resultado.next()){
                    RegionAcademica regionAcademica=new RegionAcademica();
                    regionAcademica.setIdRegionAcademica(resultado.getInt("idRegionAcademica"));
                    regionAcademica.setRegion(resultado.getString("region"));
                    regionesAcademicas.add(regionAcademica);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return regionesAcademicas;        
    }
    
    /**
    *Obtener el ID de una región académica a traves su nombre registrado dentro de
    * la base de datos
    * @param region String con el nombre de region académica registrada en la base 
    * de datos
    *@return Regresa el ID de una región académica
    **/
    @Override
    public int consultarIdDeRegionPorRegion(String region){
        ResultSet resultado;
        int idRegion=0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idRegionAcademica from RegionAcademica where region=?")){
            declaracion.setString(1, region);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idRegion=resultado.getInt("idRegionAcademica");
                }
            }                    
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return idRegion;                
    }
    
    /**
    *Obtener el numero de regiones académicas registradas dentro de la base de datos
    *@return Regresa el numero de regiones académicas
    **/
    @Override
    public int verificarRegion(){
        int resultadoVerificacion=0;
        ResultSet resultado;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select count(*) from regionAcademica")){
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
