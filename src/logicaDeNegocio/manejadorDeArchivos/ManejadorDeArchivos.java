package logicaDeNegocio.manejadorDeArchivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import logicaDeNegocio.DAOImplementacion.DAOEvidenciaImplementacion;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import org.apache.log4j.Logger;

public class ManejadorDeArchivos {
    
    private static final Logger LOG=Logger.getLogger(ManejadorDeArchivos.class);
    
    public boolean crearCarpetaDeActividad(Actividad actividad,Colaboracion colaboracion) {
        boolean resultadoCreacionDeCarpeta = true;
        String rutaCarpeta = "Colaboraciones\\Colaboracion"+colaboracion.getIdColaboracion()+"\\Actividad"+actividad.getIdActividad();
        File carpetaActividad = new File(rutaCarpeta);
        try{
            if(!carpetaActividad.exists()){
                resultadoCreacionDeCarpeta = carpetaActividad.mkdirs();
            }
        }catch(SecurityException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoCreacionDeCarpeta = false;
        }
        return resultadoCreacionDeCarpeta;
    }
    
    public int guardarSyllabusDeColaboracion(Colaboracion colaboracion,File archivoSyllabus){
        int resultadoGuardoDeSyllabus = 0;
        String rutaOriginal = archivoSyllabus.getAbsolutePath();
        String rutaDeDestino = "Colaboraciones/Colaboracion"+colaboracion.getIdColaboracion()+"/"+archivoSyllabus.getName();
        Path rutaDeArchivoOriginal = Paths.get(rutaOriginal);
        Path rutaArchivoDeDestino = Paths.get(rutaDeDestino);
        try{
            Files.copy(rutaDeArchivoOriginal, rutaArchivoDeDestino, StandardCopyOption.REPLACE_EXISTING);
            resultadoGuardoDeSyllabus = 1;
        }catch(IOException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoGuardoDeSyllabus = -1;
        }
        return resultadoGuardoDeSyllabus;
    }

    public String guardarEvidenciaDeActividad(Actividad actividad, Colaboracion colaboracion, File archivoNuevo, int idEvidencia) {
        String rutaDeRegistro="";
        String rutaOriginal = archivoNuevo.getAbsolutePath();
        String rutaDeDestino = "Colaboraciones/Colaboracion"+colaboracion.getIdColaboracion()+"/Actividad"+actividad.getIdActividad()+"/Evidencia"+idEvidencia+"/"+archivoNuevo.getName();
        Path rutaDeArchivoOriginal = Paths.get(rutaOriginal);
        Path rutaArchivoDeDestino = Paths.get(rutaDeDestino);
        try{
            Files.createDirectories(rutaArchivoDeDestino.getParent());
            Files.copy(rutaDeArchivoOriginal, rutaArchivoDeDestino, StandardCopyOption.REPLACE_EXISTING);
            rutaDeRegistro = rutaDeDestino;
        }catch(IOException excepcion){
            LOG.error(excepcion.getCause());
        }
        return rutaDeRegistro;
    }

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
