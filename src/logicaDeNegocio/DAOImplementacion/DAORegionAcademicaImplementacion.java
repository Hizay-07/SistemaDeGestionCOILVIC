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
    private Connection conexion;
    private static final Logger LOG=Logger.getLogger(DAORegionAcademicaImplementacion.class);

    @Override
    public int registrarRegionAcademica(RegionAcademica regionAcademica) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("INSERT INTO regionAcademica (region) VALUES (?);");
            declaracion.setString(1, regionAcademica.getRegion());numeroFilasAfectadas=declaracion.executeUpdate();
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();            
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;                        
    }

    @Override
    public List<RegionAcademica> consultarRegionesAcademicas() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<RegionAcademica> regionesAcademicas=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT * FROM RegionAcademica;");
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){             
                while(resultado.next()){
                    RegionAcademica regionAcademica=new RegionAcademica();
                    regionAcademica.setIdRegionAcademica(resultado.getInt("idRegionAcademica"));
                    regionAcademica.setRegion(resultado.getString("region"));
                    regionesAcademicas.add(regionAcademica);
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return regionesAcademicas;        
    }
    
    @Override
    public int consultarIdDeRegionPorRegion(String region){
        PreparedStatement declaracion;
        ResultSet resultado;
        int idRegion=0;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT idRegionAcademica from RegionAcademica where region=?");
            declaracion.setString(1, region);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idRegion=resultado.getInt("idRegionAcademica");
                }
            }
            conexion.close();                      
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return idRegion;                
    }
    
}
