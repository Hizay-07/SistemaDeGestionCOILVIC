package logicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.Evidencia;
import logicaDeNegocio.interfaces.EvidenciaInterface;
import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Actividad;
import org.apache.log4j.Logger;


public class DAOEvidenciaImplementacion implements EvidenciaInterface{
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final Logger LOG=Logger.getLogger(DAOEvidenciaImplementacion.class);
    private Connection conexion;
    
    @Override
    public int agregarEvidencia(Evidencia evidencia){
        int resultadoInsercion;
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            CallableStatement sentencia = conexion.prepareCall("call registrarEvidencia(?,?,?,?)");
            sentencia.setString(1, evidencia.getNombre());
            sentencia.setString(2, evidencia.getRutaEvidencia());
            sentencia.setInt(3, evidencia.getIdActividad());
            sentencia.registerOutParameter(4,Types.INTEGER);
            sentencia.execute();
            resultadoInsercion = sentencia.getInt(4);
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoInsercion = -1;
        }
        return resultadoInsercion;
    }

    @Override
    public int modificarEvidencia(Evidencia evidenciaNueva) {
        int resultadoModificacion;
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Update evidencia set nombre = ?, ruta = ? where idEvidencia = ?");
            sentencia.setString(1, evidenciaNueva.getNombre());
            sentencia.setString(2, evidenciaNueva.getRutaEvidencia());
            sentencia.setInt(3, evidenciaNueva.getIdEvidencia());
            resultadoModificacion = sentencia.executeUpdate();
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
            resultadoModificacion = -1;
        }
        return resultadoModificacion;
    }

    @Override
    public List<Evidencia> obtenerEvidenciasDeActividad(int idActividad){
        List<Evidencia> evidenciasDeActividad = new ArrayList();
        try{
            conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select * from evidencia where Actividad_idActividad = ?");
            sentencia.setInt(1, idActividad);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Evidencia evidenciaObtenida = new Evidencia();
                    evidenciaObtenida.setIdEvidencia(resultado.getInt(1));
                    evidenciaObtenida.setNombre(resultado.getString(2));
                    evidenciaObtenida.setRutaEvidencia(resultado.getString(3));
                    evidenciaObtenida.setIdActividad(resultado.getInt(4));
                    evidenciasDeActividad.add(evidenciaObtenida);
                }
            }
            BASE_DE_DATOS.cerrarConexion(conexion);
        }catch(SQLException | NullPointerException excepcion){
            LOG.error(excepcion.getCause());
        }
        return evidenciasDeActividad;
    }

    @Override
    public boolean crearCarpetaDeActividad(Actividad actividad,Colaboracion colaboracion) {
        boolean resultadoCreacionDeCarpeta = true;
        String rutaCarpeta = "Colaboraciones\\Colaboracion"+colaboracion.getIdColaboracion()+"\\Actividad"+actividad.getIdActividad();
        File carpetaActividad = new File(rutaCarpeta);
        try{
            if(!carpetaActividad.exists()){
                resultadoCreacionDeCarpeta = carpetaActividad.mkdirs();
            }
        }catch(SecurityException excepcion){
            LOG.error(excepcion.getCause());
            resultadoCreacionDeCarpeta = false;
        }
        return resultadoCreacionDeCarpeta;
    }

    @Override
    public String guardarEvidenciaDeActividad(Actividad actividad, Colaboracion colaboracion, File archivoNuevo) {
        String rutaDeRegistro="";
        String rutaOriginal = archivoNuevo.getAbsolutePath();
        String rutaDeDestino = "Colaboraciones/Colaboracion"+colaboracion.getIdColaboracion()+"/Actividad"+actividad.getIdActividad()+"/"+archivoNuevo.getName();
        Path rutaDeArchivoOriginal = Paths.get(rutaOriginal);
        Path rutaArchivoDeDestino = Paths.get(rutaDeDestino);
        try{
            Files.copy(rutaDeArchivoOriginal, rutaArchivoDeDestino, StandardCopyOption.REPLACE_EXISTING);
            rutaDeRegistro = rutaDeDestino;
        }catch(IOException excepcion){
            LOG.error(excepcion.getCause());
        }
        return rutaDeRegistro;
    }

    @Override
    public int borrarArchivoDeEvidencia(String rutaEvidencia) {
       int resultadoEliminacion = 0;
       File archivoAEliminar = new File(rutaEvidencia);
       try{
           archivoAEliminar.delete();
           resultadoEliminacion = 1;
       }catch(SecurityException excepcion){
           LOG.error(excepcion.getCause());
           resultadoEliminacion = -1;
       }
       return resultadoEliminacion;
    }
    
    
}
