package com.market.Entities;

import com.market.Repository.UserRepository;
import com.market.Services.impl.ImplUserServices;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    
    private String role; // "USER" veya "ADMIN" gibi değerler
    
 // Yeni alanlar
    private String phone;         // Telefon numarası
    private String address;       // Adres
    private String fullName;      // İsim Soyisim (Opsiyonel, name’den farklı)
    private String paymentMethod; // Ödeme yöntemi (örn: Kredi kartı, Paypal vb
    
    private boolean enabled = false; // Email doğrulanana kadar giriş engelli

}