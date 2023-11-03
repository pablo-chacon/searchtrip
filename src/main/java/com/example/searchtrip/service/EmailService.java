package com.example.searchtrip.service;


import com.example.searchtrip.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    User user;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(user.getEmail());
        message.setTo("complaints@example.com");
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
