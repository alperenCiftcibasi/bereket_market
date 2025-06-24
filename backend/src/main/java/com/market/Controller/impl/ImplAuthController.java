package com.market.Controller.impl;

import com.market.Dto.AuthRequest;
import com.market.Dto.AuthResponse;
import com.market.Entities.User;
import com.market.Repository.UserRepository;
import com.market.Security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
         return ResponseEntity.ok(new AuthResponse(token));
        
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email zaten kayıtlı");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        
        // Eğer rol boşsa USER olsun
        String role = request.getRole();
        if (role == null || (!role.equalsIgnoreCase("ADMIN") && !role.equalsIgnoreCase("USER"))) {
            role = "USER";
        }
        user.setRole(role.toUpperCase());

        userRepository.save(user);
        return ResponseEntity.ok("Kayıt başarılı!");
    }
    
}
