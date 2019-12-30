package mx.com.utils.email;

//[START simple_includes]
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//[END simple_includes]

//[START multipart_includes]
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
//[END multipart_includes]
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.utils.CfgApp;
import mx.com.utils.controller.exception.RestError;

/*
* https://cloud.google.com/appengine/docs/standard/java/mail/sending-mail-with-mail-api
* https://cloud.google.com/appengine/docs/standard/java/mail/#who_can_send_mail
* https://github.com/GoogleCloudPlatform/java-docs-samples/blob/2e5996c68440134a79f1511c57529fa5cf987628/appengine-java8/mail/src/main/java/com/example/appengine/mail/MailServlet.java
*/

@Service
public class EmailSRV {

  @Autowired
  private CfgApp cfg;
	
  public void sendSimpleMail(final EmailDTO.SimpleMail sm) {
    Properties props = new Properties();
    Session  session = Session.getDefaultInstance(props, null);
    try {
      if (sm.getTipo().getEnvioIndividual()) {
        for(EmailDTO.Users usr: sm.getUsuarios()) {
          Message msg = new MimeMessage(session);
          msg.setFrom(new InternetAddress(cfg.getEmail().getIde(), cfg.getEmail().getAlias()));
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usr.getEmail(), usr.getAlias()));
          msg.setSubject(sm.getSubject());
          msg.setText(sm.getContenido());
          Transport.send(msg);
        }
      } else {
        Message msg = new MimeMessage(session);
        // Falta colocar el if para el tipo de correo
        msg.setFrom(new InternetAddress(cfg.getEmail().getIde(), cfg.getEmail().getAlias()));
        for(EmailDTO.Users usr: sm.getUsuarios()) {
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usr.getEmail(), usr.getAlias()));				
        }
        msg.setSubject(sm.getSubject());
        msg.setText(sm.getContenido());
        Transport.send(msg);
      }
    } catch (AddressException e) {
      throw new RestError.DataValidation(e.getMessage());
    } catch (MessagingException e) {
      throw new RestError.DataValidation(e.getMessage());
    } catch (UnsupportedEncodingException e) {
      throw new RestError.DataValidation(e.getMessage());
    }
  }

  public void sendHtmlMail(final EmailDTO.SimpleMail sm) {
    Properties props = new Properties();
    Session  session = Session.getDefaultInstance(props, null);
    try {
      if (sm.getTipo().getEnvioIndividual()) {
        for(EmailDTO.Users usr: sm.getUsuarios()) {
          Message msg = new MimeMessage(session);
          msg.setFrom(new InternetAddress(cfg.getEmail().getIde(), cfg.getEmail().getAlias()));
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usr.getEmail(), usr.getAlias()));
          msg.setSubject(sm.getSubject());
          msg.setContent(sm.getContenido(), "text/html");
          Transport.send(msg);
        }
      } else {
        Message msg = new MimeMessage(session);
        // Falta colocar el if para el tipo de correo
        msg.setFrom(new InternetAddress(cfg.getEmail().getIde(), cfg.getEmail().getAlias()));
        for(EmailDTO.Users usr: sm.getUsuarios()) {
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usr.getEmail(), usr.getAlias()));                              
        }
        msg.setSubject(sm.getSubject());
        msg.setContent(sm.getContenido(), "text/html");
        Transport.send(msg);
      }
    } catch (AddressException e) {
      throw new RestError.DataValidation(e.getMessage());
    } catch (MessagingException e) {
      throw new RestError.DataValidation(e.getMessage());
    } catch (UnsupportedEncodingException e) {
      throw new RestError.DataValidation(e.getMessage());
    }
  }
  
  public void sendHtmlMailAttach(final EmailDTO.SimpleMail sm, final List<EmailDTO.Attach> attachs) {
    Properties props = new Properties();
    Session  session = Session.getDefaultInstance(props, null);
    try {
      if (sm.getTipo().getEnvioIndividual()) {
        for(EmailDTO.Users usr: sm.getUsuarios()) {
          Message msg = new MimeMessage(session);
          msg.setFrom(new InternetAddress(cfg.getEmail().getIde(), cfg.getEmail().getAlias()));
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usr.getEmail(), usr.getAlias()));
          msg.setSubject(sm.getSubject());
          //msg.setContent(sm.getContenido(), "text/html");
          
          // Create the message part
          BodyPart messageBodyPart = new MimeBodyPart();
          // Now set the actual message
          messageBodyPart.setHeader("Content-Type", "text/html");
          messageBodyPart.setText(sm.getContenido());
          messageBodyPart.setContent(sm.getContenido(), "text/html");
          // Create a multipar message
          Multipart multipart = new MimeMultipart();
          // Set text message part
          multipart.addBodyPart(messageBodyPart);
          
          // Part two is attachment
          for (EmailDTO.Attach att : attachs) {
            messageBodyPart = new MimeBodyPart();
            ByteArrayDataSource bds = new ByteArrayDataSource(att.getFile(), att.getFileContent()); 
            messageBodyPart.setDataHandler(new DataHandler(bds));
            messageBodyPart.setFileName(att.getFileName());
            multipart.addBodyPart(messageBodyPart);
          }
          
          // Send the complete message parts
          msg.setContent(multipart);
          
          Transport.send(msg);
        }
      } else {
        Message msg = new MimeMessage(session);
        // Falta colocar el if para el tipo de correo
        msg.setFrom(new InternetAddress(cfg.getEmail().getIde(), cfg.getEmail().getAlias()));
        for(EmailDTO.Users usr: sm.getUsuarios()) {
          msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usr.getEmail(), usr.getAlias()));                              
        }
        msg.setSubject(sm.getSubject());
        msg.setContent(sm.getContenido(), "text/html");
        Transport.send(msg);
      }
    } catch (AddressException e) {
      throw new RestError.DataValidation(e.getMessage());
    } catch (MessagingException e) {
      throw new RestError.DataValidation(e.getMessage());
    } catch (UnsupportedEncodingException e) {
      throw new RestError.DataValidation(e.getMessage());
    }
  }
  
}