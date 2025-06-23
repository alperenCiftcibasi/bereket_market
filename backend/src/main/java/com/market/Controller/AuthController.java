package com.market.Controller;

import com.market.Dto.AuthRequest;
import com.market.Dto.AuthResponse;
import com.market.Entities.User;
import com.market.Repository.UserRepository;
import com.market.Security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

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

        String token = jwtUtil.generateToken(user.getEmail());
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
        user.setName(request.getName()); // name artık buradan alınacak
        user.setRole("USER");

        userRepository.save(user);
        return ResponseEntity.ok("Kayıt başarılı!");
    }
    
}
