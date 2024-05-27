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
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import logicaDeNegocio.manejadorDeArchivos.InformeImplementacion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class InformeImplemenracionPrueba {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaCrearInformeDeColaboracionExitoso() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        
        PropuestaColaboracion propuesta = new PropuestaColaboracion();
        propuesta.setFechaInicio("2021-01-01");
        propuesta.setFechaCierre("2021-12-31");
        propuesta.setProgramaEducativoEstudiantil("Ingeniería de Software");
        propuesta.setExperienciaEducativa("Desarrollo de Software");
        propuesta.setIdioma("Español");
        propuesta.setObjetivo("Mejorar habilidades de programación");
        
        TipoColaboracion tipo = new TipoColaboracion();
        tipo.setTipo("Virtual");
        
        propuesta.setTipoColaboracion(tipo);
        colaboracion.setPropuestaColaboracion(propuesta);
        colaboracion.setCantidadEstudiantes(30);
        
        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad = new Actividad();
        actividad.setNumeroActividad(1);
        actividad.setNombre("Actividad 1");
        actividad.setDescripcion("Descripción de la actividad 1");
        actividad.setFechaDeInicio("2021-02-01");
        actividad.setFechaDeCierre("2021-03-01");
        actividad.setEstado("Completada");
        actividades.add(actividad);
        
        List<Profesor> profesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        profesor.setNombre("Juan");
        profesor.setApellidoPaterno("Pérez");
        profesor.setApellidoMaterno("López");
        profesores.add(profesor);
        
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = informe.crearInformeDeColaboracion(colaboracion, actividades, profesores);
        
        assertNotNull(doc);
    }
    
    @Test
    public void pruebaCrearInformeDeColaboracionFallido() {
        InformeImplementacion informe = new InformeImplementacion();
        Colaboracion colaboracion = null;
        List<Actividad> actividades = new ArrayList<>();
        List<Profesor> profesores = new ArrayList<>();
        Document result = informe.crearInformeDeColaboracion(colaboracion, actividades, profesores);
        assertNull(result);
    }

    
    @Test
    public void pruebaCrearBordeDelInforme() {
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = new Document();
        Rectangle borde = informe.crearBordeDelInforme(doc);
        
        assertEquals(doc.leftMargin() - 10, borde.getLeft(), 0);
        assertEquals(doc.getPageSize().getWidth() - doc.rightMargin() + 10, borde.getRight(), 0);
        assertEquals(doc.getPageSize().getHeight() - doc.topMargin() + 10, borde.getTop(), 0);
        assertEquals(doc.bottomMargin() - 10, borde.getBottom(), 0);
        assertEquals(Rectangle.BOX, borde.getBorder());
        assertEquals(BaseColor.BLACK, borde.getBorderColor());
        assertEquals(2, borde.getBorderWidth(), 0);
    }
    
    @Test
    public void pruebaObtenerActividadesDeInforme() {
        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad = new Actividad();
        actividad.setNumeroActividad(1);
        actividad.setNombre("Actividad 1");
        actividad.setDescripcion("Descripción de la actividad 1");
        actividad.setFechaDeInicio("2021-02-01");
        actividad.setFechaDeCierre("2021-03-01");
        actividad.setEstado("Completada");
        actividades.add(actividad);
        
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable tabla = informe.obtenerActividadesDeInforme(actividades);
        
        assertEquals(6, tabla.getNumberOfColumns());
        assertEquals(7, tabla.getRows().size()); // 1 header row + 1 data row
    }

    @Test
    public void pruebaObtenerTituloDeInforme() {
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable titulo = informe.obtenerTituloDeInforme();
        
        assertEquals(2, titulo.getNumberOfColumns());
    }
    
    @Test
    public void pruebaGuardarArchivoDeInformeExitoso() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        
        List<Actividad> actividades = new ArrayList<>();
        List<Profesor> profesores = new ArrayList<>();
        
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = informe.crearInformeDeColaboracion(colaboracion, actividades, profesores);
        
        File archivo = new File("Informes/informeDeColaboracionTest.pdf");
        int resultado = informe.guardarArchivoDeInforme(archivo, doc, colaboracion.getIdColaboracion());
        
        assertEquals(1, resultado);
        assertTrue(archivo.exists());
        archivo.delete(); 
    }
}
