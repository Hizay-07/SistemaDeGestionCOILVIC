package envioDeCorreos;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EnvioDeCorreo {
    private static String REMITENTE = "sistemadegestioncoilvic@gmail.com";
    private static String CONTRASENIAREMITENTE = "qjtymlntlwvmhkju";
    private String destinatario;
    private String asunto;
    private String contenido;
    
    public EnvioDeCorreo(String destinatario, String asunto, String contenido) {
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.contenido = contenido;
    }
    
    
    public void enviarCorreo() {
        // Configurar las propiedades del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Crear una sesión de correo con autenticación
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(REMITENTE, CONTRASENIAREMITENTE);
                    }
                });

        try {
            // Crear un mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(REMITENTE));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(contenido);

            // Enviar el mensaje de correo
            Transport.send(message);

            System.out.println("Correo enviado satisfactoriamente.");

        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}
