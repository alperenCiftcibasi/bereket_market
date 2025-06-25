package com.market.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Email doğrulama fonksiyonu
    public void sendVerificationEmail(String toEmail, String token) {
        String link = "http://localhost:8080/api/auth/verify?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Email Doğrulama");
        message.setText("Email adresinizi doğrulamak için bu linke tıklayın: " + link);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println("Mail gönderilemedi: " + e.getMessage());
            throw e;
        }
    }

    // Genel amaçlı mail gönderme (şifre sıfırlama, bilgilendirme, vs.)
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            mailSender.send(message);
        } catch (MailException e) {
            System.out.println("Mail gönderilemedi: " + e.getMessage());
            throw e;
        }
    }
}
