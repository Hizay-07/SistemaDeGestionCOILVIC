package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import logicaDeNegocio.clases.EmisionPropuesta;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.interfaces.EmisionPropuestaInterface;

public class DAOEmisionPropuestaImplementacion implements EmisionPropuestaInterface {
    
    private static final Logger LOG=Logger.getLogger(DAOEmisionPropuestaImplementacion.class);
    private static final ManejadorBaseDeDatos BASE_DE_DATOS=new ManejadorBaseDeDatos();
    
    @Override
    public int registrarEmisionPropuesta(EmisionPropuesta emisionPropuesta) {
        int numeroFilasAfectadas=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("INSERT INTO EmisionPropuesta (idProfesor,idPropuestaColaboracion,fechaEmision) VALUES (?,?,?);")){
            declaracion.setInt(1, emisionPropuesta.getIdProfesor());
            declaracion.setInt(2, emisionPropuesta.getIdPropuestaColaboracion());
            declaracion.setString(3, emisionPropuesta.getFechaEmision());
            numeroFilasAfectadas=declaracion.executeUpdate();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            numeroFilasAfectadas = -1;
        }
        return numeroFilasAfectadas;                
    }

    @Override
    public List<EmisionPropuesta> consultarEmisionesDePropuestas() {
        ResultSet resultado;
        List<EmisionPropuesta> emisionesPropuesta=new ArrayList<>();
        try(Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT * from EmisionPropuesta;")){
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    EmisionPropuesta emisionPropuesta=new EmisionPropuesta();
                    emisionPropuesta.setIdProfesor(resultado.getInt("idProfesor"));
                    emisionPropuesta.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                    emisionPropuesta.setFechaEmision(resultado.getString("fechaEmision"));
                    emisionesPropuesta.add(emisionPropuesta);
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
        }
        return emisionesPropuesta;        
    }

    @Override
    public int consultarIdProfesorPorIdPropuestaColaboracion(int idPropuestaColaboracion) {
        ResultSet resultado;
        int idProfesor=0;
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idProfesor from EmisionPropuesta where idPropuestaColaboracion=?;")){
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idProfesor=resultado.getInt("idProfesor");
                }
            }
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());
            idProfesor=-1;
        }
        return idProfesor;        
    }
    
    @Override
    public List<Integer> consultarIdPropuestaDeColaboracionPorIdProfesor(Profesor profesor){
        ResultSet resultado;        
        List<Integer> idPropuestas=new ArrayList<>();
        try (Connection conexion=BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement declaracion=conexion.prepareStatement("SELECT idPropuestaColaboracion from EmisionPropuesta where idProfesor=?;")){
            declaracion.setInt(1, profesor.getIdProfesor());
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){                    
                    int idPropuestaColaboracion=resultado.getInt("idPropuestaColaboracion");
                    idPropuestas.add(idPropuestaColaboracion);
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getMessage());            
        }
        return idPropuestas;
    }

}
