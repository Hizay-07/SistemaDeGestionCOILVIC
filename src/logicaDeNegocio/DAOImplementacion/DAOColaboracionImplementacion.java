package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.interfaces.ColaboracionInterface;
import java.sql.ResultSet;

public class DAOColaboracionImplementacion implements ColaboracionInterface{
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private Connection conexion;
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(DAOColaboracionImplementacion.class);

    @Override
    public int registrarColaboracion(Colaboracion colaboracion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("INSERT INTO Colaboracion(estadoColaboracion,idPropuestaColaboracion,cantidadEstudiantes)"
                    + "VALUES (?,?,?);");
            declaracion.setString(1, colaboracion.getEstadoColaboracion());
            declaracion.setInt(2, colaboracion.getIdPropuestaColaboracion());
            declaracion.setInt(3, colaboracion.getCantidadEstudiantes());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.fatal(ex);
        }
        return numeroFilasAfectadas;        
    }

    @Override
    public List<Colaboracion> consultarColaboraciones() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Colaboracion> colaboraciones=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * FROM Colaboracion");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                Colaboracion colaboracion=new Colaboracion();
                colaboracion.setIdColaboracion(resultado.getInt("idColaboracion"));
                colaboracion.setRetroalimentacion(resultado.getString("retroalimentacion"));
                colaboracion.setEstadoColaboracion(resultado.getString("estadoColaboracion"));
                colaboracion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                colaboracion.setCantidadEstudiantes(resultado.getInt("cantidadEstudiantes"));
                colaboraciones.add(colaboracion);
            }
            conexion.close();
        } catch (SQLException ex) {
            LOG.error(ex);
        }
        return colaboraciones;
    }

    @Override
    public int registrarRetroalimentacionColaboracionPorId(int idColaboracion,String retroalimentacion) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("UPDATE Colaboracion set retroalimentacion=? where idColaboracion=?;");
            declaracion.setString(1, retroalimentacion);
            declaracion.setInt(2, idColaboracion);
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            LOG.warn(ex);
        }
        return numeroFilasAfectadas;        
    }
    
}
