package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.interfaces.UsuarioInterface;
import logicaDeNegocio.clases.Usuario;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUsuarioImplementacion implements UsuarioInterface{
   
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private Connection conexion;
    
    
    @Override
    public boolean validarCredenciales(Usuario usuario) {
        boolean resultadoValidacion;
        
        try{
            conexion = BASE_DE_DATOS.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM usuario where nombreDeUsuario = ? AND contrasenia = aes_encrypt(?,'clave')");
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            ResultSet resultado = sentencia.executeQuery();
            int resultadoCoincidencias=0;
            while(resultado.next()){
                resultadoCoincidencias = (int)resultado.getLong(1);
            }
            if(resultadoCoincidencias==1){
                resultadoValidacion = true;
            }else{
                resultadoValidacion = false;
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAOActividadImplementacion.class.getName()).log(Level.SEVERE, excepcion.getMessage(), excepcion);
            resultadoValidacion = false;
        }
        return resultadoValidacion;
    }

    @Override
    public int registrarUsuario(Usuario usuario) {
        int resultadoInsercion;
        
        try{
            conexion = BASE_DE_DATOS.getConexion();
            CallableStatement sentencia = conexion.prepareCall("call registrarUsuario(?,?,?)");
            sentencia.setString(1, usuario.getNombreUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.registerOutParameter(3,Types.INTEGER);
            sentencia.execute();
            resultadoInsercion = sentencia.getInt(3);
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAOActividadImplementacion.class.getName()).log(Level.SEVERE, excepcion.getMessage(), excepcion);
            resultadoInsercion = -1;
        }
        
        return resultadoInsercion;
    }
    
}
