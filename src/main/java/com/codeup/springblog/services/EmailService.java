package com.codeup.springblog.services;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;
//    if emailSender has a redsquiggle, you need to add info in the application.properties

//    the value annotation pulls a properties and assigns it.  below is how you pull from different api's
    @Value("${spring.mail.from}")
//    the line above comes from application properties.
    private String from;

    public void prepareAndSend(Post post, String subject, String body) {
//        the object that composes your email: make a new message object:
        SimpleMailMessage msg = new SimpleMailMessage();
//        these are the message object properties:
//        from is the @value above:
        msg.setFrom(from);
        msg.setTo(post.getAuthor().getEmail());
        msg.setSubject(subject);
        msg.setText(body);
//this try catch is needed to catch the exception
        try{
            this.emailSender.send(msg);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
}