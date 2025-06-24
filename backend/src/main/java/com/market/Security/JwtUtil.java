package com.market.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "buçokuzunveguvenlibirsecrettokenkeyolmalıdır123456"; // en az 32 karakter
    private final long EXPIRATION = 1000 * 60 * 60; // 1 saat

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Token üretme (email + role içerir)
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role) // rolü JWT içine ekliyoruz
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Token'dan email çıkar
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Token'dan rol çıkar (gerekirse frontend'de de kullanılabilir)
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // Token geçerlilik kontrolü
    public boolean isTokenValid(String token, String userEmail) {
        return extractEmail(token).equals(userEmail) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
