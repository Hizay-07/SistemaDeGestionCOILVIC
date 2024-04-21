package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.interfaces.TipoColaboracionInterface;

public class DAOTipoColaboracion implements TipoColaboracionInterface{
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public int registrarTipoColaboracion(TipoColaboracion tipoColaboracion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("INSERT INTO TipoColaboracion(tipo) VALUES (?)");
            declaracion.setString(1, tipoColaboracion.getTipo());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;               
    }

    @Override
    public List<TipoColaboracion> consultarTiposDeColaboracion() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<TipoColaboracion> tiposColaboracion=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * from TipoColaboracion");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                TipoColaboracion tipoColaboracion=new TipoColaboracion();
                tipoColaboracion.setTipo(resultado.getString("tipo"));
                tipoColaboracion.setIdTipoColaboracion(resultado.getInt("idTipoColaboracion"));
                tiposColaboracion.add(tipoColaboracion);
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTipoColaboracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiposColaboracion;
    }            
}
