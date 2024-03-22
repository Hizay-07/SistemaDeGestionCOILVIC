package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.interfaces.PaisInterface;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOPaisImplementacion implements PaisInterface {
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    
    @Override
    public int registrarPais(Pais paisAIngresar){
        int resultadoRegistro;
        
        try{
            Connection conexion = BASE_DE_DATOS.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO pais(nombrePais) values (?)");
            sentencia.setString(1, paisAIngresar.getNombrePais());
            resultadoRegistro = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAOPaisImplementacion.class.getName()).log(Level.SEVERE, null, excepcion);
            resultadoRegistro = -1;
        }
        
        return resultadoRegistro;
    }
    
    @Override
    public int obtenerNumeroDePais(Pais paisAConsultar){
        int paisObtenido=0;
        
        try{
           Connection conexion = BASE_DE_DATOS.getConexion();
           PreparedStatement sentencia = conexion.prepareStatement("SELECT numeroDePais FROM pais WHERE nombrePais = ?");
           sentencia.setString(1, paisAConsultar.getNombrePais());
           ResultSet numeroPaisObtenido = sentencia.executeQuery();
           
           while(numeroPaisObtenido.next()){
             paisObtenido = (int)numeroPaisObtenido.getObject(1);  
           }
           
           BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException excepcion){
            Logger.getLogger(DAOPaisImplementacion.class.getName()).log(Level.SEVERE, excepcion.getMessage(), excepcion);
            paisObtenido = -1;
        }
        return paisObtenido;
    }
    
}
