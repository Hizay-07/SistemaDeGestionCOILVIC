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
    public void testCrearCarpetaDeActividadFallido() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        boolean resultado = false;
        try {
            resultado = manejador.crearCarpetaDeActividad(actividad, colaboracion);
        } catch (SecurityException e) {
            LOG.error(e.getCause());
        }
        assertFalse(resultado);
    }

    @Test
    public void testGuardarSyllabusDeColaboracionExitoso() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoSyllabus = new File("syllabus_test.pdf");
        try {
            archivoSyllabus.createNewFile();
        } catch (IOException e) {
            LOG.error(e.getCause());
        }
        int resultado = manejador.guardarSyllabusDeColaboracion(colaboracion, archivoSyllabus);
        assertEquals(1, resultado);
        File archivoCopiado = new File("Colaboraciones/Colaboracion1/syllabus_test.pdf");
        assertTrue(archivoCopiado.exists());
        archivoCopiado.delete();
        archivoCopiado.getParentFile().delete();
        archivoCopiado.getParentFile().getParentFile().delete();
        archivoSyllabus.delete();
    }

    @Test
    public void testGuardarSyllabusDeColaboracionFallido() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoSyllabus = new File("syllabus_test.pdf");
        int resultado = -1;
        try {
            resultado = manejador.guardarSyllabusDeColaboracion(colaboracion, archivoSyllabus);
        } catch (Exception e) {
            LOG.error(e.getCause());
        }
        assertNotEquals(-1, resultado);
    }

    @Test
    public void testGuardarEvidenciaDeActividadExitoso() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoNuevo = new File("evidencia_test.pdf");
        try {
            archivoNuevo.createNewFile();
        } catch (IOException e) {
            LOG.error(e.getCause());
        }
        String ruta = manejador.guardarEvidenciaDeActividad(actividad, colaboracion, archivoNuevo);
        assertNotNull(ruta);
        assertFalse(ruta.isEmpty());
        File archivoCopiado = new File(ruta);
        assertTrue(archivoCopiado.exists());
        archivoCopiado.delete();
        archivoCopiado.getParentFile().delete();
        archivoCopiado.getParentFile().getParentFile().delete();
        archivoNuevo.delete();
    }

    @Test
    public void testGuardarEvidenciaDeActividadFallido() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        File archivoNuevo = new File("evidencia_test.pdf");
        String ruta = "";
        try {
            ruta = manejador.guardarEvidenciaDeActividad(actividad, colaboracion, archivoNuevo);
        } catch (Exception e) {
            LOG.error(e.getCause());
        }
        assertTrue(ruta.isEmpty());
    }

    @Test
    public void testBorrarArchivoDeEvidenciaExitoso() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        File archivoAEliminar = new File("evidencia_a_borrar.pdf");
        try {
            archivoAEliminar.createNewFile();
        } catch (IOException e) {
            LOG.error(e.getCause());
        }
        int resultado = manejador.borrarArchivoDeEvidencia(archivoAEliminar.getAbsolutePath());
        assertEquals(1, resultado);
        assertFalse(archivoAEliminar.exists());
    }

    @Test
    public void testBorrarArchivoDeEvidenciaFallido() {
        ManejadorDeArchivos manejador = new ManejadorDeArchivos();
        String rutaInvalida = "ruta/invalida/evidencia_a_borrar.pdf";
        int resultado = -1;
        try {
            resultado = manejador.borrarArchivoDeEvidencia(rutaInvalida);
        } catch (Exception e) {
            LOG.error(e.getCause());
        }
        assertEquals(-1, resultado);
    }
}
