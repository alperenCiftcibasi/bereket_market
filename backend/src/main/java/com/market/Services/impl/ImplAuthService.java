package com.market.Services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.market.Entities.PasswordResetToken;
import com.market.Entities.User;
import com.market.Repository.PasswordResetTokenRepository;
import com.market.Repository.UserRepository;
import com.market.Services.IAuthService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ImplAuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void sendPasswordResetToken(String email) {
        // 1. Kullanıcıyı bul
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        // 2. Token üret
        String token = UUID.randomUUID().toString();

        // 3. Token entity'si oluştur
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

        // 4. Tokenı kaydet
        tokenRepository.save(resetToken);

        // 5. Email gönder
        String link = "http://localhost:3000/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Şifre Sıfırlama");
        message.setText("Şifrenizi sıfırlamak için linke tıklayın: " + link);
        mailSender.send(message);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Geçersiz token!"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token süresi dolmuş!");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }
}
