package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.AreaAcademica;
import logicaDeNegocio.interfaces.AreaAcademicaInterface;
import org.apache.log4j.Logger;

public class DAOAreaAcademicaImplementacion implements AreaAcademicaInterface {
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOAreaAcademicaImplementacion.class);
    
    /**
    *Obtener la lista de áreas académicas registradas en la base de datos
    *@return Regresa la lista de Áreas académicas registradas en la base de datos
    **/
    @Override
    public List<AreaAcademica> consultarAreasAcademicas() {
        ResultSet resultado;
        List<AreaAcademica> areasAcademicas=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * from areaAcademica;")){
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    AreaAcademica areaAcademica=new AreaAcademica();
                    areaAcademica.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    areaAcademica.setArea(resultado.getString("area"));
                    areasAcademicas.add(areaAcademica);
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            AreaAcademica areaAcademica=new AreaAcademica();
            areaAcademica.setArea("Excepcion generada");
            areasAcademicas.add(0,areaAcademica);
        }
        return areasAcademicas;
    }
    
    /**
    *Obtener el ID de una área académica
    *@param area String con el nombre del área académica a consultar
    *@return Regresa el ID del área académica consultado
    **/
    @Override
    public int consultarIdDeAreaAcademicaPorArea(String area){
        ResultSet resultado;
        int idArea=0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idAreaAcademica from AreaAcademica where area=?;")){
            declaracion.setString(1, area);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idArea=resultado.getInt("idAreaAcademica");                                
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            idArea = -1;
        }
        return idArea;        
    }
    
    /**
    *Verificar el número de áreas académicas registradas dentro de la base de datos
    *@return Regresa el número de áreas académicas registradas en la base de datos
    **/
    @Override
    public int verificarAreaAcademica(){
        int resultadoVerificacion=0;
        ResultSet resultado;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select count(*) from areaAcademica")){
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                resultadoVerificacion=resultado.getInt(1);                
            }            
            conexion.close();
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;   
    }
    
}
