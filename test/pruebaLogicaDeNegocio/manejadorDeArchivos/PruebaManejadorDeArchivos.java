package pruebaLogicaDeNegocio.manejadorDeArchivos;

import logicaDeNegocio.manejadorDeArchivos.ManejadorDeArchivos;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import org.apache.log4j.Logger;
import org.junit.Test;
import java.io.File;
import java.io.IOException; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.ProfesorSingleton;
import static org.junit.Assert.*;
import org.junit.Before;

public class PruebaManejadorDeArchivos {
    
    @Before
    public void inicializar() {
        Profesor profesor = new Profesor();
        profesor.setNombre("Beto");
        profesor.setApellidoPaterno("Gómez");
        profesor.setApellidoMaterno("Pérez");
        profesor.setCorreo("profesorpruebaunocambio@gmail.com");
        profesor.setEstado("Esperando");
        profesor.setIdProfesor(2);
        ProfesorSingleton.setInstancia(profesor);
    }

    private static final Logger LOG = Logger.getLogger(PruebaManejadorDeArchivos.class);

    @Test
    public void pruebaCrearCarpetaDeActividadExitoso() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Actividad actividad = new Actividad();
        actividad.setIdActividad(5);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        boolean resultado = manejador.crearCarpetaDeActividad(actividad, colaboracion);
        assertTrue(resultado);
        File carpeta = new File("Colaboraciones/Colaboracion1/Actividad5");
        assertTrue(carpeta.exists());
        carpeta.delete();
        carpeta.getParentFile().delete();
        carpeta.getParentFile().getParentFile().delete();
    }

    @Test (expected = AssertionError.class)
    public void pruebaCrearCarpetaDeActividadFallida() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        boolean resultado = manejador.crearCarpetaDeActividad(actividad, colaboracion);
        assertTrue(resultado);
        File carpeta = new File("Colaboraciones/Colaboracion1/ActividadU");
        assertTrue(carpeta.exists());
        carpeta.delete();
        carpeta.getParentFile().delete();
        carpeta.getParentFile().getParentFile().delete();
    }

    @Test
    public void pruebaGuardarSyllabusDeColaboracionExitosa() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoSyllabus = new File("Colaboraciones/Colaboracion1/syllabusprueba.pdf");
        try {
            archivoSyllabus.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int resultado = manejador.guardarSyllabusDeColaboracion(colaboracion, archivoSyllabus);
        assertEquals(1, resultado);
        archivoSyllabus.delete();
    }

    @Test
    public void pruebaGuardarSyllabusDeColaboracionFallida() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoSyllabus = new File("ruta/no/existente/syllabus.pdf"); 
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        int resultado = manejador.guardarSyllabusDeColaboracion(colaboracion, archivoSyllabus);
        assertEquals(-1, resultado);
    }

    @Test
    public void pruebaGuardarEvidenciaDeActividadExitosa() {
        String rutaEsperada = "Colaboraciones/Colaboracion1/Actividad4/Evidencia/Beto_Gómez_Pérez_evidencia4.txt";
        Actividad actividad = new Actividad();
        actividad.setIdActividad(4); 
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        File archivoEvidencia = new File("C:\\Users\\hizza\\Desktop\\RRRRR\\evidencia4.txt");
        int numeroDeEvidencias = 1;        
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        String resultado = manejador.guardarEvidenciaDeActividad(actividad, colaboracion, archivoEvidencia,numeroDeEvidencias);
        assertEquals(rutaEsperada, resultado);
    }

    @Test
    public void pruebaGuardarEvidenciaDeActividadFallida() {
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoEvidencia = new File("ruta/no/existente/evidencia1.txt");
        int numeroDeEvidencias = 1;
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        String resultado = manejador.guardarEvidenciaDeActividad(actividad, colaboracion, archivoEvidencia,numeroDeEvidencias);
        assertEquals("", resultado);
    }
    
    
    @Test
    public void pruebaValidarExistenciaDeArchivoExitoso() {
        Actividad actividad = new Actividad();
        actividad.setIdActividad(4); 
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        File archivoEvidencia = new File("Colaboraciones/Colaboracion1/Actividad4/Evidencia/evidencia4.txt");
        int idEvidencia = 1;        
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        boolean resultado = manejador.validarExistenciaDeArchivo(actividad, colaboracion, archivoEvidencia, idEvidencia);
        assertTrue(resultado);
    }


    @Test
    public void pruebaValidarExistenciaDeArchivoFallido() {
        Actividad actividad = new Actividad();
        actividad.setIdActividad(4); 
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        File archivoEvidencia = new File("Colaboraciones/Colaboracion1/Actividad4/Evidencia/Beto_Gómez_Pérez_evidencia_inexistente.txt");
        int idEvidencia = 1;        
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        boolean resultado = manejador.validarExistenciaDeArchivo(actividad, colaboracion, archivoEvidencia, idEvidencia);
        assertFalse(resultado);
    }

    @Test
    public void pruebaBorrarArchivoDeEvidenciaExitosa() {
        String rutaEvidencia = "Colaboraciones/Colaboracion1/Actividad4/Evidencia/Beto_Gómez_Pérez_evidencia2.txt";
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        int resultado = manejador.borrarArchivoDeEvidencia(rutaEvidencia);
        assertEquals(1, resultado);
    }

    @Test (expected = AssertionError.class)
    public void pruebaBorrarArchivoDeEvidenciaFallida() {
        String rutaEvidencia = "ruta/no/existente/evidencia1.txt";
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        int resultado = manejador.borrarArchivoDeEvidencia(rutaEvidencia);
        assertEquals(-1, resultado);
    }
}
