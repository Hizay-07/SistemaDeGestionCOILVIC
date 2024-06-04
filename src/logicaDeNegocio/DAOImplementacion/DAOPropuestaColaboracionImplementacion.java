package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import com.mysql.cj.jdbc.CallableStatement;
import logicaDeNegocio.clases.PropuestaColaboracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.interfaces.PropuestaColaboracionInterface;

public class DAOPropuestaColaboracionImplementacion implements PropuestaColaboracionInterface {
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(DAOPropuestaColaboracionImplementacion.class);
          
    @Override
    public int registrarPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion) {        
        int idPropuestaColaboracion=0;        
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement declaracion=(CallableStatement) conexion.prepareCall("call registrarPropuestaColaboracion(?,?,?,?,?,?,?,?,?)")){
            declaracion.setString(1, propuestaColaboracion.getFechaInicio());
            declaracion.setString(2, propuestaColaboracion.getFechaCierre());
            declaracion.setString(3, propuestaColaboracion.getIdioma());
            declaracion.setString(4, propuestaColaboracion.getExperienciaEducativa());
            declaracion.setString(5,propuestaColaboracion.getObjetivo());
            declaracion.setString(6, propuestaColaboracion.getProgramaEducativoEstudiantil());
            declaracion.setString(7, propuestaColaboracion.getEstadoPropuesta());
            declaracion.setInt(8, propuestaColaboracion.getTipoColaboracion().getIdTipoColaboracion());
            declaracion.registerOutParameter(9, Types.INTEGER);
            declaracion.execute();            
            idPropuestaColaboracion=declaracion.getInt(9);           
        } catch (SQLException | NullPointerException excepcion) {
            LOG.fatal(excepcion.getMessage());
            System.out.println(excepcion.getMessage());
            idPropuestaColaboracion = -1;
        }
        return idPropuestaColaboracion;        
    }
  
    @Override
    public List<PropuestaColaboracion> consultarPropuestasColaboracion() {
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion")){
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));
                propuestaColaboracion.getTipoColaboracion().setIdTipoColaboracion(resultado.getInt("idTipoColaboracion"));
                propuestasColaboracion.add(propuestaColaboracion);                
            }
        } catch (SQLException | NullPointerException ex) {
            LOG.error(ex);
        }
        return propuestasColaboracion;
    }

    @Override
    public List<PropuestaColaboracion> consultarPropuestasColaboracionPorFechaDeInicio(String fecha) {
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where fechaInicio=?")){
            declaracion.setString(1, fecha);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));
                propuestaColaboracion.getTipoColaboracion().setIdTipoColaboracion(resultado.getInt("idTipoColaboracion"));
                propuestasColaboracion.add(propuestaColaboracion);                
            }
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
        }
        return propuestasColaboracion;        
    }

    @Override
    public int editarFechaDeInicioDePropuestaColaboracionPorId(String fechaDeInicio, int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;       
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set fechaInicio=? where idPropuestaColaboracion=?")){
            declaracion.setString(1, fechaDeInicio);
            declaracion.setInt(2, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
        }
        return numeroFilasAfectadas;            
    }

    @Override
    public int editarFechaDeCierreDePropuestaColaboracionPorId(String fechaDeCierre, int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;       
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set fechaCierre=? where idPropuestaColaboracion=?")){
            declaracion.setString(1, fechaDeCierre);
            declaracion.setInt(2, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
        }
        return numeroFilasAfectadas;                    
    }

    @Override
    public int aprobarPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;  
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Aprobada' where idPropuestaColaboracion=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.error(ex);
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public int rechazarPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0; 
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Rechazada' where idPropuestaColaboracion=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.error(ex);
        }
        return numeroFilasAfectadas;         
    }
    
    @Override
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadas(){
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where estadoPropuesta='Aprobada';")){      
            resultado=declaracion.executeQuery();            
            while(resultado.next()){
                PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));                
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));                
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));                
                TipoColaboracion tipoColaboracion=new TipoColaboracion();
                int idTipoColaboracion=resultado.getInt("idTipoColaboracion");
                tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                String tipo=daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                tipoColaboracion.setTipo(tipo);
                propuestaColaboracion.setTipoColaboracion(tipoColaboracion);                
                int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                Profesor profesor=daoProfesor.consultarProfesorPorId(idProfesor);            
                propuestaColaboracion.setProfesor(profesor);                
                propuestasColaboracion.add(propuestaColaboracion);                
            }
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
        }
        return propuestasColaboracion;        
    }
    
    @Override
    public PropuestaColaboracion obtenerPropuestaDeColaboracionPorId(int idPropuestaColaboracion){
        ResultSet resultado;
        PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
        DAOTipoColaboracionImplementacion daoTipoColaboracion = new DAOTipoColaboracionImplementacion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where idPropuestaColaboracion=?")){
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));
                int idTipoColaboracion = resultado.getInt("idTipoColaboracion");
                tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                String tipo = daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                tipoColaboracion.setTipo(tipo);
                propuestaColaboracion.setTipoColaboracion(tipoColaboracion);
            }
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
            propuestaColaboracion = null;
        }
        return propuestaColaboracion;
    }
    
    @Override
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionRegistradas(){
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where estadoPropuesta='Registrada';")){       
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                
                    propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                    propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                    propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                    propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                    propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                    propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));                
                    propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));                
                    propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));                
                    TipoColaboracion tipoColaboracion=new TipoColaboracion();
                    int idTipoColaboracion=resultado.getInt("idTipoColaboracion");
                    tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                    String tipo=daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                    tipoColaboracion.setTipo(tipo);
                    propuestaColaboracion.setTipoColaboracion(tipoColaboracion);                
                    int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                    Profesor profesor=daoProfesor.consultarProfesorPorId(idProfesor);            
                    propuestaColaboracion.setProfesor(profesor);                
                    propuestasColaboracion.add(propuestaColaboracion);                
                }
            }
        } catch (SQLException excepcion) {
            LOG.error(excepcion);
        }
        return propuestasColaboracion;                
    }
    
    @Override
    public int obtenerIdPropuestaColaboracionAprobadaPorIdProfesor(int idProfesor){
        ResultSet resultado;
        int idPropuestaColaboracion=0;
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("select p.idPropuestaColaboracion from emisionPropuesta e, propuestaColaboracion p where idProfesor=? AND p.idPropuestaColaboracion=e.idPropuestaColaboracion AND p.estadoPropuesta='Aprobada';")){
            declaracion.setInt(1, idProfesor);
            resultado=declaracion.executeQuery();           
            if(resultado.next()){
                idPropuestaColaboracion=resultado.getInt("idPropuestaColaboracion");                
            }      
        }catch (SQLException ex) {
            LOG.warn(ex);
        }
        return idPropuestaColaboracion;                                        
    }
    
    @Override
    public List<PropuestaColaboracion> consultarPropuestasDeColaboracionAprobadasSinPeticiones(int identificadorProfesor){
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        DAOTipoColaboracionImplementacion daoTipoColaboracion=new DAOTipoColaboracionImplementacion();
        DAOEmisionPropuestaImplementacion daoEmisionPropuesta=new DAOEmisionPropuestaImplementacion();
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * FROM propuestaColaboracion pc WHERE pc.estadoPropuesta = 'Aprobada' AND pc.idPropuestaColaboracion NOT IN (SELECT idPropuestaColaboracion FROM peticionColaboracion WHERE idProfesor = ?);")){      
            declaracion.setInt(1, identificadorProfesor);
            resultado=declaracion.executeQuery();            
            while(resultado.next()){
                PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();                
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));                
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));                
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));                
                TipoColaboracion tipoColaboracion=new TipoColaboracion();
                int idTipoColaboracion=resultado.getInt("idTipoColaboracion");
                tipoColaboracion.setIdTipoColaboracion(idTipoColaboracion);
                String tipo=daoTipoColaboracion.consultarTipoColaboracionPorId(idTipoColaboracion);
                tipoColaboracion.setTipo(tipo);
                propuestaColaboracion.setTipoColaboracion(tipoColaboracion);                
                int idProfesor=daoEmisionPropuesta.consultarIdProfesorPorIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                Profesor profesor=daoProfesor.consultarProfesorPorId(idProfesor);            
                propuestaColaboracion.setProfesor(profesor);                
                propuestasColaboracion.add(propuestaColaboracion);                
            }
        } catch (SQLException | NullPointerException ex) {
            LOG.warn(ex);
        }
        return propuestasColaboracion; 
    }
    
    @Override
    public int cambiarEstadoIniciadaPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0; 
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Iniciada' where idPropuestaColaboracion=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException ex) {
            LOG.error(ex);
        }
        return numeroFilasAfectadas;        
    }

}
