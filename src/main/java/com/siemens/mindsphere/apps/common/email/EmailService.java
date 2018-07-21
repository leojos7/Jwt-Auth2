package com.siemens.mindsphere.apps.common.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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

    Properties props = null;
    Session session = null;
    MimeMessage msg = null;


    public void sendMail(String to, String subject, String body) throws Exception {

        props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(props);
        msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from, fromName));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body, "text/html");
        Transport transport = session.getTransport();
        try {
            System.out.println("Sending...");
            transport.connect(host, smtpUsername, smtpPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("EmailService sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        } finally {
            transport.close();
        }
    }
}