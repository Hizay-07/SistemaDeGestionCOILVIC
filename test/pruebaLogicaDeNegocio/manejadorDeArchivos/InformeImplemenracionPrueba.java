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
        colaboracion.setIdColaboracion(20);        
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
        actividad.setNombre("ActividadPrueba");
        actividad.setDescripcion("Descripción de la actividad");
        actividad.setFechaDeInicio("2021-02-01");
        actividad.setFechaDeCierre("2021-03-01");
        actividad.setEstado("Completada");
        actividades.add(actividad);        
        List<Profesor> profesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        profesor.setNombre("Juanjose");
        profesor.setApellidoPaterno("López");
        profesor.setApellidoMaterno("Pérez");
        profesores.add(profesor);        
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = informe.crearInformeDeColaboracion(colaboracion, actividades, profesores);        
        assertNotNull(doc);
    }
    
    @Test (expected = NullPointerException.class)
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
    
    @Test (expected = NullPointerException.class)
    public void pruebaCrearBordeDelInformeFallida() {
        InformeImplementacion informe = new InformeImplementacion();
        Document doc = null;
        informe.crearBordeDelInforme(doc);
    }
    
    @Test
    public void pruebaObtenerActividadesDeInformeExitosa() {
        List<Actividad> actividades = new ArrayList<>();
        Actividad actividad = new Actividad();
        actividad.setNumeroActividad(1);
        actividad.setNombre("prueba Desarrollo de nuevo sitio web prueba");
        actividad.setDescripcion("Creación de las páginas principales del sitio web");
        actividad.setFechaDeInicio("2024-05-28");
        actividad.setFechaDeCierre("2024-05-30");
        actividad.setEstado("Inactiva");
        actividades.add(actividad);        
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable tabla = informe.obtenerActividadesDeInforme(actividades);        
        assertEquals(6, tabla.getNumberOfColumns());
        assertEquals(2, tabla.getRows().size()); // Encabezado + 1 actividad
    }

    @Test
    public void pruebaObtenerActividadesDeInformeFallida() {
        List<Actividad> actividades = new ArrayList<>();
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable tabla = informe.obtenerActividadesDeInforme(actividades);        
        assertEquals(6, tabla.getNumberOfColumns());
        assertEquals(1, tabla.getRows().size());
    }

    @Test
    public void pruebaObtenerTituloDeInformeExitosa() {
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable titulo = informe.obtenerTituloDeInforme();        
        assertEquals(2, titulo.getNumberOfColumns());
        assertEquals(1, titulo.getRows().size()); 
    }

    @Test (expected = AssertionError.class)
    public void pruebaObtenerTituloDeInformeFallida() {
        InformeImplementacion informe = new InformeImplementacion();
        PdfPTable titulo = informe.obtenerTituloDeInforme();        
        assertEquals(2, titulo.getNumberOfColumns());
        assertTrue(titulo.getRows().isEmpty());
    }

    @Test
    public void pruebaObtenerCuerpoDelInformeExitosa() {
        Colaboracion colaboracion = new Colaboracion();
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
        List<Profesor> profesores = new ArrayList<>();
        Profesor profesor = new Profesor();
        profesor.setNombre("Juanjose");
        profesor.setApellidoPaterno("López");
        profesor.setApellidoMaterno("Pérez");
        profesores.add(profesor);        
        InformeImplementacion informe = new InformeImplementacion();
        Paragraph cuerpo = informe.obtenerCuerpoDelInforme(colaboracion, profesores);        
        assertNotNull(cuerpo);
    }

    @Test (expected = NullPointerException.class)
    public void pruebaObtenerCuerpoDelInformeFallida() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setPropuestaColaboracion(new PropuestaColaboracion());
        List<Profesor> profesores = new ArrayList<>();
        InformeImplementacion informe = new InformeImplementacion();
        Paragraph cuerpo = informe.obtenerCuerpoDelInforme(colaboracion, profesores);        
        assertNotNull(cuerpo); // Debe manejar la situación sin errores
        assertTrue(cuerpo.isEmpty()); // No debe contener texto si no hay datos
    }

    @Test
    public void pruebaGuardarArchivoDeInformeExitosa() {
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
        List<Profesor> profesores = new ArrayList<>();        
        InformeImplementacion informe = new InformeImplementacion();     
        File archivo = new File("Informes/informeDeColaboracionTest.pdf");
        int resultado = informe.guardarArchivoDeInforme(archivo, colaboracion.getIdColaboracion());        
        assertEquals(1, resultado);
        assertTrue(archivo.exists());
        archivo.delete(); 
    }

    @Test
    public void pruebaGuardarArchivoDeInformeFallida() {
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
        List<Profesor> profesores = new ArrayList<>();        
        InformeImplementacion informe = new InformeImplementacion();     
        File archivo = new File("ruta/no/existente/informeDeColaboracionTest.pdf");
        int resultado = informe.guardarArchivoDeInforme(archivo, colaboracion.getIdColaboracion());        
        assertEquals(-1, resultado);
    }
}
