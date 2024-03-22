package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.clases.RepresentanteInstitucional;
import logicaDeNegocio.DAOImplementacion.DAORepresentanteInstitucionalImplementacion;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


public class DAORepresentanteInstitucionalImplementacionPruebas {
    
    @Test
    public void pruebaRegistrarRepresentanteInstitucional(){
        DAORepresentanteInstitucionalImplementacion pruebaRegistro = new DAORepresentanteInstitucionalImplementacion();
        
        RepresentanteInstitucional representanteDePrueba = new RepresentanteInstitucional();
        representanteDePrueba.setNombreInstitucion("UV");
        representanteDePrueba.setClaveInstitucional("1234");
        representanteDePrueba.setContacto("2281709292");
        
        int resultadoDePrueba =pruebaRegistro.registrarRepresentanteInstitucional(representanteDePrueba);
        System.out.println(resultadoDePrueba);
        assertEquals(1,resultadoDePrueba);
    }
}
