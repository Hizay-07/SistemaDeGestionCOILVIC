package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracionImplementacion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class PruebaDAOTipoColaboracionImplementacion {
    
    @Before
    public void setUp() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarTipoColaboracionExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setTipo("Intercambio Cultural");
        int resultado = dao.registrarTipoColaboracion(tipoColaboracion);
        assertEquals(1, resultado);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void pruebaFallidaDatosInvalidosRegistrarTipoColaboracion() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        TipoColaboracion tipoColaboracion = new TipoColaboracion();
        tipoColaboracion.setTipo("");
        int resultado = dao.registrarTipoColaboracion(tipoColaboracion);
    }
    
    
    @Test
    public void pruebaConsultarTiposDeColaboracionExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> tiposColaboracion = dao.consultarTiposDeColaboracion();
        assertEquals(3, tiposColaboracion.size());  // Basado en los datos proporcionados en la base de datos
    }

      
    @Test(expected = AssertionError.class)
    public void pruebaFallidaConsultarTiposDeColaboracion() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> tiposColaboracion = dao.consultarTiposDeColaboracion();
        assertEquals(5, tiposColaboracion.size());  // Basado en los datos proporcionados en la base de datos
    }

    
    @Test
    public void pruebaConsultarTipoColaboracionPorIdExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        String tipo = dao.consultarTipoColaboracionPorId(1);
        assertEquals("Clase espejo", tipo);
    }

    
    @Test
    public void pruebaFallidaConsultarTipoColaboracionPorId() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        String tipo = dao.consultarTipoColaboracionPorId(999);
        assertEquals("", tipo);
    }

    
    @Test
    public void pruebaConsultarIdTipoColaboracionPorTipoExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int idTipo = dao.consultarIdTipoColaboracionPorTipo("Implementación COIL-VIC");
        assertEquals(2, idTipo);
    }
    
    @Test
    public void pruebaFallidaConsultarIdTipoColaboracionPorTipo() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int idTipo = dao.consultarIdTipoColaboracionPorTipo("Tipo Inexistente");
        assertEquals(0, idTipo);  // Debería ser 0 o -1 dependiendo de la implementación
    }


}
