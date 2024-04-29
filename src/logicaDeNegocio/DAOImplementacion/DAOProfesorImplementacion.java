package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.interfaces.ProfesorInterface;
import logicaDeNegocio.clases.Profesor;
import accesoADatos.ManejadorBaseDeDatos;
import com.mysql.cj.jdbc.CallableStatement;
import logicaDeNegocio.enums.EnumEstados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Types;

public class DAOProfesorImplementacion implements ProfesorInterface {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private Connection conexion;
    
    @Override
    public int registrarProfesor(Profesor profesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("INSERT INTO profesor(nombre, apellidoPaterno, apellidoMaterno, correo, estadoProfesor) VALUES (?, ?, ?, ?, ?)");
            declaracion.setString(1, profesor.getNombre());
            declaracion.setString(2, profesor.getApellidoPaterno());
            declaracion.setString(3, profesor.getApellidoMaterno());
            declaracion.setString(4, profesor.getCorreo());
            declaracion.setString(5, "Sin colaboracion"); // Asignar estado al profesor
            numeroFilasAfectadas = declaracion.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas; 
    }

    @Override
    public int cambiarEstadoProfesor(int idProfesor, String nuevoEstado) {
        int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("UPDATE profesor SET estadoProfesor=? WHERE idProfesor=?;");
            declaracion.setString(1, nuevoEstado);
            declaracion.setInt(2, idProfesor);
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numeroFilasAfectadas;
    }

    @Override
    public int modificarNombreProfesor(String nombreActualizado, String correoProfesor) {
        PreparedStatement declaracion;
        int numeroFilasAfectadas = 0; 
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
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
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
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
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
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
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
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
    
    @Override
    public int obtenerIdProfesorPorCorreo(String correo){
        PreparedStatement declaracion;
        ResultSet resultado;
        int idProfesor=0;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT idProfesor from Profesor where correo=?;");
            declaracion.setString(1, correo);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                idProfesor=resultado.getInt("idProfesor");
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idProfesor;                
    }
    
   @Override
   public int asignarUsuarioDeProfesorPorCorreo(String correo){
       int resultadoModificacion;
       try{
           conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           CallableStatement declaracion = (CallableStatement) conexion.prepareCall("call asignarCuentaProfesor(?,?)");
           declaracion.setString(1, correo);
           declaracion.registerOutParameter(2, Types.INTEGER);
           declaracion.execute();
           resultadoModificacion = declaracion.getInt(2);
           BASE_DE_DATOS.cerrarConexion(conexion);
       }catch(SQLException excepcion){
           Logger.getLogger(DAOProfesorImplementacion.class.getName()).log(Level.SEVERE, null, excepcion);
           resultadoModificacion = -1;
       }
       return resultadoModificacion;
   }
   


}
