package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.DataInputStream;
import logicaDeNegocio.clases.Usuario;

public class ManejadorBaseDeDatos {
    private static ManejadorBaseDeDatos instancia;
    private Connection conexion;
    private static final String NOMBRE_BASE_DE_DATOS="jdbc:mysql://localhost/bdsistemacoilvic";;
    private final String USUARIO_BASE_DE_DATOS="chris";
    private final String CONTRASENA_BASE_DE_DATOS="PruebaFei123";

    
    public Connection getConexion()throws SQLException{
        connect();
        return conexion;
    }
    
    public void connect() throws SQLException{
        conexion = DriverManager.getConnection(NOMBRE_BASE_DE_DATOS,USUARIO_BASE_DE_DATOS,CONTRASENA_BASE_DE_DATOS);
    }
    
    public Connection conectarBaseDeDatos(Usuario usuario){
        Properties datosUsuario = new Properties();
        if(usuario.getTipoDeUsuario()==1){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionAdministrativo();
        }else if(usuario.getTipoDeUsuario()==2){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionProfesor();
        }
        
        try{
            String nombreBaseDeDatos = datosUsuario.getProperty("Direccion");
            String nombreUsuario = datosUsuario.getProperty("Usuario");
            String contrasenia = datosUsuario.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(nombreBaseDeDatos,nombreUsuario,contrasenia);
        }catch(SQLException excepcion){
            Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.WARNING, null, excepcion);
        }
        
        return conexion;
    }
    
    public Properties obtenerArchivoConexionAdministrativo(){
        Properties propiedadesAdministrativo = new Properties();
        
        try{
           DataInputStream archivoUsuario = new DataInputStream(new FileInputStream("src\\accesoADatos\\Administrativo.txt"));
           propiedadesAdministrativo.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.WARNING, null, archivoNoEncontrado);
        }catch(IOException excepcion){
            Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.WARNING, null, excepcion);
        }
        
        return propiedadesAdministrativo;
    }
    
    public Properties obtenerArchivoConexionProfesor(){
        Properties propiedadesProfesor = new Properties();
        
        try{
           DataInputStream archivoUsuario = new DataInputStream(new FileInputStream("src\\accesoADatos\\Profesor.txt"));
           propiedadesProfesor.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.WARNING, null, archivoNoEncontrado);
        }catch(IOException excepcion){
            Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.WARNING, null, excepcion);
        }
        
        return propiedadesProfesor;
    }
    
    public void cerrarConexion(Connection conexion){
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
    public static synchronized ManejadorBaseDeDatos getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorBaseDeDatos();
        }
        return instancia;
    }
}
