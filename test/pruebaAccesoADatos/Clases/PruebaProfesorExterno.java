/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebaAccesoADatos.Clases;

import logicaDeNegocio.clases.ProfesorExterno;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author hizza
 */
public class PruebaProfesorExterno {
    @Test
    public void pruebaSetIdProfesorExternoExitosa() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdProfesorExterno(1);
        assertNotNull(profesorPrueba.getIdProfesorExterno());
    }
    
    @Test
    public void pruebaSetIdPrfesorExternoInvalido() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdProfesorExterno(-1);
        assertNull(profesorPrueba.getIdProfesorExterno());
    }
    
    @Test
    public void pruebaSetIdRepresentanteInstitucionalExitosa() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdRepresentanteInstitucional(1);
        assertNotNull(profesorPrueba.getIdRepresentanteInstitucional());
    }
    
    @Test
    public void pruebaSetIdRepresentanteInstitucionalInvalida() {
        ProfesorExterno profesorPrueba = new ProfesorExterno();
        profesorPrueba.setIdRepresentanteInstitucional(-1);
        assertNull(profesorPrueba.getIdRepresentanteInstitucional());
    }
}
