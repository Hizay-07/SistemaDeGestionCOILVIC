package envioDeCorreos;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import org.apache.log4j.Logger;

public class EnvioDeCorreo {
    
    private static final Logger LOG=Logger.getLogger(EnvioDeCorreo.class);
    private String destinatario;
    private String asunto;
    private String contenido;
    
    public EnvioDeCorreo(){
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public Properties obtenerCorreoRemitente(){
        Properties propiedadesAdministrativo = new Properties();
        try{
           DataInputStream archivoUsuario = new DataInputStream(new FileInputStream("src\\envioDeCorreos\\Correo.txt"));
           propiedadesAdministrativo.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            LOG.fatal(archivoNoEncontrado.getMessage());
        }catch(IOException excepcion){
            LOG.fatal(excepcion.getCause());
        }
        return propiedadesAdministrativo;
    }
    
    
    public int enviarCorreo() {
        int resultadoEnvioDeCorreo;
        Properties propiedadess = new Properties();
        Properties datosCorreo = obtenerCorreoRemitente();
        String remitente = datosCorreo.getProperty("Remitente");
        String contraseniaRemitente = datosCorreo.getProperty("ContraseniaRemitente");
        propiedadess.put("mail.smtp.auth", "true");
        propiedadess.put("mail.smtp.starttls.enable", "true");
        propiedadess.put("mail.smtp.host", "smtp.gmail.com");
        propiedadess.put("mail.smtp.port", "587");
        Session session = Session.getInstance(propiedadess,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remitente, contraseniaRemitente);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(contenido);
            Transport.send(message);
            resultadoEnvioDeCorreo=1;
        }catch(SendFailedException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoEnvioDeCorreo = -1;
        }catch(MessagingException excepcion){
            LOG.error(excepcion.getMessage());
            resultadoEnvioDeCorreo = -1;
        }
        return resultadoEnvioDeCorreo;
    }
}
