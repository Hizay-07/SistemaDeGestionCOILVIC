package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.PeticionColaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.interfaces.PeticionColaboracionInterface;
import org.apache.log4j.Logger;

public class DAOPeticionColaboracionImplementacion implements PeticionColaboracionInterface {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOPeticionColaboracionImplementacion.class);

    private Connection conexion;

    @Override
    public int registrarPeticionColaboracion(PeticionColaboracion peticion) {
        int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("INSERT INTO peticioncolaboracion (idProfesor, idPropuestaColaboracion, estadoPeticion, fechaEnvio)"
                    + "VALUES (?, ?, ?, ?);");
            declaracion.setInt(1, peticion.getIdProfesor());
            declaracion.setInt(2, peticion.getIdPropuestaColaboracion());
            declaracion.setString(3, peticion.getEstado());
            declaracion.setString(4, peticion.getFechaEnvio());
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return numeroFilasAfectadas;
    }
    
    @Override
    public List<PeticionColaboracion> consultarPeticiones() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<PeticionColaboracion> peticiones = new ArrayList<>();
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("SELECT * FROM peticioncolaboracion");
            resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while (resultado.next()) {
                    PeticionColaboracion peticion = new PeticionColaboracion();
                    peticion.setIdProfesor(resultado.getInt("idProfesor"));
                    peticion.setIdPropuestaColaboracion(resultado.getInt("idPropuestaColaboracion"));
                    peticion.setEstado(resultado.getString("estadoPeticion"));
                    peticion.setFechaEnvio(resultado.getString("fechaEnvio"));
                    peticiones.add(peticion);
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return peticiones;
    }

    @Override
    public int aceptarColaboracion(int idColaboracion, String nuevoEstado) {
        int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("UPDATE peticioncolaboracion SET estadoPeticion=? WHERE idColaboracion=?;");
            declaracion.setString(1, nuevoEstado);
            declaracion.setInt(2, idColaboracion);
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return numeroFilasAfectadas;
    }

    @Override
    public int rechazarColaboracion(int idColaboracion, String nuevoEstado) {
        int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion = conexion.prepareStatement("UPDATE peticioncolaboracion SET estadoPeticion=? WHERE idColaboracion=?;");
            declaracion.setString(1, nuevoEstado);
            declaracion.setInt(2, idColaboracion);
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return numeroFilasAfectadas;
    }
    
    @Override
    public int consultarIdPropuestaDeColaboracionPorIdProfesor(Profesor profesor){
        PreparedStatement declaracion;
        ResultSet resultado;
        int idPropuestaColaboracion=0;
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("SELECT idPropuestaColaboracion from PeticionColaboracion where idProfesor=?;");
            declaracion.setInt(1, profesor.getIdProfesor());
            resultado=declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    idPropuestaColaboracion=resultado.getInt("idPropuestaColaboracion");
                }
            }
            conexion.close();
        } catch (SQLException | NullPointerException excepcion) {
            LOG.error(excepcion.getCause());
            idPropuestaColaboracion=-1;
        }
        return idPropuestaColaboracion;
    }
    
    public List<Integer> consultarIdProfesoresPorIdPropuestaColaboracion(int idPropuestaColaboracion){
        PreparedStatement declaracion;
        ResultSet resultado;
        List<Integer> idProfesores=new ArrayList<>();
        try {
            conexion=BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareStatement("Select idProfesor from peticionColaboracion where idPropuestaColaboracion=?;");
            declaracion.setInt(1, idPropuestaColaboracion);
            resultado=declaracion.executeQuery();
            while(resultado.next()){
                int idProfesor=resultado.getInt("idProfesor");
                idProfesores.add(idProfesor);                
            }
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPeticionColaboracionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idProfesores;                        
    }
}
