package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.DataInputStream;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.enums.EnumTipoDeUsuario;

public class ManejadorBaseDeDatos {
    
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(ManejadorBaseDeDatos.class);
    private static ManejadorBaseDeDatos instancia;
    private Connection conexion;
    
    public Connection conectarBaseDeDatos(){
        UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
        Properties datosUsuario = new Properties();
        if(usuario.getTipoDeUsuario().equals(EnumTipoDeUsuario.Administrativo.toString())){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionAdministrativo();
        }else if(usuario.getTipoDeUsuario().equals(EnumTipoDeUsuario.Profesor.toString())){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionProfesor();
        }
        try{
            String nombreBaseDeDatos = datosUsuario.getProperty("Direccion");
            String nombreUsuario = datosUsuario.getProperty("Usuario");
            String contrasenia = datosUsuario.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(nombreBaseDeDatos,nombreUsuario,contrasenia);
        }catch(SQLSyntaxErrorException excepcion){
            LOG.fatal(excepcion.getCause());
            conexion = null;
        }catch(SQLException excepcion){
            LOG.fatal(excepcion.getCause());
            conexion = null;
        }
        return conexion;
    }
    
    public Connection conectarBaseDeDatosLogger(Usuario usuario){
        Properties datosLogger = new Properties();
        if(usuario.getTipoDeUsuario().equals(EnumTipoDeUsuario.Logger.toString())){
            datosLogger = new ManejadorBaseDeDatos().obtenerArchivoConexionLogger();
        }
        try{
            String nombreBaseDeDatos = datosLogger.getProperty("Direccion");
            String nombreUsuario = datosLogger.getProperty("Usuario");
            String contrasenia = datosLogger.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(nombreBaseDeDatos,nombreUsuario,contrasenia);
        }catch(SQLSyntaxErrorException excepcion){
            LOG.fatal(excepcion.getCause());
            conexion = null;
        }catch(SQLException excepcion){
            LOG.fatal(excepcion.getCause());
            conexion = null;
        }
        return conexion;
    }
    
    public Properties obtenerArchivoConexionAdministrativo(){
        Properties propiedadesAdministrativo = new Properties();
        try{
           DataInputStream archivoUsuario = new DataInputStream(new FileInputStream("src\\accesoADatos\\Administrativo.txt"));
           propiedadesAdministrativo.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            LOG.fatal(archivoNoEncontrado.getMessage());
        }catch(IOException excepcion){
            LOG.fatal(excepcion.getCause());
        }
        return propiedadesAdministrativo;
    }
    
    public Properties obtenerArchivoConexionLogger(){
        Properties propiedadesLogger = new Properties();
          try{
           DataInputStream archivoUsuario = new DataInputStream(new FileInputStream("src\\accesoADatos\\Logger.txt"));
           propiedadesLogger.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.WARNING, null, archivoNoEncontrado);
        }catch(IOException excepcion){
            LOG.fatal(excepcion.getCause());
        }
        return propiedadesLogger;
    }
    
    public Properties obtenerArchivoConexionProfesor(){
        Properties propiedadesProfesor = new Properties();
        try{
           DataInputStream archivoUsuario = new DataInputStream(new FileInputStream("src\\accesoADatos\\Profesor.txt"));
           propiedadesProfesor.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            LOG.fatal(archivoNoEncontrado.getCause());
        }catch(IOException excepcion){
            LOG.fatal(excepcion.getCause());
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
                LOG.fatal(excepcion.getCause());
            }
        }
    }
    
}
