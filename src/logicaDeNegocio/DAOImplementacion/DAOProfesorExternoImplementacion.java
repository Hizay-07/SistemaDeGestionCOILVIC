package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.interfaces.ProfesorExternoInterface;
import org.apache.log4j.Logger;

public class DAOProfesorExternoImplementacion implements ProfesorExternoInterface {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOProfesorExternoImplementacion.class);
    
    /**
    *Registrar un profesor externo dentro de la base de datos
    *@param profesorExterno ProfesorExterno con los datos a registrar dentro de la base de datos
    *@return Regresa el numero de filas afectadas
    **/
    @Override
    public int registrarProfesorExterno(ProfesorExterno profesorExterno) {
        int numeroFilasAfectadas = 0;
        
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("INSERT INTO profesorexterno (idProfesor, idRepresentanteInstitucional) VALUES (?, ?);")){
            declaracion.setInt(1, profesorExterno.getIdProfesor());
            declaracion.setInt(2, profesorExterno.getIdRepresentanteInstitucional());
            numeroFilasAfectadas = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return numeroFilasAfectadas;
    }
    
    /**
    *Obtener la lista de profesores externos registrados dentro de la base de datos
    *@return Regresa la lista de profesores externos registrados en la base de datos
    **/
    @Override
    public List<ProfesorExterno> consultarProfesoresExternos() {
        ResultSet resultado;
        List<ProfesorExterno> profesoresExternos = new ArrayList<>();
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM profesorexterno,profesor where profesorexterno.idProfesor = profesor.idProfesor;")){
            resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while (resultado.next()) {
                    ProfesorExterno profesorExterno = new ProfesorExterno();
                    profesorExterno.setNombre(resultado.getString("nombre"));
                    profesorExterno.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesorExterno.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesorExterno.setCorreo(resultado.getString("correo"));
                    profesorExterno.setEstado(resultado.getString("estadoProfesor"));
                    profesorExterno.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    profesorExterno.setIdProfesor(resultado.getInt("idProfesor"));
                    Usuario usuarioProfesor = new Usuario();
                    usuarioProfesor.setIdUsuario(resultado.getInt("Usuario_idUsuario"));
                    profesorExterno.setUsuario(usuarioProfesor);
                    profesorExterno.setIdRepresentanteInstitucional(resultado.getInt("idRepresentanteInstitucional"));
                    profesoresExternos.add(profesorExterno);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return profesoresExternos;
    }
    
    /**
    *Obtener la lista de profesores externos registrados dentro de la base de datos
    * asociados a un representante institucional
    * @param idRepresentanteInstitucional int con el ID del representante institucional
    * asociado a un profesor externo registrado en la base de datos
    *@return Regresa la lista de profesores externos registrados en la base de datos
    * asociados al ID del representante institucional
    **/
    @Override
    public List<ProfesorExterno> consultarProfesoresExternosPorRepresentanteInstitucional(int idRepresentanteInstitucional) {
        ResultSet resultado;
        List<ProfesorExterno> profesoresExternos = new ArrayList<>();
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("SELECT * FROM profesorexterno,profesor WHERE idRepresentanteInstitucional=?;")){
            declaracion.setInt(1, idRepresentanteInstitucional);
            resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while (resultado.next()) {
                    ProfesorExterno profesorExterno = new ProfesorExterno();
                    profesorExterno.setNombre(resultado.getString("nombre"));
                    profesorExterno.setApellidoPaterno(resultado.getString("apellidoPaterno"));
                    profesorExterno.setApellidoMaterno(resultado.getString("apellidoMaterno"));
                    profesorExterno.setEstado(resultado.getString("estadoProfesor"));
                    profesorExterno.setCorreo(resultado.getString("correo"));
                    profesorExterno.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    profesorExterno.setIdRepresentanteInstitucional(resultado.getInt("idRepresentanteInstitucional"));
                    profesoresExternos.add(profesorExterno);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return profesoresExternos;
    }
    
    /**
    *Obtener el ID de un representante institucional asociado a un profesor
    * @param idProfesor int con el ID del profesor asociado a un representante institucional
    * registrado en la base de datos
    *@return Regresa la lista de profesores externos registrados en la base de datos
    * asociados al ID del representante institucional
    **/    
    @Override
    public int consultarIdRepresentanteInstitucionalPorIdProfesor(int idProfesor){     
        int idProfesorExterno=0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=conexion.prepareCall("CALL existeIdRepresentanteInstitucional(?,?);")){            
            declaracion.setInt(1, idProfesor);
            declaracion.registerOutParameter(2,Types.INTEGER);
            declaracion.execute();
            idProfesorExterno=declaracion.getInt(2);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return idProfesorExterno;                
    }
    
    /**
    *Obtener un profesor externo registrado en la base de datos a través del ID del profesor
    * asociado al profesor externo
    *@param idProfesor int con el ID del profesor asociado a un profesor externo registrado en la base de datos
    *@return Regresa el profesor externo registrado en la base de datos
    **/   
    @Override
    public ProfesorExterno obtenerProfesorExternoPorIDProfesor(int idProfesor){
        ProfesorExterno profesorObtenido = new ProfesorExterno();
        
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("Select * from profesorexterno where idProfesor = ?;")){
            declaracion.setInt(1, idProfesor);
            ResultSet resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    profesorObtenido.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    profesorObtenido.setIdRepresentanteInstitucional(resultado.getInt("idRepresentanteInstitucional"));
                    profesorObtenido.setIdProfesor(resultado.getInt("idProfesor"));
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
    *Modificar el representante institucional asociado a un profesor externo registrado en la 
    * base de datos
    * @param idRepresentanteInstitucional int con el ID de un representante institucional
    * registrado en la base de datos
    *@param idProfesorExterno int con el ID del profesor asociado a un profesor externo 
    * registrado en la base de datos
    *@return Regresa el numero de filas afectadas
    **/ 
    @Override
    public int editarInstitucionProfesorExternoPorIdProfesor(int idRepresentanteInstitucional, int idProfesorExterno){
        int resultadoModificacion=0;
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion = conexion.prepareStatement("update profesorexterno set idRepresentanteInstitucional = ? where idProfesorExterno = ?")){
            declaracion.setInt(1, idRepresentanteInstitucional);
            declaracion.setInt(2, idProfesorExterno);
            resultadoModificacion = declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }
   
}
