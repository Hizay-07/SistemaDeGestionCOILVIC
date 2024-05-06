package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.ProfesorExterno;
import logicaDeNegocio.interfaces.ProfesorExternoInterface;
import org.apache.log4j.Logger;

public class DAOProfesorExternoImplementacion implements ProfesorExternoInterface {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOProfesorExternoImplementacion.class);
    private Connection conexion;

    @Override
    public int registrarProfesorExterno(ProfesorExterno profesorExterno) {
        int numeroFilasAfectadas = 0;
        PreparedStatement declaracion;
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("INSERT INTO profesorexterno (idProfesor, idRepresentanteInstitucional)"
                    + "VALUES (?, ?);");
            declaracion.setInt(1, profesorExterno.getIdProfesor());
            declaracion.setInt(2, profesorExterno.getIdRepresentanteInstitucional());
            numeroFilasAfectadas = declaracion.executeUpdate();
            conexion.close();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return numeroFilasAfectadas;
    }

    @Override
    public List<ProfesorExterno> consultarProfesoresExternos() {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<ProfesorExterno> profesoresExternos = new ArrayList<>();
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("SELECT * FROM profesorexterno;");
            resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while (resultado.next()) {
                    ProfesorExterno profesorExterno = new ProfesorExterno();
                    profesorExterno.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    profesorExterno.setIdProfesor(resultado.getInt("idProfesor"));
                    profesorExterno.setIdRepresentanteInstitucional(resultado.getInt("idRepresentanteInstitucional"));
                    profesoresExternos.add(profesorExterno);
                }
            }
            conexion.close();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return profesoresExternos;
    }

    @Override
    public List<ProfesorExterno> consultarProfesoresExternosPorRepresentanteInstitucional(int idRepresentanteInstitucional) {
        PreparedStatement declaracion;
        ResultSet resultado;
        List<ProfesorExterno> profesoresExternos = new ArrayList<>();
        try {
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            declaracion = conexion.prepareStatement("SELECT * FROM profesorexterno WHERE idRepresentanteInstitucional=?;");
            declaracion.setInt(1, idRepresentanteInstitucional);
            resultado = declaracion.executeQuery();
            if(resultado.isBeforeFirst()){
                while (resultado.next()) {
                    ProfesorExterno profesorExterno = new ProfesorExterno();
                    profesorExterno.setIdProfesorExterno(resultado.getInt("idProfesorExterno"));
                    profesorExterno.setIdRepresentanteInstitucional(resultado.getInt("idRepresentanteInstitucional"));
                    profesoresExternos.add(profesorExterno);
                }
            }
            conexion.close();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return profesoresExternos;
    }
        
    @Override
    public int consultarIdRepresentanteInstitucionalPorIdProfesor(int idProfesor){
        CallableStatement declaracion;        
        int idProfesorExterno=0;
        try {
            conexion = BASE_DE_DATOS.getConexion();
            declaracion=conexion.prepareCall("CALL existeIdRepresentanteInstitucional(?,?);");            
            declaracion.setInt(1, idProfesor);
            declaracion.registerOutParameter(2,Types.INTEGER);
            declaracion.execute();
            idProfesorExterno=declaracion.getInt(2);
            conexion.close();
        } catch (SQLException excepcion) {
            LOG.error(excepcion.getCause());
        }
        return idProfesorExterno;                
    }
}
