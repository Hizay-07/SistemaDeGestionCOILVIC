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
import static org.junit.Assert.*;

public class ManejadorDeArchivosPrueba {

    private static final Logger LOG = Logger.getLogger(ManejadorDeArchivosPrueba.class);

    @Test
    public void testCrearCarpetaDeActividadExitoso() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        boolean resultado = manejador.crearCarpetaDeActividad(actividad, colaboracion);
        assertTrue(resultado);
        File carpeta = new File("Colaboraciones/Colaboracion1/Actividad1");
        assertTrue(carpeta.exists());
        carpeta.delete();
        carpeta.getParentFile().delete();
        carpeta.getParentFile().getParentFile().delete();
    }

    @Test
    public void pruebaCrearCarpetaDeActividadFallida() {
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(-1); 
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        boolean resultado = manejador.crearCarpetaDeActividad(actividad, colaboracion);
        assertFalse(resultado);
    }

    @Test
    public void pruebaGuardarSyllabusDeColaboracionExitosa() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoSyllabus = new File("C:\\Users\\hizza\\Desktop\\RRRRR\\syllabus.pdf");
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        int resultado = manejador.guardarSyllabusDeColaboracion(colaboracion, archivoSyllabus);
        assertEquals(1, resultado);
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
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1); 
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1); 
        File archivoEvidencia = new File("C:\\Users\\hizza\\Desktop\\RRRRR\\evidenciaII.txt");
        int numeroDeEvidencias = 1;
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        String resultado = manejador.guardarEvidenciaDeActividad(actividad, colaboracion, archivoEvidencia,numeroDeEvidencias);
        assertNotNull(resultado);
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
    public void pruebaBorrarArchivoDeEvidenciaExitosa() {
        String rutaEvidencia = "C:\\Users\\hizza\\Desktop\\RRRRR\\evidenciauno.txt";
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
