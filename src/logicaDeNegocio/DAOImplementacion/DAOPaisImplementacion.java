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

    
    @Override
    public int registrarPais(Pais paisAIngresar){
        int resultadoRegistro;        
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO pais(nombrePais) values (?)");
            sentencia.setString(1, paisAIngresar.getNombrePais());
            resultadoRegistro = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoRegistro = -1;
        }        
        return resultadoRegistro;
    }
    
    @Override
    public int obtenerNumeroDePais(Pais paisAConsultar){
        int paisObtenido=0;   
        try{
           Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
           PreparedStatement sentencia = conexion.prepareStatement("SELECT numeroDePais FROM pais WHERE nombrePais = ?");
           sentencia.setString(1, paisAConsultar.getNombrePais());
           ResultSet numeroPaisObtenido = sentencia.executeQuery();
           if(numeroPaisObtenido.isBeforeFirst()){
                while(numeroPaisObtenido.next()){
                  paisObtenido = (int)numeroPaisObtenido.getObject(1);  
                }
           }
           BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getMessage());
            paisObtenido = -1;
        }
        return paisObtenido;
    }
    
    @Override
    public List<Pais> consultarPaises(){
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Pais> paises=new ArrayList<>();
        try {
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion=conexion.prepareStatement("SELECT * from Pais;");
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                Pais pais=new Pais();
                pais.setNombrePais(resultado.getString("nombrePais"));
                pais.setNumeroDePais(resultado.getInt("numeroDePais"));
                paises.add(pais);
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }         
        return paises;
    }
    
    @Override
    public int verificarPais(){
        int resultadoVerificacion=0;
        ResultSet resultado;
        try{
            Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select count(*) from pais");
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                resultadoVerificacion=resultado.getInt(1);                
            }        
            conexion.close();
        }catch(SQLException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoVerificacion=-1;
        }
        return resultadoVerificacion;            
    }
}
