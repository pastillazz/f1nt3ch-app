package com.pastillazz.f1nt3ch.notifications.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EmailService
{
    private final JavaMailSender javaMailSender;
    @Value("${MAIL_USERNAME}")
    private String from;
     public void sendEmail(String to, String subject, String text)
     {
         SimpleMailMessage email= new SimpleMailMessage();
         email.setFrom(from);
         email.setTo(to);
         email.setSubject(subject);
         email.setText(text);
         javaMailSender.send(email);
     }
}
