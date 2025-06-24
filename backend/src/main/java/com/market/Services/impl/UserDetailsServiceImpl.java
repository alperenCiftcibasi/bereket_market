package com.market.Services.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.market.Entities.User;
import com.market.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));

        // Spring Security için role "ROLE_" prefix ile atanmalı
        String role = "ROLE_" + user.getRole().toUpperCase();

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(), // Bcrypt encoded şifre
            Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}
