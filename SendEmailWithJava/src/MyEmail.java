import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public class MyEmail {
    public static void main(String[] args) throws  Exception{
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protcl","smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("senderEmail@gmail.com", "Password");
            }
        });

        Message message = new MimeMessage(session);
        message.setSubject("Email from my Java program!");

        Address addressTo = new InternetAddress("emailThatGetsEmail@gmail.com");
        message.setRecipient(Message.RecipientType.TO, addressTo);

        MimeMultipart multipart = new MimeMultipart();

        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File("srcfile/Arrays_Classroom_Notes.txt"));

        MimeBodyPart attachment2 = new MimeBodyPart();
        attachment2.attachFile(new File("srcfile/mountain.png"));

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h1>Email from my cool java application</h1>","text/html");
        multipart.addBodyPart(attachment);
        multipart.addBodyPart(attachment2);
        multipart.addBodyPart(messageBodyPart);

        System.out.println("Email sent successfully");




    }
}
