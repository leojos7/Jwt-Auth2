package com.siemens.mindsphere.apps.modules.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Configuration
public class EmailConfiguration {

    Properties props = System.getProperties();
    Session session = Session.getDefaultInstance(props);
    MimeMessage msg = new MimeMessage(session);

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

    public EmailConfiguration() {
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        try {
            msg.setFrom(new InternetAddress(from,fromName));
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setContent(body,"text/html");
    }
}
