/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.DAOImplementacion.DAOColaboracionImplementacion;
import logicaDeNegocio.clases.Colaboracion;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author mario
 */
public class PruebaDAOColaboracionImplementacion {
    
    @Test
    public void pruebaRegistrarColaboracionExitosa(){
        DAOColaboracionImplementacion instancia=new DAOColaboracionImplementacion();
        Colaboracion colaboracion=new Colaboracion();
        colaboracion.setFechaInicio("2024-08-10");
        colaboracion.setFechaCierre("2024-12-16");
        colaboracion.setIdioma("Ingles");
        colaboracion.setTipoColaboracion("Clase espejo");
        colaboracion.setExperienciaEducativa("Base de datos no relacionales");
        instancia.setColaboracion(colaboracion);
        int resultadoEsperado=1;
        int resultadoObtenido=instancia.registrarColaboracion();
        assertEquals(resultadoEsperado,resultadoObtenido);        
    }
    
    @Test
    public void pruebaConsultarColaboracionesExitosa(){
        
    }
    
    @Test
    public void pruebaConsultarColaboracionPorFechaDeInicioExitosa(){
    }
    
    @Test
    public void pruebaEditarFechaDeInicioDeColaboracionExitosa(){
    }
    
    
}
