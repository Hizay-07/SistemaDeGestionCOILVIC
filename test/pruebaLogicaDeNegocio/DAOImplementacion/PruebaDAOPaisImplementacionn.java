package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.DAOImplementacion.DAOPaisImplementacion;
import logicaDeNegocio.clases.Pais;
import logicaDeNegocio.clases.Usuario;
import logicaDeNegocio.clases.UsuarioSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.BeforeClass;

public class PruebaDAOPaisImplementacionn {
    
    @BeforeClass
    public static void inicializar() {
        Usuario usuarioPrueba = new Usuario();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        UsuarioSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaObtenerNumeroDePaisExitosa(){
        Pais paisPrueba = new Pais();
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion(); 
        paisPrueba.setNombrePais("México");       
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(1,resultadoConsulta);
    }
    
    @Test 
    public void pruebaObtenerNumeroDePaisFallida(){
        Pais paisPrueba = new Pais();
        paisPrueba.setNombrePais("Ucrania");
        DAOPaisImplementacion pruebaMetodo = new DAOPaisImplementacion();         
        int resultadoConsulta = pruebaMetodo.obtenerNumeroDePais(paisPrueba);
        assertEquals(0,resultadoConsulta);
    }          
    
    @Test
    public void pruebaConsultarPaisesExitosa() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        Pais paisMexico = new Pais();
        paisMexico.setNombrePais("México");
        Pais paisBrasil = new Pais();
        paisBrasil.setNombrePais("Brasil");
        Pais paisEstadosUnidos = new Pais();
        paisEstadosUnidos.setNombrePais("Estados Unidos de América");
        Pais paisCuba = new Pais();
        paisCuba.setNombrePais("Cuba");
        Pais paisArgentina = new Pais();
        paisArgentina.setNombrePais("Argentina");
        Pais paisColombia = new Pais();
        paisColombia.setNombrePais("Colombia");
        Pais paisChile = new Pais();
        paisChile.setNombrePais("Chile");
        Pais paisPeru = new Pais();
        paisPeru.setNombrePais("Perú");
        Pais paisEcuador = new Pais();
        paisEcuador.setNombrePais("Ecuador");
        Pais paisUruguay = new Pais();
        paisUruguay.setNombrePais("Uruguay");
        Pais paisVenezuela = new Pais();
        paisVenezuela.setNombrePais("Venezuela");
        Pais paisAlemania = new Pais();
        paisAlemania.setNombrePais("Alemania");
        Pais paisEspaña = new Pais();
        paisEspaña.setNombrePais("España");
        Pais paisPortugal = new Pais();
        paisPortugal.setNombrePais("Portugal");
        Pais paisFrancia = new Pais();
        paisFrancia.setNombrePais("Francia");
        Pais paisItalia = new Pais();
        paisItalia.setNombrePais("Italia");
        Pais paisReinoUnido = new Pais();
        paisReinoUnido.setNombrePais("Reino Unido");
        Pais paisChina = new Pais();
        paisChina.setNombrePais("China");
        Pais paisJapon = new Pais();
        paisJapon.setNombrePais("Japón");
        Pais paisNuevaZelanda = new Pais();
        paisNuevaZelanda.setNombrePais("Nueva Zelanda");
        List<Pais> paisesEsperados=new ArrayList<>();
        paisesEsperados.add(paisMexico);
        paisesEsperados.add(paisBrasil);
        paisesEsperados.add(paisEstadosUnidos);
        paisesEsperados.add(paisCuba);
        paisesEsperados.add(paisArgentina);
        paisesEsperados.add(paisColombia);
        paisesEsperados.add(paisChile);
        paisesEsperados.add(paisPeru);
        paisesEsperados.add(paisEcuador);
        paisesEsperados.add(paisUruguay);
        paisesEsperados.add(paisVenezuela);
        paisesEsperados.add(paisAlemania);
        paisesEsperados.add(paisEspaña);
        paisesEsperados.add(paisPortugal);
        paisesEsperados.add(paisFrancia);
        paisesEsperados.add(paisItalia);
        paisesEsperados.add(paisReinoUnido);
        paisesEsperados.add(paisChina);
        paisesEsperados.add(paisJapon);
        paisesEsperados.add(paisNuevaZelanda);      
        List<Pais> paisesObtenidos = dao.consultarPaises();        
        assertEquals(paisesEsperados, paisesObtenidos);
    }
    
    @Test
    public void pruebaConsultarPaisesFallida() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        Pais paisMexico = new Pais();
        paisMexico.setNombrePais("México");
        List<Pais> paisesEsperados=new ArrayList<>();
        paisesEsperados.add(paisMexico);        
        List<Pais> paisesObtenidos = dao.consultarPaises();
        assertNotEquals(paisesEsperados,paisesObtenidos);
    }           
    
    @Test
    public void pruebaVerificarPaisExitosa() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        int resultado = dao.verificarPais();
        assertEquals(20,resultado);
    }

    @Test
    public void pruebaVerificarPaisFallida() {
        DAOPaisImplementacion dao = new DAOPaisImplementacion();
        int resultado = dao.verificarPais();
        assertNotEquals(0,resultado);                
    }            
}
