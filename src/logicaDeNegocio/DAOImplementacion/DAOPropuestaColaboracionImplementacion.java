package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import logicaDeNegocio.clases.PropuestaColaboracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import logicaDeNegocio.interfaces.PropuestaColaboracionInterface;

public class DAOPropuestaColaboracionImplementacion implements PropuestaColaboracionInterface {
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private Connection conexion;
    

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public int registrarPropuestaColaboracion(PropuestaColaboracion propuestaColaboracion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("INSERT INTO propuestaColaboracion(fechaInicio,fechaCierre,idioma,experienciaEducativa,"
                    + "objetivo,cantidadEstudiantes,programaEducativoEstudiantil,estadoPropuesta,idTipoColaboracion)"
                    + "VALUES (?,?,?,?,?,?,?,?,?);");
            declaracion.setString(1, propuestaColaboracion.getFechaInicio());
            declaracion.setString(2, propuestaColaboracion.getFechaCierre());
            declaracion.setString(3, propuestaColaboracion.getIdioma());
            declaracion.setString(4, propuestaColaboracion.getExperienciaEducativa());
            declaracion.setString(5,propuestaColaboracion.getObjetivo());
            declaracion.setInt(6,propuestaColaboracion.getCantidadEstudiantes());
            declaracion.setString(7, propuestaColaboracion.getProgramaEducativoEstudiantil());
            declaracion.setString(8, propuestaColaboracion.getEstadoPropuesta());
            declaracion.setInt(9, propuestaColaboracion.getIdTipoColaboracion());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropuestaColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;        
    }
  
    @Override
    public List<PropuestaColaboracion> consultarPropuestasColaboracion() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                PropuestaColaboracion propuestaColaboracion=new PropuestaColaboracion();
                propuestaColaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                propuestaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                propuestaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                propuestaColaboracion.setIdioma(resultado.getString("idioma"));
                propuestaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                propuestaColaboracion.setObjetivo(resultado.getString("objetivo"));
                propuestaColaboracion.setCantidadEstudiantes(resultado.getInt("cantidadEstudiantes"));
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));
                propuestaColaboracion.setInformacionAdicional(resultado.getString("informacionAdicional"));
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));
                propuestaColaboracion.setIdTipoColaboracion(resultado.getInt("idTipoColaboracion"));
                propuestasColaboracion.add(propuestaColaboracion);                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropuestaColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propuestasColaboracion;
    }

    @Override
    public List<PropuestaColaboracion> consultarPropuestasColaboracionPorFechaDeInicio(String fecha) {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<PropuestaColaboracion> propuestasColaboracion=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * FROM PropuestaColaboracion where fechaInicio=?");
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
                propuestaColaboracion.setCantidadEstudiantes(resultado.getInt("cantidadEstudiantes"));
                propuestaColaboracion.setProgramaEducativoEstudiantil(resultado.getString("programaEducativoEstudiantil"));
                propuestaColaboracion.setInformacionAdicional(resultado.getString("informacionAdicional"));
                propuestaColaboracion.setEstadoPropuesta(resultado.getString("estadoPropuesta"));
                propuestaColaboracion.setIdTipoColaboracion(resultado.getInt("idTipoColaboracion"));
                propuestasColaboracion.add(propuestaColaboracion);                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropuestaColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propuestasColaboracion;        
    }

    @Override
    public int editarFechaDeInicioDePropuestaColaboracionPorId(String fechaDeInicio, int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;        
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set fechaInicio=? where idPropuestaColaboracion=?");
            declaracion.setString(1, fechaDeInicio);
            declaracion.setInt(2, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropuestaColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;            
    }

    @Override
    public int editarFechaDeCierreDePropuestaColaboracionPorId(String fechaDeCierre, int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;        
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set fechaCierre=? where idPropuestaColaboracion=?");
            declaracion.setString(1, fechaDeCierre);
            declaracion.setInt(2, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropuestaColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;                    
    }

    @Override
    public int aprobarPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;   
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Aprobada' where idPropuestaColaboracion=?;");
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropuestaColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public int rechazarPropuestaColaboracionPorId(int idPropuestaColaboracion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;   
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE PropuestaColaboracion set estadoPropuesta='Rechazada' where idPropuestaColaboracion=?;");
            declaracion.setInt(1, idPropuestaColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropuestaColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;         
    }
    
}
