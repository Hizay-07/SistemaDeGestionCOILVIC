package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejadorBaseDeDatos {
    
    private Connection conexion;
    private static final String NOMBRE_BASE_DE_DATOS="jdbc:mysql://127.0.0.1/bdsistemacoilvic";;
    private final String USUARIO_BASE_DE_DATOS="chris";
    private final String CONTRASENA_BASE_DE_DATOS="PruebaFei123";
    
    public Connection getConexion() throws SQLException{
        connect();
        return conexion;
    }
    
    public void connect() throws SQLException{
        conexion = DriverManager.getConnection(NOMBRE_BASE_DE_DATOS,USUARIO_BASE_DE_DATOS,CONTRASENA_BASE_DE_DATOS);
    }
    
    public void cerrarConexion(Connection conexion) throws SQLException{
        if(conexion!=null){
            try{
                if(!conexion.isClosed()){
                    conexion.close();
                }
            }catch(SQLException excepcion){
                Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.SEVERE, null, excepcion);
            }
        }
    }
}
