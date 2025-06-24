package com.market.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.market.Dto.UserMapper;
import com.market.Dto.UserRequestDTO;
import com.market.Dto.UserResponseDTO;
import com.market.Entities.User;
import com.market.Repository.UserRepository;
import com.market.Services.IUserServices;

@Service
public class ImplUserServices implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO save(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));
    }
}
