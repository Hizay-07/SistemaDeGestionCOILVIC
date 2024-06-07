package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracionImplementacion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
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
    public void pruebaConsultarTiposDeColaboracionExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(new TipoColaboracion(1,"Clase espejo"));
        listaEsperada.add(new TipoColaboracion(2,"Implementación COIL-VIC"));        
        List<TipoColaboracion> tiposColaboracionObtenidas = dao.consultarTiposDeColaboracion();
        assertEquals(listaEsperada, tiposColaboracionObtenidas);  
    }

      
    @Test
    public void pruebaFallidaConsultarTiposDeColaboracion() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(new TipoColaboracion(1,"Clase espejo"));
        List<TipoColaboracion> tiposColaboracionObtenidas = dao.consultarTiposDeColaboracion();
        assertNotEquals(listaEsperada, tiposColaboracionObtenidas);
    }

    @Test
    public void pruebaConsultarTiposDeColaboracionSinConexionExitosa(){
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(new TipoColaboracion(1,"Clase espejo"));
        listaEsperada.add(new TipoColaboracion(2,"Implementación COIL-VIC"));        
        List<TipoColaboracion> tiposColaboracionObtenidas = dao.consultarTiposDeColaboracion();
        assertNotEquals(listaEsperada, tiposColaboracionObtenidas);         
    }
    
    @Test
    public void pruebaConsultarTipoColaboracionPorIdExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        String tipo = dao.consultarTipoColaboracionPorId(1);
        assertEquals("Clase espejo", tipo);
    }

    
    @Test
    public void pruebaConsultarTipoColaboracionPorIdFallida() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        String tipo = dao.consultarTipoColaboracionPorId(0);
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
        int idTipo = dao.consultarIdTipoColaboracionPorTipo("Taller");
        assertEquals(0, idTipo); 
    }

    @Test
    public void pruebaVerificarTipoColaboracionExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int resultado = dao.verificarTipoColaboracion();
        assertTrue(resultado > 1);
    }

    @Test
    public void pruebaVerificarTipoColaboracionSinValores() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion() {
            @Override
            public int verificarTipoColaboracion() {
                return 0;
            }
        };
        int resultado = dao.verificarTipoColaboracion();
        assertEquals(0, resultado);
    }
}
