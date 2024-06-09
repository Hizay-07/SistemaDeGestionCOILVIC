package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.ProfesorUV;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.interfaces.ProfesorUVInterface;
import org.apache.log4j.Logger;

public class DAOProfesorUVImplementacion implements ProfesorUVInterface{    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOProfesorUVImplementacion.class);

    /**
    *Registrar un profesor UV dentro de la base de datos
    *@param profesorUV ProfesorUV con los datos a registrar dentro de la base de datos
    *@return Regresa el numero de filas afectadas
    **/
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
    
    /**
    *Obtener la lista de profesores UV registrados en la base de datos
    *@return Regresa la lista de profesores UV registrados en la base de datos
    **/
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
                    Usuario usuarioProfesor = new Usuario();
                    usuarioProfesor.setIdUsuario(resultado.getInt("Usuario_idUsuario"));
                    profesorUV.setUsuario(usuarioProfesor);
                    profesoresUV.add(profesorUV);           
                }
            }                    
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return profesoresUV;
    }
    
    /**
    *Editar el tipo de contratación asociado a un profesor UV registrado en la base de 
    * datos a través del ID de profesor asignado
    *@param idProfesorUV int con el ID de un profesor registrado en la base de datos 
    *@param tipoDeContratacion String con el nuevo tipoDeContratacion a asignar
    *@return Regresa el numero de filas afectadas
    **/
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

    /**
    *Editar la categoria de contratacion asociada a un profesor UV registrado en la base de 
    * datos a través del ID de profesor asignado
    *@param idProfesorUV int con el ID de un profesor registrado en la base de datos 
    *@param categoriaDeContratacion String con la nueva categoriaDeContratacion a asignar
    *@return Regresa el numero de filas afectadas
    **/
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

    /**
    *Editar la categoria de contratacion asociada a un profesor UV registrado en la base de 
    * datos a través del ID de profesor asignado
    *@param idProfesorUV int con el ID de un profesor registrado en la base de datos 
    *@param areaAcademica int con el nuevo ID de areaAcademica a asignar
    *@return Regresa el numero de filas afectadas
    **/
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

    /**
    *Editar la región académica de profesor asociada a un profesor UV registrado en la base de 
    * datos a través del ID de profesor asignado
    *@param idProfesorUV int con el ID de un profesor registrado en la base de datos 
    *@param region int con el nuevo ID de región académica a asignar
    *@return Regresa el numero de filas afectadas
    **/
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
    
    /**
    *Editar el numero de personal de profesor asociado a un profesor UV registrado en la base de 
    * datos a través del ID de profesor asignado
    *@param idProfesorUV int con el ID de un profesor registrado en la base de datos 
    *@param numeroDePersonal String con el nuevo numeroDePersonal a asignar
    *@return Regresa el numero de filas afectadas
    **/
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
    
    /**
    *Obtener un profesor UV registrado en la base de datos a través del ID profesor
    *@param idProfesor int con el ID de un profesor registrado en la base de datos 
    *@return Regresa un Profesor UV registrado en la base de datos
    **/
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
    
    /**
    *Validar la inexistencia de un profesor uv en la base de datos
    *@param noPersonal String con el numero de personal de un profesor 
    * registrado en la base de datos 
    *@return Regresa el numero de coincidencias encontradas
    **/
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
    
}
