package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.interfaces.ProfesorUVInterface;
import org.apache.log4j.Logger;

public class DAOProfesorUVImplementacion implements ProfesorUVInterface{    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private Connection conexion;
    private static final Logger LOG=Logger.getLogger(DAOProfesorUVImplementacion.class);

    @Override
    public int registrarProfesorUV(ProfesorUV profesorUV) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("INSERT INTO ProfesorUV (numeroPersonal,tipoContratacion,categoriaContratacion,idProfesor,idRegionAcademica,idAreaAcademica)"
                    + "VALUES (?,?,?,?,?,?);");
            declaracion.setString(1,profesorUV.getNumeroDePersonal());
            declaracion.setString(2, profesorUV.getTipoDeContratacion());
            declaracion.setString(3,profesorUV.getCategoriaDeContratacion());
            declaracion.setInt(4,profesorUV.getIdProfesor());
            declaracion.setInt(5, profesorUV.getIdRegion());
            declaracion.setInt(6, profesorUV.getIdAreaAcademica());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public List<ProfesorUV> consultarProfesoresUV() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<ProfesorUV> profesoresUV=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("select * from profesoruv,profesor where profesoruv.idProfesor = profesor.idProfesor;");
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    ProfesorUV profesorUV=new ProfesorUV();
                    profesorUV.setNombre(resultado.getString("nombre"));
                    profesorUV.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesorUV.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesorUV.setCorreo(resultado.getString("Correo"));
                    profesorUV.setEstado(resultado.getString("estadoProfesor"));
                    profesorUV.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    profesorUV.setNumeroDePersonal(resultado.getString("numeroPersonal"));
                    profesorUV.setTipoDeContratacion(resultado.getString("tipoContratacion"));
                    profesorUV.setCategoriaDeContratacion(resultado.getString("categoriaContratacion"));
                    profesorUV.setIdProfesor(resultado.getInt("idProfesor"));
                    profesorUV.setIdRegion(resultado.getInt("idRegionAcademica"));
                    profesorUV.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    profesoresUV.add(profesorUV);           
                }
            }
            conexion.close();                        
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return profesoresUV;
    }

    @Override
    public List<ProfesorUV> consultarProfesoresUVPorAreaAcademica(int idAreaAcademica) {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<ProfesorUV> profesoresUV=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("select * from profesoruv,profesor where idAreaAcademica = ?");
            declaracion.setInt(1, idAreaAcademica);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    ProfesorUV profesorUV=new ProfesorUV();
                    profesorUV.setNombre(resultado.getString("nombre"));
                    profesorUV.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesorUV.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesorUV.setCorreo(resultado.getString("Correo"));
                    profesorUV.setEstado(resultado.getString("estadoProfesor"));
                    profesorUV.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    profesorUV.setNumeroDePersonal(resultado.getString("numeroPersonal"));
                    profesorUV.setTipoDeContratacion(resultado.getString("tipoContratacion"));
                    profesorUV.setCategoriaDeContratacion(resultado.getString("categoriaContratacion"));
                    profesorUV.setIdProfesor(resultado.getInt("idProfesor"));
                    profesorUV.setIdRegion(resultado.getInt("idRegionAcademica"));
                    profesorUV.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    profesoresUV.add(profesorUV);           
                }
            }
            conexion.close();                        
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return profesoresUV;                
    }

    @Override
    public List<ProfesorUV> consultarProfesoresUVPorRegion(int idRegion) {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<ProfesorUV> profesoresUV=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("select * from profesoruv,profesor where idRegionAcademica = ?;");
            declaracion.setInt(1, idRegion);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    ProfesorUV profesorUV=new ProfesorUV();
                    profesorUV.setNombre(resultado.getString("nombre"));
                    profesorUV.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesorUV.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesorUV.setCorreo(resultado.getString("Correo"));
                    profesorUV.setEstado(resultado.getString("estadoProfesor"));
                    profesorUV.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    profesorUV.setNumeroDePersonal(resultado.getString("numeroPersonal"));
                    profesorUV.setTipoDeContratacion(resultado.getString("tipoContratacion"));
                    profesorUV.setCategoriaDeContratacion(resultado.getString("categoriaContratacion"));
                    profesorUV.setIdProfesor(resultado.getInt("idProfesor"));
                    profesorUV.setIdRegion(resultado.getInt("idRegionAcademica"));
                    profesorUV.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    profesoresUV.add(profesorUV);           
                }
            }
            conexion.close();                        
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return profesoresUV;  
    }

    @Override
    public int editarTipoDeContratacionDeProfesorUVPorNumeroDePersonal(String tipoDeContratacion, String numeroDePersonal) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("UPDATE ProfesorUV set tipoContratacion=? where numeroPersonal=?;");
            declaracion.setString(1, tipoDeContratacion);
            declaracion.setString(2, numeroDePersonal);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;                
    }

    @Override
    public int editarCategoriaDeContratacionDeProfesorUVPorNumeroDePersonal(String categoriaDeContratacion, String numeroDePersonal) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("UPDATE ProfesorUV set categoriaContratacion=? where numeroPersonal=?;");
            declaracion.setString(1, categoriaDeContratacion);
            declaracion.setString(2, numeroDePersonal);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;         
    }

    @Override
    public int editarAreaAcademicaDeProfesorUVPorNumeroDePersonal(int areaAcademica, String numeroDePersonal) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("UPDATE ProfesorUV set idAreaAcademica=? where numeroPersonal=?;");
            declaracion.setInt(1, areaAcademica);
            declaracion.setString(2, numeroDePersonal);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;         
    }

    @Override
    public int editarRegionDeProfesorUVPorNumeroDePersonal(int region, String numeroDePersonal) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("UPDATE ProfesorUV set idRegionAcademica=? where numeroPersonal=?;");
            declaracion.setInt(1, region);
            declaracion.setString(2, numeroDePersonal);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;    
    }
    
    @Override
    public int eliminarProfesorUV(String correo){
        int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("DELETE FROM profesorUV " +
            "WHERE idProfesor IN (SELECT idProfesor FROM profesor WHERE correo = ?);");
            declaracion.setString(1, correo);
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;
    }
    
}
