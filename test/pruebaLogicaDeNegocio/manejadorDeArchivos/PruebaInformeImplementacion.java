package pruebaLogicaDeNegocio.manejadorDeArchivos;

import com.itextpdf.text.Document;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaInformeImplementacion {
    
    @BeforeClass
    public static void inicializar() {
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
    
    @Test (expected = IllegalArgumentException.class)
    public void pruebaCrearInformeDeColaboracionFallido() {
        Colaboracion colaboracion = new Colaboracion();
        colaboracion.setIdColaboracion(1);
        PropuestaColaboracion propuesta = new PropuestaColaboracion();
        propuesta.setFechaInicio("2021-01-01");
        propuesta.setFechaCierre("fecha-invalida");
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
        assertNull(doc);
    }
    
}
