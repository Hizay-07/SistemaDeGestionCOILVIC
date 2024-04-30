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
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.interfaces.EmisionPropuestaInterface;

public class DAOEmisionPropuestaImplementacion implements EmisionPropuestaInterface {
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    private Connection conexion;
    
    @Override
    public int registrarEmisionPropuesta(EmisionPropuesta emisionPropuesta) {
        int numeroFilasAfectadas=0;
        PreparedStatement declaracion;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("INSERT INTO EmisionPropuesta (idProfesor,idPropuestaColaboracion,fechaEmision) VALUES (?,?,?);");
            declaracion.setInt(1, emisionPropuesta.getIdProfesor());
            declaracion.setInt(2, emisionPropuesta.getIdPropuestaColaboracion());
            declaracion.setString(3, emisionPropuesta.getFechaEmision());
            numeroFilasAfectadas=declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmisionPropuestaImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;                
    }

    @Override
    public List<EmisionPropuesta> consultarEmisionesDePropuestas() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<EmisionPropuesta> emisionesPropuesta=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT * from EmisionPropuesta;");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                EmisionPropuesta emisionPropuesta=new EmisionPropuesta();
                emisionPropuesta.setIdProfesor(resultado.getInt("idProfesor"));
                emisionPropuesta.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                emisionPropuesta.setFechaEmision(resultado.getString("fechaEmision"));
                emisionesPropuesta.add(emisionPropuesta);
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmisionPropuestaImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return emisionesPropuesta;        
    }

    @Override
    public int consultarIdProfesorPorIdPropuestaColaboracion(int idPropuestaColaboracion) {
        PreparedStatement declaracion;
        ResultSet resultado;
        int idProfesor=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT idProfesor from EmisionPropuesta where idPropuestaColaboracion=?;");
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                idProfesor=resultado.getInt("idProfesor");
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmisionPropuestaImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idProfesor;        
    }
    
}
