/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebaLogicaDeNegocio.manejadorDeArchivos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.clases.Actividad;
import logicaDeNegocio.clases.Colaboracion;
import logicaDeNegocio.clases.Profesor;
import logicaDeNegocio.clases.PropuestaColaboracion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.manejadorDeArchivos.InformeImplementacion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class InformeImplemenracionPrueba {
    @Test
    public void testCrearInformeDeColaboracionExitoso() {
        InformeImplementacion informe = new InformeImplementacion();
        Colaboracion colaboracion = new Colaboracion(); 
        List<Actividad> actividades = new ArrayList<>();
        List<Profesor> profesores = new ArrayList<>();
        Document result = informe.crearInformeDeColaboracion(colaboracion, actividades, profesores);   
        assertNotNull(result);
        File file = new File("Informes/informeDeColaboracion" + colaboracion.getIdColaboracion() + ".pdf");
        assertTrue(file.exists());
    }
    
    @Test
    public void testCrearInformeDeColaboracionFallido() {
        InformeImplementacion informe = new InformeImplementacion();
        Colaboracion colaboracion = null;
        List<Actividad> actividades = new ArrayList<>();
        List<Profesor> profesores = new ArrayList<>();
        Document result = informe.crearInformeDeColaboracion(colaboracion, actividades, profesores);
        assertNull(result);
    }

    @Test
    public void testCrearBordeDelInformeExitoso() {
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = new Document();
        Rectangle result = informe.crearBordeDelInforme(doc);
        assertNotNull(result);
        assertEquals(Rectangle.BOX, result.getBorder());
        assertEquals(BaseColor.BLACK, result.getBorderColor());
    }

    @Test
    public void testCrearBordeDelInformeFallido() {
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = null;
        assertThrows(NullPointerException.class, () -> {
            informe.crearBordeDelInforme(doc);
        });
    }
    
    @Test
    public void testObtenerActividadesDeInformeExitoso() {
        InformeImplementacion informe = new InformeImplementacion();
        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad = new Actividad();
        actividad.setNumeroActividad(1);
        actividad.setNombre("Nombre");
        actividad.setDescripcion("Descripcion");
        actividad.setFechaDeInicio("Inicio");
        actividad.setFechaDeCierre("Cierre");
        actividad.setEstado("Estado");
        actividades.add(actividad);
        PdfPTable result = informe.obtenerActividadesDeInforme(actividades);
        assertNotNull(result);
        assertEquals(7, result.getRows().size());
    }

    @Test
    public void testObtenerActividadesDeInformeFallido() {
        InformeImplementacion informe = new InformeImplementacion();
        List<Actividad> actividades = null;
        assertThrows(NullPointerException.class, () -> {
            informe.obtenerActividadesDeInforme(actividades);
        });
    }
    
    @Test
    public void testObtenerTituloDeInformeExitoso() {
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable result = informe.obtenerTituloDeInforme();
        assertNotNull(result);
        assertEquals(2, result.getNumberOfColumns());
    }
    
    @Test
    public void testObtenerTituloDeInformeFallido() {
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable result = informe.obtenerTituloDeInforme();
        assertNotNull(result);
    }
    
     @Test
        public void testObtenerCuerpoDelInformeExitoso() {
        InformeImplementacion informe = new InformeImplementacion();
        Colaboracion colaboracion = new Colaboracion();        
        PropuestaColaboracion propuesta = new PropuestaColaboracion();
        propuesta.setFechaInicio("2023-01-01");
        propuesta.setFechaCierre("2023-12-31");
        propuesta.setProgramaEducativoEstudiantil("Programa X");
        propuesta.setExperienciaEducativa("Experiencia Y");
        propuesta.setIdioma("Español");
        propuesta.setObjetivo("Objetivo Z");
        TipoColaboracion tipo = new TipoColaboracion();
        tipo.setTipo("Virtual");
        propuesta.setTipoColaboracion(tipo);
        colaboracion.setPropuestaColaboracion(propuesta);
        colaboracion.setCantidadEstudiantes(30);        
        List<Profesor> profesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        profesor.setNombre("Nombre");
        profesor.setApellidoPaterno("ApellidoPaterno");
        profesor.setApellidoMaterno("ApellidoMaterno");
        profesores.add(profesor);        
        Paragraph result = informe.obtenerCuerpoDelInforme(colaboracion, profesores);
        assertNotNull(result);
        assertTrue(result.getChunks().size() > 0);
    }
    
    @Test
    public void testObtenerCuerpoDelInformeFallido() {
        InformeImplementacion informe = new InformeImplementacion();
        Colaboracion colaboracion = null;
        List<Profesor> profesores = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> {
            informe.obtenerCuerpoDelInforme(colaboracion, profesores);
        });
    }
    
    @Test
    public void testGuardarArchivoDeInformeExitoso() {
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = new Document();
        File file = new File("test_guardar.pdf");
        int result = informe.guardarArchivoDeInforme(file, doc, 1);
        assertEquals(1, result);
        assertTrue(file.exists());
        file.delete();
    }
    
    @Test
    public void testGuardarArchivoDeInformeFallido() {
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = new Document();
        File file = null;
        int result = informe.guardarArchivoDeInforme(file, doc, 1);
        assertEquals(-1, result);
    }
}
