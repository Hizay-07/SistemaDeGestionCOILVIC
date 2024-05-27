package pruebaLogicaDeNegocio.InformeImplementacion;

import java.io.File;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.manejadorDeArchivos.ManejadorDeArchivos;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ManejadorDeArchivosImplementacion {
     @Test
    public void pruebaCrearCarpetaDeActividadesExistosa(){
        ManejadorDeArchivos pruebaDeMetodo = new ManejadorDeArchivos();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        boolean resultadoObtenido = pruebaDeMetodo.crearCarpetaDeActividad(actividad, colaboracionPrueba);
        assertEquals(true,resultadoObtenido);
    }
    
    @Test
    public void pruebaCrearCarpetaDeActividadesFallida() {
         ManejadorDeArchivos pruebaDeMetodo = new ManejadorDeArchivos();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        Actividad actividad = new Actividad();
        actividad.setIdActividad(99);
        boolean resultadoObtenido = pruebaDeMetodo.crearCarpetaDeActividad(actividad, colaboracionPrueba);
        assertEquals(false, resultadoObtenido);
}
    
    @Test
    public void pruebaGuardarEvidenciaDeActividadExitosa(){
         ManejadorDeArchivos pruebaDeMetodo = new ManejadorDeArchivos();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        File archivoAGuardar = new File("C:\\Users\\chris\\Downloads\\ModeloDeEstandarizacion.docx");
        String resultadoEsperado = "Colaboraciones/Colaboracion1/Actividad1/ModeloDeEstandarizacion.docx";
        String resultadoObtenido = pruebaDeMetodo.guardarEvidenciaDeActividad(actividad, colaboracionPrueba, archivoAGuardar);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void pruebaGuardarEvidenciaDeActividadFallida() {
         ManejadorDeArchivos pruebaDeMetodo = new ManejadorDeArchivos();
        Colaboracion colaboracionPrueba = new Colaboracion();
        colaboracionPrueba.setIdColaboracion(1);
        Actividad actividad = new Actividad();
        actividad.setIdActividad(1);
        File archivoAGuardar = new File("C:\\Users\\chris\\Downloads\\ModeloDeEstandarizacion.docx");
        String resultadoEsperado = "Colaboraciones/Colaboracion2/Actividad1/ModeloDeEstandarizacion.docx";
        String resultadoObtenido = pruebaDeMetodo.guardarEvidenciaDeActividad(actividad, colaboracionPrueba, archivoAGuardar);
        assertEquals(resultadoEsperado, resultadoObtenido);
}
    
    @Test
    public void prebaBorrarArchivoDeEvidenciaExitosa(){
         ManejadorDeArchivos pruebaDeMetodo = new ManejadorDeArchivos();
        String rutaArchivoABorrar = "C:\\Users\\chris\\Escritorio\\SistemaDeGestionCOILVIC\\Colaboraciones\\Colaboracion2\\Actividad11\\GuiaEstandares.doc";
        int resultadoEsperado = 1;
        int resultadoObtenido = pruebaDeMetodo.borrarArchivoDeEvidencia(rutaArchivoABorrar);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void prebaBorrarArchivoDeEvidenciaFallida() {
        ManejadorDeArchivos pruebaDeMetodo = new ManejadorDeArchivos();
        String rutaArchivoABorrar = "C:\\Users\\chris\\Escritorio\\SistemaDeGestionCOILVIC\\Colaboraciones\\Colaboracion2\\Actividad11\\GuiaEstandares.doc";
        int resultadoEsperado = 0;
        int resultadoObtenido = pruebaDeMetodo.borrarArchivoDeEvidencia(rutaArchivoABorrar);
        assertEquals(resultadoEsperado, resultadoObtenido);
    }
}
