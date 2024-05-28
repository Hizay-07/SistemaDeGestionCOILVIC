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
    private Connection conexion;
    private static final Logger LOG=Logger.getLogger(DAOAreaAcademicaImplementacion.class);

    @Override
    public int registrarAreaAcademica(AreaAcademica areaAcademica) {
        
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try{
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("INSERT INTO areaAcademica (area) VALUES (?);");
            declaracion.setString(1, areaAcademica.getArea());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        }catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage()); 
        }
         return numeroFilasAfectadas;
    }

    @Override
    public List<AreaAcademica> consultarAreasAcademicas() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<AreaAcademica> areasAcademicas=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT * from areaAcademica;");
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
        }
        return areasAcademicas;
    }
    
    @Override
    public int consultarIdDeAreaAcademicaPorArea(String area){
        PreparedStatement declaracion;
        ResultSet resultado;
        int idArea=0;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT idAreaAcademica from AreaAcademica where area=?;");
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
    
    public int verificarAreaAcademica(){
        int resultadoVerificacion=0;
        ResultSet resultado;
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select count(*) from areaAcademica");
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                resultadoVerificacion=resultado.getInt(1);                
            }            
            conexion.close();
        }catch(SQLException excepcion){
            LOG.error(excepcion);
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;   
    }
    
}
