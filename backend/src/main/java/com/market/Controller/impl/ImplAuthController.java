package com.market.Controller.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.market.Dto.AuthRequest;
import com.market.Dto.AuthResponse;
import com.market.Dto.PasswordResetRequestDTO;
import com.market.Dto.PasswordResetDTO;
import com.market.Entities.User;
import com.market.Entities.VerificationToken;
import com.market.Entities.PasswordResetToken;
import com.market.Repository.UserRepository;
import com.market.Repository.VerificationTokenRepository;
import com.market.Repository.PasswordResetTokenRepository;
import com.market.Security.JwtUtil;
import com.market.Services.impl.EmailService;
@CrossOrigin(origins = "http://localhost:3000") // React portu
@RestController
@RequestMapping("api/auth")
public class ImplAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;

    // 📌 Kullanıcı girişi
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(403).body(new AuthResponse("Hatalı e-posta veya şifre"));
        } catch (DisabledException e) {
            return ResponseEntity.status(403).body(new AuthResponse("Hesap pasif durumda"));
        } catch (LockedException e) {
            return ResponseEntity.status(403).body(new AuthResponse("Hesap kilitli"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(403).body(new AuthResponse("Yetkilendirme hatası"));
        }

        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Kullanıcı veritabanında bulunamadı"));

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        // 🔁 Hem token hem role bilgisini dön
        return ResponseEntity.ok(new AuthResponse(token, user.getRole()));
    }


    // 📌 Kayıt işlemi
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email zaten kayıtlı");
        }

        // Kullanıcı bilgilerini hazırla
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole("USER");
        user.setEnabled(false); // email doğrulanana kadar FALSE

        // Veritabanına kaydet
        System.out.println("Kullanıcı kayıt ediliyor: " + user.getEmail());
        userRepository.save(user);
        System.out.println("Kayıt tamamlandı");

        // Token üret ve kaydet
        String token = UUID.randomUUID().toString();
        VerificationToken vt = new VerificationToken(token, LocalDateTime.now().plusMinutes(30), user);
        tokenRepository.save(vt);

        // Email gönder
        try {
            emailService.sendVerificationEmail(user.getEmail(), token);
        } catch (Exception e) {
            System.err.println("Email gönderilemedi: " + e.getMessage());
            // Email gönderilemese bile kullanıcı kayıt oldu
        }

        return ResponseEntity.ok("Kayıt başarılı! Email adresinizi doğrulayın.");
    }

    // 📌 Email doğrulama
    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String token) {
        VerificationToken vt = tokenRepository.findByToken(token)
            .orElseThrow(() -> new RuntimeException("Geçersiz token"));

        if (vt.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token süresi dolmuş");
        }

        User user = vt.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        tokenRepository.delete(vt); // doğrulandıktan sonra token silinir

        return ResponseEntity.ok("Email başarıyla doğrulandı.");
    }

    // 📌 Şifremi unuttum (reset linki gönder)
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);

        // Güvenlik için: kullanıcı bulunamazsa da aynı cevap verilir
        if (user == null) {
            return ResponseEntity.ok("Eğer bu email kayıtlıysa, sıfırlama bağlantısı gönderildi.");
        }

        // Token oluştur ve kaydet
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));
        passwordResetTokenRepository.save(resetToken);

        // Mail gönder
        String link = "http://localhost:3000/reset-password?token=" + token; // frontend linkini güncelleyebilirsin
        emailService.sendSimpleMessage(
            user.getEmail(),
            "Şifre Sıfırlama",
            "Şifrenizi sıfırlamak için aşağıdaki linke tıklayın:\n" + link
        );

        return ResponseEntity.ok("Eğer bu email kayıtlıysa, sıfırlama bağlantısı gönderildi.");
    }

    // 📌 Şifreyi sıfırla (yeni şifre belirleme)
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordResetDTO dto) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(dto.getToken()).orElse(null);

        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Geçersiz veya süresi dolmuş token");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        passwordResetTokenRepository.delete(resetToken);

        return ResponseEntity.ok("Şifreniz başarıyla güncellendi.");
    }
}
