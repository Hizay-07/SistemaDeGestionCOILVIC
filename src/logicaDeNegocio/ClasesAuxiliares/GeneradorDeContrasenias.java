package logicaDeNegocio.ClasesAuxiliares;

import java.security.SecureRandom;

public class GeneradorDeContrasenias {
    private static final String CARACTERES_PERMITIDOS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*()[]{}<>?/\\|:;,.\\-_+=";

    public static String generarContraseña() {
        String patronContrasenia = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=\\S+$)(?=(?:[^@$!%*?&]*[@$!%*?&]){1,2}[^@$!%*?&]*$)[a-zA-Z0-9@$!%*?&]{8,15}$";

        SecureRandom random = new SecureRandom();
        StringBuilder contraseñaGenerada = new StringBuilder();
        do {
            contraseñaGenerada.setLength(0);
            for (int i = 0; i < 15; i++) {
                char caracter = CARACTERES_PERMITIDOS.charAt(random.nextInt(CARACTERES_PERMITIDOS.length()));
                contraseñaGenerada.append(caracter);
            }
        } while (!contraseñaGenerada.toString().matches(patronContrasenia));
        return contraseñaGenerada.toString();
    }
}
