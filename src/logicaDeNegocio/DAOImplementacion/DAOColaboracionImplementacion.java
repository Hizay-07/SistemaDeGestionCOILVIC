    package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.interfaces.ColaboracionInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOColaboracionImplementacion implements ColaboracionInterface {
    private static int NUMERO_FILAS_AFECTADAS=0;
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private Connection conexion;
    private Colaboracion colaboracion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Colaboracion getColaboracion() {
        return colaboracion;
    }

    public void setColaboracion(Colaboracion colaboracion) {
        this.colaboracion = colaboracion;
    }
    
    @Override
    public int registrarColaboracion() {        
        PreparedStatement declaracion;    
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("INSERT INTO colaboracion(tipoColaboracion,fechaInicio,fechaCierre,idioma,experienciaEducativa) VALUES (?,?,?,?,?)");
            declaracion.setString(1,colaboracion.getTipoColaboracion() );
            declaracion.setString(2, colaboracion.getFechaInicio());
            declaracion.setString(3, colaboracion.getFechaCierre());
            declaracion.setString(4, colaboracion.getIdioma());
            declaracion.setString(5, colaboracion.getExperienciaEducativa());
            NUMERO_FILAS_AFECTADAS=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return NUMERO_FILAS_AFECTADAS;        
    }

    @Override
    public List<Colaboracion> consultarColaboraciones() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Colaboracion> colaboraciones=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * from Colaboracion");
            resultado=declaracion.executeQuery();   
            while(resultado.next()){
                Colaboracion consultaColaboracion=new Colaboracion();
                consultaColaboracion.setTipoColaboracion(resultado.getString("tipoColaboracion"));
                consultaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                consultaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                consultaColaboracion.setIdioma(resultado.getString("idioma"));
                consultaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                consultaColaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                colaboraciones.add(consultaColaboracion);                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colaboraciones;       
    }

    @Override
    public List<Colaboracion> consultarColaboracionPorFechaDeInicio(String fecha) {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Colaboracion> colaboraciones=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * from Colaboracion where fechaInicio=?");
            declaracion.setString(1, fecha);
            resultado=declaracion.executeQuery();   
            while(resultado.next()){
                Colaboracion consultaColaboracion=new Colaboracion();
                consultaColaboracion.setTipoColaboracion(resultado.getString("tipoColaboracion"));
                consultaColaboracion.setFechaInicio(resultado.getString("fechaInicio"));
                consultaColaboracion.setFechaCierre(resultado.getString("fechaCierre"));
                consultaColaboracion.setIdioma(resultado.getString("idioma"));
                consultaColaboracion.setExperienciaEducativa(resultado.getString("experienciaEducativa"));
                consultaColaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                colaboraciones.add(consultaColaboracion);                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colaboraciones;                
    }

    @Override
    public int editarFechaDeInicioDeColaboracion(String fechaDeInicio) {
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE colaboracion set fechaInicio=? where idColaboracion=?");
            declaracion.setString(1, fechaDeInicio);
            declaracion.setInt(2, colaboracion.getIdColaboracion());
            NUMERO_FILAS_AFECTADAS=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NUMERO_FILAS_AFECTADAS;                        
    }

    @Override
    public int editarFechaDeCierreDeColaboracion(String fechaDeCierre) {
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE colaboracion set fechaCierre=? where idColaboracion=?");
            declaracion.setString(1, fechaDeCierre);
            declaracion.setInt(2, colaboracion.getIdColaboracion());
            NUMERO_FILAS_AFECTADAS=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NUMERO_FILAS_AFECTADAS;        
    }

    @Override
    public int editarIdiomaDeColaboracion(String idioma) {
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE colaboracion set idioma=? where idColaboracion=?");
            declaracion.setString(1, idioma);
            declaracion.setInt(2, colaboracion.getIdColaboracion());
            NUMERO_FILAS_AFECTADAS=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NUMERO_FILAS_AFECTADAS;         
    }

    @Override
    public int editarExperienciaEducativaDeColaboracion(String experienciaEducativa) {
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE colaboracion set experienciaEducativa=? where idColaboracion=?");
            declaracion.setString(1, experienciaEducativa);
            declaracion.setInt(2, colaboracion.getIdColaboracion());
            NUMERO_FILAS_AFECTADAS=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NUMERO_FILAS_AFECTADAS; 
    }

    @Override
    public int editarTipoDeColaboracion(String tipoDeColaboracion) {        
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE colaboracion set tipoColaboracion=? where idColaboracion=?");
            declaracion.setString(1, tipoDeColaboracion);
            declaracion.setInt(2, colaboracion.getIdColaboracion());
            NUMERO_FILAS_AFECTADAS=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NUMERO_FILAS_AFECTADAS; 
    }
    
    @Override
    public int darDeBajaColaboracionPorId(int idColaboracion){
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("DELETE from colaboracion where idColaboracion=?");
            declaracion.setInt(1, idColaboracion);
            NUMERO_FILAS_AFECTADAS=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return NUMERO_FILAS_AFECTADAS;                
    }
    
}
