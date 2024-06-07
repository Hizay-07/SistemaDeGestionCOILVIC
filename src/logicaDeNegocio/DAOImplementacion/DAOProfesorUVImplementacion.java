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
    private static final Logger LOG=Logger.getLogger(DAOProfesorUVImplementacion.class);

    @Override
    public int registrarProfesorUV(ProfesorUV profesorUV) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("INSERT INTO ProfesorUV (numeroPersonal,tipoContratacion,categoriaContratacion,idProfesor,idRegionAcademica,idAreaAcademica)"
                    + "VALUES (?,?,?,?,?,?);")){
            declaracion.setString(1,profesorUV.getNumeroDePersonal());
            declaracion.setString(2, profesorUV.getTipoDeContratacion());
            declaracion.setString(3,profesorUV.getCategoriaDeContratacion());
            declaracion.setInt(4,profesorUV.getIdProfesor());
            declaracion.setInt(5, profesorUV.getIdRegion());
            declaracion.setInt(6, profesorUV.getIdAreaAcademica());
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public List<ProfesorUV> consultarProfesoresUV() {
        ResultSet resultado;
        List<ProfesorUV> profesoresUV=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("select * from profesoruv,profesor where profesoruv.idProfesor = profesor.idProfesor;")){
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
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return profesoresUV;
    }

    @Override
    public int editarTipoDeContratacionDeProfesorUVPorIdProfesorUV(String tipoDeContratacion, int idProfesorUV) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE ProfesorUV set tipoContratacion=? where idProfesorUV=?;")){
            declaracion.setString(1, tipoDeContratacion);
            declaracion.setInt(2, idProfesorUV);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;                
    }

    @Override
    public int editarCategoriaDeContratacionDeProfesorUVPorIdProfesorUV(String categoriaDeContratacion, int idProfesorUV) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE ProfesorUV set categoriaContratacion=? where idProfesorUV=?;")){
            declaracion.setString(1, categoriaDeContratacion);
            declaracion.setInt(2, idProfesorUV);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;         
    }

    @Override
    public int editarAreaAcademicaDeProfesorUVPorIdProfesorUV(int areaAcademica, int idProfesorUV) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE ProfesorUV set idAreaAcademica=? where idProfesorUV=?;")){
            declaracion.setInt(1, areaAcademica);
            declaracion.setInt(2, idProfesorUV);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;         
    }

    @Override
    public int editarRegionDeProfesorUVPorIdProfesorUV(int region, int idProfesorUV) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE ProfesorUV set idRegionAcademica=? where idProfesorUV=?;")){
            declaracion.setInt(1, region);
            declaracion.setInt(2, idProfesorUV);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;    
    }
    
    @Override 
    public int editarNumeroDePersonalPorIdProfesorUV(String numeroDePersonal, int idProfesorUV){
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE ProfesorUV set numeroPersonal=? where idProfesorUV=?;")){
            declaracion.setString(1, numeroDePersonal);
            declaracion.setInt(2, idProfesorUV);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;    
    }
    
    @Override
    public ProfesorUV obtenerProfesorUVPorIDProfesor(int idProfesor){
        ProfesorUV profesorObtenido = new ProfesorUV();
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("Select * from profesoruv where idProfesor = ?;")){
            declaracion.setInt(1, idProfesor);
            ResultSet resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    profesorObtenido.setCategoriaDeContratacion(resultado.getString("categoriaContratacion"));
                    profesorObtenido.setTipoDeContratacion(resultado.getString("tipoContratacion"));
                    profesorObtenido.setNumeroDePersonal(resultado.getString("numeroPersonal"));
                    profesorObtenido.setIdProfesorUV(resultado.getInt("idProfesorUV"));
                    profesorObtenido.setIdProfesor(resultado.getInt("idProfesor"));
                    profesorObtenido.setIdAreaAcademica(resultado.getInt("idAreaAcademica"));
                    profesorObtenido.setIdRegion(resultado.getInt("idRegionAcademica"));
                }
            }else{
                profesorObtenido = null;
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            profesorObtenido = null;
        }
        return profesorObtenido;
    }
    
    @Override
    public int validarInexistenciaProfesorUV(String noPersonal){
        int numeroDeCoincidencias=0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("Select COUNT(*) from profesoruv where numeroPersonal = ?;")){
            declaracion.setString(1, noPersonal);
            ResultSet resultado = declaracion.executeQuery();
            while(resultado.next()){
                numeroDeCoincidencias = resultado.getInt(1);
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroDeCoincidencias = -1;
        }
        return numeroDeCoincidencias;
    }
    
    //No se hacen pruebas
    @Override
    public int eliminarProfesorUV(String correo){
        int numeroFilasAfectadas = 0;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("DELETE FROM profesorUV " +
            "WHERE idProfesor IN (SELECT idProfesor FROM profesor WHERE correo = ?);")){
            declaracion.setString(1, correo);
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;
    }
    
}
