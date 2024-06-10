package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.interfaces.PaisInterface;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class DAOPaisImplementacion implements PaisInterface {    
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOPaisImplementacion.class);

    /**
    *Obtener el numero de un país registrado en la base de datos
    *@param paisAConsultar Pais del cual se desea obtener su numero registrado en
    * la base de datos
    *@return Regresa el numero de pais asignado en la base de datos
    **/
    @Override
    public int obtenerNumeroDePais(Pais paisAConsultar){
        int paisObtenido=0;   
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement sentencia = conexion.prepareStatement("SELECT numeroDePais FROM pais WHERE nombrePais = ?")){
           sentencia.setString(1, paisAConsultar.getNombrePais());
           ResultSet numeroPaisObtenido = sentencia.executeQuery();
           if(numeroPaisObtenido.isBeforeFirst()){
                while(numeroPaisObtenido.next()){
                  paisObtenido = (int)numeroPaisObtenido.getObject(1);  
                }
           }
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            paisObtenido = -1;
        }
        return paisObtenido;
    }
    
    /**
    *Obtener la lista de paises registrados en la base de datos
    *@return Regresa la lista de paises registrados en la base de datos
    **/
    @Override
    public List<Pais> consultarPaises(){
        ResultSet resultado;
        List<Pais> paises=new ArrayList<>();
        try (Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * from Pais;")){
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                Pais pais=new Pais();
                pais.setNombrePais(resultado.getString("nombrePais"));
                pais.setNumeroDePais(resultado.getInt("numeroDePais"));
                paises.add(pais);
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }         
        return paises;
    }
    
    /**
    *Obtener el número de paises registrados en la base de datos
    *@return Regresa el número de paises registrados en la base de datos
    **/
    @Override
    public int verificarPais(){
        int resultadoVerificacion=0;
        ResultSet resultado;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select count(*) from pais")){
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                resultadoVerificacion=resultado.getInt(1);                
            }        
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;            
    }
    
}
