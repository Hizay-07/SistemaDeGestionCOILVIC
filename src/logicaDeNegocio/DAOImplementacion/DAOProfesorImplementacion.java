package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.interfaces.ProfesorInterface;
import logicaDeNegocio.clases.Profesor;
import accesoADatos.ManejadorBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProfesorImplementacion implements ProfesorInterface {

    private static int NUMERO_FILAS_AFECTADAS = 0;
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private Connection conexion;
    
    @Override
    public int registrarProfesor(Profesor profesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("INSERT INTO profesor(nombre, apellidoPaterno, apellidoMaterno, correo) VALUES (?, ?, ?, ?)");
            declaracion.setString(1, profesor.getNombre());
            declaracion.setString(2, profesor.getApellidoPaterno());
            declaracion.setString(3, profesor.getApellidoMaterno());
            declaracion.setString(4, profesor.getCorreo());
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int eliminarProfesor(String correo) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("DELETE FROM profesor WHERE correo = ?");
            declaracion.setString(1, correo);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int modificarNombreProfesor(String nombreActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("UPDATE profesor SET nombre = ? WHERE correo = ?");
            declaracion.setString(1, nombreActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int modificarApellidoPaternoProfesor(String apellidoPaternoActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("UPDATE profesor SET apellidoPaterno = ? WHERE correo = ?");
            declaracion.setString(1, apellidoPaternoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int modificarApellidoMaternoProfesor(String apellidoMaternoActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("UPDATE profesor SET apellidoMaterno = ? WHERE correo = ?");
            declaracion.setString(1, apellidoMaternoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int modificarCorreoProfesor(String correoActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("UPDATE profesor SET correo = ? WHERE correo = ?");
            declaracion.setString(1, correoActualizado);
            declaracion.setString(2, correoProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }
    
    
   


}
