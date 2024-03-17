package accesoADatosTest;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class ManejadorBaseDeDatosPrueba{
    
    @Test
    public void pruebaGetConexionExitosa() throws SQLException{
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.getConexion();
        assertNotNull(resultado);
        resultado.close();
    }
}
