package com.siemens.mindsphere.apps.modules.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Value("${email.address.from}")
    private String from;
    @Value("${email.fromName}")
    private String fromName;
    @Value("${email.smtpUsername}")
    private String smtpUsername;
    @Value("${email.smtpPassword}")
    private String smtpPassword;
    @Value("${email.host}")
    private String host;
    @Value("${email.port}")
    private String port;
/*
    Properties props = System.getProperties();
    Session session = Session.getDefaultInstance(props);
    MimeMessage msg = new MimeMessage(session);*/

    public void sendMail(String to, String subject, String body) throws Exception {
/*        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        msg.setFrom(new InternetAddress(from,fromName));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");*/
        Transport transport = session.getTransport();
        try
        {
            System.out.println("Sending...");
            transport.connect(host, smtpUsername, smtpPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("EmailService sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            transport.close();
        }
    }
}