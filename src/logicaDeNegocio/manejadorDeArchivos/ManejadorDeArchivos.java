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
import logicaDeNegocio.clases.ProfesorSingleton;
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
    
    public boolean validarExistenciaDeArchivo(Actividad actividad, Colaboracion colaboracion, File archivoNuevo, int idEvidencia){
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        String nuevoNombre = profesor.getNombre()+"_"+profesor.getApellidoPaterno()+"_"+profesor.getApellidoMaterno()+"_"+archivoNuevo.getName();
        String rutaAGuardarArchivo = "Colaboraciones/Colaboracion"+colaboracion.getIdColaboracion()+"/Actividad"+actividad.getIdActividad()+"/Evidencia/"+nuevoNombre;
        Path rutaAGuardar = Paths.get(rutaAGuardarArchivo);
        boolean resultado = true;
        if(Files.exists(rutaAGuardar)){
            resultado = true;
        }else{
            resultado = false;
        }
        return resultado;
    }

    public String guardarEvidenciaDeActividad(Actividad actividad, Colaboracion colaboracion, File archivoNuevo, int idEvidencia) {
        ProfesorSingleton profesor = ProfesorSingleton.getInstancia();
        String rutaDeRegistro="";
        String rutaOriginal = archivoNuevo.getAbsolutePath();
        String nuevoNombre = profesor.getNombre()+"_"+profesor.getApellidoPaterno()+"_"+profesor.getApellidoMaterno()+"_"+archivoNuevo.getName();
        String rutaDeDestino = "Colaboraciones/Colaboracion"+colaboracion.getIdColaboracion()+"/Actividad"+actividad.getIdActividad()+"/Evidencia/"+nuevoNombre;
        Path rutaDeArchivoOriginal = Paths.get(rutaOriginal);
        Path rutaArchivoDeDestino = Paths.get(rutaDeDestino);
        try{
            Files.createDirectories(rutaArchivoDeDestino.getParent());
            Files.move(rutaDeArchivoOriginal, rutaArchivoDeDestino, StandardCopyOption.REPLACE_EXISTING);
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
