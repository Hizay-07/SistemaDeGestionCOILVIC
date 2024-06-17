package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOTipoColaboracionImplementacion;
import logicaDeNegocio.clases.TipoColaboracion;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOTipoColaboracionImplementacion {
    
    @BeforeClass
    public static void inicializar() {
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
    public void pruebaConsultarTiposDeColaboracionFallida() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        List<TipoColaboracion> listaEsperada=new ArrayList<>();
        listaEsperada.add(new TipoColaboracion(1,"Clase espejo"));
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
    public void pruebaConsultarIdTipoColaboracionPorTipoFallida() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int idTipo = dao.consultarIdTipoColaboracionPorTipo("Taller");
        assertEquals(0, idTipo); 
    }            

    @Test
    public void pruebaVerificarTipoColaboracionExitosa() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int resultado = dao.verificarTipoColaboracion();
        assertEquals(2,resultado);
    }
    
    @Test
    public void pruebaVerificarTipoColaboracionFallida() {
        DAOTipoColaboracionImplementacion dao = new DAOTipoColaboracionImplementacion();
        int resultado = dao.verificarTipoColaboracion();
        assertNotEquals(0,resultado);                            
    }        
}
